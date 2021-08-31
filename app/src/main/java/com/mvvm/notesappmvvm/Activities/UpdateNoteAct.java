package com.mvvm.notesappmvvm.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mvvm.notesappmvvm.Model.Notes;
import com.mvvm.notesappmvvm.R;
import com.mvvm.notesappmvvm.ViewModel.NotesViewModel;
import com.mvvm.notesappmvvm.databinding.ActivityInsertNoteBinding;
import com.mvvm.notesappmvvm.databinding.ActivityUpdateNoteBinding;

import java.util.Date;

public class UpdateNoteAct extends AppCompatActivity {

    ActivityUpdateNoteBinding binding;
    String priority = "1";
    NotesViewModel notesViewModel;
    String stitle,ssubtitle,snote,sprority;
    int iit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        iit = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title").toString();
        ssubtitle = getIntent().getStringExtra("subtitle").toString();
        sprority = getIntent().getStringExtra("prioriy").toString();
        snote = getIntent().getStringExtra("notes").toString();


        binding.updatetitle.setText(stitle);
        binding.updatesubtitle.setText(ssubtitle);
        binding.updatenotedata.setText(snote);

        if(sprority.equals("1")) {
            binding.greenpriority.setBackgroundResource(R.drawable.green_circle);

        }
        if(sprority.equals("2")) {
            binding.yellowpriority.setBackgroundResource(R.drawable.yellow_circle);

        }
        if(sprority.equals("3")) {
            binding.redpriority.setBackgroundResource(R.drawable.red_circle);

        }




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
        binding.updateNoteBtn.setOnClickListener(v -> {
            String title = binding.updatetitle.getText().toString();
            String subtitle = binding.updatesubtitle.getText().toString();
            String notes = binding.updatenotedata.getText().toString();

            updateNotes(title,subtitle,notes);
        });

    }

    private void updateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes update = new Notes();
        update.id=iit;
        update.notesTitle = title;
        update.notesSubTitle = subtitle;
        update.notes = notes;
        update.notesDate = sequence.toString();
        update.notesprority = priority;
        notesViewModel.updatenote(update);


        Toast.makeText(UpdateNoteAct.this, "Note Updated Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.deleteicon){
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNoteAct.this,R.style.BottmSheetStyle);

            View v = LayoutInflater.from(UpdateNoteAct.this).inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottomcheet));

            bottomSheetDialog.setContentView(v);

            TextView yes,no;
            yes= v.findViewById(R.id.yesbtn);
            no= v.findViewById(R.id.nobtn);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notesViewModel.deletenote(iit);
                    finish();
                }
            });
            no.setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.show();
        }
        return true;
    }
}