package com.izza.pmobile.latihan;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register.db";
    public static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL ("create table userregister(nim integer primary key, nama text, surel text, sandi text, konfirmasisandi text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("drop table if exists userregister");
    }

    public boolean tambahpengguna(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("surel", username);
        cv.put("sandi", password);

        long result = db.insert("userregister", null, cv);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean periksausername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userregister where surel=?", new String[] {username});
        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean periksausernamepassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userregister where surel=? and sandi=?", new String[] {username, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
}