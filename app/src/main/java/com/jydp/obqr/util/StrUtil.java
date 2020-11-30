package com.jydp.obqr.util;

import android.util.Log;

import com.google.gson.Gson;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.menus;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/*
2020/10/17 8:47 星期六
作用 ：
*/
public class StrUtil {

    public static RequestBody getCreatOrderBody(String seatId, ArrayList<menus> menuList){
        String jsonStr = new Gson().toJson(menuList);
        String str = "{\n" +
                "    \"seat_id\":" + seatId  + "," +
                "    \"menus\":" + jsonStr +
                "}";
        Log.d("-----------OrderBody",str);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str );
        return requestBody;
    }

    public static List<String> getDateList(String str){
        List<String> dataList = new ArrayList<>();
        dataList.add(str);
        for(int i = 15 ;i > 0; i -- ){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = df.format(new Date().getTime() + i*24*60*60*1000);
            dataList.add(startTime);
//            String endTime = df.format(new Date());
            Log.d("---------","日期：" + startTime);
        }
        for(int i = 0 ;i <= 15; i ++){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = df.format(new Date().getTime() - i*24*60*60*1000);
            dataList.add(startTime);
//            String endTime = df.format(new Date());
            Log.d("---------","日期：" + startTime);
        }
        return  dataList;
    }

    public static String dateFormat1(String str){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
        try {
            Date date = formatter.parse(str);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateFormat2(String str){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
        try {
            Date date = formatter.parse(str);
            DateFormat df = new SimpleDateFormat("HH:mm");
            return df.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateFormat3(String str){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
        try {
            Date date = formatter.parse(str);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return df.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String fmtMicrometer(String text)
    {
        DecimalFormat df = null;
        if(text.indexOf(".") > 0)
        {
            if(text.length() - text.indexOf(".")-1 == 0)
            {
                df = new DecimalFormat("###,##0.");
            }else if(text.length() - text.indexOf(".")-1 == 1)
            {
                df = new DecimalFormat("###,##0.0");
            }else
            {
                df = new DecimalFormat("###,##0.00");
            }
        }else
        {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    public static boolean isNum(String str) {

        try {
            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isInt(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
