package com.example.note1.Adapter;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note1.MainActivity;
import com.example.note1.Model.NoteObj;
import com.example.note1.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyNoteViewHolder> {
    private static final int MENU_ITEM_EDIT = 11;
    private static final int MENU_ITEM_DELETE = 22;
    private ArrayList<NoteObj> mDataNote;
    private Context mContext;
    private OnNoteSelectedListener mListener;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    //constructor
    public NoteAdapter(Context context ,ArrayList<NoteObj> data) {
        mContext = context;
        this.mDataNote = data;
    }

    public NoteAdapter(Context context ,ArrayList<NoteObj> data, OnNoteSelectedListener listener) {
        this.mDataNote = data;
        this.mContext = context;
        this.mListener = listener;
    }


    @NonNull
    @Override
    public MyNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.item_note_recylce_view
                                                    ,parent
                                                    ,false);
       final MyNoteViewHolder viewHolder = new MyNoteViewHolder(view);
       viewHolder.container.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mListener.onSeLected(viewHolder.getAdapterPosition());
           }
       });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNoteViewHolder holder, int position) {
        NoteObj temp = mDataNote.get(position);

        holder.title.setText(temp.getTitle());
        holder.detail.setText(temp.getDetail());
        holder.dateTime.setText(temp.getDateTime());

    }




    @Override
    public int getItemCount() {
        return mDataNote != null ? mDataNote.size() : 0;
    }



    public class MyNoteViewHolder extends RecyclerView.ViewHolder  implements View.OnCreateContextMenuListener {
        TextView title, detail, dateTime;
        LinearLayout container;
        public MyNoteViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.note_container);
            title = itemView.findViewById(R.id.itemTitle);
            detail = itemView.findViewById(R.id.itemDetail);
            dateTime = itemView.findViewById(R.id.itemDateTime);

            itemView.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select The Action");
            //groupId, itemId, order, title
            menu.add(this.getAdapterPosition(),MENU_ITEM_EDIT , 0, "Edit");
            menu.add(this.getAdapterPosition(), MENU_ITEM_DELETE , 1, "Delete");

        }

    }
    //bat su kien click vao item
    public interface  OnNoteSelectedListener{
        void onSeLected(int index);
    }
}
