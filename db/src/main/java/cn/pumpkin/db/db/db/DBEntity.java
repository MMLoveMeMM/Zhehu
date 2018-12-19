package cn.pumpkin.db.db.db;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBEntity {

    private transient HashMap<Class<? extends DBEntity>, Field[]> fieldsMap = new HashMap<Class<? extends DBEntity>, Field[]>();
    public transient static final String PRIMARY_KEY_ID = "_id";

    transient long _id = -1;

    public long getPrimaryId() {
        return _id;
    }

    public void setPrimaryId(long id) {
        this._id = id;
    }

    public String getTableName() {
        return getClass().getSimpleName();
    }

    public Field[] getCurrentFields() {
        Class<? extends DBEntity> clazz = getClass();
        Field[] fields = null;
        if (fieldsMap.containsKey(clazz)) {
            fields = fieldsMap.get(clazz);
        } else {
            fields = getFieldColumn();
            fieldsMap.put(clazz, fields);
        }
        return fields;
    }

    private Field[] getFieldColumn() {
        Class<? extends DBEntity> clazz = getClass();
        Field[] retFields = null;
        ArrayList<Field> fieldList = new ArrayList<>();
        Field[] allFieldArray = clazz.getDeclaredFields();
        List<String> filter = getFilterFields();
        for (Field field : allFieldArray) {
            String fieldName = field.getName();
            if (!Modifier.isStatic(field.getModifiers()) && ((filter == null ) || (null != filter && !filter.contains(fieldName)))) {
                fieldList.add(field);
            }
        }
        retFields = fieldList.toArray(new Field[fieldList.size()]);
        return retFields;
    }

    /**
     * 若需过滤无需存储的列，重写此方法；
     *
     * @return
     */
    protected List<String> getFilterFields() {
        return null;
    }
}
