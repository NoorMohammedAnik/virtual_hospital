package com.example.rr.virtual_hospital.utils;

import android.util.Log;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Utilities {

    public Utilities() {
    }

    public String getDateTime(long millis, String DateFormat, Locale current){
        SimpleDateFormat formatter = new SimpleDateFormat(DateFormat,current);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return formatter.format(calendar.getTime());
    }

    public String getAge(String date){
        int day=Integer.valueOf(date.substring(0,2)) ;
        int month=Integer.valueOf(date.substring(3,5));
        int year=Integer.valueOf(date.substring(6,10));
        //Log.d("utilsLog",String.valueOf(day) +String.valueOf(month)+String.valueOf(year));
        LocalDate birtday= new LocalDate(year,month,day);
        LocalDate today=new LocalDate();
        Years age= Years.yearsBetween(birtday,today);
        int s= age.getYears();
        return String.valueOf(s);
    }
}
