package com.example.bae.ui.include.menu.menu_bottom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bae.R;
import com.example.bae.data.CartNotConfirm.CartNotConfirmItem;
import com.example.bae.ui.include.menu.menu_bottom.ItemCartConfirm.ItemCartConfirmragment;

import java.util.ArrayList;

public class ItemCartConfirmAdapter extends BaseAdapter {
    private ArrayList<CartNotConfirmItem> cartNotConfirmItemData;
    private Context context ;
    public ItemCartConfirmAdapter(Context context , ArrayList<CartNotConfirmItem> cartNotConfirmItemData) {
        this.context = context;
        this.cartNotConfirmItemData = cartNotConfirmItemData;
    }

    class ViewHolder{

        private TextView name_battery , point , total_point , quantity;


        public ViewHolder(View view) {
            name_battery = view.findViewById(R.id.tv_item_cart_confirm_namebattery);
            point = view.findViewById(R.id.tv_item_cart_confirm_point);
            total_point = view.findViewById(R.id.tv_item_cart_confirm_totalpoint);
            quantity = view.findViewById(R.id.tv_item_cart_confirm_quantity);

        }

    }

    @Override
    public int getCount() {
        return cartNotConfirmItemData.size();
    }

    @Override
    public Object getItem(int position) {
        return cartNotConfirmItemData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        ItemCartConfirmAdapter.ViewHolder viewHolder ;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context) ;
            view = ItemCartConfirmragment.newInstance().onCreateView(inflater , null , null);
            viewHolder = new ItemCartConfirmAdapter.ViewHolder(view) ;
            view.setTag(viewHolder);
        }
        else {
            view = convertView ;
            viewHolder = (ItemCartConfirmAdapter.ViewHolder) view.getTag();
        }

        CartNotConfirmItem cartNotConfirmItem = (CartNotConfirmItem) getItem(position);

        viewHolder.name_battery.setText(cartNotConfirmItem.getBatteryData().getName_battery());
        viewHolder.point.setText(cartNotConfirmItem.getBatteryData().getPoint()+"");

        totalPoint(viewHolder , cartNotConfirmItem) ;


        return view;
    }
    private void totalPoint(ItemCartConfirmAdapter.ViewHolder viewHolder , CartNotConfirmItem cartNotConfirmItem){
        int total = cartNotConfirmItem.getBatteryData().getPoint() * cartNotConfirmItem.getQuantity() ;
        viewHolder.total_point.setText(total+"");
        viewHolder.quantity.setText(cartNotConfirmItem.getQuantity()+"");
    }
}
