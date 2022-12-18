package com.example.healthyway.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.healthyway.Entities.DietPlanEntity;
import com.example.healthyway.Repo.DietPlanDao;

@Database(entities = DietPlanEntity.class,
        version = 1)
abstract class DietPlanDatabase extends RoomDatabase {
    abstract DietPlanDao getDietPlanDao();
}
