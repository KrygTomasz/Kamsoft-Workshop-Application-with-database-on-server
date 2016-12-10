package com.example.kryguu.application;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.Locale;

/**
 * Created by kryguu on 26.11.2016.
 */

public abstract class BaseDao<T extends IDatabaseObject> {
    public abstract T getObjectFromCursor(Cursor cursor);

    public abstract ContentValues getObjectContentValues(T object);

    private String mTableName;

    public BaseDao(String tableName) {
        mTableName = tableName;
    }

    public long getLong(Cursor cursor, String column) {
        int columnIndex = cursor.getColumnIndex(column);
        long rowColumnValue = cursor.getLong(columnIndex);

        return rowColumnValue;
    }

    public String getString(Cursor cursor, String column) {
        int columnIndex = cursor.getColumnIndex(column);
        String rowColumnValue = cursor.getString(columnIndex);

        return rowColumnValue;
    }

    public long insertObject(T object) {
        ContentValues values = getObjectContentValues(object);

        SQLiteDatabase database = FirstAppApplication.getsInstance().getmDbHelper().getWritableDatabase();
        long objectId = database.insert(mTableName, null, values);
        object.setId(objectId);

        return objectId;
    }

    public boolean updateObject(T object) {
        ContentValues values = getObjectContentValues(object);

        SQLiteDatabase database = FirstAppApplication.getsInstance().getmDbHelper().getWritableDatabase();
        String whereClause = String.format(Locale.getDefault(), "id = %d", object.getId());

        return database.update(mTableName, values, whereClause, null) > 0;
    }

    public boolean deleteObject(T object) {
        SQLiteDatabase database = FirstAppApplication.getsInstance().getmDbHelper().getWritableDatabase();
        String whereClause = String.format(Locale.getDefault(), "id = %d", object.getId());

        return database.delete(mTableName, whereClause, null) > 0;
    }
}
