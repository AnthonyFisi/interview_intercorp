package com.example.intercorp.viewmodel;

import android.app.Activity;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.intercorp.repository.LoginRepository;
import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {
    private LoginRepository loginRepository;
    private MutableLiveData<FirebaseUser> userLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository();
        userLiveData = loginRepository.getUserLiveData();
    }

    public void login(CallbackManager mCallbackManager, Activity activity) {
        loginRepository.login(mCallbackManager,activity);
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }
}
