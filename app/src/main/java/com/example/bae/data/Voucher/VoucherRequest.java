package com.example.bae.data.Voucher;

import com.example.bae.data.RequestCustome;

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

}
