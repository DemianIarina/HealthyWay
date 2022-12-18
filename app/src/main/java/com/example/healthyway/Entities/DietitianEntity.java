package com.example.healthyway.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dietitian")
public class DietitianEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String family_name;
    private int addressId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
