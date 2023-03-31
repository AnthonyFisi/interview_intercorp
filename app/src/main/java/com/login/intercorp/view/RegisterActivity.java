package com.login.intercorp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.login.intercorp.R;
import com.login.intercorp.model.UserModel;
import com.login.intercorp.view.dialog.DatePickerFragment;
import com.login.intercorp.viewmodel.RegisterViewModel;


public class RegisterActivity extends AppCompatActivity {
    private UserModel userModel;
    private EditText birthdateEditText;
    private EditText nameEditText;
    private EditText lastNameEditText;
    private EditText ageEditText;
    private Button buttonRegister;
    private RegisterViewModel registerViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Get data from the last activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userModel = (UserModel) getIntent().getSerializableExtra("user");
        }

        // Declare fields
        nameEditText = findViewById(R.id.name);
        lastNameEditText = findViewById(R.id.lastName);
        ageEditText = findViewById(R.id.age);
        birthdateEditText = findViewById(R.id.birthdate);
        buttonRegister = findViewById(R.id.register);

        // Fill data inside fields
        nameEditText.setText(userModel.getName());

        // Pop up to get birthdate
        birthdateEditText.setOnClickListener(view -> showDatePickerDialog());

        // Response to event register
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        registerViewModel.getRegisterLiveData().observe(this, aBoolean -> {
            if(aBoolean){
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                intent.putExtra("user", userModel);
                startActivity(intent);
            }else{
                Toast.makeText(this, "We're presenting problems, try again!!", Toast.LENGTH_SHORT).show();
            }
        });

        // Register a user to firebase
        buttonRegister.setOnClickListener(view -> {
            userModel.setName(nameEditText.getText().toString());
            userModel.setLastName(lastNameEditText.getText().toString());
            userModel.setAge(Integer.parseInt(ageEditText.getText().toString()));
            userModel.setBirthdate(birthdateEditText.getText().toString());

            registerViewModel.registerUser(userModel);
        });



    }


    // Get data from pop up date picker and edit a birthdateEditText
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = day + " / " + (month+1) + " / " + year;
            birthdateEditText.setText(selectedDate);
        });
        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }
}