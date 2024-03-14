package com.example.bae.data.Voucher;

import com.example.bae.data.RequestCustome;

public class VoucherRequest {
    public static void  getVoucher(String id_branch, String id_client, RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("voucherapi/"+id_branch+"/"+id_client , handleResponeJSON);
    }
}
