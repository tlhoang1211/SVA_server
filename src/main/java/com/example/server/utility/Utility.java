package com.example.server.utility;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    public static String convertLongToDate (long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }
}
