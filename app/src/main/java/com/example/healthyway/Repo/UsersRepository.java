package com.example.healthyway.Repo;

import androidx.annotation.NonNull;

import com.example.healthyway.Entities.UserEntity;
import com.example.healthyway.util.Resource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private final UsersDao userDao;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
    private List<UserEntity> users;

    public UsersRepository(UsersDao userDao) {
        this.userDao = userDao;
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users = new ArrayList<>();
                if(snapshot.exists()) {
                    for (DataSnapshot i:snapshot.getChildren()
                    ) {
                        UserEntity itm = i.getValue(UserEntity.class);
                        users.add(itm);
                    }
                    new Resource.Success(users, "Data fetched successfully");
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

    public List<UserEntity> getAllUser(){
        return userDao.getAllUsers();
    }

    public void insertUser(UserEntity user){
        userDao.insertUser(user);
    }

    public List<UserEntity> searchDatabase(String searchQuery){
        return userDao.searchUser(searchQuery);
    }
}
