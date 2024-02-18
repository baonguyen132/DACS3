package com.example.bae.ui.Login_SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bae.Interface.fingerprintAuthentication;
import com.example.bae.MainActivity;
import com.example.bae.R;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.data.User.UserRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements fingerprintAuthentication {

    private EditText email , password ;
    private Button signUp ;

    private UserRequest userRequest ;
    private ImageView fingerPrint ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.et_login_email);
        password = findViewById(R.id.et_login_password);
        signUp = findViewById(R.id.btn_login_signIn);
        fingerPrint = findViewById(R.id.iv_login_signIn);

        userRequest = new UserRequest(getApplicationContext());

        email.setText(DataLocalManager.getUsename());

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = email.getText().toString() ;
                String textPassword = password.getText().toString();
                login(textEmail , textPassword);
            }
        });

        fingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFirstLogin()){
                    figerAuthen(LoginActivity.this, new Succeeded() {
                        @Override
                        public void Succeededs() {
                            Toast.makeText(getApplicationContext() , "Đang thực hiện đăng nhập" , Toast.LENGTH_LONG).show();
                            login(DataLocalManager.getUsename() , DataLocalManager.getPassword());
                        }
                    }, new Error() {
                        @Override
                        public void Errors() {

                        }
                    }, new Failed() {
                        @Override
                        public void Faileds() {

                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext() , "Bạn cần đăng nhập lần đầu" , Toast.LENGTH_LONG).show();
                }
            }
        });



    }



    private void login(String email , String password){
        userRequest.checkAccount(email, password, new UserRequest.HandleResponeString() {
            @Override
            public void handleResponeString(String response) throws JSONException {
                int id = Integer.parseInt(response) ;
                if(id != 0){
                    if(isFirstLogin()){
                        saveEmailandPassword(email , password);
                    }
                    saveDataFromServe(id);
                }
                else {


                }
            }
        });
    }

    private void saveDataFromServe(int id){
        userRequest.getDataFromServe(id, new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                JSONObject jsonObject = response.getJSONObject("data");
                UserData userData = new UserData(
                        jsonObject.getString("id"),
                        jsonObject.getString("email"),
                        jsonObject.getString("name"),
                        jsonObject.getString("cccd"),
                        jsonObject.getString("dob"),
                        jsonObject.getString("gender"),
                        jsonObject.getString("pob"),
                        jsonObject.getString("address"),
                        jsonObject.getInt("status"),
                        jsonObject.getInt("point")
                );
                DataLocalManager.setUser(userData);
                loginSuceessful();
            }
        });

    }

    private void loginSuceessful(){
        Toast.makeText(getApplicationContext() , R.string.loign_succeful , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(intent);

    }
    private boolean isFirstLogin(){
        String user = DataLocalManager.getUsename() ;
        if(user.equals("")){
            return true ;
        }
        return false ;

    }

    private void saveEmailandPassword (String textEmail , String textPassword) {
        DataLocalManager.setUsename(textEmail);
        DataLocalManager.setPassword(textPassword);
    }


}