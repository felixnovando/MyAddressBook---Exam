package com.testing.myaddressbook.Models;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {

    @SerializedName("employeeId")
    Integer id;
    @SerializedName("email")
    String email;
    @SerializedName("phone")
    String phone;
    @SerializedName("cell")
    String cell;
    @SerializedName("name")
    Name name;
    @SerializedName("location")
    Location location;
    @SerializedName("registered")
    Registered registered;
    @SerializedName("picture")
    Picture picture;

    public String getDisplayName(){
        return this.name.firstName + " " + this.name.lastName;
    }

    public String getDisplayLocation(){
        return this.location.city + ", " + this.location.country;
    }

    public Date getRegisterDate(){
       return this.registered.registeredDate;
    }

    public Double getLatitude(){
        return this.location.coordinates.latitude;
    }

    public Double getLongitude(){
        return this.location.coordinates.longitude;
    }

    public Employee(Integer id, String email, String phone, String cell, Name name, Location location, Registered registered, Picture picture) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.cell = cell;
        this.name = name;
        this.location = location;
        this.registered = registered;
        this.picture = picture;
    }

    //setter n getter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
