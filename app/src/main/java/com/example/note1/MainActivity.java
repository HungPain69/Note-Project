package com.example.note1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.note1.Activity.AddNoteActivity;
import com.example.note1.Adapter.NoteAdapter;
import com.example.note1.Model.NoteObj;
import com.example.note1.Sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycleViewNote;
    NoteAdapter noteAdapter;
    List<NoteObj> listNote=new ArrayList<NoteObj>();
    private RecyclerView.LayoutManager mLayoutManager;

    NoteAdapter.OnNoteSelectedListener listener = new NoteAdapter.OnNoteSelectedListener(){
        @Override
        public void onSeLected(int index) {
            Toast.makeText(MainActivity.this,  "you tap on"+listNote.get(index).getTitle(), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create table
        DBHelper db = new DBHelper(this);

        //insert fake data
//        db.addNote(new NoteObj("title1","detail1"));
//       //update test
//        db.testUpdateNote(new NoteObj("title update","detail update"),1);
//        //delete test
//        db.testDeleteNote(new NoteObj("",""),0);

        //get all data to list and add this list to ListNote
        final List<NoteObj> list = db.getAll();
        this.listNote.addAll(list);

        RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recycle_view_contact);
        //Init adapter
        noteAdapter = new NoteAdapter(this,(ArrayList<NoteObj>) listNote, listener);
        //set Adapter
        recyclerViewNote.setAdapter(noteAdapter);
        //create layout manager
        mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //set layout manager
        recyclerViewNote.setLayoutManager(mLayoutManager);



        Button buttonAdd = findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }



}