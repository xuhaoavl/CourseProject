package com.example.mobilesafe.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BlackNumberDBHelper extends SQLiteOpenHelper{

	public BlackNumberDBHelper(Context context) {
		super(context, "blacknumber.db", null, 1);
	}

	/**
	 * 第一次创建数据库的时候执行 oncreate方法abcdefg
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE blacknumber (_id integer primary key autoincrement, number varchar(20))");
	}

	/**
	 * 更新数据库的操作
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	
	
	
}
