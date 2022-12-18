package com.example.healthyway.Repo;

import androidx.annotation.NonNull;

import com.example.healthyway.Entities.DietitianEntity;
import com.example.healthyway.Entities.UserEntity;
import com.example.healthyway.util.Resource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DietitianRepository {
    private final DietitianDao dietitianDao;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("dietitian");
    private List<DietitianEntity> dietitians;

    public DietitianRepository(DietitianDao dietitianDao) {
        this.dietitianDao = dietitianDao;
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dietitians = new ArrayList<>();
                if(snapshot.exists()) {
                    for (DataSnapshot i:snapshot.getChildren()
                    ) {
                        DietitianEntity itm = i.getValue(DietitianEntity.class);
                        dietitians.add(itm);
                    }
                    new Resource.Success(dietitians, "Data fetched successfully");
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

    public List<DietitianEntity> getAllDietitian(){
        return dietitianDao.getAllDietitian();
    }

    public void insertDietitian(DietitianEntity dietitian){
        dietitianDao.insertDietitian(dietitian);
    }

    public List<DietitianEntity> searchDatabase(String searchQuery){
        return dietitianDao.searchDietitian(searchQuery);
    }
}
