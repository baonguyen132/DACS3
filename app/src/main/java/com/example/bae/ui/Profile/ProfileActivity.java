package com.example.bae.ui.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bae.MainActivity;
import com.example.bae.R;
import com.example.bae.data.CartOfUser.CartOfUser;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.data.Voucher.VoucherOfUser;
import com.example.bae.ui.Login_SignUp.LoginActivity;
import com.example.bae.ui.Login_SignUp.SignUpActivity;

public class ProfileActivity extends AppCompatActivity {

    View layout_finsh , logout , login , signup ;
    TextView name , email ;
    ImageView avata ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        layout_finsh = findViewById(R.id.finish_profile);
        logout = findViewById(R.id.logout_profile_activity);
        login = findViewById(R.id.login_profile_activity);
        signup = findViewById(R.id.sign_up_profile_activity) ;
        name = findViewById(R.id.tv_name_activity_profile);
        email = findViewById(R.id.tv_email_activity_profile);
        avata = findViewById(R.id.image_avata_profile_activity);

        UserData userData = DataLocalManager.getUser() ;

        if(userData != null){
            login.setVisibility(View.GONE);
            signup.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);

            String img = RequestCustome.getInstance().getUrlStorage()+"upload/"+userData.getCccd()+"/upload_image_avata.jpg";
            Glide.with(getApplicationContext()).load(img).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(100))).into(avata);

            name.setText(userData.getName());
            email.setText(userData.getEmail());
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VoucherOfUser.removeVoucherOfUser();
                    CartOfUser.removeCart();
                    DataLocalManager.removeUser();
                    finish();
                    startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                }
            });
        }
        else {
            login.setVisibility(View.VISIBLE);
            signup.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(new Intent(ProfileActivity.this , LoginActivity.class));
                }
            });

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(new Intent(ProfileActivity.this , SignUpActivity.class));
                }
            });


        }
        layout_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ProfileActivity.this , MainActivity.class));
            }
        });
    }
}