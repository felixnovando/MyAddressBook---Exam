package com.testing.myaddressbook.Models;

import com.google.gson.annotations.SerializedName;

public class Name {
    @SerializedName("first")
    public String firstName;

    @SerializedName("last")
    public String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
