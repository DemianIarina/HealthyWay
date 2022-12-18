package com.example.healthyway.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dietPlan")
public class DietPlanEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int steps;
    private float kcal;
    private int idUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
