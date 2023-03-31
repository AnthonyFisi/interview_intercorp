package com.example.intercorp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.intercorp.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterRepository {

    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference databaseReference;
    private MutableLiveData<Boolean> registerLiveData;

    private MutableLiveData<UserModel> userModelLiveData;

    public RegisterRepository(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        String DATABASE_NAME = "User";
        databaseReference = firebaseDatabase.getReference(DATABASE_NAME);
        this.registerLiveData = new MutableLiveData<>();
        this.userModelLiveData = new MutableLiveData<>();
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

    public MutableLiveData<Boolean> getRegisterLiveData(){
        return registerLiveData;
    }

    public MutableLiveData<UserModel> getUserModelLiveData(){
        return userModelLiveData;
    }
}
