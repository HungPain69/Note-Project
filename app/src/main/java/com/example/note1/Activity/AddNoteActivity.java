package com.example.note1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.note1.MainActivity;
import com.example.note1.Model.NoteObj;
import com.example.note1.R;
import com.example.note1.Sqlite.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    EditText addTitle;
    EditText addDetail;
    EditText addDateTime;
    NoteObj note;
    Button btnSave;
    String getTimeNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);

        this.addTitle = (EditText)findViewById(R.id.addTitle);
        this.addDetail = (EditText)findViewById(R.id.addDetail);
//        this.addDateTime = (EditText)findViewById(R.id.addDateTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd 'thg' MM', 'yyyy");
        Date now = Calendar.getInstance().getTime();
        getTimeNow = dateFormat.format(now);
        Log.d("datetime",dateFormat.format(now)+"");
    }
    //    btnSave =
    public void onClickSave(View view)  {
        DBHelper db = new DBHelper(this);
        String title = this.addTitle.getText().toString();
        String content = this.addDetail.getText().toString();
//        String dateTime = this.addDateTime.getText().toString();
        this.note = new NoteObj(title,content,getTimeNow);

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.addNote(this.note);
        Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
        intent.putExtra("note", this.note);
        startActivity(intent);
        finish();

//        this.onBackPressed();

    }

}
