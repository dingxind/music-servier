package com.xindong.utils;
 
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ToolUtil {
    private static SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.ENGLISH) ;
 
    //Date转String
    public static  String dateToString(Date date) {
        String dateString = sfEnd.format(date);
        return dateString;
    }
 
 
    //String转Date
    public static Date StringToDate(String dateString) {
        Date date = null;
        try {
            date = sfEnd.parse(dateString);
        } catch (ParseException e) {
            //sdf的格式要与dateString的格式相同，否者会报错
            e.printStackTrace();
        }
        return date;
    }
 
    //字符串时间转字符串时间
    public static String stringToString(String string){
        String format=null;
        try {
             format = sfEnd.format(sfStart.parse(string));
        } catch (ParseException e) {
            //sdf的格式要与dateString的格式相同，否者会报错
            e.printStackTrace();
        }
      return format;
    }
   
}