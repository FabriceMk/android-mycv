package net.fabricemk.android.mycv.models;

import java.io.Serializable;

public class Location implements Serializable {

    float latitude;

    float longitude;

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
