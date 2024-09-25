package com.example.bustehran;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Tbl_Stations")
public class Station {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    public Integer id;

    @ColumnInfo(name = "Title")
    public String title;

    @ColumnInfo(name = "EnglishTitle")
    public String englishTitle;

    @ColumnInfo(name = "Line")
    public Integer line; // Ensure this is an Integer, not an int

    @ColumnInfo(name = "Address")
    public String address;

    @ColumnInfo(name = "Lat")
    public String lat;

    @ColumnInfo(name = "Lang")
    public String lang;

    @ColumnInfo(name = "Description")
    public String description;

    public String getTitle() {
        return title; // Ensure this method is present
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
