package cn.pumpkin.db.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static final String TAG = DBManager.class.getName();

    private Context mContext;

    public static final String DB_DEFAULT_NAME = "hddb";
    private int dbVersion = 4;
    private String dbName = DB_DEFAULT_NAME;
    private SQLiteHelperListener sqliteListener;
    private SQLiteOpenHelperProxy sqliteHelper;
    private SQLiteDatabase db;

    public DBManager(Context context, SQLiteHelperListener sqlListener) {
        mContext = context;
        sqliteListener = sqlListener;
        sqliteHelper = new SQLiteOpenHelperProxy(mContext, dbName, null, dbVersion, sqliteListener);
        db = sqliteHelper.getWritableDatabase();
    }

    public boolean isOpen() {
        if (null == db) {
            return false;
        }
        return db.isOpen();
    }

    public void close() {
        if (null != db) {
            db.close();
        }

        if (null != sqliteHelper) {
            sqliteHelper.close();
        }
    }

    public void beginTransaction() {
        if (null != db) {
            db.beginTransaction();
        }
    }

    public void endTransaction() {
        if (null != db) {
            db.endTransaction();
        }
    }

    /**
     * 使用SQLiteDatabase的beginTransaction()方法可以开启一个事务，程序执行到endTransaction()
     * 方法时会检查事务的标志是否为成功，如果程序执行到endTransaction()之前调用了setTransactionSuccessful()
     * 方法设置事务的标志为成功则提交事务，如果没有调用setTransactionSuccessful() 方法则回滚事务。
     */
    public void setTransactionSuccessful() {
        if (null != db) {
            db.setTransactionSuccessful();
        }
    }

    public boolean execSQL(String sql) {
        boolean result = true;
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Log.d(TAG, "", e);
            result = false;
        }
        return result;
    }

    public boolean execSQL(String sql, Object[] bindArgs) {
        boolean result = true;
        try {
            db.execSQL(sql, bindArgs);
        } catch (Exception e) {
            Log.d(TAG, "", e);
            result = false;
        }
        return result;
    }

    public Cursor rawQuery(String sql, String selectionArgs[]) {
        return db.rawQuery(sql, selectionArgs);
    }

    public boolean insert(DBEntity en) {
        boolean success = false;
        try {
            ContentValues values = DBUtil.getContentValuesByEntity(en);
            Log.d(TAG, "insert:" + DBUtil.getTableName(en) + "," + values.toString());
            long result = db.insert(DBUtil.getTableName(en), null, values);
            if (result != -1) {
                success = true;
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return success;
    }

    /**
     * 根据实体类的主键进行更新
     *
     * @param en
     * @return
     */
    public boolean update(DBEntity en) {
        boolean success = false;
        try {
            ContentValues values = DBUtil.getContentValuesByEntity(en);
            long result = db.update(DBUtil.getTableName(en), values, DBUtil.getPrimaryKeyName(en) + "=?",
                    new String[]{DBUtil.getPrimaryKeyValue(en)});
            if (result > 0) {
                success = true;
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return success;
    }

    public int update(DBEntity en, ContentValues values, String whereClause,
                      String[] whereArgs) {
        return db.update(DBUtil.getTableName(en), values, whereClause, whereArgs);
    }

    public int delete(DBEntity en, String whereClause, String[] whereArgs) {
        return db.delete(DBUtil.getTableName(en), whereClause, whereArgs);
    }

    /**
     * 根据实体类的主键进行匹配删除
     *
     * @param en
     * @return
     */
    public boolean deleteEntity(DBEntity en) {
        boolean success = false;
        try {
            long result = db.delete(DBUtil.getTableName(en), DBUtil.getPrimaryKeyName(en) + "=?",
                    new String[]{DBUtil.getPrimaryKeyValue(en)});
            if (result > 0) {
                success = true;
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return success;
    }

    public Cursor query(boolean distinct, String table, String[] columns,
                        String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy, String limit) {
        return db.query(distinct, table, columns, selection, selectionArgs,
                groupBy, having, orderBy, limit);
    }

    /**
     * @param clazz
     * @param table
     * @param distinct
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return 实体集合
     */
    public List<? extends DBEntity> query(Class<? extends DBEntity> clazz,
                                          String table, boolean distinct, String selection,
                                          String[] selectionArgs, String groupBy, String having,
                                          String orderBy, String limit) {
        Cursor cursor = null;
        try {
            cursor = query(distinct, table, null, selection, selectionArgs,
                    groupBy, having, orderBy, limit);
            List<? extends DBEntity> list = DBUtil.cursor2List(clazz, cursor);
            return list;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public DBEntity queryByWhere(Class<? extends DBEntity> clazz, String field, String value) {
        String tabName = null;
        try {
            tabName = clazz.newInstance().getTableName();
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        if (null == tabName) {
            return null;
        }
        Cursor cursor = null;
        DBEntity entity = null;
        try {
            cursor = query(true, tabName, null, field + "=?", new String[]{value}, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                entity = DBUtil.cursor2Entity(clazz, cursor);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return entity;
    }

    public List<? extends DBEntity> queryListByWhere(Class<? extends DBEntity> clazz, String[] fields, String[] values, boolean distinct,
                                                     String groupBy, String having, String orderBy, String limit) {
        String tabName = null;
        try {
            tabName = clazz.newInstance().getTableName();
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        if (null == tabName) {
            return null;
        }
        List<? extends DBEntity> entityList = null;
        try {
            String filed = "";
            for (int i = 0; i < fields.length; i++) {
                if (i == fields.length - 1) {
                    filed += (fields[i] + "=?");
                } else {
                    filed += (fields[i] + "=? and ");
                }
            }
            Log.d(TAG, "queryByWhere filed:" + filed);
            entityList = query(clazz, tabName, true, filed, values, groupBy, having, orderBy, limit);
        } finally {
        }
        return entityList;
    }

    public List<? extends DBEntity> queryListByWhere(Class<? extends DBEntity> clazz, String[] fields, String[] values) {
        String tabName = null;
        try {
            tabName = clazz.newInstance().getTableName();
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        if (null == tabName) {
            return null;
        }
        List<? extends DBEntity> entityList = null;
        try {
            String filed = "";
            for (int i = 0; i < fields.length; i++) {
                if (i == fields.length - 1) {
                    filed += (fields[i] + "=?");
                } else {
                    filed += (fields[i] + "=? and ");
                }
            }
            Log.d(TAG, "queryByWhere filed:" + filed);
            entityList = query(clazz, tabName, true, filed, values, null, null, null, null);
        } finally {
        }
        return entityList;
    }

    public List<? extends DBEntity> queryListByWhere(Class<? extends DBEntity> clazz, String field, String value) {
        String tabName = null;
        try {
            tabName = clazz.newInstance().getTableName();
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        if (null == tabName) {
            return null;
        }
        Cursor cursor = null;
        List<? extends DBEntity> entities = null;
        try {
            cursor = query(true, tabName, null, field + "=?", new String[]{value}, null, null, null, null);
            entities = DBUtil.cursor2List(clazz, cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return entities;
    }


    public List<DBEntity> queryEntity(Class<? extends DBEntity> clazz, int count) {
        String tableName = null;
        try {
            tableName = clazz.newInstance().getTableName();
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        if (null == tableName) {
            return null;
        }
        String sql = "select * from " + tableName + " limit " + count;
        Cursor cursor = null;
        ArrayList<DBEntity> fiList = new ArrayList<>();
        try {
            cursor = rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    DBEntity entity = DBUtil.cursor2Entity(clazz, cursor);
                    fiList.add(entity);
                } while (cursor.moveToNext());
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return fiList;
    }

    public List<DBEntity> queryEntity(Class<? extends DBEntity> clazz) {
        String tableName = null;
        try {
            tableName = clazz.newInstance().getTableName();
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        if (null == tableName) {
            return null;
        }
        String sql = "select * from " + tableName;
        Cursor cursor = null;
        ArrayList<DBEntity> fiList = new ArrayList<>();
        try {
            cursor = rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    DBEntity entity = DBUtil.cursor2Entity(clazz, cursor);
                    fiList.add(entity);
                } while (cursor.moveToNext());
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return fiList;
    }

    public boolean insertOrUpdate(DBEntity entity) {
        boolean success = false;
        if (null == entity) {
            return success;
        }

        DBEntity existEntity = entityExist(entity);

        Log.d(TAG, "inertOrUpdate ?:" + (null == existEntity));
        if (null == existEntity) {
            success = insert(entity);
        } else {
            success = update(entity);
        }
        return success;
    }

    public DBEntity entityExist(DBEntity entity) {
        if (null == entity) {
            return null;
        }
        String tableName = entity.getTableName();
        String key = DBUtil.getPrimaryKeyName(entity);
        String value = DBUtil.getPrimaryKeyValue(entity);
        String sql = "select * from " + tableName + " where " + key + " = ? ";
        Cursor cursor = null;
        DBEntity existEntity = null;
        try {
            cursor = db.rawQuery(sql, new String[]{value});
            if (cursor != null && cursor.moveToFirst()) {
                existEntity = DBUtil.cursor2Entity(entity.getClass(), cursor);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return existEntity;
    }

    public SQLiteOpenHelper getSQLiteOpenHelper() {
        return sqliteHelper;
    }

    public int queryTableCount(Class<? extends DBEntity> clazz, String field, String value) {
        String tableName = null;
        try {
            tableName = clazz.newInstance().getTableName();
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        if (null == tableName) {
            return 0;
        }
        String sql = "select count(*) from " + tableName +" where "+field+" ="+value;
        Cursor cursor = null;
        int count = 0;
        try {
            cursor = rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return count;
    }
}