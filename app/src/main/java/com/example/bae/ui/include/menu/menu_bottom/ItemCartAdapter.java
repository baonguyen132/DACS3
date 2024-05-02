package com.example.bae.ui.include.menu.menu_bottom;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.bae.data.CartNotConfirm.CartNotConfirm;
import com.example.bae.data.CartNotConfirm.CartNotConfirmItem;

import java.util.ArrayList;
import com.example.bae.R;
import com.example.bae.ui.include.menu.menu_bottom.ItemCart.ItemCartFragment;

public class ItemCartAdapter extends BaseAdapter {

    private ArrayList<CartNotConfirmItem> cartNotConfirmItemData;
    private Context context ;

    public ItemCartAdapter(Context context) {
        this.context = context;
        cartNotConfirmItemData = new ArrayList<>(CartNotConfirm.getCart().values());
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

        CartNotConfirmItem cartNotConfirmItem = (CartNotConfirmItem) getItem(position);

        viewHolder.name_battery.setText(cartNotConfirmItem.getBatteryData().getName_battery());
        viewHolder.point.setText(cartNotConfirmItem.getBatteryData().getPoint()+"");

        totalPoint(viewHolder , cartNotConfirmItem) ;

        viewHolder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int change = cartNotConfirmItem.getQuantity() + 1 ;
                CartNotConfirm.changeQuantityOfItem(cartNotConfirmItem, change);

                totalPoint(viewHolder , cartNotConfirmItem) ;
            }
        });
        viewHolder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int change = cartNotConfirmItem.getQuantity() - 1   ;
                CartNotConfirm.changeQuantityOfItem(cartNotConfirmItem, change);

                if(change == 0){
                    ItemCartAdapter.this.cartNotConfirmItemData.remove(position);
                    notifyDataSetChanged();
                }
                else {
                    totalPoint(viewHolder , cartNotConfirmItem) ;
                }


            }

        });



        return view;
    }

    private void totalPoint(ItemCartAdapter.ViewHolder viewHolder , CartNotConfirmItem cartNotConfirmItem){
        int total = cartNotConfirmItem.getBatteryData().getPoint() * cartNotConfirmItem.getQuantity() ;
        viewHolder.total_point.setText(total+"");
        viewHolder.quantity.setText(cartNotConfirmItem.getQuantity()+"");
    }
}
