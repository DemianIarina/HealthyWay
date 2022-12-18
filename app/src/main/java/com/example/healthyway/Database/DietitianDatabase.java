package com.example.healthyway.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.healthyway.Entities.DietitianEntity;
import com.example.healthyway.Repo.DietitianDao;

@Database(entities = DietitianEntity.class,
        version = 1)
abstract class DietitianDatabase extends RoomDatabase {
    abstract DietitianDao getDietitianDao();
}
