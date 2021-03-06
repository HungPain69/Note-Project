package com.example.note1.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.note1.Model.NoteObj;

import java.util.ArrayList;
import java.util.List;

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNote(NoteObj note){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        //dung contentValue de luu tru du lieu
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_TITLE,note.getTitle());
        contentValues.put(COLUMN_DETAIL,note.getDetail());
        contentValues.put(COLUMN_DATETIME,note.getDateTime());
//        contentValues.put(COLUMN_FAVORITE,note.getDateTime());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        Log.d("adddd",note+"insertContact");
        sqLiteDatabase.close();

    }
    //method update
    public void updateNote(NoteObj note){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_ID,note.getId());
        contentValues.put(COLUMN_TITLE,note.getTitle());
        contentValues.put(COLUMN_DETAIL,note.getDetail());
        contentValues.put(COLUMN_DATETIME,note.getDateTime());
//        contentValues.put(COLUMN_FAVORITE,note.isFavorite());


        //update where column_id=?
        sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_ID+" =?",new String[]{String.valueOf(note.getId())});
        sqLiteDatabase.close();
    }

    public void testUpdateNote(NoteObj note, int idItem){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_ID,note.getId());
        contentValues.put(COLUMN_TITLE,note.getTitle());
        contentValues.put(COLUMN_DETAIL,note.getDetail());
        contentValues.put(COLUMN_DATETIME,note.getDateTime());
//        contentValues.put(COLUMN_FAVORITE,note.isFavorite());


        //update where column_id=?
        sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_ID+" =?",new String[]{String.valueOf(idItem)});
        sqLiteDatabase.close();
    }
    // method delete
    public void deleteNote(NoteObj note){
        //xoa note object theo id
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID+" =?",
                new String[]{String.valueOf(note.getId())});
        sqLiteDatabase.close();
    }
    //method test delete
    public void testDeleteNote(NoteObj note, int idItem){
        //xoa note object theo id
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID+" =?",
                new String[]{String.valueOf(idItem)});
        sqLiteDatabase.close();
    }

    //method get all note and return List
    public List<NoteObj> getAll(){
        Log.d("ContactLog","getAllContact");
        List <NoteObj> list=new ArrayList<NoteObj>();
        String query="SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do{
                NoteObj note=new NoteObj();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setDetail(cursor.getString(2));
                note.setDateTime(cursor.getString(3));
//                note.setFavorite(cursor.getInt(4)>0);
                list.add(note);

            }while (cursor.moveToNext());
        }
        return list;
    }

}
