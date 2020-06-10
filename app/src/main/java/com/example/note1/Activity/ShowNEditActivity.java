package com.example.note1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.note1.MainActivity;
import com.example.note1.Model.NoteObj;
import com.example.note1.R;
import com.example.note1.Sqlite.DBHelper;

public class ShowNEditActivity extends AppCompatActivity {
    EditText editTextTitle, editTextContent, editTextDateTime;

    NoteObj note;
    Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_n_edit);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextContent = findViewById(R.id.edit_text_content);
        editTextDateTime = findViewById(R.id.edit_text_dateTime);
        Intent intent = getIntent();
        this.note = (NoteObj) intent.getSerializableExtra("note");
        editTextTitle.setText(note.getTitle());
        editTextContent.setText(note.getDetail());
        editTextDateTime.setText(note.getDateTime());


    }
    public void onClickSave(View view){
        DBHelper db = new DBHelper(this);
        this.note.setTitle(editTextTitle.getText().toString());
        this.note.setDetail(editTextContent.getText().toString());
        this.note.setDateTime(editTextDateTime.getText().toString());

        db.updateNote(note);
        Intent inten= new Intent(ShowNEditActivity.this, MainActivity.class);
        startActivity(inten);
        finish();
    }
}