package com.example.bae.ui.include.menu.menu_bottom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.bae.R;
import com.example.bae.data.Cart.CartData;
import com.example.bae.data.Cart.CartItemData;
import com.example.bae.ui.include.menu.menu_bottom.ItemCart.ItemCartFragment;
import com.example.bae.ui.include.menu.menu_bottom.ItemCartConfirm.ItemCartConfirmragment;

import java.util.ArrayList;

public class ItemCartConfirmAdapter extends BaseAdapter {
    private ArrayList<CartItemData> cartItemDatas ;
    private Context context ;

    public ItemCartConfirmAdapter(Context context) {
        this.context = context;
        cartItemDatas = new ArrayList<>(CartData.getCart().values());
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

        CartItemData cartItemData = (CartItemData) getItem(position);

        viewHolder.name_battery.setText(cartItemData.getBatteryData().getName_battery());
        viewHolder.point.setText(cartItemData.getBatteryData().getPoint()+"");

        totalPoint(viewHolder , cartItemData) ;



        return view;
    }

    private void totalPoint(ItemCartConfirmAdapter.ViewHolder viewHolder , CartItemData cartItemData){
        int total = cartItemData.getBatteryData().getPoint() * cartItemData.getQuantity() ;
        viewHolder.total_point.setText(total+"");
        viewHolder.quantity.setText(cartItemData.getQuantity()+"");
    }
}
