package com.example.note1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.note1.MainActivity;
import com.example.note1.Model.NoteObj;
import com.example.note1.R;
import com.example.note1.Sqlite.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ShowNEditActivity extends AppCompatActivity {
    EditText editTextTitle, editTextContent;
    TextView textViewDateTime;
    String mOldTitle, mOldDetail;

    NoteObj note;
    Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_n_edit);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextContent = findViewById(R.id.edit_text_content);
        textViewDateTime = findViewById(R.id.tv_dateTime);
        Intent intent = getIntent();
        this.note = (NoteObj) intent.getSerializableExtra("note");

         mOldTitle = note.getTitle();
        editTextTitle.setText(mOldTitle);
         mOldDetail = note.getDetail();
        editTextContent.setText(mOldDetail);
        textViewDateTime.setText("Đã chỉnh sửa "+note.getDateTime());


    }
    public void onClickSave(View view){
        DBHelper db = new DBHelper(this);
        String newTiltle = editTextTitle.getText().toString();
        this.note.setTitle(newTiltle);
        String newDetail = editTextContent.getText().toString();
        this.note.setDetail(newDetail);
        if(!mOldTitle.equals(newTiltle) || !mOldDetail.equals(newDetail)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd 'thg' MM', 'yyyy");
            Date now = Calendar.getInstance().getTime();
            String getTimeNow = dateFormat.format(now);
            this.note.setDateTime(getTimeNow);
        }else{
            this.note.setDateTime(textViewDateTime.getText().toString());
        }

        db.updateNote(note);
        Intent inten= new Intent(ShowNEditActivity.this, MainActivity.class);
        startActivity(inten);
        finish();
    }
}