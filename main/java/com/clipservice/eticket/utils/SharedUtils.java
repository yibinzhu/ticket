package com.clipservice.eticket.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * Created by clip-771 on 2018-02-27.
 * 변수에 다라 welcompage 나온지 안나온지 걸정
 */

public class SharedUtils {
    private static final String FILE_NAME = "cloudTicket";
    private static final String MODE_NAME_WELCOME = "welcome";
    private static final String MODE_NAME_STORAGE = "storage";
    private static final String MODE_NAME_REGISTER = "register";
    private static final String MODE_NAME_PHONINFO = "phoneInfo";
    private static final String MODE_NAME_PUBLISH = "ticketPublish";

    public static boolean getPhoneInfo(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(MODE_NAME_PHONINFO ,false);
    }

    public static void putPhoneInfoBoolean(Context context,boolean isFirst){
        @SuppressLint("WrongConstant") Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND).edit();
        editor.putBoolean(MODE_NAME_PHONINFO ,isFirst);
        editor.commit();
    }

    public static boolean getWelcomeBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(MODE_NAME_WELCOME ,false);
    }

    public static void putWelcomeBoolean(Context context,boolean isFirst){
        @SuppressLint("WrongConstant") Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND).edit();
        editor.putBoolean(MODE_NAME_WELCOME ,isFirst);
        editor.commit();
    }

    public static boolean getRegisterBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(MODE_NAME_REGISTER,false);
    }

    public static void putRegisterBoolean(Context context,boolean isLogin){
        @SuppressLint("WrongConstant") Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND).edit();
        editor.putBoolean(MODE_NAME_REGISTER,isLogin);
        editor.commit();
    }

    public static boolean getStorageBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(MODE_NAME_STORAGE,false);
    }

    public static void putStorageBoolean(Context context,boolean isStorage){
        @SuppressLint("WrongConstant") Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND).edit();
        editor.putBoolean(MODE_NAME_STORAGE,isStorage);
        editor.commit();
    }

    public static boolean getPublishBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(MODE_NAME_PUBLISH,false);
    }

    public static void putPublishBoolean(Context context,boolean isPublish){
        @SuppressLint("WrongConstant") Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND).edit();
        editor.putBoolean(MODE_NAME_PUBLISH,isPublish);
        editor.commit();
    }
}
