package com.example.bae.ui.Login_SignUp;


import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


import com.example.bae.Interface.replaceFragement;
import com.example.bae.R;

import com.example.bae.data.User.Clients;

import com.example.bae.ui.CheckCodeOTP.CheckCodeOPTActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;


public class SignUpActivity extends AppCompatActivity   {
    private EditText selectDay , fullname , cid , address , email, password ;
    private RadioGroup radioGroupGender ;
    private Button scanQR, signUp;
    private Clients client ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
        setUp();

        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQR();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                client.setEmail(String.valueOf(email.getText()));
                client.setPassword(String.valueOf(password.getText()));

                finish();
                Intent intent = new Intent(SignUpActivity.this , CheckCodeOPTActivity.class);
                intent.putExtra("data" , client);
                intent.putExtra("messenger" , "signup");
                startActivity(intent);
            }
        });
        

    }


    private void setUp(){
        selectDay = findViewById(R.id.et_signup_dob) ;
        fullname = findViewById(R.id.et_signup_fullname);
        cid = findViewById(R.id.et_signup_cid);
        address = findViewById(R.id.et_signup_address);
        email = findViewById(R.id.et_signup_email);
        password = findViewById(R.id.et_signup_password);
        radioGroupGender = findViewById(R.id.rg_signup_gender);
        scanQR = findViewById(R.id.btn_signup_scanQR);
        signUp = findViewById(R.id.btn_login_signIn);

        client = new Clients();

        setEdittextReadOnly(selectDay);
        setEdittextReadOnly(fullname);
        setEdittextReadOnly(cid);
        setEdittextReadOnly(address);
    }

    private void setEdittextReadOnly(EditText editTexts){

        editTexts.setTag(editTexts.getKeyListener());
        editTexts.setKeyListener(null);

    }

    private void scanQR(){
        client.scanQR(barLaucher);
    }
    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract() , result->{
        if(result.getContents() != null){

            String[] results = result.getContents().split("\\|") ;
            fullname.setText(results[2]);
            cid.setText(results[0]);

            String[] dob = results[3].split("");

            selectDay.setText(dob[4]+dob[5]+dob[6]+dob[7]+"/" +dob[2]+dob[3]+"/"+dob[0]+dob[1] );

            address.setText(results[5]);

            if(results[4].equals("Nam")){
                radioGroupGender.check(R.id.r_signup_male);
                client.setGender("Male");
            }
            else {
                radioGroupGender.check(R.id.r_signup_female);
                client.setGender("Female");
            }

            client.setName(results[2]);
            client.setCccd(results[0]);
            client.setDob(dob[4]+dob[5]+dob[6]+dob[7]+"/" +dob[2]+dob[3]+"/"+dob[0]+dob[1]);
            client.setAddress(results[5]);

        }
    });

}