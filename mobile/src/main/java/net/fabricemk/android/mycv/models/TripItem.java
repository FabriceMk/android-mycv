package net.fabricemk.android.mycv.models;

import java.io.Serializable;

/**
 * A POJO which represents a trip, an exhibition or an event
 */
public class TripItem implements Serializable {

    /**
     * The event, trip name
     */
    private String eventName;

    /**
     * A string representation of the dates especially if recurring exhibitions.
     * But doesn't follow any specific fornat
     */
    private String dates;

    /**
     * The GPS coordinates of the trip
     */
    private Location location;

    /**
     * The description of the trip, exhibition
     */
    private String description;

    /**
     * A list of URLs of pictures to be loaded remotely to illustrate the trip
     */
    private String[] pictures;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }
}
