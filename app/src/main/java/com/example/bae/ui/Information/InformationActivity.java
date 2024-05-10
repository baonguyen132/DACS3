package com.example.bae.ui.Information;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import com.example.bae.Interface.hanldeUploadImage;
import com.example.bae.R;
import com.example.bae.data.CartNotConfirm.CartNotConfirm;
import com.example.bae.data.FirebaseCustome;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.data.User.UserRequest;
import com.example.bae.ui.Profile.ProfileActivity;
import com.example.bae.ui.include.menu.menu_bottom.ItemCartConfirmAdapter;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class InformationActivity extends AppCompatActivity {
    private EditText selectDay , fullname , cid , address , email;
    private RadioGroup radioGroupGender ;
    private View layout_finsh ;
    private Button replace_address, replace_password ;
    private ImageView image_avata_information_activity ;
    TextView tvname , tvemail ;
    ImageView avata ;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        anhXa() ;
        userData = DataLocalManager.getUser();
        setUp();

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
        image_avata_information_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(InformationActivity.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start() ;

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData() ;
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            image_avata_information_activity.setImageBitmap(bitmap);

            hanldeUploadImage.handle(bitmap, "avatar", userData.getCccd(), new hanldeUploadImage() {
                @Override
                public void success() {
                    Toast.makeText(getApplicationContext() , "Thành công" , Toast.LENGTH_LONG).show();
                }

                @Override
                public void fail() {
                    Toast.makeText(getApplicationContext() , "Không thành công" , Toast.LENGTH_LONG).show();
                }
            });


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show() ;
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }




        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setUp(){
        fullname.setText(userData.getName());
        cid.setText(userData.getCccd());
        selectDay.setText(userData.getDob());
        email.setText(userData.getEmail());
        address.setText(userData.getAddress());

        String img = FirebaseCustome.getInstance().getUrlFirebaseStorage() +userData.getCccd()+"%2Favatar.png?alt=media";
        Picasso.with(getApplicationContext()).load(img).into(avata);

        tvname.setText(userData.getName());
        tvemail.setText(userData.getEmail());

        if(userData.getGender().equals("Male")){
            radioGroupGender.check(R.id.r_information_male);

        }
        else {
            radioGroupGender.check(R.id.r_information_female);

        }
    }

    private void anhXa(){
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
        image_avata_information_activity = findViewById(R.id.image_avata_information_activity);

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