package com.example.note1.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="NOTE_DB";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="NOTE_TABLEE";
    private static final String COLUMN_ID="COLUMN_ID";
    private static final String COLUMN_TITLE="COLUMN_TITLE";
    private static final String COLUMN_DETAIL="COLUMN_DETAIL";
    private static final String COLUMN_DATETIME="COLUMN_DATETIME";
    private static final String COLUMN_FAVORITE="COLUMN_FAVORITE";

    //Constructor
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script="CREATE TABLE "+
                TABLE_NAME+"("+
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_TITLE+" TEXT,"+
                COLUMN_DETAIL+" TEXT,"+
                COLUMN_DATETIME+" TEXT,"+
                COLUMN_FAVORITE+" TEXT"+
                ")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
