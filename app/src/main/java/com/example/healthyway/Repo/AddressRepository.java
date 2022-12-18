package com.example.healthyway.Repo;

import androidx.annotation.NonNull;

import com.example.healthyway.Entities.AddressEntity;
import com.example.healthyway.util.Resource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddressRepository {
    private final AddressDao addressDao;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("address");
    private List<AddressEntity> address;

    public AddressRepository(AddressDao addressDao) {
        this.addressDao = addressDao;
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                address = new ArrayList<>();
                if(snapshot.exists()) {
                    for (DataSnapshot i:snapshot.getChildren()
                    ) {
                        AddressEntity itm = i.getValue(AddressEntity.class);
                        address.add(itm);
                    }
                    new Resource.Success(address, "Data fetched successfully");
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

    public List<AddressEntity> getAllAddress(){
        return addressDao.getAllAddress();
    }

    public void insertAddress(AddressEntity address){
        addressDao.insertAddress(address);
    }

    public List<AddressEntity> searchDatabase(String searchQuery){
        return addressDao.searchAddress(searchQuery);
    }
}