package com.example.healthyway.Repo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.healthyway.Entities.TrackingEntity;
import java.util.List;

@Dao
public interface TrackingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertTracking(TrackingEntity tracking);

    @Query("SELECT * FROM tracking ORDER BY id ASC")
    public List<TrackingEntity> getAllTracking();

    @Query("SELECT * FROM tracking WHERE idUser LIKE :searchQuery")
    public List<TrackingEntity> searchTracking(String searchQuery);
}