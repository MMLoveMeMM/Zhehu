package cn.pumpkin.db.db.db;

import android.database.sqlite.SQLiteDatabase;

public interface SQLiteHelperListener {

	void onCreate(SQLiteDatabase db);

	void onOpen(SQLiteDatabase db);

	void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

	void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
