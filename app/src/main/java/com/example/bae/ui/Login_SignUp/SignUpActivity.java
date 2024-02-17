package com.example.bae.ui.Login_SignUp;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import com.example.bae.Interface.replaceFragement;
import com.example.bae.R;

import com.example.bae.ui.Login_SignUp.SignUp.SignUpFragment;


public class SignUpActivity extends AppCompatActivity implements replaceFragement {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        replaceFragement(new SignUpFragment() , getSupportFragmentManager());
    }





}