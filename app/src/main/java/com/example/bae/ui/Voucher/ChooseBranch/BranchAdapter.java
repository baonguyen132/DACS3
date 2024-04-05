package com.example.bae.ui.Voucher.ChooseBranch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bae.R;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.Voucher_Branchs.Voucher_BranchData;
import com.example.bae.ui.Voucher.ChangeVoucher.ChangeVoucherActivity;
import com.example.bae.ui.Voucher.ChooseBranch.ItemChooseBranch.ItemChooseBranchFragment;

import java.util.ArrayList;

public class BranchAdapter extends BaseAdapter {

    ArrayList<Voucher_BranchData> voucherBranchDataArrayList ;
    Context context ;

    public BranchAdapter(Context context , ArrayList<Voucher_BranchData> voucherBranchDataArrayList) {
        this.voucherBranchDataArrayList = voucherBranchDataArrayList ;
        this.context = context ;
    }

    class ViewHolder{

        TextView name_branch, tv_number_voucher ;
        ImageView image_branch ;
        View open_listview ;

        public ViewHolder (View row) {
            name_branch = row.findViewById(R.id.name_branch);
            tv_number_voucher = row.findViewById(R.id.tv_number_voucher);
            image_branch = row.findViewById(R.id.image_branch);
            open_listview = row.findViewById(R.id.open_listvoucher);
        }


    }

    @Override
    public int getCount() {
        return voucherBranchDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return voucherBranchDataArrayList.get(position);
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
            view = ItemChooseBranchFragment.newInstance().onCreateView(inflater , null , null);
            viewHolder = new ViewHolder(view) ;
            view.setTag(viewHolder);
        }
        else {
            view = convertView ;
            viewHolder = (ViewHolder) view.getTag();
        }

        Voucher_BranchData voucherBranchData  = (Voucher_BranchData) getItem(position);

        viewHolder.name_branch.setText(voucherBranchData.getName());
        viewHolder.tv_number_voucher.setText(voucherBranchData.getCount());

        String img = RequestCustome.getInstance().getUrlStorage()+"image/Branch/"+voucherBranchData.getId()+".jpg";
        Glide.with(view.getContext()).load(img).into(viewHolder.image_branch);

        viewHolder.open_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context  , ChangeVoucherActivity.class) ;
                intent.putExtra("data" , voucherBranchData);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                context.startActivity(intent);

            }
        });


        return view;
    }
}
