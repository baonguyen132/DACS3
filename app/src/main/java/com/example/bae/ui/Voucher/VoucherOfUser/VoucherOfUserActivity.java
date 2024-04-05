package com.example.bae.ui.Voucher.VoucherOfUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

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



        if(VoucherOfUser.getVoucherOfUser() == null){
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

                    listView.setAdapter(new VoucherOfUserAdapter(getApplicationContext(), voucherDataArrayList));

                }
            });
        }
        else {
            ArrayList<VoucherData> voucherDataArrayList = new ArrayList<>(VoucherOfUser.getVoucherOfUser().values()) ;
            listView.setAdapter(new VoucherOfUserAdapter(getApplicationContext(), voucherDataArrayList));
        }


        finish_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}