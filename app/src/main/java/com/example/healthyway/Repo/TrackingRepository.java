package com.example.healthyway.Repo;

import androidx.annotation.NonNull;

import com.example.healthyway.Entities.TrackingEntity;
import com.example.healthyway.util.Resource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrackingRepository {
    private final TrackingDao trackingDao;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("tracking");
    private List<TrackingEntity> tracking;

    public TrackingRepository(TrackingDao trackingDao) {
        this.trackingDao = trackingDao;
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tracking = new ArrayList<>();
                if(snapshot.exists()) {
                    for (DataSnapshot i:snapshot.getChildren()
                    ) {
                        TrackingEntity itm = i.getValue(TrackingEntity.class);
                        tracking.add(itm);
                    }
                    new Resource.Success(tracking, "Data fetched successfully");
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

    public List<TrackingEntity> getAllTracking(){
        return trackingDao.getAllTracking();
    }

    public void insertTracking(TrackingEntity tracking){
        trackingDao.insertTracking(tracking);
    }

    public List<TrackingEntity> searchDatabase(String searchQuery){
        return trackingDao.searchTracking(searchQuery);
    }
}