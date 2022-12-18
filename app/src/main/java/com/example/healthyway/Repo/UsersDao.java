package com.example.healthyway.Repo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.healthyway.Entities.UserEntity;

import java.util.List;

@Dao
public interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertUser(UserEntity user);

    @Query("SELECT * FROM user ORDER BY id ASC")
    public List<UserEntity> getAllUsers();

    @Query("SELECT * FROM user WHERE email LIKE :searchQuery")
    public List<UserEntity> searchUser(String searchQuery);
}
