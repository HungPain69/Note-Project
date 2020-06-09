package com.example.note1.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note1.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyNoteViewHolder> {

    @NonNull
    @Override
    public MyNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNoteViewHolder holder, int position) {

    }




    @Override
    public int getItemCount() {
        return 0;
    }



    public class MyNoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, detail, dateTime;
        public MyNoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            detail = itemView.findViewById(R.id.itemDetail);
            dateTime = itemView.findViewById(R.id.itemDateTime);
        }
    }
}
