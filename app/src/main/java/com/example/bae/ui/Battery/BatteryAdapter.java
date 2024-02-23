package com.example.bae.ui.Battery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.bae.R;
import com.example.bae.data.Battery.BatteryData;
import com.example.bae.ui.Battery.CardViewBattery.CardViewBatteryFragment;

import java.util.ArrayList;

public class BatteryAdapter extends BaseAdapter {

    ArrayList<BatteryData> batteryDatas ;
    Context context ;

    public BatteryAdapter(Context context , ArrayList<BatteryData> batteryDatas) {
        this.batteryDatas = batteryDatas ;
        this.context = context ;
    }

    class ViewHolder{
        private TextView name_battery ;

        public ViewHolder (View row) {
            name_battery = row.findViewById(R.id.tv_cardview_battery_name);
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
        viewHolder.name_battery.setText(batteryData.getName());


        return view;
    }
}
