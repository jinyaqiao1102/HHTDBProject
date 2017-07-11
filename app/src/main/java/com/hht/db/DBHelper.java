package com.hht.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.hht.dbmanager.HSQLiteDatabaseHelper;
import java.io.File;
import static com.hht.db.DateBaseManager.DB_PATH;

/**
 * Created by JinYQ on 2017/6/28.
 */

public class DBHelper extends HSQLiteDatabaseHelper{
    private final static String dbPaht=DB_PATH + File.separator +"hht_tv.db";
    public DBHelper(Context context, String path, int mNewVersion) {
        super(context, path, mNewVersion);
    }
    public DBHelper(Context context) {
        super(context,dbPaht,4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
