package com.example.bae.ui.CollectBattery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bae.R;
import com.example.bae.data.Carts.CartData;
import com.example.bae.data.Carts.CartRequest;
import com.example.bae.data.RequestCustome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CollectBatteryActivity extends AppCompatActivity {

    private ListView listView ;
    private ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_battery);
        listView = findViewById(R.id.listCollect);
        imageView = findViewById(R.id.closeCollect);

        ArrayList<CartData> cartData = new ArrayList<>();
        CartRequest cartRequest = new CartRequest() ;
        cartRequest.getAllNotConfirm(new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                JSONArray jsonArray = response.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    CartData cartDataItem = new CartData(jsonObject.getString("id") , jsonObject.getString("created_at") , jsonObject.getString("token") , jsonObject.getString("address") , Integer.parseInt(jsonObject.getString("total")) , jsonObject.getString("image"));
                    cartData.add(cartDataItem);
                }

                listView.setAdapter(new ItemCollectionAdapter(cartData , CollectBatteryActivity.this));
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}