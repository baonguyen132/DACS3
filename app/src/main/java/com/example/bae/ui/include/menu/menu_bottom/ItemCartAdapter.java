package com.example.bae.ui.include.menu.menu_bottom;

import android.content.Context;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.bae.data.Cart.CartData;
import com.example.bae.data.Cart.CartItemData;

import java.util.ArrayList;
import com.example.bae.R;
import com.example.bae.ui.include.menu.menu_bottom.ItemCart.ItemCartFragment;

public class ItemCartAdapter extends BaseAdapter {

    private ArrayList<CartItemData> cartItemDatas ;
    private Context context ;

    public ItemCartAdapter(Context context) {
        this.context = context;
        cartItemDatas = new ArrayList<>(CartData.getCart().values());
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
        return cartItemDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemDatas.get(position);
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

        CartItemData cartItemData = (CartItemData) getItem(position);

        viewHolder.name_battery.setText(cartItemData.getBatteryData().getName_battery());
        viewHolder.point.setText(cartItemData.getBatteryData().getPoint()+"");

        totalPoint(viewHolder , cartItemData) ;

        viewHolder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int change = cartItemData.getQuantity() + 1 ;
                CartData.changeQuantityOfItem(cartItemData , change);

                totalPoint(viewHolder , cartItemData) ;
            }
        });
        viewHolder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int change = cartItemData.getQuantity() - 1   ;
                CartData.changeQuantityOfItem(cartItemData , change);

                if(change == 0){
                    cartItemDatas.remove(position);
                    notifyDataSetChanged();
                }
                else {
                    totalPoint(viewHolder , cartItemData) ;
                }


            }

        });



        return view;
    }

    private void totalPoint(ItemCartAdapter.ViewHolder viewHolder , CartItemData cartItemData){
        int total = cartItemData.getBatteryData().getPoint() * cartItemData.getQuantity() ;
        viewHolder.total_point.setText(total+"");
        viewHolder.quantity.setText(cartItemData.getQuantity()+"");
    }
}
