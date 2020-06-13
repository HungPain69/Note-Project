package com.example.note1.Adapter;
import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note1.MainActivity;
import com.example.note1.Model.NoteObj;
import com.example.note1.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyNoteViewHolder> implements Filterable {
    private static final int MENU_ITEM_EDIT = 11;
    private static final int MENU_ITEM_DELETE = 22;
    private ArrayList<NoteObj> mDataNote;
    private ArrayList<NoteObj> mDataNoteAll;
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
        this.mDataNoteAll = new ArrayList<>();
        mDataNoteAll.addAll(mDataNote);

    }

    public NoteAdapter(Context context ,ArrayList<NoteObj> data, OnNoteSelectedListener listener) {
        this.mDataNote = data;
        this.mDataNoteAll = new ArrayList<>();
        mDataNoteAll.addAll(mDataNote);
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

    @Override
    public Filter getFilter() {

        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<NoteObj> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                Log.d("charS", String.valueOf(charSequence));
                filteredList.addAll(mDataNoteAll);
            } else {
                for (NoteObj note: mDataNoteAll) {
                    if (note.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            note.getDetail().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(note);
                    }
                }
            }
//            for (NoteObj note: mDataNote) {
//                Log.d("NoteAll",note.getTitle());
//            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mDataNote.clear();
            mDataNote.addAll((Collection<? extends NoteObj>) filterResults.values);
            notifyDataSetChanged();
        }
    };




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
