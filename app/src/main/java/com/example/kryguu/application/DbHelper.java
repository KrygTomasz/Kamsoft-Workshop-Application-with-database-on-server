package com.example.kryguu.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kryguu on 26.11.2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String NAME = "clients.db";
    private static final int VERSION = 1;
    private Context mContext;

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper() {
        super(null, NAME, null, VERSION);
    }

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
        mContext = context;
    }

    public void setmContext(Context contex) {
        mContext = contex;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(getSql(R.string.table_test));
    }

    public String getSql(int table_test) {
        return mContext.getString(table_test);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL();
    }
}
