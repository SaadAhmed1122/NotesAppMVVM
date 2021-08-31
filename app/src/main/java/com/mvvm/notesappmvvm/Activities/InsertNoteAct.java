package com.mvvm.notesappmvvm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.mvvm.notesappmvvm.Model.Notes;
import com.mvvm.notesappmvvm.R;
import com.mvvm.notesappmvvm.ViewModel.NotesViewModel;
import com.mvvm.notesappmvvm.databinding.ActivityInsertNoteBinding;

import java.util.Date;

public class InsertNoteAct extends AppCompatActivity {

    ActivityInsertNoteBinding binding;
    String title,subtitle,notes;
    NotesViewModel  notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.redpriority.setOnClickListener(v -> {
            binding.redpriority.setImageResource(R.drawable.doneicon);
            binding.yellowpriority.setImageResource(0);
            binding.greenpriority.setImageResource(0);
            priority = "3";
        });
        binding.yellowpriority.setOnClickListener(v -> {
            binding.redpriority.setImageResource(0);
            binding.yellowpriority.setImageResource(R.drawable.doneicon);
            binding.greenpriority.setImageResource(0);
            priority = "2";
        });
        binding.greenpriority.setOnClickListener(v -> {
            binding.redpriority.setImageResource(0);
            binding.yellowpriority.setImageResource(0);
            binding.greenpriority.setImageResource(R.drawable.doneicon);
            priority = "1";
        });


        binding.doneNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = binding.notestitleedttxt.getText().toString();
                subtitle = binding.notessubtitleedttxt.getText().toString();
                notes = binding.notesdata.getText().toString();

                createNotes(title,subtitle,notes);
            }
        });

    }

    private void createNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubTitle = subtitle;
        notes1.notes = notes;
        notes1.notesDate = sequence.toString();
        notes1.notesprority = priority;
        notesViewModel.insertnote(notes1);


        Toast.makeText(InsertNoteAct.this, "Note Created Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
}