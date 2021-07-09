package com.example.nofapmainactivity.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
public class OpenHelper extends SQLiteOpenHelper {
    public static final String APP_DATABASE = "NOFAPDATABASE";
    public static final String USER_TABLE = "USERS";
    public static final String TODOLIST_TABLE = "TODOLIST";
    public static final String ADD_TO_TODOLIST_TASK_ID = "taskid";
    public static final String ADD_TO_TODOLIST_TASK_NAME = "taskname";
    String CREATE_USERS_TABLE = "create table USER_TABLE (ID integer primary key autoincrement, USER_NAME text, USER_PASSWORD text)";
    String CREATE_TODOLIST_TABLE = "create table PLAYLIST (ADD_TO_TODOLIST_TASK_ID integer primary key autoincrement,ADD_TO_TODOLIST_TASK_NAME text)";
    public static OpenHelper oh;

    public OpenHelper(Context context) {
        super(context, APP_DATABASE, (SQLiteDatabase.CursorFactory)null, 5);
    }

    public static OpenHelper sharedInstance(Context context) {
        if (oh == null) {
            oh = new OpenHelper(context);
        }

        return oh;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_TODOLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(CREATE_USERS_TABLE);
            db.execSQL(CREATE_TODOLIST_TABLE);
        }
    }

    public String insertTask(long j, String str) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ADD_TO_TODOLIST_TASK_ID, j);
        contentValues.put(ADD_TO_TODOLIST_TASK_NAME, str);
        return db.insert(TODOLIST_TABLE, (String)null, contentValues) + "";
    }

    public void deleteTask(long j) {
        getWritableDatabase().execSQL("DELETE FROM TODOLIST_TABLE WHERE taskid='" + j + "'");
    }
}
