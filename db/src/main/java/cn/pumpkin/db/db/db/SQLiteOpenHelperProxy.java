package cn.pumpkin.db.db.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteOpenHelperProxy extends SQLiteOpenHelper {

	private SQLiteHelperListener listener;

	public SQLiteOpenHelperProxy(Context context, String name,
                                 CursorFactory factory, int version, SQLiteHelperListener listener) {
		super(context, name, factory, version);
		this.listener = listener;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		if (listener != null) {
			listener.onCreate(db);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (listener != null) {
			listener.onUpgrade(db, oldVersion, newVersion);
		}
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (listener != null) {
			listener.onDowngrade(db, oldVersion, newVersion);
		}
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		if (listener != null) {
			listener.onOpen(db);
		}
	}

}
