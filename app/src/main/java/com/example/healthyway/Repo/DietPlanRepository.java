package com.example.healthyway.Repo;

import androidx.annotation.NonNull;

import com.example.healthyway.Entities.AddressEntity;
import com.example.healthyway.Entities.DietPlanEntity;
import com.example.healthyway.util.Resource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DietPlanRepository {
    private final DietPlanDao dietPlanDao;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("dietPlan");
    private List<DietPlanEntity> dietPlan;

    public DietPlanRepository(DietPlanDao dietPlanDao) {
        this.dietPlanDao = dietPlanDao;
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dietPlan = new ArrayList<>();
                if(snapshot.exists()) {
                    for (DataSnapshot i:snapshot.getChildren()
                    ) {
                        DietPlanEntity itm = i.getValue(DietPlanEntity.class);
                        dietPlan.add(itm);
                    }
                    new Resource.Success(dietPlan, "Data fetched successfully");
                }
                else{
                    new Resource.Error(null, "Unknown error occurred");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public List<DietPlanEntity> getAllDietPlan(){
        return dietPlanDao.getAllDietPlan();
    }

    public void insertDietPlan(DietPlanEntity dietPlan){
        dietPlanDao.insertDietPlan(dietPlan);
    }

    public List<DietPlanEntity> searchDatabase(String searchQuery){
        return dietPlanDao.searchDietPlan(searchQuery);
    }
}
