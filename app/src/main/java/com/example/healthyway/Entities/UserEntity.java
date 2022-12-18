package com.example.healthyway.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String password;
    private int dietitianId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDietitianId() {
        return dietitianId;
    }

    public void setDietitianId(Integer dietitianId) {
        this.dietitianId = dietitianId;
    }
}
