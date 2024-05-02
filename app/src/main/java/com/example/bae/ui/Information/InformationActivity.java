package com.example.bae.ui.Information;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bae.R;
import com.example.bae.data.CartNotConfirm.CartNotConfirm;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.data.User.UserRequest;
import com.example.bae.ui.Profile.ProfileActivity;
import com.example.bae.ui.include.menu.menu_bottom.ItemCartConfirmAdapter;

import org.json.JSONException;

public class InformationActivity extends AppCompatActivity {
    private EditText selectDay , fullname , cid , address , email;
    private RadioGroup radioGroupGender ;
    private View layout_finsh ;
    private Button replace_address, replace_password ;
    TextView tvname , tvemail ;
    ImageView avata ;
    UserData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setUp();
         userData = DataLocalManager.getUser();

        fullname.setText(userData.getName());
        cid.setText(userData.getCccd());
        selectDay.setText(userData.getDob());
        email.setText(userData.getEmail());
        address.setText(userData.getAddress());

        String img = RequestCustome.getInstance().getUrlStorage()+"upload/"+userData.getCccd()+"/upload_image_avata.jpg";
        Glide.with(getApplicationContext()).load(img).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(100))).into(avata);

        tvname.setText(userData.getName());
        tvemail.setText(userData.getEmail());


        if(userData.getGender().equals("Male")){
            radioGroupGender.check(R.id.r_information_male);

        }
        else {
            radioGroupGender.check(R.id.r_information_female);

        }

        layout_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(InformationActivity.this , ProfileActivity.class));
            }
        });
        replace_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogReplaceAddress();
            }
        });
        replace_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogReplacePassword();
            }
        });

    }

    private void setUp(){
        selectDay = findViewById(R.id.et_information_dob) ;
        fullname = findViewById(R.id.et_information_fullname);
        cid = findViewById(R.id.et_information_cid);
        address = findViewById(R.id.et_information_address);
        email = findViewById(R.id.et_information_email);
        layout_finsh=findViewById(R.id.layout_informtion_finish);
        radioGroupGender = findViewById(R.id.rg_information_gender);
        replace_address = findViewById(R.id.replace_address);
        replace_password= findViewById(R.id.replace_password);
        tvname = findViewById(R.id.tv_name_activity_information);
        tvemail = findViewById(R.id.tv_email_activity_information);
        avata = findViewById(R.id.image_avata_information_activity);

        setEdittextReadOnly(selectDay);
        setEdittextReadOnly(fullname);
        setEdittextReadOnly(cid);
        setEdittextReadOnly(address);
        setEdittextReadOnly(email);
    }

    private void setEdittextReadOnly(EditText editTexts){

        editTexts.setTag(editTexts.getKeyListener());
        editTexts.setKeyListener(null);

    }

    private void openDialogReplaceAddress(){
        final Dialog dialog = new Dialog(InformationActivity.this) ;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        dialog.setContentView(R.layout.layout_dialog_replace_address);

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

        Button confirm = dialog.findViewById(R.id.btn_dialog_replace_address);
        EditText newaddress = dialog.findViewById(R.id.newaddress);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.setAddress(String.valueOf(newaddress.getText()));
                DataLocalManager.setUser(userData);

                UserRequest.replaceAddress(userData, new RequestCustome.HandleResponeString() {
                    @Override
                    public void handleResponeString(String response) throws JSONException {
                        if(response.equals("sucessful")){
                            Toast.makeText(getApplicationContext() , "Đổi địa chỉ thành công" , Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getApplicationContext() , "Đổi địa chỉ không thành công" , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });



        dialog.show();
    }

    private void openDialogReplacePassword(){
        final Dialog dialog = new Dialog(InformationActivity.this) ;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        dialog.setContentView(R.layout.layout_dialog_replace_password);

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

        EditText oldPassword = dialog.findViewById(R.id.oldPassword);
        EditText newPassword = dialog.findViewById(R.id.newPassword);
        EditText confirmPassword = dialog.findViewById(R.id.confirmPassword);
        Button button = dialog.findViewById(R.id.btn_dialog_replace_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPasswords = String.valueOf(newPassword.getText());
                String confirmPasswords = String.valueOf(confirmPassword.getText());
                String oldPasswords = String.valueOf(oldPassword.getText());

                if(newPasswords.equals("") || confirmPasswords.equals("") || oldPasswords.equals("")){
                    Toast.makeText(getApplicationContext() , "Hãy nhập đầy đủ" , Toast.LENGTH_LONG).show();
                }
                else {
                    if (newPasswords.equals(confirmPasswords)){
                        UserRequest.replacePassword(userData.getId(), oldPasswords, newPasswords, new RequestCustome.HandleResponeString() {
                            @Override
                            public void handleResponeString(String response) throws JSONException {
                                if(response.equals("sucessful")){
                                    Toast.makeText(getApplicationContext() , "Đổi mật khẩu thành công" , Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                                else if(response.equals("failPassword")){
                                    Toast.makeText(getApplicationContext() , "Sai mật khẩu" , Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext() , "Đổi mật khẩu không thành công" , Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(getApplicationContext() , "Không đúng" , Toast.LENGTH_LONG).show();
                    }
                }



            }
        });


        dialog.show();
    }
}