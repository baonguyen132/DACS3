package com.example.bae.ui.Voucher.ChangeVoucher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bae.R;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.Voucher.VoucherData;
import com.example.bae.ui.Voucher.ChangeVoucher.ItemChangeVoucher.ItemChangeVoucherFragment;

import java.util.ArrayList;

public class VoucherAdapter extends BaseAdapter {
    ArrayList<VoucherData> voucherDataArrayList ;
    Context context ;

    HanldeConfirm hanldeConfirm ;

    public VoucherAdapter(Context context , ArrayList<VoucherData> voucherDataArrayList , HanldeConfirm hanldeConfirm ) {
        this.voucherDataArrayList = voucherDataArrayList ;
        this.context = context ;
        this.hanldeConfirm = hanldeConfirm ;
    }

    class ViewHolder{

        TextView name_voucher, tv_point_voucher ;
        ImageView image_voucher ;

        View change_voucher ;

        public ViewHolder (View row) {
            name_voucher = row.findViewById(R.id.name_voucher);
            tv_point_voucher = row.findViewById(R.id.tv_point_voucher);
            image_voucher = row.findViewById(R.id.image_voucher);
            change_voucher = row.findViewById(R.id.change_voucher);
        }


    }

    @Override
    public int getCount() {
        return voucherDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return voucherDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view ;
        VoucherAdapter.ViewHolder viewHolder ;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context) ;
            view = ItemChangeVoucherFragment.newInstance().onCreateView(inflater , null , null);
            viewHolder = new VoucherAdapter.ViewHolder(view) ;
            view.setTag(viewHolder);
        }
        else {
            view = convertView ;
            viewHolder = (VoucherAdapter.ViewHolder) view.getTag();
        }

        VoucherData voucherData  = (VoucherData) getItem(position);

        viewHolder.name_voucher.setText(voucherData.getName_voucher());
        viewHolder.tv_point_voucher.setText(voucherData.getPoint());

        String img = RequestCustome.getInstance().getUrlStorage()+"image/Branch/"+voucherData.getId_branch()+".jpg";
        Glide.with(view.getContext()).load(img).into(viewHolder.image_voucher);


        viewHolder.change_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hanldeConfirm.hanldeConfirm(voucherData) ;
            }
        });


        return view;
    }

    interface HanldeConfirm{
        void hanldeConfirm(VoucherData voucherBranchData);
    }
}
