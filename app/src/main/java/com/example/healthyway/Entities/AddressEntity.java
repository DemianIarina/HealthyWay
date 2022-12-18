package com.example.healthyway.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "address")
public class AddressEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String country;
    private String city;
    private int number;
    private int apartment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }
}
