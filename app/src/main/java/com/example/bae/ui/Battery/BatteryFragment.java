package com.example.bae.ui.Battery;

import static android.app.Activity.RESULT_OK;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bae.R;
import com.example.bae.data.AI.RequestDataAI;
import com.example.bae.data.Battery.BatteryData;
import com.example.bae.data.Battery.BatteryRequest;
import com.example.bae.data.CartNotConfirm.CartNotConfirm;
import com.example.bae.data.CartNotConfirm.CartNotConfirmItem;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import java.util.Calendar;

public class BatteryFragment extends Fragment {

    private BatteryViewModel mViewModel;
    private ListView listBattey ;
    private View view ;
    private static final int PICK_IMAGE_REQUEST = 1;
    private AppCompatButton click, confirm_button_s;
    ArrayList<BatteryData> batteryData = new ArrayList<>() ;

    public static BatteryFragment newInstance() {
        return new BatteryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_battery, container, false);
        listBattey = view.findViewById(R.id.l_list_battery);
        click = view.findViewById(R.id.scan);
        confirm_button_s = view.findViewById(R.id.confirm_button_s);
        CartNotConfirm.setObjectCart(null);

        BatteryRequest batteryRequest = new BatteryRequest() ;
        batteryRequest.getDataFromServe(new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                JSONArray jsonArray = response.getJSONArray("data") ;
                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    batteryData.add(new BatteryData(jsonObject.getString("id") , jsonObject.getString("name_battery") , jsonObject.getString("size") , jsonObject.getString("shape") , jsonObject.getString("point") , jsonObject.getString("image"))) ;
                }
                listBattey.setAdapter(new BatteryAdapter(getContext() , batteryData));
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery() ;
            }
        });
        confirm_button_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CartNotConfirm.getObjectCart() != null){
                    for (String key : CartNotConfirm.getObjectCart().keySet()) {
                        for (BatteryData item : batteryData) {
                            String[] name_item = item.getName_battery().split(" ");
                            if(name_item[name_item.length-1].equals(key)){
                                CartNotConfirm.putCart(item.getId() , new CartNotConfirmItem(item , Integer.parseInt(CartNotConfirm.getObjectCart().get(key).toString())));

                                break;
                            }
                        }
                    }
                    CartNotConfirm.setObjectCart(null);

                    listBattey.setAdapter(new BatteryAdapter(getContext() , batteryData));
                }

            }
        });


        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BatteryViewModel.class);
        // TODO: Use the ViewModel
    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            Calendar calendar = Calendar.getInstance() ;
            String namefile = DataLocalManager.getUser().getCccd()+"="+calendar.getTimeInMillis()+"="+"cart" ;
            CartNotConfirm.setNamefile(namefile);
            RequestDataAI.uploadImage(getFileFromUri(imageUri), namefile ,new RequestDataAI.hanldeResultUpload() {
                @Override
                public void hanldeResult(JsonObject data) {
                    CartNotConfirm.setObjectCart(data);
                    listBattey.setAdapter(new BatteryAdapter(getContext() , batteryData));
                }
            });



        }
    }

    private File getFileFromUri(Uri uri) {
        File file = null;
        try {
            InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
            File tempFile = File.createTempFile("image", ".jpg", getContext().getCacheDir());
            OutputStream outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
            file = tempFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}