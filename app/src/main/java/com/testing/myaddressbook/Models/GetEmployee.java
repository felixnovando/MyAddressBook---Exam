package com.testing.myaddressbook.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetEmployee {

    @SerializedName("statusCode")
    Integer statusCode;

    @SerializedName("employees")
    List<Employee> employeeList;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
