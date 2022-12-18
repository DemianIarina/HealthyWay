package com.example.healthyway.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.healthyway.Entities.AddressEntity;
import com.example.healthyway.Entities.DietitianEntity;
import com.example.healthyway.Repo.AddressDao;

@Database(entities = AddressEntity.class,
        version = 1)
abstract class AddressDatabase extends RoomDatabase {
    abstract AddressDao getAddressDao();
}
