package cn.pumpkin.db.db.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
    private static final String TAG = DBUtil.class.getName();

    public static final String PRIMARY_KEY = "_id";
    public static final Map<Class<?>, String> TYPES = new HashMap<Class<?>, String>();

    static {
        TYPES.put(byte.class, "INTEGER");
        TYPES.put(boolean.class, "INTEGER");
        TYPES.put(short.class, "INTEGER");
        TYPES.put(int.class, "INTEGER");
        TYPES.put(long.class, "INTEGER");
        TYPES.put(String.class, "TEXT");
        TYPES.put(byte[].class, "BLOB");
        TYPES.put(float.class, "REAL");
        TYPES.put(double.class, "REAL");
    }

    public static ContentValues getContentValuesByEntity(DBEntity entity) {
        ContentValues cv = new ContentValues();
        try {
            Field[] fieldList = entity.getCurrentFields();
            for (Field field : fieldList) {
                String name = field.getName();
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object value = field.get(entity);
                if (value instanceof Integer) {
                    cv.put(name, (Integer) value);
                } else if (value instanceof String) {
                    cv.put(name, (String) value);
                } else if (value instanceof Boolean) {
                    cv.put(name, (Boolean) value);
                } else if (value instanceof Byte) {
                    cv.put(name, (Byte) value);
                } else if (value instanceof Short) {
                    cv.put(name, (Short) value);
                } else if (value instanceof Long) {
                    cv.put(name, (Long) value);
                } else if (value instanceof Double) {
                    cv.put(name, (Double) value);
                } else if (value instanceof Float) {
                    cv.put(name, (Float) value);
                } else if (value instanceof byte[]) {
                    cv.put(name, (byte[]) value);
                } else {
                    if (value != null) {

                    }
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return cv;
    }

    public static List<? extends DBEntity> cursor2List(Class<? extends DBEntity> clazz,
                                                       Cursor cursor) {
        List<DBEntity> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                DBEntity entity = cursor2Entity(clazz, cursor);
                list.add(entity);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public static DBEntity cursor2Entity(Class<? extends DBEntity> clazz, Cursor cursor) {
        DBEntity entity = null;
        try {
            entity = clazz.newInstance();
            long _id = -1;
            if (cursor.getColumnIndex(DBUtil.PRIMARY_KEY) >= 0) {
                _id = cursor.getLong(cursor.getColumnIndex(DBUtil.PRIMARY_KEY));
            }
            entity.setPrimaryId(_id);

            Field[] fields = entity.getCurrentFields();
            for (Field field : fields) {
                Class<?> type = field.getType();

                String columnName = field.getName();
                int columnIndex = cursor.getColumnIndex(columnName);
                if (columnIndex != -1) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    if (type == boolean.class) {
                        field.set(entity, cursor.getInt(columnIndex) != 0);
                    } else if (type == byte.class) {
                        field.set(entity, (byte) cursor.getShort(columnIndex));
                    } else if (type == short.class) {
                        field.set(entity, cursor.getShort(columnIndex));
                    } else if (type == int.class) {
                        field.set(entity, cursor.getInt(columnIndex));
                    } else if (type == long.class) {
                        field.set(entity, cursor.getLong(columnIndex));
                    } else if (type == float.class) {
                        field.set(entity, cursor.getFloat(columnIndex));
                    } else if (type == double.class) {
                        field.set(entity, cursor.getDouble(columnIndex));
                    } else if (type == String.class) {
                        field.set(entity, cursor.getString(columnIndex));
                    } else if (type == byte[].class) {
                        field.set(entity, cursor.getBlob(columnIndex));
                    } else {

                    }
                } else {

                }
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return entity;
    }

    public static String getTableName(DBEntity en) {
        return en.getTableName();
    }

    public static String getPrimaryKeyName(DBEntity en) {
        String keyName = DBEntity.PRIMARY_KEY_ID;
        Field[] fields = en.getCurrentFields();
        for (Field field : fields) {
            PrimaryKey primaryName = field.getAnnotation(PrimaryKey.class);
            if (null != primaryName && primaryName.value()) {
                keyName = field.getName();
                break;
            }
        }
        return keyName;
    }

    public boolean setPrimaryKeyValue(DBEntity en, Object value) {
        boolean success = false;
        Field[] fields = en.getCurrentFields();
        try {
            for (Field field : fields) {
                PrimaryKey primaryName = field.getAnnotation(PrimaryKey.class);
                if (null != primaryName && primaryName.value()) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    field.set(en, value);
                    success = true;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return success;
    }

    public static String getPrimaryKeyValue(DBEntity en) {
        String keyValue = null;
        Field[] fields = en.getCurrentFields();
        try {
            for (Field field : fields) {
                PrimaryKey primaryName = field.getAnnotation(PrimaryKey.class);
                if (null != primaryName && primaryName.value()) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    keyValue = field.get(en).toString();
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return keyValue;
    }
}
