package com.bitchinc.romantics;

public class SpotJSON {
	
private String id;

private double latitude;

private double longitude;

private String description;

public SpotJSON() {}

public SpotJSON(String id, String description, double latitude, double longitude) {
	this.id = id;
	this.description = description;
	this.latitude = latitude;
	this.longitude = longitude;
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


}
