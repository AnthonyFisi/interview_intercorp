package com.login.intercorp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.login.intercorp.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RegisterRepository {

    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference databaseReference;
    private MutableLiveData<Boolean> registerLiveData;
    private MutableLiveData<UserModel> userModelLiveData;

    private MutableLiveData<List<UserModel>> listUserModelLiveData;

    public RegisterRepository(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        String DATABASE_NAME = "User";
        databaseReference = firebaseDatabase.getReference().child(DATABASE_NAME);
        this.registerLiveData = new MutableLiveData<>();
        this.userModelLiveData = new MutableLiveData<>();
        this.listUserModelLiveData = new MutableLiveData<>();
    }

    public void registerUser(UserModel userModel){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference
                        .child(userModel.getId())
                        .setValue(
                                new UserModel(
                                        userModel.getId(),
                                        userModel.getName(),
                                        userModel.getLastName(),
                                        userModel.getAge(),
                                        userModel.getBirthdate())
                        );
                registerLiveData.postValue(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                registerLiveData.postValue(false);
            }
        });
    }

    public void validateUser(String id){
        databaseReference.child(id).get().addOnCompleteListener(task -> {
            if (task.getResult().getValue() == null) {
                userModelLiveData.postValue(null);
            }
            else {
                UserModel userModel = new UserModel(
                        task.getResult().child("id").getValue().toString(),
                        task.getResult().child("name").getValue().toString(),
                        task.getResult().child("lastName").getValue().toString(),
                        Integer.parseInt(task.getResult().child("age").getValue().toString()),
                        task.getResult().child("birthdate").getValue().toString()
                );
                userModelLiveData.postValue(userModel);
            }
        });
    }

    public void getUsers(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<UserModel> userModelList = new ArrayList<>();
                for(DataSnapshot postSnapShot:snapshot.getChildren())
                {
                    HashMap<String, String> data = (HashMap<String, String>) postSnapShot.getValue();
                    String name = data.get("name");
                    String lastName = data.get("lastName");
                    String birthdate = data.get("birthdate");
                    String id = data.get("id");
                    UserModel userModel = new UserModel(id,name,lastName,0,birthdate);
                    userModelList.add(userModel);
                }
                listUserModelLiveData.postValue(userModelList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listUserModelLiveData.postValue(Collections.emptyList());
            }
        });
    }


    public MutableLiveData<Boolean> getRegisterLiveData(){
        return registerLiveData;
    }
    public MutableLiveData<UserModel> getUserModelLiveData(){
        return userModelLiveData;
    }
    public MutableLiveData<List<UserModel>> getListUserModelLiveData(){
        return listUserModelLiveData;}
}
