package com.example.bae.ui.Cart;


import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.bae.R;

import com.example.bae.data.Carts.CartData;

import com.example.bae.ui.Cart.CartItem.CartItemFragment;


import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    ArrayList<CartData> cartDatas ;
    Context context ;

    public CartAdapter(Context context , ArrayList<CartData> cartDatas) {
        this.cartDatas = cartDatas ;
        this.context = context ;
    }

    class ViewHolder{

        private TextView tvId , tvPoint, tvDate,tvstatus ;

        private FrameLayout layout ;

        public ViewHolder (View row) {

            tvId = row.findViewById(R.id.tv_history_item_id) ;
            tvPoint = row.findViewById(R.id.tv_history_item_point) ;
            tvDate =row.findViewById(R.id.tv_history_item_date) ;
            layout = row.findViewById(R.id.item_history) ;
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

        viewHolder.tvId.setText(cartData.getId());
        viewHolder.tvPoint.setText(cartData.getTotal()+"");

        String[] date = cartData.getCreated_at().split("T");
        viewHolder.tvDate.setText(date[0]);

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context , ItemHistoryActivity.class) ;
                intent.putExtra("data" , cartData);
                context.startActivity(intent);
            }
        });


        return view;
    }
}
