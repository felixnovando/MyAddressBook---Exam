package com.testing.myaddressbook.Models;

import com.google.gson.annotations.SerializedName;

public class Coordinates {
    @SerializedName("latitude")
    public Double latitude;

    @SerializedName("longitude")
    public Double longitude;

    public Coordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
