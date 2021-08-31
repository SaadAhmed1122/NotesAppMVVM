package com.mvvm.notesappmvvm.Adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mvvm.notesappmvvm.Activities.UpdateNoteAct;
import com.mvvm.notesappmvvm.MainActivity;
import com.mvvm.notesappmvvm.Model.Notes;
import com.mvvm.notesappmvvm.R;

import java.util.ArrayList;
import java.util.List;


public class NotesAdaptor extends RecyclerView.Adapter<NotesAdaptor.NotesViewHolder>{

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allnotesitem;
    public NotesAdaptor(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allnotesitem = new ArrayList<>(notes);
    }

    public void searchnotes(List<Notes> filterednotes){
        this.notes = filterednotes;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Notes note =notes.get(position);

        if(note.notesprority.equals("3")) {
            holder.notespriority.setBackgroundResource(R.drawable.red_circle);
        }
        else if(note.notesprority.equals("2")){
            holder.notespriority.setBackgroundResource(R.drawable.yellow_circle);
        }
        else if(note.notesprority.equals("1")){
            holder.notespriority.setBackgroundResource(R.drawable.green_circle);
        }

        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubTitle);
        holder.date.setText(note.notesDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(mainActivity, UpdateNoteAct.class);
                ii.putExtra("id",note.id);
                ii.putExtra("title",note.notesTitle);
                ii.putExtra("subtitle",note.notesSubTitle);
                ii.putExtra("prioriy",note.notesprority);
                ii.putExtra("notes",note.notes);



                mainActivity.startActivity(ii);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView title,subtitle,date;
        View notespriority;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.notestitle);
        notespriority = itemView.findViewById(R.id.notespriority);
        subtitle = itemView.findViewById(R.id.notessubtitle);
        date = itemView.findViewById(R.id.notesdate);

    }
}
}
