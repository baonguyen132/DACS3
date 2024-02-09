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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
        signUp = findViewById(R.id.btn_login_signIn);
        mAuth = FirebaseAuth.getInstance() ;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = email.getText().toString() ;
                String textPassword = password.getText().toString();
                mAuth.signInWithEmailAndPassword(textEmail , textPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext() , "đăng nhập thành công" , Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(LoginActivity.this , MainActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext() , "đăng nhập không thành công" , Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });

    }
}