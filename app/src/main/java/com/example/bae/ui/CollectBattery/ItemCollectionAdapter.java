package com.example.bae.ui.CollectBattery;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.bae.R;
import com.example.bae.data.Carts.CartData;
import com.example.bae.ui.Cart.CartAdapter;
import com.example.bae.ui.Cart.CartItem.CartItemFragment;
import com.example.bae.ui.CollectBattery.ItemCollection.ItemCollectionFragment;
import com.example.bae.ui.ScanQRCollection.ScanQRCodeColloectActivity;
import com.example.bae.ui.include.menu.menu_bottom.ItemCartAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class ItemCollectionAdapter extends BaseAdapter {

    private ArrayList<CartData> listCollection;
    private Context context;
    private Activity activity;


    public ItemCollectionAdapter(ArrayList<CartData> listCollection, Activity activity) {
        this.listCollection = listCollection;
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    class ViewHolder {
        private TextView id, point, date, address;
        private Button scan;

        public ViewHolder(View view) {
            this.id = view.findViewById(R.id.tv_item_id);
            this.point = view.findViewById(R.id.tv_item_point);
            this.date = view.findViewById(R.id.tv_item_date);
            this.address = view.findViewById(R.id.tv_item_address);
            this.scan = view.findViewById(R.id.scanqr);


        }
    }

    @Override
    public int getCount() {
        return listCollection.size();
    }

    @Override
    public Object getItem(int position) {
        return listCollection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ItemCollectionAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = ItemCollectionFragment.newInstance().onCreateView(inflater, null, null);
            viewHolder = new ItemCollectionAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ItemCollectionAdapter.ViewHolder) view.getTag();
        }

        CartData cartData = listCollection.get(position);

        viewHolder.id.setText(cartData.getId());
        viewHolder.point.setText(cartData.getTotal() + "");
        viewHolder.address.setText(cartData.getAddress());
        String[] date = cartData.getCreated_at().split("T");
        viewHolder.date.setText(date[0]);

        viewHolder.scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ScanQRCodeColloectActivity.class);
                intent.putExtra("data", cartData);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        return view;
    }



}
