package com.example.bae.ui.Login_SignUp.SignUp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.bae.Interface.replaceFragement;
import com.example.bae.R;
import com.example.bae.data.User.Clients;
import com.example.bae.ui.Login_SignUp.CheckCodeOTP.CheckCodeOtpFragment;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Arrays;

public class SignUpFragment extends Fragment implements replaceFragement {

    private SignUpViewModel mViewModel;

    private EditText selectDay , fullname , cid , address , email ;
    private RadioGroup radioGroupGender ;
    private Button scanQR, signUp;
    private Clients client ;
    private View view ;


    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        selectDay = view.findViewById(R.id.et_signup_dob) ;
        fullname = view.findViewById(R.id.et_signup_fullname);
        cid = view.findViewById(R.id.et_signup_cid);
        address = view.findViewById(R.id.et_signup_address);
        email = view.findViewById(R.id.et_signup_email);
        radioGroupGender = view.findViewById(R.id.rg_signup_gender);
        scanQR = view.findViewById(R.id.btn_signup_scanQR);
        signUp = view.findViewById(R.id.btn_login_signIn);

        client = new Clients();

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

                client.setEmail(String.valueOf(email.getText()));
                replaceFragement(new CheckCodeOtpFragment(client) , getActivity().getSupportFragmentManager());
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        // TODO: Use the ViewModel
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
                client.setGender("Male");
            }
            else {
                radioGroupGender.check(R.id.r_signup_female);
                client.setGender("Female");
            }

            client.setName(results[2]);
            client.setCccd(results[0]);
            client.setDob(dob[0]+dob[1]+"/" +dob[2]+dob[3]+"/"+dob[4]+dob[5]+dob[6]+dob[7]);
            client.setAddress(results[5]);

        }
    });



}