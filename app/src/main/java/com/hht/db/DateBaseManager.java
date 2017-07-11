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


    public static final String DB_NAME = "hht_tv2.db";
    public static final String PACKAGE_NAME = "com.hht.db";
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + File.separator + PACKAGE_NAME;
    private final static int DB_VERSION = 1;
    public static DateBaseManager instance;

    public static DateBaseManager getInstance() {
        if (instance == null)
            instance = new DateBaseManager();
        return instance;
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



}
