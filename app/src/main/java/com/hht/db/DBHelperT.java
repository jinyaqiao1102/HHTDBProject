package com.hht.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hht.dbmanager.HSQLiteDatabaseHelper;

import java.io.File;

import static com.hht.db.DateBaseManager.DB_NAME;
import static com.hht.db.DateBaseManager.DB_PATH;

/**
 * Created by JinYQ on 2017/6/28.
 */

public class DBHelperT extends HSQLiteDatabaseHelper{
    private final static String dbPaht=DB_PATH + File.separator + DB_NAME;
    public DBHelperT(Context context, String path, int mNewVersion) {
        super(context, path, mNewVersion);
    }
    public DBHelperT(Context context) {
        super(context,dbPaht,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("=====jinyaqiao===","onUpgrade========");
    }
}
