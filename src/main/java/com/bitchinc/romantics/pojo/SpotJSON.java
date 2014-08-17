package com.bitchinc.romantics.pojo;

public class SpotJSON {

    private String id;

    private double latitude;

    private double longitude;

    private String description;

    private double rating;

    private boolean secluded;

    private Object image;


    public SpotJSON() {
    }

    public SpotJSON(String id, String description, double latitude, double longitude) {
        this.id = id;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public SpotJSON(String id, double latitude, double longitude, String description, double rating, boolean secluded, Object image) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.rating = rating;
        this.secluded = secluded;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isSecluded() {
        return secluded;
    }

    public void setSecluded(boolean secluded) {
        this.secluded = secluded;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
