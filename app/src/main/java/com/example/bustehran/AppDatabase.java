package com.example.bustehran;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Station.class, Information.class}, version = 2, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract StationDao stationDao();
    public abstract InformationDao informationDao(); // اضافه کردن این خط
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "Bus.db")
                            .createFromAsset("Bus.db")  // استفاده از این متد برای کپی از assets
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
