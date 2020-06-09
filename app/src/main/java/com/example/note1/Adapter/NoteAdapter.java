package com.example.note1.Adapter;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        public MyNoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
