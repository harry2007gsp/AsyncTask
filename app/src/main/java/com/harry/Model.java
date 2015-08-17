package com.harry;

/**
 * Created by Harry on 8/15/15.
 */
public class Model {
    String country;
    String place;
    double longitude;
    double lattitude;

    public Model(String country, String place, double longitude, double lattitude) {
        this.country = country;
        this.place = place;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(int lattitude) {
        this.lattitude = lattitude;
    }
}
