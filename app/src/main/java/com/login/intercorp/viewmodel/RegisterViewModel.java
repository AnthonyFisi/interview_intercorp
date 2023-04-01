package com.login.intercorp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.login.intercorp.model.UserModel;
import com.login.intercorp.repository.RegisterRepository;

import java.util.List;

public class RegisterViewModel extends AndroidViewModel {
    private RegisterRepository registerRepository;

    private MutableLiveData<Boolean> registerLiveData;

    private MutableLiveData<UserModel> userModelLiveData;

    private MutableLiveData<List<UserModel>> listUserModelLiveData;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        registerRepository = new RegisterRepository();
        userModelLiveData = registerRepository.getUserModelLiveData();
        registerLiveData = registerRepository.getRegisterLiveData();
        listUserModelLiveData = registerRepository.getListUserModelLiveData();

    }

    public void registerUser(UserModel userModel){
        registerRepository.registerUser(userModel);
    }

    public void validateUser(String id){
        registerRepository.validateUser(id);
    }

    public void getListUserModel(){
        registerRepository.getUsers();
    }

    public MutableLiveData<Boolean> getRegisterLiveData(){
        return registerLiveData;
    }

    public MutableLiveData<UserModel> getUserModelLiveData(){
        return userModelLiveData;
    }

    public MutableLiveData<List<UserModel>> getListUserModelLiveData(){
        return listUserModelLiveData;
    }
}
