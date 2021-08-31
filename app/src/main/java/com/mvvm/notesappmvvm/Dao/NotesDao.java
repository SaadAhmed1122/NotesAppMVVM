package com.mvvm.notesappmvvm.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.mvvm.notesappmvvm.Model.Notes;

import java.util.List;

@androidx.room.Dao
public
interface NotesDao {

    @Query("SELECT * from Notes_database")
    LiveData<List<Notes>> getAllnotes();


    @Query("SELECT * from Notes_database ORDER BY notes_prority DESC")
    LiveData<List<Notes>> hightolow();


    @Query("SELECT * from Notes_database ORDER BY notes_prority ASC")
    LiveData<List<Notes>> lowtohigh();

    @Insert
    void insertNotes(Notes...notes);

    @Query("Delete from Notes_database where id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
