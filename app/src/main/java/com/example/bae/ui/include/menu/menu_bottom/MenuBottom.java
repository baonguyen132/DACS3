package com.example.bae.ui.include.menu.menu_bottom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.bae.Interface.replaceFragement;
import com.example.bae.MainActivity;
import com.example.bae.R;
import com.example.bae.data.CartOfUser.CartOfUser;
import com.example.bae.data.CartOfUser.CartOfUserItem;
import com.example.bae.data.User.UserData;
import com.example.bae.ui.Battery.BatteryFragment;
import com.example.bae.ui.Cart.CartFragment;
import com.example.bae.ui.Login_SignUp.LoginActivity;
import com.example.bae.ui.Profile.ProfileActivity;
import com.example.bae.ui.home.HomeFragment;
import com.example.bae.ui.include.menu.MenuCustome;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MenuBottom extends MenuCustome implements replaceFragement {

    private FloatingActionButton fab ;
    private BottomNavigationView bottomNavigationView ;
    private ArrayList<CartOfUserItem> cartOfUserItemData;


    public MenuBottom (FragmentManager fragmentManager , Activity activity , UserData user ){
        super(fragmentManager , user, activity);
        this.bottomNavigationView = activity.findViewById(R.id.bottomNavigationView);
        this.fab = activity.findViewById(R.id.fab); ;
    }

    @Override
    public void createView() {
        bottomNavigationView.setBackground(null);
    }

    public void action(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartOfUserItemData = new ArrayList<>(CartOfUser.getCart().values());
                if(cartOfUserItemData.size() == 0){
                    Toast.makeText(context , "Giỏ hàng đang trống" , Toast.LENGTH_LONG).show();
                }
                else {
                    showBottomDialog();
                }
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId() ;
                if(id == R.id.home){
                    replaceFragement(new HomeFragment() , fragmentManager);
                }
                else if(id == R.id.battery){
                    replaceFragement(new BatteryFragment(), fragmentManager);
                }
                else if(id == R.id.history){
                    if(user == null){
                        Toast.makeText(context , "Bạn cần đăng nhập" , Toast.LENGTH_LONG).show();
                    }
                    else {
                        replaceFragement(new CartFragment() , fragmentManager);
                    }
                }
                else if(id == R.id.profile){
                    activity.finish();
                    activity.startActivity(new Intent(context, ProfileActivity.class));
                }


                return true;
            }
        });
    }

    protected void showBottomDialog() {

            if(user != null){
                final Dialog dialog = new Dialog(bottomNavigationView.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheetlayout);

                ListView listView = dialog.findViewById(R.id.listCart);
                ImageView cancelButton = dialog.findViewById(R.id.cancelButton);
                Button confirmCart = dialog.findViewById(R.id.btn_bottom_sheet_layout_confirm_cart);

                listView.setAdapter(new ItemCartAdapter(bottomNavigationView.getContext()));

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                confirmCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialogNotification() ;
                        dialog.dismiss();
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
            else {
                Toast.makeText(context , "Bạn cần đăng nhập" , Toast.LENGTH_LONG).show();
            }

    }

    private void openDialogNotification(){
        final Dialog dialog = new Dialog(bottomNavigationView.getContext()) ;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        dialog.setContentView(R.layout.layout_dialog_confrim_cart);

        Window window = dialog.getWindow();
        if(window == null){
            return ;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribute = window.getAttributes() ;
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);

        dialog.setCancelable(true);



        ListView listView = dialog.findViewById(R.id.listCartConfirm);
        TextView tvtotalPoint = dialog.findViewById(R.id.tv_dialog_cart_confirm_total_point);
        TextView tvtotalQuanity = dialog.findViewById(R.id.tv_dialog_cart_confirm_total_quantity);
        EditText edaddress = dialog.findViewById(R.id.et_dialog_cart_address);

        listView.setAdapter(new ItemCartConfirmAdapter(context , cartOfUserItemData));




        int totalPoint = 0 , totalQuanity = 0 ;
        for (int i = 0; i < cartOfUserItemData.size(); i++) {
            totalPoint = totalPoint + cartOfUserItemData.get(i).getQuantity()* cartOfUserItemData.get(i).getBatteryData().getPoint() ;
            totalQuanity = totalQuanity + cartOfUserItemData.get(i).getQuantity() ;
        }

        tvtotalPoint.setText(totalPoint+"");
        tvtotalQuanity.setText(totalQuanity+"");


        Button button = dialog.findViewById(R.id.btn_dialog_cart_confirm) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartOfUser.confirmCart(String.valueOf(edaddress.getText()) , user );
                dialog.dismiss();
            }
        });

        dialog.show();
    }




}
