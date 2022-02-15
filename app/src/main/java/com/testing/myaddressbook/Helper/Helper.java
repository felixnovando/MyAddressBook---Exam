package com.testing.myaddressbook.Helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Helper {

    private static SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");

    public static String convertDate(Date date){
        return format.format(date);
    }

}
