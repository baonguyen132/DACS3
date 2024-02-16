package com.example.bae.ui.Login_SignUp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bae.Interface.replaceFragement;
import com.example.bae.R;
import com.example.bae.data.User.Clients;
import com.example.bae.data.User.UserRequest;
import com.example.bae.ui.Login_SignUp.CheckCodeOTP.CheckCodeOtpFragment;
import com.example.bae.ui.Login_SignUp.SignUp.SignUpFragment;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class SignUpActivity extends AppCompatActivity implements replaceFragement {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        replaceFragement(new SignUpFragment() , getSupportFragmentManager());
    }





}