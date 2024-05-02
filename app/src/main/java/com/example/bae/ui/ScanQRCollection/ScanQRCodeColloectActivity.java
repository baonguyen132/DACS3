package com.example.bae.ui.ScanQRCollection;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bae.R;
import com.example.bae.data.Battery.BatteryData;
import com.example.bae.data.Carts.CartData;
import com.example.bae.data.Carts.CartRequest;
import com.example.bae.data.Details.DetailData;
import com.example.bae.data.Details.DetailRequest;
import com.example.bae.data.RequestCustome;
import com.example.bae.ui.CollectBattery.CollectBatteryActivity;
import com.example.bae.ui.ItemBatteryOfCart.ItemBatteryCartAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class ScanQRCodeColloectActivity extends AppCompatActivity {

    private TextView name , cccd , pointofuser , pointofcart ;
    private ImageView close ;
    private ListView listView ;
    private Button openScan, openMap ;
    private CartData cartData ;
    private CartRequest cartRequest ;

    private FusedLocationProviderClient fusedLocationProviderClient ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode_collection);
        anhXa();
        Intent intent = getIntent();
         cartData = (CartData) intent.getSerializableExtra("data");

         cartRequest = new CartRequest() ;
        cartRequest.getInformation(cartData.getId(), new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                JSONObject jsonObject  = response.getJSONArray("data").getJSONObject(0);
                if(jsonObject.getString("token").equals("NULL")){
                    back();
                }
                else {
                    name.setText(jsonObject.getString("name"));
                    cccd.setText(jsonObject.getString("cccd"));
                    pointofcart.setText(jsonObject.getString("total"));
                    pointofuser.setText(jsonObject.getString("point"));
                }

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


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        openScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQR();
            }
        });
        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ScanQRCodeColloectActivity.this);
                getLastLocation();
            }
        });

    }

    private void anhXa(){
        close = findViewById(R.id.closeCollectItemDetail);
        name = findViewById(R.id.tv_name_user);
        cccd = findViewById(R.id.tv_cccd_user);
        pointofuser = findViewById(R.id.tv_point_user);
        pointofcart = findViewById(R.id.tv_point_cart);
        listView = findViewById(R.id.listCollect);
        openScan = findViewById(R.id.openScan);
        openMap = findViewById(R.id.openMap);
    }
    private void back(){
        Intent intent = new Intent(ScanQRCodeColloectActivity.this , CollectBatteryActivity.class);
        startActivity(intent);
        finish();
    }
    public void scanQR(){
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("") ;
        options.setCameraId(0) ;
        options.setBeepEnabled(true) ;
        options.setOrientationLocked(true) ;
        options.setCaptureActivity(CaptureActivity.class);
        barLaucher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract() , result->{
        if(result.getContents() != null){
            if(result.getContents().equals(cartData.getToken())){
                cartRequest.confirmCartAddPoint(new RequestCustome.HandleRequest() {
                    @Override
                    public void hanldeRequest(String respone) throws JSONException {
                        if(respone.equals("Successful")){
                            Toast.makeText(getApplicationContext() , "Cập nhật thành công" , Toast.LENGTH_LONG).show();
                            back();
                        } else if (respone.equals("NotSuccessful")) {
                            Toast.makeText(getApplicationContext() , "Không đúng mã" , Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext() , "Lỗi" , Toast.LENGTH_LONG).show();
                        }
                    }
                }, new RequestCustome.setParams() {
                    @Override
                    public void setParams(Map<String, String> params) {
                        params.put("token" , cartData.getToken());
                    }
                });
            }
            else {
                Toast.makeText(getApplicationContext() , "Không đúng mã" , Toast.LENGTH_LONG).show();
            }


        }
    });


    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ScanQRCodeColloectActivity.this , new String[]{Manifest.permission.ACCESS_FINE_LOCATION} , 1);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    String userDestination = cartData.getAddress();
                    getDirections(location.getLatitude()+","+location.getLongitude(), userDestination);
                }
            }
        });
    }

    private void getDirections(String form , String to){
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/"+form+"/"+to);
            Intent intent = new Intent(Intent.ACTION_VIEW , uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=vi&gl=US");
            Intent intent = new Intent(Intent.ACTION_VIEW , uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

}