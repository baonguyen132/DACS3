package com.example.bae.ui.CheckCodeOTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bae.R;
import com.example.bae.data.User.Clients;
import com.example.bae.data.User.UserData;
import com.example.bae.data.Voucher.VoucherData;
import com.example.bae.data.Voucher.VoucherOfUser;

import java.util.Random;

public class CheckCodeOPTActivity extends AppCompatActivity {



    private TextView textView ;
    private EditText digit1, digit2, digit3, digit4 ;
    private Button confirm ;

    private String code ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_code_opt);
        setUp();

        Intent intent = getIntent() ;
        String messenger =  intent.getStringExtra("messenger") ;

        code = (createOTP()) ;

        switch (messenger){
            case "signup": {
                Clients clients = (Clients) intent.getSerializableExtra("data");
                sendOTP(code , clients);
                confirmOTP(new HandleConfirmOTP() {
                    @Override
                    public void handle() {

                        clients.signUp(CheckCodeOPTActivity.this);
                        Toast.makeText(getApplicationContext() , "Sucessful" , Toast.LENGTH_LONG).show();                    }
                });
                break;
            }

            case "changeVoucher": {

                UserData userData = (UserData) intent.getSerializableExtra("data");
                VoucherData voucherData = (VoucherData) intent.getSerializableExtra("voucher");
                sendOTP(code , userData);
                confirmOTP(new HandleConfirmOTP() {
                    @Override
                    public void handle() {
                        userData.changeVoucher(voucherData, new UserData.HandleRequest() {
                            @Override
                            public void handle(String respone) {
                                if(respone.equals("sucessful")){
                                    VoucherOfUser.putItemVoucher(voucherData);
                                    Toast.makeText(getApplicationContext() , "Đã đổi voucher" , Toast.LENGTH_LONG ).show();
                                }
                                else if(respone.equals("not_sucessful")){
                                    Toast.makeText(getApplicationContext() , "Bạn không đủ điểm" , Toast.LENGTH_LONG ).show();
                                }
                                finish();
                            }
                        });
                    }
                });

                break;
            }

            default: {
                break;
            }
        }
    }

    private void setUp(){
        digit1 = findViewById(R.id.et_checkOTP_digit1) ;
        digit2 = findViewById(R.id.et_checkOTP_digit2) ;
        digit3 = findViewById(R.id.et_checkOTP_digit3) ;
        digit4 = findViewById(R.id.et_checkOTP_digit4) ;
        confirm = findViewById(R.id.btn_checkOTP_check) ;
        digit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    digit2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        digit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    digit3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        digit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    digit4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void sendOTP (String codeOTP , UserData userData){
        userData.sendOtp(codeOTP , getApplicationContext());
    }
    private String createOTP(){
        Random random = new Random();
        String code = random.nextInt(8999) + 1000 +"" ;
        return code ;
    }


    private void confirmOTP(HandleConfirmOTP handleConfirmOTP){
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeOTPConfirm = digit1.getText().toString() + digit2.getText().toString() + digit3.getText().toString() +digit4.getText().toString() ;
                if(code.equals(codeOTPConfirm)){
                    handleConfirmOTP.handle();
                }
                else {
                    Toast.makeText(getApplicationContext() , "Mã OTP đúng" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    interface HandleConfirmOTP{
        void handle();
    }
}
