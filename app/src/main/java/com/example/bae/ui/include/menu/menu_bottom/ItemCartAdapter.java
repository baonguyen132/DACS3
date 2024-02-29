package com.example.bae.ui.include.menu.menu_bottom;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.bae.data.CartOfUser.CartOfUser;
import com.example.bae.data.CartOfUser.CartOfUserItem;

import java.util.ArrayList;
import com.example.bae.R;
import com.example.bae.ui.include.menu.menu_bottom.ItemCart.ItemCartFragment;

public class ItemCartAdapter extends BaseAdapter {

    private ArrayList<CartOfUserItem> cartOfUserItemData;
    private Context context ;

    public ItemCartAdapter(Context context) {
        this.context = context;
        cartOfUserItemData = new ArrayList<>(CartOfUser.getCart().values());
    }

    class ViewHolder{

        private TextView name_battery , point , total_point , quantity;
        private Button increase , decrease ;

        public ViewHolder(View view) {
            name_battery = view.findViewById(R.id.tv_item_cart_namebattery);
            point = view.findViewById(R.id.tv_item_cart_point);
            total_point = view.findViewById(R.id.tv_item_cart_totalpoint);
            quantity = view.findViewById(R.id.tv_Item_cart_quantity);
            increase  = view.findViewById(R.id.btn_increase_quantity);
            decrease  = view.findViewById(R.id.btn_decrease_quantity);

        }

    }

    @Override
    public int getCount() {
        return cartOfUserItemData.size();
    }

    @Override
    public Object getItem(int position) {
        return cartOfUserItemData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        ItemCartAdapter.ViewHolder viewHolder ;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context) ;
            view = ItemCartFragment.newInstance().onCreateView(inflater , null , null);
            viewHolder = new ItemCartAdapter.ViewHolder(view) ;
            view.setTag(viewHolder);
        }
        else {
            view = convertView ;
            viewHolder = (ItemCartAdapter.ViewHolder) view.getTag();
        }

        CartOfUserItem cartOfUserItem = (CartOfUserItem) getItem(position);

        viewHolder.name_battery.setText(cartOfUserItem.getBatteryData().getName_battery());
        viewHolder.point.setText(cartOfUserItem.getBatteryData().getPoint()+"");

        totalPoint(viewHolder , cartOfUserItem) ;

        viewHolder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int change = cartOfUserItem.getQuantity() + 1 ;
                CartOfUser.changeQuantityOfItem(cartOfUserItem, change);

                totalPoint(viewHolder , cartOfUserItem) ;
            }
        });
        viewHolder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int change = cartOfUserItem.getQuantity() - 1   ;
                CartOfUser.changeQuantityOfItem(cartOfUserItem, change);

                if(change == 0){
                    ItemCartAdapter.this.cartOfUserItemData.remove(position);
                    notifyDataSetChanged();
                }
                else {
                    totalPoint(viewHolder , cartOfUserItem) ;
                }


            }

        });



        return view;
    }

    private void totalPoint(ItemCartAdapter.ViewHolder viewHolder , CartOfUserItem cartOfUserItem){
        int total = cartOfUserItem.getBatteryData().getPoint() * cartOfUserItem.getQuantity() ;
        viewHolder.total_point.setText(total+"");
        viewHolder.quantity.setText(cartOfUserItem.getQuantity()+"");
    }
}
