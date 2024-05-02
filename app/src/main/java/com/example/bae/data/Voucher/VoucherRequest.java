package com.example.bae.data.Voucher;

import android.content.Context;
import android.widget.Toast;

import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;

import org.json.JSONException;

import java.util.Map;

public class VoucherRequest {
    public static void  getVoucher(String id_branch, String id_client, RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("voucherapi/branch="+id_branch+"/client="+id_client , handleResponeJSON);
    }
    public static void  getVoucherOfUser(String id_client, RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("voucherapi/voucherofuser/client="+id_client , handleResponeJSON);
    }

    public static void getCount( String id_branch , RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("voucherapi/number_detail/branch=" + id_branch, handleResponeJSON);
    }
    public static void removeVoucher(Context context ,VoucherData voucherData){
        RequestCustome.RequestData("voucherapi/remove_voucher", new RequestCustome.HandleRequest() {
            @Override
            public void hanldeRequest(String respone) throws JSONException {
                Toast.makeText(context , respone , Toast.LENGTH_LONG).show();
            }
        }, new RequestCustome.setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("idClient" , DataLocalManager.getUser().getId());
                params.put("idVoucher" , voucherData.getId());
            }
        });
    }

}
