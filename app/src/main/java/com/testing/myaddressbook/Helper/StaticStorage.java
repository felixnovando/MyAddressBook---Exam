package com.testing.myaddressbook.Helper;

import com.testing.myaddressbook.Models.Employee;

import java.util.ArrayList;

public class StaticStorage {

    public static ArrayList<Employee> employees;

    public static Employee getEmployeeWithID(int id){
        for(int a=0; a < employees.size(); a++){
            if(employees.get(a).getId() == id) return employees.get(a);
        }
        return null;
    }

}
