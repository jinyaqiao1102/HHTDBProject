package com.hht.dbmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by JinYQ on 2017/6/21.
 */

public class HDBManager extends HSQLiteDatabaseHelper {

    public HDBManager(Context context, String path) {
        super(context, path);
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
