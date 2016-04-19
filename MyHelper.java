package com.example.mymovie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyHelper extends SQLiteOpenHelper {
     
	Context con;
	public MyHelper(Context context)
	{
		
		super(context, "USER_DB1", null, 2);
		con=context;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table usertable(moviename TEXT);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		db.execSQL("drop table usertable");
		onCreate(db);

	}

}
