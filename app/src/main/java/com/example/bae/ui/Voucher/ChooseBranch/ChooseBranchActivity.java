package com.example.bae.ui.Voucher.ChooseBranch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bae.R;
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
    private TextView total_branch , total_voucher , remaining ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_branch);
        finish_choose = findViewById(R.id.finish_choose);
        listView = findViewById(R.id.listBranch);

        total_branch = findViewById(R.id.total_branch);
        total_voucher = findViewById(R.id.total_voucher);
        remaining = findViewById(R.id.remaining);


        Voucher_BranchRequest.getCount(new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                total_branch.setText(response.getString("totalBranch"));
                total_voucher.setText(response.getString("totalVoucher"));
                remaining.setText(response.getString("remaining"));

            }
        });

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