package com.hht.db;

import java.io.Serializable;

/**
 * Created by HHT-JIN on 2016/12/3.
 */
public class BackAppInfo implements Serializable {
    /*[mPkgName] VARCHAR(50) NOT NULL,
      [mAppName] VARCHAR,
      [mDate] VARCHAR(40),
      [mOldVersionCode] VARCHAR(100),
      [mOldVersionName] VARCHAR(20),
      [mNewVersionCode] INT(10) NOT NULL,
      [mNewVersionName] VARCHAR(20),
      [mApkPath] VARCHAR(100)
  */
    private String mPkgName;
    private String mAppName;
    private String mDate;
    private String mIcon;
    public String getmIcon() {
        return mIcon;
    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    private String mSize;
    
    private int mOldVersionCode;
    private String mOldVersionName;
    private int mNewVersionCode;
    private String mNewVersionName;
    private String mApkPath;

    public String getmPkgName() {
        return mPkgName;
    }

    public void setmPkgName(String mPkgName) {
        this.mPkgName = mPkgName;
    }

    public String getmAppName() {
        return mAppName;
    }

    public void setmAppName(String mAppName) {
        this.mAppName = mAppName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmOldVersionName() {
        return mOldVersionName;
    }

    public void setmOldVersionName(String mOldVersionName) {
        this.mOldVersionName = mOldVersionName;
    }

    public int getmOldVersionCode() {
        return mOldVersionCode;
    }

    public void setmOldVersionCode(int mOldVersionCode) {
        this.mOldVersionCode = mOldVersionCode;
    }

    public int getmNewVersionCode() {
        return mNewVersionCode;
    }

    public void setmNewVersionCode(int mNewVersionCode) {
        this.mNewVersionCode = mNewVersionCode;
    }

    public String getmNewVersionName() {
        return mNewVersionName;
    }

    public void setmNewVersionName(String mNewVerisonName) {
        this.mNewVersionName = mNewVerisonName;
    }

    public String getmApkPath() {
        return mApkPath;
    }

    public void setmApkPath(String mApkPath) {
        this.mApkPath = mApkPath;
    }

    @Override
    public String toString() {
        return "BackAppInfo{" +
                "mPkgName='" + mPkgName + '\'' +
                ", mAppName='" + mAppName + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mOldVersionCode='" + mOldVersionCode + '\'' +
                ", mOldVerisonName='" + mOldVersionName + '\'' +
                ", mNewVersionCode='" + mNewVersionCode + '\'' +
                ", mNewVerisonName='" + mNewVersionName + '\'' +
                ", mApkPath='" + mApkPath + '\'' +
                '}';
    }
}
