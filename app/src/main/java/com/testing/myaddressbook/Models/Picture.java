package com.testing.myaddressbook.Models;

import com.google.gson.annotations.SerializedName;

public class Picture {
    @SerializedName("thumbnail")
    public String thumbnailUrl;

    @SerializedName("medium")
    public String mediumUrl;

    @SerializedName("large")
    public String largeUrl;

    public Picture(String thumbnailUrl, String mediumUrl, String largeUrl) {
        this.thumbnailUrl = thumbnailUrl;
        this.mediumUrl = mediumUrl;
        this.largeUrl = largeUrl;
    }
}
