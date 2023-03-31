package com.login.intercorp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.widget.Button;
import com.login.intercorp.model.UserModel;
import com.login.intercorp.view.HomeActivity;
import com.login.intercorp.view.RegisterActivity;
import com.login.intercorp.viewmodel.LoginViewModel;
import com.login.intercorp.viewmodel.RegisterViewModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
public class MainActivity extends AppCompatActivity {
    private CallbackManager mCallbackManager;
    private LoginViewModel loginViewModel;
    private RegisterViewModel registerViewModel;
    private Intent intent;
    private  UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Response of registerViewModel if a user exist or not
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        registerViewModel.getUserModelLiveData().observe(this, userModelData -> {
            if(userModelData == null){
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra("user", userModel);

            }else{
                intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("user", userModelData);
            }
            startActivity(intent);

        });

        // ViewModel for retrieve data from login by facebook and then validate if the user exist or not
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getUserLiveData().observe(this, firebaseUser -> {

            if(firebaseUser != null){
                //Data from firebase authentication
                userModel = new UserModel(
                        firebaseUser.getUid(),
                        firebaseUser.getDisplayName(),
                        "",
                        0,
                        ""
                        );

                // ViewModel for validate if user was registered or not
                registerViewModel.validateUser(firebaseUser.getUid());
            }
        });

        // Setting to login by facebook
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        Button loginFacebook = findViewById(R.id.loginFacebook);
        loginFacebook.setOnClickListener(view -> loginViewModel.login(mCallbackManager,MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}

