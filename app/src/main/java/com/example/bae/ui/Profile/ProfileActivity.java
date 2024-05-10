package com.example.bae.ui.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bae.MainActivity;
import com.example.bae.R;
import com.example.bae.data.CartNotConfirm.CartNotConfirm;
import com.example.bae.data.Carts.CartOfUser;
import com.example.bae.data.FirebaseCustome;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.data.Voucher.VoucherOfUser;
import com.example.bae.ui.CollectBattery.CollectBatteryActivity;
import com.example.bae.ui.Information.InformationActivity;
import com.example.bae.ui.Login_SignUp.LoginActivity;
import com.example.bae.ui.Login_SignUp.SignUpActivity;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    View layout_finsh , logout , login , signup , function1 , information ;
    TextView name , email ;
    ImageView avata ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setUp();
        UserData userData = DataLocalManager.getUser() ;

        if(userData != null){
            login.setVisibility(View.GONE);
            signup.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            information.setVisibility(View.VISIBLE);
            if(userData.getStatus() != 5){
                function1.setVisibility(View.VISIBLE);
            }

            String img = FirebaseCustome.getInstance().getUrlFirebaseStorage() +userData.getCccd()+"%2Favatar.png?alt=media";
            Picasso.with(getApplicationContext()).load(img).into(avata);

            name.setText(userData.getName());
            email.setText(userData.getEmail());

            function1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProfileActivity.this , CollectBatteryActivity.class) ;
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VoucherOfUser.removeVoucherOfUser();
                    CartNotConfirm.removeCart();
                    CartOfUser.removeCartOfUser();
                    DataLocalManager.removeUser();
                    finish();
                    startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                }
            });

            information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(new Intent(ProfileActivity.this , InformationActivity.class));
                }
            });

        }
        else {
            login.setVisibility(View.VISIBLE);
            signup.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
            information.setVisibility(View.GONE);
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

    private void setUp(){
        layout_finsh = findViewById(R.id.finish_profile);
        logout = findViewById(R.id.logout_profile_activity);
        login = findViewById(R.id.login_profile_activity);
        signup = findViewById(R.id.sign_up_profile_activity) ;
        name = findViewById(R.id.tv_name_activity_profile);
        email = findViewById(R.id.tv_email_activity_profile);
        avata = findViewById(R.id.image_avata_profile_activity);

        function1 = findViewById(R.id.function1);
        information = findViewById(R.id.information);
    }
}