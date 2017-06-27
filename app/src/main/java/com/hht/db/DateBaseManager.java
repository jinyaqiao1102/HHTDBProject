package com.hht.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.*;

/**
 * Created by JINYAQIAO on 2015/6/29.
 */
public class DateBaseManager {


    public static final String DB_NAME = "hht_tv.db";
    public static final String PACKAGE_NAME = "com.hht.db";
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + File.separator + PACKAGE_NAME;
    private final static int DB_VERSION = 1;
    public static DateBaseManager instance;

    public static DateBaseManager getInstance() {
        if (instance == null)
            instance = new DateBaseManager();
        return instance;
    }

    private SQLiteDatabase openDatabase() {
        String dbfile = DB_PATH + File.separator + DB_NAME;
        File file = new File(dbfile);
        if (!file.exists()) {
            return null;
        }
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
        return database;
    }

    public void copyDatabase(InputStream newDb) {

        InputStream is = null;
        try {
            is = newDb;
            FileOutputStream fos = new FileOutputStream(DB_PATH + File.separator + DB_NAME);
            byte[] buffer = new byte[2048];
            int count = 0;

            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateDatabase(Context context) {
        SQLiteDatabase database = openDatabase();
        if (database == null) {
            try {
                copyDatabase(context.getAssets().open(DateBaseManager.DB_NAME));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Cursor cursor = database.rawQuery("select * from db_version", null);
            int version = 1;
            while (cursor.moveToNext()) {
                int versionIndex = cursor.getColumnIndex("version");
                version = cursor.getInt(versionIndex);
                break;
            }
            if (DB_VERSION > version) {
                try {
                    copyDatabase(context.getAssets().open(DateBaseManager.DB_NAME));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public SQLiteDatabase rawQuery() {
        SQLiteDatabase database = openDatabase();
        return database;
    }

    public long insert(String table, String nullColumnHack, ContentValues values) {
        SQLiteDatabase database = null;
        long rows = 0;
        try {
            database = openDatabase();
            rows = database.insert(table, nullColumnHack, values);

        } finally {
            database.close();
        }
        return rows;
    }

    public int delete(String table, String whereClause, String[] whereArgs) {
        SQLiteDatabase database = null;
        int rows = 0;
        try {
            database = openDatabase();
            rows = database.delete(table, whereClause, whereArgs);
        } finally {
            database.close();
        }
        return rows;
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase database = null;
        int rows = 0;
        try {
            database = openDatabase();
            rows = database.update(table, values, whereClause, whereArgs);
        } finally {
            database.close();
        }
        return rows;
    }

}
