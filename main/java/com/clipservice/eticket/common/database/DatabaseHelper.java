package com.clipservice.eticket.common.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.clipservice.eticket.common.Const;

/**
 * Created by clip-771 on 2018-02-06.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context){
        super(context, Const.DATABASE_NAME,null,Const.DATABASE_VERSION);
        Log.i(TAG ,"DatabaseHelper");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG,"----onCreate----");
        /*
        * 주문키,그룹키,좌석정보,수량
        * table => 결제된 티켓, 좌석정보
        * 1.내티켓 => 공연키,판매공연키,회차키,	정가설정그룹키,*주문수량정보,주문 세션아이디,판매처키,예매자 아이디,폰
        * 2.주문수량정보 = > 주문키,공연키,좌석종류,주문수량
         * */
        Log.i(TAG,"database is created");
        String sql_prepaid = "CREATE TABLE "+DbConfig.TABLE_NAME_PREPAID+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"play_title TEXT,"
                +"img TEXT,"
                +"enc_orderNum TEXT,"
                +"userOrderNum TEXT,"
                +"enc_playNum TEXT,"
                +"enc_playSaleNum TEXT,"
                +"enc_sequence TEXT,"
                +"enc_priceGrp TEXT,"
                +"seatData TEXT,"
                +"ticket_counter INTEGER,"
                +"sId TEXT,"
                +"enc_storeNum TEXT,"
                +"id TEXT,phone TEXT);";
        String sql_myticket = "CREATE TABLE "+DbConfig.TABLE_NAME_MYTICKET+" (_id INTEGER PRIMARY KEY AUTOINCREMENT,enc_ticketNum TEXT,enc_validKey TEXT,enc_orderNum TEXT,enc_printGrp TEXT,enc_playNum TEXT,enc_playSaleNum TEXT,playTitle TEXT,enc_sequence TEXT,sequenceText TEXT,seatType TEXT,enc_seatNum TEXT,discountNum INTEGER,discountName TEXT,publishDate TEXT,status INTEGER,statusName TEXT,ticketImg TEXT,sharedTo TEXT);";
        String sql_seatData = "CREATE TABLE "+DbConfig.TABLE_NAME_SEATDATA+" (_id INTEGER PRIMARY KEY AUTOINCREMENT,enc_orderNum TEXT,enc_playNum TEXT,seat TEXT,counter INTEGER);";
        Log.i("DatabseHelper","sql_prepaid  =>"+sql_prepaid);
        Log.i("DatabseHelper","sql_seatData =>"+sql_seatData);
        Log.i("DatabseHelper","sql_myticket =>"+sql_myticket);
        sqLiteDatabase.execSQL(sql_prepaid);
        sqLiteDatabase.execSQL(sql_myticket);
        sqLiteDatabase.execSQL(sql_seatData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    Log.i(TAG,"----onUpgrade----");
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DbConfig.TABLE_NAME_PREPAID);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DbConfig.TABLE_NAME_MYTICKET);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DbConfig.TABLE_NAME_SEATDATA);
    onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.i(TAG,"----DB open----");
        super.onOpen(db);
    }
    /**获取数据库路径**/
    public String getDBPath(Context context){
        return context.getDatabasePath(DbConfig.DATABASE_NAME).getPath();
    }

    public Cursor getPrepaidData(SQLiteDatabase db){
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+DbConfig.TABLE_NAME_PREPAID,null);
        return res;
    }
}
