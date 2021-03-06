package net.fabricemk.android.mycv.models;

import java.io.Serializable;

/**
 * Represents a GPS coordinate
 */
public class Location implements Serializable {

    private float latitude;

    private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
