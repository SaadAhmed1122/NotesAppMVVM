package com.mvvm.notesappmvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mvvm.notesappmvvm.Dao.NotesDao;
import com.mvvm.notesappmvvm.Database.NotesDatabase;
import com.mvvm.notesappmvvm.Model.Notes;

import java.util.List;

public class NotesRepository {
    NotesDao notesDao;

    public LiveData<List<Notes>> getallNotes;

    public LiveData<List<Notes>> hightolow;

    public LiveData<List<Notes>> lowtohigh;

    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getallNotes = notesDao.getAllnotes();
        hightolow = notesDao.hightolow();
        lowtohigh = notesDao.lowtohigh();

    }
    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }
}
