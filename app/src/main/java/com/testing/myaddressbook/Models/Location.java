package com.testing.myaddressbook.Models;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("city")
    public String city;

    @SerializedName("country")
    public String country;

    @SerializedName("coordinates")
    public Coordinates coordinates;

    public Location(String city, String country, Coordinates coordinates) {
        this.city = city;
        this.country = country;
        this.coordinates = coordinates;
    }
}
