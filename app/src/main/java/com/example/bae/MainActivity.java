package com.example.bae;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;


import com.example.bae.Interface.hanldeUploadImage;
import com.example.bae.Interface.replaceFragement;


import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.ui.home.HomeFragment;

import com.example.bae.ui.include.menu.MenuCustome;
import com.example.bae.ui.include.menu.menu_bottom.MenuBottom;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.IOException;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements replaceFragement {



    private MenuBottom menuBottom ;

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