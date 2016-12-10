package com.example.kryguu.application;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kryguu on 26.11.2016.
 */

public class ClientDao extends BaseDao<Client> {

    private static final String TABLE_NAME = "clients";
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_SURNAME = 2;
    private static final int COLUMN_PHONE = 3;
    private static final int COLUMN_EMAIL = 4;

    public ClientDao() {
        super(TABLE_NAME);
    }

    @Override
    public Client getObjectFromCursor(Cursor cursor) {
        Client client = new Client();
        client.setId(cursor.getLong(COLUMN_ID));
        client.setmName(cursor.getString(COLUMN_NAME));
        client.setmSurname(cursor.getString(COLUMN_SURNAME));
        client.setmPhone(cursor.getString(COLUMN_PHONE));
        client.setmEmail(cursor.getString(COLUMN_EMAIL));
        return client;
    }

    @Override
    public ContentValues getObjectContentValues(Client object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", object.getmName());
        contentValues.put("surname", object.getmSurname());
        contentValues.put("phone", object.getmPhone());
        contentValues.put("email", object.getmEmail());
        return contentValues;
    }

    public List<Client> getClientsList() {
        String sql = FirstAppApplication.getsInstance().getmDbHelper().getSql(R.string.sql_dao_client_get_clients);
        Cursor cursor = openRawQueryCursor(sql);
        try {
            List<Client> clientList = new ArrayList<>();

            while (cursor.moveToNext()) {
                Client client = getObjectFromCursor(cursor);
                clientList.add(client);
            }

            return clientList;
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            closeCursor(cursor);
        }
    }

    private Cursor openRawQueryCursor(String sql) {
        SQLiteDatabase database = FirstAppApplication.getsInstance().getmDbHelper().getReadableDatabase();
        Cursor cursor = database.rawQuery(sql,null);
        return cursor;
    }

    private void closeCursor(Cursor cursor) {
        if (cursor!=null){
            cursor.close();
        }
    }
}
