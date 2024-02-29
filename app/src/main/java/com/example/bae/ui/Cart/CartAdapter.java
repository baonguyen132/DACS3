package com.example.bae.ui.Cart;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bae.R;
import com.example.bae.data.Battery.BatteryData;
import com.example.bae.data.Carts.CartData;
import com.example.bae.ui.Battery.BatteryAdapter;
import com.example.bae.ui.Battery.CardViewBattery.CardViewBatteryFragment;
import com.example.bae.ui.Cart.CartItem.CartItemFragment;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    ArrayList<CartData> cartDatas ;
    Context context ;

    public CartAdapter(Context context , ArrayList<CartData> cartDatas) {
        this.cartDatas = cartDatas ;
        this.context = context ;
    }

    class ViewHolder{

        private ImageView imageView ;


        public ViewHolder (View row) {

            imageView = row.findViewById(R.id.imgsss) ;
        }


    }

    @Override
    public int getCount() {
        return cartDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return cartDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view ;
        CartAdapter.ViewHolder viewHolder ;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context) ;
            view = CartItemFragment.newInstance().onCreateView(inflater , null , null);
            viewHolder = new CartAdapter.ViewHolder(view) ;
            view.setTag(viewHolder);
        }
        else {
            view = convertView ;
            viewHolder = (CartAdapter.ViewHolder) view.getTag();
        }

        CartData cartData = cartDatas.get(position) ;

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter() ;

        if(!cartData.getToken().equals("NULL")){
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(cartData.getToken() , BarcodeFormat.QR_CODE , 400 , 400);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                viewHolder.imageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }


        }

        return view;
    }
}
