package com.mvvm.notesappmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mvvm.notesappmvvm.Activities.InsertNoteAct;
import com.mvvm.notesappmvvm.Adaptor.NotesAdaptor;
import com.mvvm.notesappmvvm.Model.Notes;
import com.mvvm.notesappmvvm.ViewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNoteBtn;
    RecyclerView notesrecycler;
    NotesAdaptor adaptor;
    TextView nofilter,hightolow,lowtohigh;
    NotesViewModel notesViewModel;
    List<Notes> filterednotesalllist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNoteBtn = findViewById(R.id.newNoteBtn);
        notesrecycler = findViewById(R.id.notesrecycler);
        nofilter = findViewById(R.id.nofilter);
        hightolow = findViewById(R.id.hightolow);
        lowtohigh = findViewById(R.id.lowtohigh);

        nofilter.setBackgroundResource(R.drawable.filter_sel_shape);

        nofilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadData(1);
                nofilter.setBackgroundResource(R.drawable.filter_sel_shape);
                hightolow.setBackgroundResource(R.drawable.filter_un_shape);
                lowtohigh.setBackgroundResource(R.drawable.filter_un_shape);

            }
        });
        hightolow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(2);
                hightolow.setBackgroundResource(R.drawable.filter_sel_shape);
                nofilter.setBackgroundResource(R.drawable.filter_un_shape);
                lowtohigh.setBackgroundResource(R.drawable.filter_un_shape);
            }
        });
        lowtohigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(3);
                lowtohigh.setBackgroundResource(R.drawable.filter_sel_shape);
                hightolow.setBackgroundResource(R.drawable.filter_un_shape);
                nofilter.setBackgroundResource(R.drawable.filter_un_shape);
            }
        });

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        newNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertNoteAct.class));

            }
        });
        notesViewModel.getAllNotes.observe(this,notes -> {
            setAdaptor(notes);
            filterednotesalllist = notes;
        });
    }

    private void loadData(int i) {
    if(i==1){
        notesViewModel.getAllNotes.observe(this,notes -> {

            setAdaptor(notes);
            filterednotesalllist = notes;
        });
    }
    else if(i==2){
        notesViewModel.hightolow.observe(this,notes -> {
            setAdaptor(notes);
            filterednotesalllist = notes;
        });
    }
    else if(i==3){
        notesViewModel.lowtohigh.observe(this,notes -> {
            setAdaptor(notes);
            filterednotesalllist = notes;
        });
    }

    }

    public void setAdaptor(List<Notes> notes){
        notesrecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adaptor = new NotesAdaptor(MainActivity.this,notes);
        notesrecycler.setAdapter(adaptor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search_note);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes Here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Notesfilter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void Notesfilter(String newText) {
        ArrayList<Notes> filternotess = new ArrayList<>();

        for(Notes notes:this.filterednotesalllist){
            if(notes.notesTitle.contains(newText) || notes.notesSubTitle.contains(newText)){
                filternotess.add(notes);
            }
        }
        this.adaptor.searchnotes(filternotess);
    }
}