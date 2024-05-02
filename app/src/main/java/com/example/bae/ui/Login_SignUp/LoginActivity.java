package com.example.bae.ui.Login_SignUp;

import androidx.annotation.NonNull;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bae.Interface.fingerprintAuthentication;

import com.example.bae.MainActivity;
import com.example.bae.R;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.data.User.UserRequest;
import com.example.bae.ui.include.Dialog.LoadingDialog;


import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements fingerprintAuthentication {

    private EditText email , password ;
    private Button signUp ;


    private ImageView fingerPrint ;
    private CheckBox checkBox ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.et_login_email);
        password = findViewById(R.id.et_login_password);
        signUp = findViewById(R.id.btn_login_signIn);
        fingerPrint = findViewById(R.id.iv_login_signIn);
        checkBox = findViewById(R.id.cb_save_account);



        email.setText(DataLocalManager.getUsename());

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = email.getText().toString() ;
                String textPassword = password.getText().toString();
                if (checkBox.isChecked()){
                    saveEmailandPassword(textEmail , textPassword);
                }

                login(textEmail , textPassword);
            }
        });

        fingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isCheckUserAndPassword()){
                    figerAuthen(LoginActivity.this, new Succeeded() {
                        @Override
                        public void Succeededs() {
                            login(DataLocalManager.getUsename() , DataLocalManager.getPassword());
                        }
                    }, new Error() {
                        @Override
                        public void Errors() {

                        }
                    }, new Failed() {
                        @Override
                        public void Faileds() {

                        }
                    });
                }
                else {
                    openDialogNotification("Bạn cần đăng nhập và bấm chọn lưu tài khoản để thực hiện đăng nhập bằng vân tay");
                }
            }
        });



    }

    private void login(String email , String password){
        LoadingDialog dialog = new LoadingDialog(LoginActivity.this);
        dialog.startAlerDialog();
        UserRequest.checkAccount(email, password, new RequestCustome.HandleResponeString() {
            @Override
            public void handleResponeString(String response) throws JSONException {
                dialog.dismissDialog();
                int id = Integer.parseInt(response) ;
                if(id != 0){
                    saveDataFromServe(id);
                }
                else {
                    Toast.makeText(getApplicationContext() , "Sai email hoặc mật khẩu" , Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void saveDataFromServe(int id){
        UserRequest.getDataFromServe(id, new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                JSONObject jsonObject = response.getJSONObject("data");
                UserData userData = new UserData(
                        jsonObject.getString("id"),
                        jsonObject.getString("email"),
                        jsonObject.getString("name"),
                        jsonObject.getString("cccd"),
                        jsonObject.getString("dob"),
                        jsonObject.getString("gender"),
                        jsonObject.getString("pob"),
                        jsonObject.getString("address"),
                        jsonObject.getInt("status"),
                        jsonObject.getInt("point")
                );
                DataLocalManager.setUser(userData);
                loginSuceessful();
            }
        });

    }

    private void loginSuceessful(){
        Toast.makeText(getApplicationContext() , R.string.loign_succeful , Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(intent);

    }
    private boolean isCheckUserAndPassword(){
        String user = DataLocalManager.getUsename() ;
        if(user.equals("")){
            return true ;
        }
        return false ;

    }

    private void saveEmailandPassword (String textEmail , String textPassword) {
        DataLocalManager.setUsename(textEmail);
        DataLocalManager.setPassword(textPassword);
    }

    private void openDialogNotification(String content){
        final Dialog dialog = new Dialog(this) ;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        dialog.setContentView(R.layout.layout_dialog_notification);

        Window window = dialog.getWindow();
        if(window == null){
            return ;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribute = window.getAttributes() ;
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);

        dialog.setCancelable(false);

        TextView textView = dialog.findViewById(R.id.tv_dialog_notification) ;
        Button button = dialog.findViewById(R.id.btn_dialog_notification_confirm);
        textView.setText(content);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}