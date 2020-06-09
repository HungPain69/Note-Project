package com.example.note1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.note1.Model.NoteObj;
import com.example.note1.Sqlite.DBHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create table
        DBHelper db = new DBHelper(this);

        //insert fake data
        db.addNote(new NoteObj("title1","detail1"));


       //update test
        db.testUpdateNote(new NoteObj("title update","detail update"),1);
    }
}