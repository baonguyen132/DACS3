package com.example.bae.ui.Cart.ItemActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bae.R;
import com.example.bae.data.Carts.CartData;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ItemHistoryActivity extends AppCompatActivity {
    ImageView imageView ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_history);
        imageView = findViewById(R.id.qrcodes);
        Button button = findViewById(R.id.sssss);
        Intent intent = getIntent() ;
        CartData cartData = (CartData) intent.getSerializableExtra("data" );

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter() ;

        if(!cartData.getToken().equals("NULL")){
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(cartData.getToken() , BarcodeFormat.QR_CODE , 400 , 400);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }


        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}