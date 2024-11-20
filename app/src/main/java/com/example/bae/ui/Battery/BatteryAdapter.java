package com.example.bae.ui.Battery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bae.R;
import com.example.bae.data.Battery.BatteryData;
import com.example.bae.data.CartNotConfirm.CartNotConfirm;
import com.example.bae.data.CartNotConfirm.CartNotConfirmItem;
import com.example.bae.data.RequestCustome;
import com.example.bae.ui.Battery.CardViewBattery.CardViewBatteryFragment;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BatteryAdapter extends BaseAdapter {

    ArrayList<BatteryData> batteryDatas ;
    Context context ;
    public BatteryAdapter(Context context , ArrayList<BatteryData> batteryDatas) {
        this.batteryDatas = batteryDatas ;
        this.context = context ;

    }

    class ViewHolder{
        private TextView name_battery , shape , point , size ;
        private EditText number ;

        private ImageView imageView ;


        public ViewHolder (View row) {
            name_battery = row.findViewById(R.id.tv_cardview_battery_name);
            shape = row.findViewById(R.id.tv_cardview_battery_shape);
            point = row.findViewById(R.id.tv_cardview_battery_point);
            size = row.findViewById(R.id.tv_cardview_battery_size);
            number = row.findViewById(R.id.et_cardview_number) ;

//            number.setTag(number.getKeyListener());
//            number.setKeyListener(null);

            imageView = row.findViewById(R.id.img_cardview_battery_imageBattery) ;
        }
    }

    @Override
    public int getCount() {
        return batteryDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return batteryDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view ;
        ViewHolder viewHolder ;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context) ;
            view = CardViewBatteryFragment.newInstance().onCreateView(inflater , null , null);
            viewHolder = new ViewHolder(view) ;
            view.setTag(viewHolder);
        }
        else {
            view = convertView ;
            viewHolder = (ViewHolder) view.getTag();
        }

        BatteryData batteryData = (BatteryData) getItem(position);
        viewHolder.name_battery.setText(batteryData.getName_battery());
        viewHolder.shape.setText("Shape: "+batteryData.getShape());
        viewHolder.point.setText("Point: " + batteryData.getPoint());
        viewHolder.size.setText("Size: "+batteryData.getSize());

        if (CartNotConfirm.getObjectCart() != null) {
            String[] name_battery = batteryData.getName_battery().split(" ");
            try {
                viewHolder.number.setText(CartNotConfirm.getObjectCart().get(name_battery[1]).toString());
            }
            catch (Exception e) {

            }
        }

        String img = RequestCustome.getInstance().getUrlStorage()+"image/Battery/"+batteryData.getImage()+".jpg";
        Glide.with(view.getContext()).load(img).into(viewHolder.imageView);

        return view;
    }
}
