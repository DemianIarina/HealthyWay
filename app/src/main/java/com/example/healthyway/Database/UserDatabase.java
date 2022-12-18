package com.example.healthyway.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.healthyway.Entities.UserEntity;
import com.example.healthyway.Repo.UsersDao;

@Database(entities = UserEntity.class,
            version = 1)
abstract class UserDatabase extends RoomDatabase{
    abstract UsersDao getUsersDao();
}
