package com.example.intercorp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.intercorp.model.UserModel;
import com.example.intercorp.repository.RegisterRepository;

public class RegisterViewModel extends AndroidViewModel {
    private RegisterRepository registerRepository;

    private MutableLiveData<Boolean> registerLiveData;

    private MutableLiveData<UserModel> userModelLiveData;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        registerRepository = new RegisterRepository();
        userModelLiveData = registerRepository.getUserModelLiveData();
        registerLiveData = registerRepository.getRegisterLiveData();

    }

    public void registerUser(UserModel userModel){
        registerRepository.registerUser(userModel);
    }

    public void validateUser(String id){
        registerRepository.validateUser(id);
    }

    public MutableLiveData<Boolean> getRegisterLiveData(){
        return registerLiveData;
    }

    public MutableLiveData<UserModel> getUserModelLiveData(){
        return userModelLiveData;
    }
}
