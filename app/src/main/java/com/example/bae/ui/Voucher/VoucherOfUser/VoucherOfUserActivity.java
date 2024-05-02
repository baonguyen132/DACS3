package com.example.bae.ui.Voucher.VoucherOfUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bae.R;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.data.Voucher.VoucherData;
import com.example.bae.data.Voucher.VoucherOfUser;
import com.example.bae.data.Voucher.VoucherRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class VoucherOfUserActivity extends AppCompatActivity {
    private View finish_change ;
    private ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_of_user);

        finish_change = findViewById(R.id.finish_of_user);
        listView = findViewById(R.id.listVoucherOfUser);



        if(VoucherOfUser.getVoucherOfUser().size() == 0){
            UserData userData = DataLocalManager.getUser() ;
            HashMap<String , VoucherData> stringVoucherDataHashMap = new HashMap<>();

            VoucherRequest.getVoucherOfUser(userData.getId(), new RequestCustome.HandleResponeJSON() {
                @Override
                public void handleResponeJSON(JSONObject response) throws JSONException {

                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        VoucherData voucherData = new VoucherData(jsonObject.getString("id") , jsonObject.getString("name_voucher"), jsonObject.getString("code_voucher") , jsonObject.getString("point") , jsonObject.getString("id_branch_voucher"));
                        stringVoucherDataHashMap.put(voucherData.getId() , voucherData);
                    }
                    VoucherOfUser.setListvoucherOfUser(stringVoucherDataHashMap);
                    ArrayList<VoucherData> voucherDataArrayList = new ArrayList<>(stringVoucherDataHashMap.values());

                    setListView(voucherDataArrayList);

                }
            });
        }
        else {
            ArrayList<VoucherData> voucherDataArrayList = new ArrayList<>(VoucherOfUser.getVoucherOfUser().values()) ;
            setListView(voucherDataArrayList);
        }


        finish_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void setListView(ArrayList<VoucherData> voucherDataArrayList){
        listView.setAdapter(new VoucherOfUserAdapter(getApplicationContext(), voucherDataArrayList, new VoucherOfUserAdapter.ShowDialogDelete() {
            @Override
            public void show(VoucherData voucherData) {
                openDialogNotification(voucherData);
            }
        }));
    }

    private void openDialogNotification(VoucherData voucherData){
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

        dialog.setCancelable(true);

        TextView header_title = dialog.findViewById(R.id.header_title);
        TextView textView = dialog.findViewById(R.id.tv_dialog_notification) ;
        Button button = dialog.findViewById(R.id.btn_dialog_notification_confirm);
        textView.setText("Bạn có muốn xoá voucher này chứ ? \n Nếu huỷ bỏ bấm ra ngoài");
        header_title.setText("Xoá mã giảm giá");
        button.setText("Xoá voucher");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VoucherOfUser.removeItemVoucher(voucherData.getId())){
                    VoucherRequest.removeVoucher(getApplicationContext() ,voucherData);
                    ArrayList<VoucherData> voucherDataArrayList = new ArrayList<>(VoucherOfUser.getVoucherOfUser().values()) ;
                    setListView(voucherDataArrayList);
                }
                else {
                    Toast.makeText(getApplicationContext() , "Lỗi xoá" ,   Toast.LENGTH_LONG).show();
                }

                dialog.dismiss();

            }
        });

        dialog.show();
    }


}