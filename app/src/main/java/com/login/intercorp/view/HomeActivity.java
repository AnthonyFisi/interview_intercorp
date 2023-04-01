package com.login.intercorp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.login.intercorp.R;
import com.login.intercorp.model.UserModel;
import com.login.intercorp.view.adapter.UserAdapter;
import com.login.intercorp.viewmodel.RegisterViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private UserModel userModel;
    private TextView nameText;
    private TextView ageText;
    private TextView birthdateText;
    private RecyclerView listUserRecyclerView;
    private RegisterViewModel registerViewModel;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get data from the last activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userModel = (UserModel) getIntent().getSerializableExtra("user");
        }

        // Declare fields
        nameText = findViewById(R.id.name_lastname);
        ageText = findViewById(R.id.age);
        birthdateText = findViewById(R.id.birthdate);
        listUserRecyclerView = findViewById(R.id.listUsers);

        // Fill data inside fields
        nameText.setText(userModel.getName()+" "+ userModel.getLastName());
        ageText.setText(String.valueOf(userModel.getAge())+" years old");
        birthdateText.setText(userModel.getBirthdate());


        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        registerViewModel.getListUserModelLiveData().observe(this, userModels -> {
            UserAdapter userAdapter = new UserAdapter(userModels);
            listUserRecyclerView.setHasFixedSize(true);
            listUserRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            listUserRecyclerView.setAdapter(userAdapter);
        });

        registerViewModel.getListUserModel();
    }
}