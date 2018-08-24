package com.clipservice.eticket.common.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by clip-771 on 2018-02-27.
 */

public class DbManger {
    private static DatabaseHelper helper;
    public static DatabaseHelper getInstance(Context context){
        if(helper==null){
            helper = new DatabaseHelper(context);
        }
        return helper;
    }
    public static  void execSQL(SQLiteDatabase db,String sql){
        if(sql!=null&&!"".equals(sql)){
            db.execSQL(sql);
        }
    }

    public static Cursor selectDataBySql(SQLiteDatabase db,String sql,String[] selectionArgs){
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(sql,selectionArgs);
        }
        return cursor;
    }

//    public static List<UserTicketListModel> cursorToList(Cursor cursor){
//        List<UserTicketListModel> list = new ArrayList<>();
//        while (cursor.moveToNext()){
//            String
//            int _id = cursor.getInt();
//        }
//        return list;
//    }
}
