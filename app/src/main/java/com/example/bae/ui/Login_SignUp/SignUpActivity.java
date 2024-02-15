package com.example.bae.ui.Login_SignUp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bae.R;
import com.example.bae.data.User.Clients;
import com.example.bae.data.User.UserRequest;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity {

    private EditText selectDay , fullname , cid , address , email ;
    private RadioGroup radioGroupGender ;
    private Button scanQR, signUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        selectDay = findViewById(R.id.et_signup_dob) ;
        fullname = findViewById(R.id.et_signup_fullname);
        cid = findViewById(R.id.et_signup_cid);
        address = findViewById(R.id.et_signup_address);
        email = findViewById(R.id.et_signup_email);
        radioGroupGender = findViewById(R.id.rg_signup_gender);
        scanQR = findViewById(R.id.btn_signup_scanQR);
        signUp = findViewById(R.id.btn_signUp);

        setEdittextReadOnly(selectDay);
        setEdittextReadOnly(fullname);
        setEdittextReadOnly(cid);
        setEdittextReadOnly(address);




        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQR();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clients client = new Clients(getApplicationContext());
                client.signUp(email.getText().toString().trim());
            }
        });


    }

    private void setEdittextReadOnly(EditText editTexts){

            editTexts.setTag(editTexts.getKeyListener());
            editTexts.setKeyListener(null);

    }


    private void scanQR(){
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("Hãy quét mã QR trên căn cước công dân") ;
        options.setCameraId(0) ;
        options.setBeepEnabled(true) ;
        options.setOrientationLocked(true) ;
        options.setCaptureActivity(CaptureActivity.class);
        barLaucher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract() , result->{
        if(result.getContents() != null){

            String[] results = result.getContents().split("\\|") ;
            fullname.setText(results[2]);
            cid.setText(results[0]);

            String[] dob = results[3].split("");
            Log.d("aaa" , Arrays.toString(dob));
            selectDay.setText(dob[0]+dob[1]+"/" +dob[2]+dob[3]+"/"+dob[4]+dob[5]+dob[6]+dob[7] );

            address.setText(results[5]);

            if(results[4].equals("Nam")){
                radioGroupGender.check(R.id.r_signup_male);
            }
            else {
                radioGroupGender.check(R.id.r_signup_female);
            }


        }
    });
}