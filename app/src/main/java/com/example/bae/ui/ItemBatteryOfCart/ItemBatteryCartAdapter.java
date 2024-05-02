package com.example.bae.ui.ItemBatteryOfCart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bae.R;
import com.example.bae.data.Details.DetailData;
import com.example.bae.data.RequestCustome;
import com.example.bae.ui.ItemBatteryOfCart.ItemBatteryCart.ItemBatteryCartFragment;

import java.util.ArrayList;

public class ItemBatteryCartAdapter extends BaseAdapter {


    private ArrayList<DetailData> detailDataArrayList;
    private Context context ;

    public ItemBatteryCartAdapter(Context context , ArrayList<DetailData> batteryDatalist) {
        this.context = context;
        this.detailDataArrayList = batteryDatalist;
    }

    class ViewHolder{

        private TextView name_battery , point , total_point, number;
        private ImageView imageView ;

        public ViewHolder(View view) {
            name_battery = view.findViewById(R.id.tv_item_battery_cart_namebattery);
            point = view.findViewById(R.id.tv_item_battery_cart_point);
            total_point = view.findViewById(R.id.tv_item_battery_cart_totalpoint);
            number = view.findViewById(R.id.tv_item_battery_cart_number);
            imageView = view.findViewById(R.id.img_item_battery_cart_imageBattery);
        }

    }

    @Override
    public int getCount() {
        return detailDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return detailDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        ItemBatteryCartAdapter.ViewHolder viewHolder ;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context) ;
            view = ItemBatteryCartFragment.newInstance().onCreateView(inflater , null , null);
            viewHolder = new ItemBatteryCartAdapter.ViewHolder(view) ;
            view.setTag(viewHolder);
        }
        else {
            view = convertView ;
            viewHolder = (ItemBatteryCartAdapter.ViewHolder) view.getTag();
        }

        DetailData detailData = (DetailData) getItem(position);

        viewHolder.name_battery.setText(detailData.getBatteryData().getName_battery());
        viewHolder.point.setText(detailData.getBatteryData().getPoint()+"");
        viewHolder.number.setText(detailData.getCount());
        int total = Integer.parseInt(detailData.getCount())*detailData.getBatteryData().getPoint();
        viewHolder.total_point.setText(total+"");

        String img = RequestCustome.getInstance().getUrlStorage()+"image/Battery/"+detailData.getBatteryData().getImage()+".jpg";
        Glide.with(view.getContext()).load(img).into(viewHolder.imageView);

        return view;
    }


}
