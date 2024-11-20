package com.example.bae.ui.Cart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bae.R;
import com.example.bae.data.Battery.BatteryData;
import com.example.bae.data.Carts.CartData;
import com.example.bae.data.Details.DetailData;
import com.example.bae.data.Details.DetailRequest;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.ui.Image.ImageActivity;
import com.example.bae.ui.ItemBatteryOfCart.ItemBatteryCartAdapter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItemHistoryActivity extends AppCompatActivity {
    ImageView QR_code ;
    View finish_detail ;
    ListView listView ;
    Button button ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_history);

        setUp();

        Intent intent = getIntent() ;
        CartData cartData = (CartData) intent.getSerializableExtra("data" );



        if(!cartData.getToken().equals("NULL")){
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter() ;
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(cartData.getToken() , BarcodeFormat.QR_CODE , 400 , 400);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                QR_code.setImageBitmap(bitmap);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }


        }
        if(cartData.getNamefile().equals("NULL")){
            button.setVisibility(View.GONE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ItemHistoryActivity.this , ImageActivity.class);
                intent1.putExtra("data" , cartData.getNamefile());
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });



        ArrayList<DetailData> detailDataArrayList = new ArrayList<>();
        DetailRequest.getDetailCart(cartData.getId(), new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                JSONArray jsonArray = response.getJSONArray("data") ;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    BatteryData batteryData = new BatteryData(jsonObject.getString("idbatterys"), jsonObject.getString("name_battery"), jsonObject.getString("size"), jsonObject.getString("shape"), jsonObject.getString("point"), jsonObject.getString("image"));
                    detailDataArrayList.add(new DetailData(jsonObject.getString("id") , jsonObject.getString("count") , batteryData )) ;
                }

                listView.setAdapter(new ItemBatteryCartAdapter(getApplicationContext() , detailDataArrayList));
            }
        });



        finish_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setUp(){
        QR_code = findViewById(R.id.qrcodes);
        finish_detail = findViewById(R.id.finish_detail);
        listView = findViewById(R.id.listview_item_cart_battery_activity_item_history);
        button = findViewById(R.id.openimage);
    }
}