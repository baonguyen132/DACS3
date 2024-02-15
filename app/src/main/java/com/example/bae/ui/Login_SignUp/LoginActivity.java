package com.example.bae.ui.Login_SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bae.MainActivity;
import com.example.bae.R;
import com.example.bae.data.User.UserRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText email , password ;
    private Button signUp ;

    FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.et_login_email);
        password = findViewById(R.id.et_login_password);
        signUp = findViewById(R.id.btn_signUp);
        mAuth = FirebaseAuth.getInstance() ;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = email.getText().toString() ;
                String textPassword = password.getText().toString();

                UserRequest request = new UserRequest(getApplicationContext());

                mAuth.signInWithEmailAndPassword(textEmail , textPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            request.checkAccount(textEmail, textPassword ,new UserRequest.HandleRespone() {
                                @Override
                                public void handleRespone(JSONObject response) throws JSONException {
                                    int status = response.getInt("status") ;
                                    if(status == 1){
                                        loginSuceessful();
                                    }
                                    else {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        user.delete() ;
                                        Toast.makeText(getApplicationContext() , R.string.loign_fail, Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                        }
                        else {

                            request.checkAccount(textEmail, textPassword, new UserRequest.HandleRespone() {
                                @Override
                                public void handleRespone(JSONObject response) throws JSONException {
                                    int status = response.getInt("status") ;
                                    if(status == 1){
                                        createAuth(textEmail , textPassword);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext() , R.string.loign_fail, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }
                    }
                });


            }
        });

    }

    private void createAuth(String email, String password){
        mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   loginSuceessful();
                }
            }
        });
    }

    private void loginSuceessful(){
        Toast.makeText(getApplicationContext() , R.string.loign_succeful , Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(LoginActivity.this , MainActivity.class));
    }


}