package com.hht.db;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.hht.dbmanager.HDBManager;
import com.hht.dbmanager.ISQLStatement;
import com.hht.dbmanager.SQLStatementImp;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.hht.db.DateBaseManager.DB_NAME;
import static com.hht.db.DateBaseManager.DB_PATH;

public class MainActivity extends AppCompatActivity {
    private HDBManager manager=null;
    private HDBManager manager2=null;
    private ISQLStatement statement1= null;
    private ISQLStatement statement2= null;
    private BackAppInfo info=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            DateBaseManager.getInstance().copyDatabase(getAssets().open(DateBaseManager.DB_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        info=new BackAppInfo();
        info.setmPkgName("1234567");
        info.setmAppName("jinyaqiao");
        info.setmDate("dddddddd");
        info.setmIcon("888888888888");
        info.setmNewVersionCode(5);
        info.setmOldVersionCode(4);
        info.setmNewVersionName("iiiiiiii");
        info.setmOldVersionName("99989989");
        manager=new HDBManager(this,DB_PATH + File.separator + DB_NAME);
        manager2=new HDBManager(this,DB_PATH + File.separator + DB_NAME);
        statement1= SQLStatementImp.getInstance(manager.getWritableDatabase());
        statement2= SQLStatementImp.getInstance(manager2.getWritableDatabase());

        new Thread(new MyRunable2()).start();
       // for(int i=0;i<101;i++){
            new Thread(new MyRunable()).start();
        //}
    }
    class MyRunable implements Runnable{

        @Override
        public void run() {
            int count=0;
            while (count<5000){
               // List<Map<String, Object>> list=manager.query("select * from db_version",null);
                count++;
                Log.i("===jinyaqiao=====","====MyRunable1========"+"count====="+count);
                List<Map<String, Object>> list=statement1.query("select * from back_app_info where mPkgName=?", new String[]{info.getmPkgName()});
                Log.i("===jinyaqiao=====","insertOrUpdateBackAppData====list.size()======"+list.size());
                if (list.size() > 0) {
                    long ret=SQLStatementImp.getInstance(manager.getWritableDatabase()).update("back_app_info",getContentValues(info),"mPkgName=?",new String[]{info.getmPkgName()});
                    Log.i("===jinyaqiao=====","insertOrUpdateBackAppData========update  result======"+ret);
                } else {
                    long ret =SQLStatementImp.getInstance(manager.getWritableDatabase()).insert("back_app_info",getContentValues(info));
                    Log.i("===jinyaqiao=====","insertOrUpdateBackAppData========insert  result======"+ret);
                }
            }
        }
    }
    class MyRunable2 implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int count=0;
            while (count<5000){
               // List<Map<String, Object>> list=manager2.query("select * from db_version",null);
                count++;
                Log.i("===jinyaqiao=====","====MyRunable2========"+"count====="+count);
                delete(info);
            }
        }
    }
    public void insertOrUpdateBackAppData(BackAppInfo info) {

        List<Map<String, Object>> list=statement1.query("select * from back_app_info where mPkgName=?", new String[]{info.getmPkgName()});
        Log.e("===jinyaqiao=====","insertOrUpdateBackAppData====list.size()======"+list.size());
            if (list.size() > 0) {
                long ret=statement1.update("back_app_info",getContentValues(info),"mPkgName=?",new String[]{info.getmPkgName()});
                Log.e("===jinyaqiao=====","insertOrUpdateBackAppData========update  result======"+ret);
            } else {
                long ret =statement1.insert("back_app_info",getContentValues(info));
                Log.e("===jinyaqiao=====","insertOrUpdateBackAppData========insert  result======"+ret);
            }

    }
    private void delete(BackAppInfo info) {
        List<Map<String, Object>> list = statement1.query("select * from back_app_info where mPkgName=?", new String[]{info.getmPkgName()});
        Log.i("===jinyaqiao=====", "delete==========list.size()"+list.size());
        if (list.size() > 0) {
            long ret=SQLStatementImp.getInstance(manager.getWritableDatabase()).delete("back_app_info", "mPkgName=?", new String[]{info.getmPkgName()});
            Log.i("===jinyaqiao=====","===delete========insert  result======"+ret);
        }
    }
    public void insertOrUpdateBackAppData2(BackAppInfo info) {

        List<Map<String, Object>> list=statement2.query("select * from back_app_info where mPkgName=?", new String[]{info.getmPkgName()});
        Log.e("===jinyaqiao=====","insertOrUpdateBackAppData2======list.size()======"+list.size());
        if (list.size() > 0) {
            long ret=statement2.update("back_app_info",getContentValues(info),"mPkgName=?",new String[]{info.getmPkgName()});
            Log.e("===jinyaqiao=====","insertOrUpdateBackAppData2=========update  result======"+ret);
        } else {
            long ret =statement2.insert("back_app_info",getContentValues(info));
            Log.e("===jinyaqiao=====","insertOrUpdateBackAppData2========insert  result======"+ret);
        }

    }
    private ContentValues getContentValues(BackAppInfo info) {
        ContentValues values = new ContentValues();
        values.put("mPkgName", info.getmPkgName());
        if (!TextUtils.isEmpty(info.getmAppName())) {
            values.put("mAppName", info.getmAppName());
        }
        if (info.getmOldVersionCode() > 0) {
            values.put("mOldVersionCode", info.getmOldVersionCode());
        }
        if (!TextUtils.isEmpty(info.getmOldVersionName())) {
            values.put("mOldVersionName", info.getmOldVersionName());
        }
        if (info.getmNewVersionCode() > 0) {
            values.put("mNewVersionCode", info.getmNewVersionCode());
        }
        if (!TextUtils.isEmpty(info.getmNewVersionName())) {
            values.put("mNewVersionName", info.getmNewVersionName());
        }
        if (!TextUtils.isEmpty(info.getmApkPath())) {
            values.put("mApkPath", info.getmApkPath());
        }
        if (!TextUtils.isEmpty(info.getmDate())) {
            values.put("mDate", info.getmDate());
        }
        if (!TextUtils.isEmpty(info.getmIcon())) {
            values.put("mIcon", info.getmIcon());
        }
        return values;
    }

}