package com.paraxco.basictools.MapTools.Model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 *
 */

public class LocationInfo implements ClusterItem {
    LatLng latLng;
    String title;

    public LocationInfo(LatLng latLng, String title) {
        this.latLng = latLng;
        this.title = title;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public Location getLocatino() {
        return getLocation(getLatLng());
    }

    @Override
    public LatLng getPosition() {
        return getLatLng();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LocationInfo) {
            LocationInfo locationInfo = (LocationInfo) obj;
            return locationInfo.latLng.equals(latLng);

        }
        if (obj instanceof LatLng)
            return obj.equals(latLng);

        return false;
    }
    public static Location getLocation(LatLng latLng) {
        Location location = new Location("");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);
        return location;
    }
}
