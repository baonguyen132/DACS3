package com.example.bae.ui.Voucher.ChangeVoucher;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bae.Interface.fingerprintAuthentication;
import com.example.bae.R;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.data.Voucher.VoucherData;
import com.example.bae.data.Voucher.VoucherOfUser;
import com.example.bae.data.Voucher.VoucherRequest;
import com.example.bae.data.Voucher_Branchs.Voucher_BranchData;
import com.example.bae.ui.CheckCodeOTP.CheckCodeOPTActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChangeVoucherActivity extends AppCompatActivity implements fingerprintAuthentication {
    private View finish_change ;
    private ListView listView ;
    private TextView totalVoucher , remaining , changed ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_voucher);

        finish_change = findViewById(R.id.finish_change);
        listView = findViewById(R.id.listVoucher);
        totalVoucher = findViewById(R.id.total_voucher);
        remaining = findViewById(R.id.remaining);
        changed = findViewById(R.id.changed);


        Intent intent = getIntent() ;
        Voucher_BranchData voucherBranchData = (Voucher_BranchData) intent.getSerializableExtra("data");

        ArrayList<VoucherData> voucherDataArrayList = new ArrayList<>();
        VoucherRequest.getVoucher(voucherBranchData.getId(), "0", new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                JSONArray jsonArray = response.getJSONArray("data") ;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    voucherDataArrayList.add(new VoucherData(jsonObject.getString("id") , jsonObject.getString("name_voucher") , jsonObject.getString("code_voucher") , jsonObject.getString("point") , jsonObject.getString("id_branch_voucher")));
                }

                listView.setAdapter(new VoucherAdapter(getApplicationContext(), voucherDataArrayList, new VoucherAdapter.HanldeConfirm() {
                    @Override
                    public void hanldeConfirm(VoucherData voucherData) {
                        openDialogNotification("Bạn có muốn đổi voucher" , voucherData);

                    }
                }));
            }
        });

        VoucherRequest.getCount(voucherBranchData.getId(), new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                totalVoucher.setText(response.getString("totalVoucher"));
                remaining.setText(response.getString("remaining"));
                changed.setText(response.getString("changed"));

            }
        });


        finish_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void openDialogNotification(String content , VoucherData voucherData){
        final Dialog dialog = new Dialog(this) ;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        dialog.setContentView(R.layout.layout_dialog_confirm_change_voucher);

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

        TextView textView = dialog.findViewById(R.id.tv_dialog_notification_confirm_change_voucher) ;
        Button confirm = dialog.findViewById(R.id.btn_dialog_notification_confirm);
        Button cancel = dialog.findViewById(R.id.btn_dialog_notification_cancel);
        textView.setText(content);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = DataLocalManager.getUsename();
                UserData userData =  DataLocalManager.getUser();
                if(!username.equals("")){
                    figerAuthen(ChangeVoucherActivity.this, new Succeeded() {
                        @Override
                        public void Succeededs() {
                            if(username.equals(userData.getEmail())){
                                userData.changeVoucher(voucherData, new UserData.HandleRequest() {
                                    @Override
                                    public void handle(String respone) {
                                        if(respone.equals("sucessful")){
                                            VoucherOfUser.putVoucher(voucherData);
                                            finish();
                                            Toast.makeText(getApplicationContext() , "Đã đổi voucher" , Toast.LENGTH_LONG ).show();
                                        }
                                        else if(respone.equals("not_sucessful")){
                                            Toast.makeText(getApplicationContext() , "Bạn không đủ điểm" , Toast.LENGTH_LONG ).show();
                                        }

                                    }
                                });
                                Toast.makeText(dialog.getContext() , R.string.figerAuthen_sucessful, Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(dialog.getContext() , R.string.figerAuthen_not_sucessful, Toast.LENGTH_LONG).show();
                                checkOTP(userData , voucherData);
                            }
                        }
                    }, new Error() {
                        @Override
                        public void Errors() {
                            checkOTP(userData , voucherData);
                        }
                    }, new Failed() {
                        @Override
                        public void Faileds() {
                            checkOTP(userData , voucherData);
                            ;
                        }
                    });


                }
                else {
                    checkOTP(userData ,  voucherData);
                }

                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void checkOTP(UserData userData , VoucherData voucherData){
        finish();
        Intent intent = new Intent(ChangeVoucherActivity.this , CheckCodeOPTActivity.class);
        intent.putExtra("data" , userData);
        intent.putExtra("voucher" , voucherData);
        intent.putExtra("messenger" , "changeVoucher");
        startActivity(intent);
    }
}

