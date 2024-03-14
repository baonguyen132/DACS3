package com.example.bae.ui.Voucher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bae.R;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.Voucher.VoucherData;
import com.example.bae.data.Voucher.VoucherRequest;
import com.example.bae.data.Voucher_Branchs.Voucher_BranchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChangeVoucherActivity extends AppCompatActivity {
    private View finish_change ;
    private ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_voucher);

        finish_change = findViewById(R.id.finish_change);
        listView = findViewById(R.id.listVoucher);

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

                listView.setAdapter(new VoucherAdapter(getApplicationContext() , voucherDataArrayList));
            }
        });

        finish_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}