package com.mpiyush3510.authlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DBName="Testing.db";

    public DbHelper(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table test(email text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists test");
    }

    public boolean insertData(String email,String password)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = database.insert("test",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkEmail(String email)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        Cursor cursor=database.rawQuery("select * from test where email = ?",new String[]{email});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkEmailPassword(String email,String password)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        Cursor cursor=database.rawQuery("select * from test where email=? and password =? ",new String[]{email,password});
        if(cursor.getCount()>0)
        {
            return true;
        }else{
            return false;
        }
    }
}
