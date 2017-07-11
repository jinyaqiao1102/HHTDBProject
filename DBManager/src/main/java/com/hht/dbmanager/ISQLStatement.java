package com.hht.dbmanager;

import android.content.ContentValues;

import java.util.List;
import java.util.Map;

/**
 * Created by JinYQ on 2017/6/22.
 */

public interface ISQLStatement {
    /**
     * 数据库查询方法
     *
     * @param sql
     * @param selectionArgs
     * @return  List<Map<String, Object>>
     * @throws
     */
    List<Map<String, Object>> query(String sql, String[] selectionArgs);

    /**
     * 数据库查询方法
     * @param table
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return List<Map<String, Object>> query
     */
    List<Map<String, Object>> query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    /**
     * 数据库更新方法
     * @param tableName
     * @param valueMap
     * @param where
     * @param whereArg
     * @return
     */
    long update(String tableName, Map<String, Object> valueMap, String where, String[] whereArg);

    /**
     * 数据库更新方法
     * @param tableName
     * @param values
     * @param where
     * @param whereArg
     * @return
     */
    long update(String tableName, ContentValues values, String where, String[] whereArg);

    /**
     * 数据库删除方法
     * @param tableName
     * @param where
     * @param whereArg
     * @return
     */
    long delete(String tableName, String where, String[] whereArg);

    /**
     * 插入方法
     * @param tableName
     * @param valueMap
     * @return
     */
    long insert(String tableName, Map<String, Object> valueMap);

    /**
     * 数据库插入
     * @param tableName
     * @param values
     * @return
     */
    long insert(String tableName, ContentValues values);

    /**
     * 数据库插入
     * @param tableName
     * @param valueList
     * @return
     */
    long insert(String tableName, List<Map<String, Object>> valueList);

    /**
     * 数据库替换
     * @param tableName
     * @param nullColumnHack
     * @param values
     * @return
     */
    long replace(String tableName, String nullColumnHack, ContentValues values);

    /**
     * 数据库替换
     * @param tableName
     * @param nullColumnHack
     * @param valueList
     * @return
     */
    long replace(String tableName, String nullColumnHack, Map<String, Object> valueList);

}
