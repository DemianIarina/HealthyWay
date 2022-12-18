package com.example.healthyway.Repo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.healthyway.Entities.AddressEntity;
import java.util.List;

@Dao
public interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertAddress(AddressEntity address);
    @Query("SELECT * FROM address ORDER BY id ASC")
    public List<AddressEntity> getAllAddress();

    @Query("SELECT * FROM address WHERE city LIKE :searchQuery")
    public List<AddressEntity> searchAddress(String searchQuery);
}
