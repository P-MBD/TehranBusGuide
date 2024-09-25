package com.example.bustehran;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tbl_Information")
public class Information {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;

    @ColumnInfo(name = "Id_Station")
    private Integer idStation;

    @ColumnInfo(name = "day")
    private String day;

    @ColumnInfo(name = "Time")
    private String time;

    // Constructors, Getters and Setters
    public Information() {}

    public Information(Integer idStation, String day, String time) {
        this.idStation = idStation;
        this.day = day;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdStation() {
        return idStation;
    }

    public void setIdStation(Integer idStation) {
        this.idStation = idStation;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
