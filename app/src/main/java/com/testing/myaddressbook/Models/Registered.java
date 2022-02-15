package com.testing.myaddressbook.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Registered {
    @SerializedName("date")
    public Date registeredDate;

    public Registered(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
