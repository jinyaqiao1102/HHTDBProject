package com.hht.dbmanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JinYQ on 2017/6/22.
 */

public class SQLStatementImp implements ISQLStatement {
    private SQLiteDatabase mDatabase;
    private static SQLStatementImp sqlStatementImp = null;
    private static Object read = new Object();
    private static Object write = new Object();

    private SQLStatementImp(SQLiteDatabase mDatabase) {
        this.mDatabase = mDatabase;
    }

    public static SQLStatementImp getInstance(SQLiteDatabase mDatabase) {
        if (sqlStatementImp == null) {
            synchronized (SQLStatementImp.class) {
                if (sqlStatementImp == null) {
                    sqlStatementImp = new SQLStatementImp(mDatabase);
                }
            }
        }
        return sqlStatementImp;
    }

    @Override
    public List<Map<String, Object>> query(String sql, String[] selectionArgs) {
        synchronized (read) {
            List<Map<String, Object>> rsall = new ArrayList<Map<String, Object>>();
            Cursor cursor = null;
            try {
                cursor = mDatabase.rawQuery(sql, selectionArgs);
                while (cursor.moveToNext()) {
                    int count = cursor.getColumnCount();
                    Map<String, Object> rsTree = new HashMap<>(count);
                    for (int i = 0; i < count; i++) {
                        String colName = cursor.getColumnName(i);
                        Object colValue = getCursorObject(cursor, i);
                        rsTree.put(colName, colValue);
                    }
                    rsall.add(rsTree);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            return rsall;
        }
    }

    @Override
    public List<Map<String, Object>> query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        synchronized (read) {
            List<Map<String, Object>> rsall = new ArrayList<Map<String, Object>>();
            Cursor cursor = null;
            try {
                cursor = mDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
                while (cursor.moveToNext()) {
                    int count = cursor.getColumnCount();
                    Map<String, Object> rsTree = new HashMap<>(count);
                    for (int i = 0; i < count; i++) {
                        String colName = cursor.getColumnName(i);
                        Object colValue = getCursorObject(cursor, i);
                        rsTree.put(colName, colValue);
                    }
                    rsall.add(rsTree);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }

            }
            return rsall;
        }
    }

    @Override
    public long update(String tableName, Map<String, Object> valueMap, String where, String[] whereArg) {
        synchronized (write) {
            long count = -1;
            try {
                ContentValues values = mapToContent(valueMap);
                count = mDatabase.update(tableName, values, where, whereArg);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mDatabase != null) {
                }
            }

            return count;
        }
    }

    @Override
    public long update(String tableName, ContentValues values, String where, String[] whereArg) {
        synchronized (write) {
            long count = -1;
            try {
                count = mDatabase.update(tableName, values, where, whereArg);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
            return count;
        }
    }

    @Override
    public long delete(String tableName, String where, String[] whereArg) {
        synchronized (write) {
            long count = -1;
            try {
                count = mDatabase.delete(tableName, where, whereArg);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mDatabase != null) {
                }
            }

            return count;
        }
    }

    @Override
    public long insert(String tableName, Map<String, Object> valueMap) {
        synchronized (write) {
            long count = -1;
            try {
                ContentValues values = mapToContent(valueMap);
                count = mDatabase.insert(tableName, null, values);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mDatabase != null) {
                }
            }

            return count;
        }
    }

    @Override
    public long insert(String tableName, ContentValues values) {
        synchronized (write) {
            long count = -1;
            try {
                count = mDatabase.insert(tableName, null, values);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mDatabase != null) {
                }
            }

            return count;
        }
    }

    @Override
    public long insert(String tableName, List<Map<String, Object>> valueList) {
        synchronized (write) {
            long cnt = 0;
            try {
                mDatabase.beginTransaction();
                if (valueList != null) {
                    for (Map<String, Object> valueMap : valueList) {
                        ContentValues contentValues = mapToContent(valueMap);
                        long ret = mDatabase.insert(tableName, null, contentValues);
                        if (ret > 0) {
                            cnt++;
                        }
                    }
                }
                mDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mDatabase != null) {
                    mDatabase.endTransaction();
                }
            }

            return cnt;
        }
    }

    @Override
    public long replace(String tableName, String nullColumnHack, ContentValues values) {
        synchronized (write) {
            long count = -1;
            try {
                count = mDatabase.replace(tableName, nullColumnHack, values);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return count;
        }
    }

    @Override
    public long replace(String tableName, String nullColumnHack, Map<String, Object> valueList) {
        synchronized (write) {
            long count = -1;
            try {
                count = mDatabase.replace(tableName, nullColumnHack, mapToContent(valueList));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return count;
        }
    }

    /**
     * 获取游标指向的对象
     *
     * @param cursor：游标
     * @param i：引索下标
     * @return：指向对象
     */
    public Object getCursorObject(Cursor cursor, int i) {
        Object colValue = null;

        int colType = cursor.getType(i);
        switch (colType) {
            case Cursor.FIELD_TYPE_STRING:
                colValue = cursor.getString(i);
                break;
            case Cursor.FIELD_TYPE_INTEGER:
                colValue = cursor.getInt(i);
                break;
            case Cursor.FIELD_TYPE_FLOAT:
                colValue = cursor.getFloat(i);
                break;
            case Cursor.FIELD_TYPE_BLOB:
                colValue = cursor.getBlob(i);
                break;
            case Cursor.FIELD_TYPE_NULL:
                colValue = null;
                break;
            default:
                colValue = cursor.getString(i);
                break;
        }
        return colValue;
    }

    private ContentValues mapToContent(Map<String, Object> valueMap) {
        ContentValues values = new ContentValues();
        if (valueMap != null) {
            for (String key : valueMap.keySet()) {
                Object value = valueMap.get(key);
                if (value == null)
                    values.putNull(key);
                else if (value instanceof String)
                    values.put(key, value.toString());
                else if (value instanceof Byte)
                    values.put(key, Byte.parseByte(value.toString()));
                else if (value instanceof byte[])
                    values.put(key, (byte[]) value);
                else if (value instanceof Float)
                    values.put(key, Float.parseFloat(value.toString()));
                else if (value instanceof Double)
                    values.put(key, Double.parseDouble(value.toString()));
                else if (value instanceof Boolean)
                    values.put(key, Boolean.parseBoolean(value.toString()));
                else if (value instanceof Integer)
                    values.put(key, Integer.parseInt(value.toString()));
                else if (value instanceof Long)
                    values.put(key, Long.parseLong(value.toString()));
                else if (value instanceof Short)
                    values.put(key, Short.parseShort(value.toString()));
                else
                    values.put(key, value.toString());
            }
        }
        return values;
    }
}
