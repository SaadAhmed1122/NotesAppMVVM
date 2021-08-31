package com.mvvm.notesappmvvm.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_database")
public
class Notes {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_title")
    public String notesTitle;
    @ColumnInfo(name = "notes_subtitle")
    public String notesSubTitle;
    @ColumnInfo(name = "notes_prority")
    public String notesprority;
    @ColumnInfo(name = "notes_date")
    public String notesDate;
    @ColumnInfo(name = "notes")
    public String notes;

}
