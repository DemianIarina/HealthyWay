package com.example.healthyway.Database;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

import com.example.healthyway.Entities.TrackingEntity;
import com.example.healthyway.Repo.TrackingDao;

@Database(entities = TrackingEntity.class,
        version = 1)
abstract  class TrackingDatabase extends RoomDatabase {
    abstract TrackingDao getTrackingDao();
}
