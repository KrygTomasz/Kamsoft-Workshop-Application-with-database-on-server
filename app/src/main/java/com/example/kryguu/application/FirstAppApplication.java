package com.example.kryguu.application;

import android.app.Application;

/**
 * Created by kryguu on 26.11.2016.
 */

public class FirstAppApplication extends Application {
    private static FirstAppApplication sInstance;
    private DbHelper mDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static FirstAppApplication getsInstance(){
        return sInstance;
    }

    public DbHelper getmDbHelper() {
        if( mDbHelper == null) {
            mDbHelper = new DbHelper(this);
        }
        return mDbHelper;
    }
}
