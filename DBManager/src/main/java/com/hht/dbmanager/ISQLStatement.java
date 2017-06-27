package com.hht.dbmanager;

import android.content.ContentValues;

import java.util.List;
import java.util.Map;

/**
 * Created by JinYQ on 2017/6/22.
 */

public interface ISQLStatement {
    List<Map<String, Object>> query(String sql, String[] selectionArgs);

    List<Map<String, Object>> query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    long update(String tableName, Map<String, Object> valueMap, String where, String[] whereArg);

    long update(String tableName, ContentValues values, String where, String[] whereArg);

    long delete(String tableName, String where, String[] whereArg);

    long insert(String tableName, Map<String, Object> valueMap);

    long insert(String tableName, ContentValues values);

    long insert(String tableName, List<Map<String, Object>> valueList);

    long replace(String tableName, String nullColumnHack, ContentValues values);

    long replace(String tableName, String nullColumnHack, Map<String, Object> valueList);

}
