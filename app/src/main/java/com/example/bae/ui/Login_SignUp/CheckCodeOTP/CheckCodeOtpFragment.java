package com.example.bae.ui.Login_SignUp.CheckCodeOTP;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bae.R;
import com.example.bae.data.User.Clients;
import com.example.bae.ui.Login_SignUp.SignUpActivity;

import java.util.Random;

public class CheckCodeOtpFragment extends Fragment  {

    private CheckCodeOtpViewModel mViewModel;
    private TextView textView ;
    private View view ;
    private Clients clients ;

    private EditText digit1, digit2, digit3, digit4 ;
    private Button confirm ;
    public CheckCodeOtpFragment(Clients clients) {
        this.clients = clients ;
    }


    public static CheckCodeOtpFragment newInstance(Clients clients) {
        return new CheckCodeOtpFragment(clients);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_check_code_otp, container, false);

        digit1 = view.findViewById(R.id.et_checkOTP_digit1) ;
        digit2 = view.findViewById(R.id.et_checkOTP_digit2) ;
        digit3 = view.findViewById(R.id.et_checkOTP_digit3) ;
        digit4 = view.findViewById(R.id.et_checkOTP_digit4) ;
        confirm = view.findViewById(R.id.btn_checkOTP_check) ;
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


        String code = (createOTP()) ;
        sendOTP(code);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeOTPConfirm = digit1.getText().toString() + digit2.getText().toString() + digit3.getText().toString() +digit4.getText().toString() ;
                if(code.equals(codeOTPConfirm)){
                    clients.signUp(getContext());
                    Toast.makeText(getContext() , "Sucessful" , Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext() , "Not Sucessful" , Toast.LENGTH_LONG).show();
                }
            }
        });

        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CheckCodeOtpViewModel.class);
        // TODO: Use the ViewModel
    }

    private String createOTP(){
        Random random = new Random();
        String code = random.nextInt(8999) + 1000 +"" ;
        return code ;
    }
    private void sendOTP (String codeOTP){
        clients.sendOtp(codeOTP , getContext());
    }

}