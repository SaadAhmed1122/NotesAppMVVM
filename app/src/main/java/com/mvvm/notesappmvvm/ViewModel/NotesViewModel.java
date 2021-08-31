package com.mvvm.notesappmvvm.ViewModel;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mvvm.notesappmvvm.Database.NotesDatabase;
import com.mvvm.notesappmvvm.Model.Notes;
import com.mvvm.notesappmvvm.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;

    public LiveData<List<Notes>> hightolow;

    public LiveData<List<Notes>> lowtohigh;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getallNotes;
        hightolow = repository.hightolow;
        lowtohigh = repository.lowtohigh;


    }
    public void insertnote(Notes notes){
        repository.insertNotes(notes);
    }
    public void updatenote(Notes notes){
        repository.updateNotes(notes);
    }
    public void deletenote(int id){
        repository.deleteNotes(id);
    }

}
