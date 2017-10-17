package com.paraxco.basictools.MapTools;

/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.paraxco.basictools.MapTools.Model.LocationInfo;
import com.paraxco.basictools.Utils.Utils;


/**
 * Draws profile photos inside markers (using IconGenerator).
 * When there are multiple people in the cluster, draw multiple photos (using MultiDrawable).
 */
public class LocationInfoRender extends DefaultClusterRenderer<LocationInfo> {
    private final IconGenerator mIconGenerator;
    private final IconGenerator mClusterIconGenerator;
    private final ImageView mImageView;
    private final ImageView mClusterImageView;
    private final Context context;
    private final GoogleMap map;
    int mapPointCircle;
    int mapPointClusterItem;

    public LocationInfoRender(Context context, ClusterManager clusterManager, GoogleMap map,int mapPointCircle,int mapPointClusterItem) {
        super(context, map, clusterManager);
        this.context = context;
        this.map = map;
        this.mapPointCircle=mapPointCircle;
        this.mapPointClusterItem=-mapPointClusterItem;
        mIconGenerator = new IconGenerator(getApplicationContext());
        mClusterIconGenerator = new IconGenerator(getApplicationContext());
//        View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
//        mClusterIconGenerator.setContentView(multiProfile);
//        mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);
//
//        mImageView = new ImageView(getApplicationContext());
//        mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
//        mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
//        int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
//        mImageView.setPadding(padding, padding, padding, padding);
//        mIconGenerator.setContentView(mImageView);
        int mDimension;
        mClusterImageView = new ImageView(getApplicationContext());
        mDimension = 70;
        mClusterImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
        int padding = 0;
        mClusterImageView.setPadding(padding, padding, padding, padding);
        mClusterIconGenerator.setContentView(mClusterImageView);


//        View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
//        mClusterIconGenerator.setContentView(multiProfile);
//        mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);

        mImageView = new ImageView(getApplicationContext());
        mDimension = 50;
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
        padding = 0;
        mImageView.setPadding(padding, padding, padding, padding);
        mIconGenerator.setContentView(mImageView);
    }


    @Override
    protected void onBeforeClusterItemRendered(LocationInfo locationInfo, MarkerOptions markerOptions) {
//        // Draw a single person.
//        // Set the info window to show their name.
//        mImageView.setImageResource(person.profilePhoto);
//        Bitmap icon = mIconGenerator.makeIcon();
//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(person.name);
        // Draw a single person.
        // Set the info window to show their name.
        mImageView.setImageResource(mapPointCircle);
//        Bitmap icon = mIconGenerator.makeIcon();
        markerOptions.icon(getPointIcon()).title(locationInfo.getTitle());
        markerOptions.anchor(0.5f, 0.5f);
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<LocationInfo> cluster, MarkerOptions markerOptions) {
//        // Draw multiple people.
//        // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
//        List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
//        int width = mDimension;
//        int height = mDimension;
//
//        for (LocationInfo p : cluster.getItems()) {
//            // Draw 4 at most.
//            if (profilePhotos.size() == 4) break;
//            Drawable drawable = getResources().getDrawable(p.profilePhoto);
//            drawable.setBounds(0, 0, width, height);
//            profilePhotos.add(drawable);
//        }
//        MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
//        multiDrawable.setBounds(0, 0, width, height);
//
//        mClusterImageView.setImageDrawable(multiDrawable);
//        Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        // Draw multiple people.
        // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).


//        mClusterImageView.setImageResource(R.drawable.map_car_icon);
//        Bitmap icon = mClusterIconGenerator.makeIcon();
        LocationInfo locationInfo = new LocationInfo(null, "");
        for (LocationInfo p : cluster.getItems()) {
            locationInfo = p;
        }

        markerOptions.icon(getClusterIcon()).title(locationInfo.getTitle());
        markerOptions.anchor(0.5f, 0.5f);

    }

    private BitmapDescriptor getClusterIcon() {
        return BitmapDescriptorFactory.fromBitmap(getBitmap(mapPointClusterItem));
    }
    private BitmapDescriptor getPointIcon() {
        return BitmapDescriptorFactory.fromBitmap(getBitmap(mapPointCircle));
    }

    private Bitmap getBitmap(int drawableRes) {
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(getSize(), getSize(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, getSize(), getSize());
        drawable.draw(canvas);

        return bitmap;
    }

    private int getSize() {
        return (int) Utils.pxFromDP(20, getApplicationContext());
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        // Always render clusters.
        return cluster.getSize() > 1;
    }

    private GoogleMap getMap() {
        return map;
    }

    private Context getApplicationContext() {
        return context;
    }

}


