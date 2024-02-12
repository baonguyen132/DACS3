package com.example.bae.ui.include.menu.menu_navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.bae.Interface.replaceFragement;
import com.example.bae.R;
import com.example.bae.data.UserData;
import com.example.bae.data.UserRequest;
import com.example.bae.ui.Login_SignUp.LoginActivity;
import com.example.bae.ui.Login_SignUp.SignUpActivity;
import com.example.bae.ui.Sub.SubFragment;
import com.example.bae.ui.include.menu.MenuCustome;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class Navigation extends MenuCustome implements replaceFragement {
    private NavigationView navigationView ;
    private DrawerLayout drawerLayout ;


    TextView nav_header_t_fullname , nav_view_t_header ;

    public Navigation( FragmentManager fragmentManager ,  Activity activity   , FirebaseUser user){
        super(fragmentManager , user , activity);
        this.activity = activity ;

        this.navigationView =  activity.findViewById(R.id.nav_view) ;
        this.drawerLayout = activity.findViewById(R.id.drawer_layout);


        View headerView = navigationView.getHeaderView(0) ;
        nav_header_t_fullname = headerView.findViewById(R.id.nav_header_fullname);
        nav_view_t_header = headerView.findViewById(R.id.nav_header_email);

    }



    public void createView(){
        navigationView.bringToFront();

        Menu menu = navigationView.getMenu() ;
        if(user != null){
            UserRequest request = new UserRequest(context) ;
            request.ResponseData(user.getEmail(), new UserRequest.HandleRespone() {
                @Override
                public void handleRespone(JSONObject response) throws JSONException {
                    nav_header_t_fullname.setText(response.getString("name"));
                    nav_view_t_header.setText(response.getString("email"));

                }
            });

            menu.add(R.id.group_login_and_sign_up , 1 , 0 , "Logout");
        }
        else {
            menu.add(R.id.group_login_and_sign_up , 2 , 0 , "Login");
            menu.add(R.id.group_login_and_sign_up , 3 , 0 , "Sign up");

        }
    }



    @Override
    public void action() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId() ;
                if(i == R.id.nav_settings){
                    replaceFragement(new SubFragment() ,fragmentManager);
                }
                else if (i == 1) {
                    FirebaseAuth.getInstance().signOut();
                    activity.finish();
                    activity.startActivity(new Intent(context, LoginActivity.class));
                }
                else if (i == 2) {
                    activity.finish();
                    activity.startActivity(new Intent(context , LoginActivity.class));
                }
                else if (i == 3) {
                    activity.finish();
                    activity.startActivity(new Intent(context , SignUpActivity.class));
                }
                Log.d("ssss" , (i + "-" + item));
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }
}
