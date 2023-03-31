package com.login.intercorp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import com.login.intercorp.R;
import com.login.intercorp.model.UserModel;

public class HomeActivity extends AppCompatActivity {
    private UserModel userModel;
    private TextView nameText;
    private TextView ageText;
    private TextView birthdateText;


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

        // Fill data inside fields
        nameText.setText(userModel.getName()+" "+ userModel.getLastName());
        ageText.setText(String.valueOf(userModel.getAge())+" years old");
        birthdateText.setText(userModel.getBirthdate());

    }
}