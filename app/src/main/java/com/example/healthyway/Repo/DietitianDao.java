package com.example.healthyway.Repo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.healthyway.Entities.DietitianEntity;
import java.util.List;

@Dao
public interface DietitianDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertDietitian(DietitianEntity dietitian);
    @Query("SELECT * FROM dietitian ORDER BY id ASC ")
    public List<DietitianEntity> getAllDietitian();

    @Query("SELECT * FROM dietitian WHERE family_name LIKE :searchQuery")
    public List<DietitianEntity> searchDietitian(String searchQuery);
}
