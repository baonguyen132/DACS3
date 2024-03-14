package com.example.bae.ui.Voucher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bae.R;
import com.example.bae.data.Battery.BatteryData;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.Voucher_Branchs.Voucher_BranchData;
import com.example.bae.data.Voucher_Branchs.Voucher_BranchRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChooseBranchActivity extends AppCompatActivity {

    private View finish_choose ;
    private ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_branch);
        finish_choose = findViewById(R.id.finish_choose);
        listView = findViewById(R.id.listBranch);

        finish_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<Voucher_BranchData> voucherBranchDataArrayList = new ArrayList<>();

        Voucher_BranchRequest.getBranch(new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                JSONArray jsonArray = response.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    voucherBranchDataArrayList.add(new Voucher_BranchData(jsonObject.getString("id") , jsonObject.getString("name_branch_voucher") , jsonObject.getString("count") ));
                }
                listView.setAdapter(new BranchAdapter(getApplicationContext() , voucherBranchDataArrayList));
            }
        });

    }
}