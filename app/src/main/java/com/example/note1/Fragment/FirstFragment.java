package com.example.note1.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.note1.Activity.AddNoteActivity;
import com.example.note1.Activity.ShowNEditActivity;
import com.example.note1.Adapter.NoteAdapter;
import com.example.note1.Model.NoteObj;
import com.example.note1.R;
import com.example.note1.Sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment{
    private static final int MENU_ITEM_EDIT = 11;
    private static final int MENU_ITEM_DELETE = 22;
    private static final int REQUEST_CODE = 69;
    DBHelper db;
    List<NoteObj> listNote=new ArrayList<NoteObj>();
    NoteAdapter noteAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SearchView msearchView;
    RecyclerView recyclerViewNote;

    NoteAdapter.OnNoteSelectedListener listener = new NoteAdapter.OnNoteSelectedListener(){
        @Override
        public void onSeLected(int index) {
            Toast.makeText(getContext(),  "you tap on"+listNote.get(index).getTitle(), Toast.LENGTH_LONG).show();
            NoteObj note = listNote.get(index);
            Intent intent = new Intent(getContext(), ShowNEditActivity.class);
            intent.putExtra("note", note);
            startActivity(intent);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(),"f1",Toast.LENGTH_LONG).show();

        View rootView= inflater.inflate(R.layout.first_fragment, container, false);
        //Create table
        db = new DBHelper(getContext());

        //insert fake data
//        db.addNote(new NoteObj("title1","detail1"));
//       //update test
//        db.testUpdateNote(new NoteObj("title update","detail update"),1);
//        //delete test
//        db.testDeleteNote(new NoteObj("",""),0);

        //get all data to list and add this list to ListNote
        List<NoteObj> list = db.getAll();
        this.listNote.addAll(list);

        recyclerViewNote = (RecyclerView) rootView.findViewById(R.id.recycle_view_contact);
        //Init adapter
        noteAdapter = new NoteAdapter(getContext(),(ArrayList<NoteObj>) listNote, listener);
        //set Adapter
        recyclerViewNote.setAdapter(noteAdapter);
        //create layout manager
        mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //set layout manager
        recyclerViewNote.setLayoutManager(mLayoutManager);
        Button buttonAdd = rootView.findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });

        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_convert_list_grid:
                Toast.makeText(getContext(), "uList", Toast.LENGTH_SHORT).show();
                item.setIcon(R.drawable.ic_grid);
                initListDisplay();
                return true;
            case R.id.action_convert_grid:
                initGridDisplay();
                return true;
            case R.id.action_search:
                Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
                msearchView = (SearchView) item.getActionView();
                msearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Log.d("queryy",newText);
                        noteAdapter.getFilter().filter(newText);
                        return false;
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
                }
    }
    private void initListDisplay(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewNote.setLayoutManager(layoutManager);

    }
    private void initGridDisplay(){

        //create layout manager
        mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //set layout manager
        recyclerViewNote.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //getGroupId tu menuItem de chon ra note Obj dang bi longClick
        final NoteObj selectNote = listNote.get(item.getGroupId());
        switch (item.getItemId()) {
            case MENU_ITEM_DELETE:
                Toast.makeText(getContext(),"You have clicked Delete" ,Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(getContext())
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
                Toast.makeText(getContext(),"You have clicked Edit",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getContext(), ShowNEditActivity.class);
                intent.putExtra("note",selectNote);
                startActivityForResult(intent,REQUEST_CODE);
                break;
        }
        return super.onContextItemSelected(item);
    }




}
