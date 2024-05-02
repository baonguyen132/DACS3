package com.example.bae.ui.Voucher.VoucherOfUser;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bae.R;
import com.example.bae.data.Voucher.VoucherData;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.example.bae.ui.Voucher.VoucherOfUser.ItemVoucherOfUserFragment ;

import java.util.ArrayList;

public class VoucherOfUserAdapter extends BaseAdapter {
    ArrayList<VoucherData> voucherDataArrayList ;
    Context context ;
    ShowDialogDelete showDialogDelete ;

    public VoucherOfUserAdapter( Context context, ArrayList<VoucherData> voucherDataArrayList , ShowDialogDelete showDialogDelete) {
        this.voucherDataArrayList = voucherDataArrayList;
        this.context = context;
        this.showDialogDelete = showDialogDelete ;
    }

    class ViewHolder{

        TextView name_voucher, tv_point_voucher , title_branch , value_branch ;
        ImageView image_voucher ;

        View view ;

        public ViewHolder (View row) {
            name_voucher = row.findViewById(R.id.name_voucher);
            tv_point_voucher = row.findViewById(R.id.tv_point_voucher);
            image_voucher = row.findViewById(R.id.image_voucher);
            title_branch = row.findViewById(R.id.textView21branch);
            value_branch = row.findViewById(R.id.tv_branch_voucher);
            view = row.findViewById(R.id.LayoutVoucherOfUser);
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
        VoucherOfUserAdapter.ViewHolder viewHolder ;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context) ;
            view = ItemVoucherOfUserFragment.newInstance().onCreateView(inflater , null , null);
            viewHolder = new VoucherOfUserAdapter.ViewHolder(view) ;
            view.setTag(viewHolder);
        }
        else {
            view = convertView ;
            viewHolder = (VoucherOfUserAdapter.ViewHolder) view.getTag();
        }

        VoucherData voucherData  = (VoucherData) getItem(position);

        viewHolder.name_voucher.setText(voucherData.getName_voucher());
        viewHolder.tv_point_voucher.setText(voucherData.getPoint());
        
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter() ;
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(voucherData.getCode_voucher() , BarcodeFormat.QR_CODE , 500 , 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            viewHolder.image_voucher.setImageBitmap(bitmap);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        viewHolder.title_branch.setVisibility(View.VISIBLE);
        viewHolder.value_branch.setVisibility(View.VISIBLE);
        viewHolder.value_branch.setText(voucherData.getId_branch());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDelete.show(voucherData);
            }
        });
        return view;
    }

    interface ShowDialogDelete{
        void show(VoucherData voucherData);
    }

}
