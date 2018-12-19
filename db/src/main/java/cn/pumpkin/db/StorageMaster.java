package cn.pumpkin.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cn.pumpkin.db.db.db.DBManager;
import cn.pumpkin.db.db.db.SQLiteHelperListener;
import cn.pumpkin.db.sp.ISP;
import cn.pumpkin.db.sp.SPImpl;

/**
 * 存储管理类，用于初始化数据库，SharedPreferences等
 */
public class StorageMaster {
    private static String TAG = StorageMaster.class.getName();

    public static String SP_NAME_DEFAULT = "zhefu";

    private static StorageMaster mStorageMaster = null;
    private Context mContext = null;

    private DBManager mDBManager = null;
    private SPImpl mDefaultISP = null;

    public static final String TABLE_ZF_CONFIG = "zhefu";

    public static synchronized StorageMaster getInstance() {
        if (null == mStorageMaster) {
            mStorageMaster = new StorageMaster();
        }
        return mStorageMaster;
    }

    public void initStorage(Context context) {
        mContext = context.getApplicationContext();
        mDBManager = new DBManager(context, sqlListener);
        mDefaultISP = new SPImpl(context, SP_NAME_DEFAULT);
    }

    public DBManager getDBManager() {
        return mDBManager;
    }

    public ISP getDefaultISP() {
        return mDefaultISP;
    }

    public ISP getISP(String fileName) {
        return new SPImpl(mContext, fileName);
    }

    private SQLiteHelperListener sqlListener = new SQLiteHelperListener() {

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTTSConfig(db);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG, "onUpgrade oldVersion = " + oldVersion + ",newVersion = " + newVersion);
            if (newVersion > oldVersion){

            }
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG, "onDowngrade oldVersion = " + oldVersion + ",newVersion = " + newVersion);
        }

        private void createTTSConfig(SQLiteDatabase db){
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ZF_CONFIG + " ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "uid INTEGER,"
                    + "name varchar(64),"
                    + "status INTEGER" + ")");
        }

        private boolean checkTableExist(SQLiteDatabase db, String tableName) {
            boolean result = false;
            Cursor cursor = null;
            try {
                cursor = db.rawQuery("SELECT count(*) FROM sqlite_master where type='table' AND name='" + tableName + "'"
                        , null);
                cursor.moveToFirst();
                result = cursor.getInt(0) > 0;
            } catch (Exception e) {
                Log.e("checkTableExist...", e.toString());
            } finally {
                if (null != cursor && !cursor.isClosed()) {
                    cursor.close();
                }
            }
            return result;
        }

    };
}
