package com.example.bae;


import androidx.appcompat.app.AppCompatActivity;



import android.annotation.SuppressLint;
import android.os.Bundle;


import com.example.bae.Interface.replaceFragement;


import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.ui.home.HomeFragment;

import com.example.bae.ui.include.menu.MenuCustome;
import com.example.bae.ui.include.menu.menu_bottom.MenuBottom;


public class MainActivity extends AppCompatActivity implements replaceFragement {



    private MenuCustome menuBottom ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserData user = DataLocalManager.getUser() ;



        //==== Thiết lập biến ====//

        //==== Tạo ====//
        menuBottom = new MenuBottom(getSupportFragmentManager(), this , user );

        replaceFragement(new HomeFragment() , getSupportFragmentManager());

        menuBottom.createView();
        menuBottom.action();

    }


}