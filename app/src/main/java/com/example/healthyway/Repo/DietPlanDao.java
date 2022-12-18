package com.example.healthyway.Repo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.healthyway.Entities.DietPlanEntity;
import java.util.List;

@Dao
public interface DietPlanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertDietPlan(DietPlanEntity dietPlan);
    @Query("SELECT * FROM dietPlan ORDER BY id ASC")
    public List<DietPlanEntity> getAllDietPlan();

    @Query("SELECT * FROM dietPlan WHERE idUser LIKE :searchQuery")
    public List<DietPlanEntity> searchDietPlan(String searchQuery);
}