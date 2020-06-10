package com.example.note1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.note1.Activity.AddNoteActivity;
import com.example.note1.Activity.ShowNEditActivity;
import com.example.note1.Adapter.NoteAdapter;
import com.example.note1.Model.NoteObj;
import com.example.note1.Sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_ITEM_EDIT = 11;
    private static final int MENU_ITEM_DELETE = 22;
    private static final int REQUEST_CODE = 69;

    RecyclerView recycleViewNote;
    NoteAdapter noteAdapter;
    List<NoteObj> listNote=new ArrayList<NoteObj>();
    private RecyclerView.LayoutManager mLayoutManager;
    DBHelper db;

    NoteAdapter.OnNoteSelectedListener listener = new NoteAdapter.OnNoteSelectedListener(){
        @Override
        public void onSeLected(int index) {
            Toast.makeText(MainActivity.this,  "you tap on"+listNote.get(index).getTitle(), Toast.LENGTH_LONG).show();
            NoteObj note = listNote.get(index);
            Intent intent = new Intent(MainActivity.this, ShowNEditActivity.class);
            intent.putExtra("note", note);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create table
        db = new DBHelper(this);

        //insert fake data
//        db.addNote(new NoteObj("title1","detail1"));
//       //update test
//        db.testUpdateNote(new NoteObj("title update","detail update"),1);
//        //delete test
//        db.testDeleteNote(new NoteObj("",""),0);

        //get all data to list and add this list to ListNote
        List<NoteObj> list = db.getAll();
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //getGroupId tu menuItem de chon ra note Obj dang bi longClick
        final NoteObj selectNote = listNote.get(item.getGroupId());
        switch (item.getItemId()) {
            case MENU_ITEM_DELETE:
                Toast.makeText(this,"You have clicked Delete" ,Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(this)
                        .setMessage(selectNote.getTitle()+". Are you sure you want to delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.deleteNote(selectNote);
                                listNote.remove(selectNote);
                                noteAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case MENU_ITEM_EDIT:
                Toast.makeText(this,"You have clicked Edit",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, ShowNEditActivity.class);
                intent.putExtra("note",selectNote);
                startActivityForResult(intent,REQUEST_CODE);
                break;
        }
        return super.onContextItemSelected(item);
    }



}