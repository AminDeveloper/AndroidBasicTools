package com.paraxco.maptools.MapTools;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.paraxco.commontools.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Amin on 5/29/2017.
 */

public class MapRouteUtils {

    private Context context;
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
    ConcurrentHashMap<DownloadTask, Long> requestTimes = new ConcurrentHashMap<>();
    private GoogleMap map;

    public void drawWaypoints(List<LatLng> points) {

        String url = getDirectionsUrl(points.get(0), points.get(points.size() - 1), points.subList(1, points.size() - 1));

        DownloadTask downloadTask = new DownloadTask(url);
        // Start downloading json data from Google Directions API
        startDownload(downloadTask);
    }

    private void startDownload(AsyncTask downloadTask) {

        downloadTask.executeOnExecutor(threadPoolExecutor);

    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<Object, Void, String> {
        String url;
        int distance;
        private PolylineOptions lineOptions;

        public DownloadTask(String url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        public AsyncTask getRouts(LatLng origin, LatLng dest) {


            // Getting URL to the Google Directions API
            String url = getDirectionsUrl(origin, dest);

            DownloadTask downloadTask = new DownloadTask(url);

            // Start downloading json data from Google Directions API
            return downloadTask.execute();
        }

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(Object... args) {
            requestTimes.put(this, System.currentTimeMillis());

            // For storing data from web service
            String data = "";
            boolean isOk;
            try {
                // Fetching the data from web service
                Log.d("web_call", String.valueOf(System.currentTimeMillis()));

                do {
                    data = downloadUrl(url);
                    isOk = isOkResult(data);
                    if (!isOk)
                        Thread.sleep(100);

                }
                while (!isOk);

                JSONObject jObject;
                List<List<HashMap<String, String>>> routes = null;

                try {
                    jObject = new JSONObject(data);
                    DirectionsJSONParser parser = new DirectionsJSONParser();
                    distance = jObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getInt("value");

                    // Starts parsing data
                    routes = parser.parse(jObject);
                    lineOptions = getPolyline(routes);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.d("web_result", data);

            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        private boolean isOkResult(String data) {
            boolean isOk = true;
            try {
                JSONObject jObject = new JSONObject(data);
                if (jObject.has("error_message"))
                    isOk = !jObject.getString("error_message").equals("You have exceeded your rate-limit for this API.");
            } catch (JSONException e) {
                e.printStackTrace();
                return true;//jus with error message return false
            }

            return isOk;

        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            requestTimes.remove(this);
            if (requestTimes.size() == 0)
                Toast.makeText(context, "OK!", Toast.LENGTH_LONG).show();

            if (lineOptions == null) {
                Toast.makeText(context, context.getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                return;
            }
//            Toast.makeText(activity, distance + " meter", Toast.LENGTH_LONG).show();

            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
//            List<LatLng> deawPoints = lineOptions.getPoints();
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(deawPoints.get(deawPoints.size() - 1), 15));

        }

        private PolylineOptions getPolyline(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(4);
                lineOptions.color(context.getResources().getColor(R.color.colorPrimaryDark));

            }
            return lineOptions;
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest, List<LatLng> waypoints) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
//        String sensor = "sensor=false";
        String sensor = "";

        // Building the parameters to the web service

        // Output format
        String output = "json";
        String str_waypoints = "";

        if (waypoints.size() > 0) {
            str_waypoints = "waypoints=optimize:false|";
            for (LatLng waypoint : waypoints) {
                str_waypoints += waypoint.latitude + "," + waypoint.longitude + "|";
            }
            str_waypoints = str_waypoints.substring(0, str_waypoints.length() - 1);
        }
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + str_waypoints;

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        Log.d("WEB_SERVICE", "map url : " + strUrl);
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception downloading", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}
