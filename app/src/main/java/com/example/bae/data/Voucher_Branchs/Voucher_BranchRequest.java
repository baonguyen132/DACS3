package com.example.bae.data.Voucher_Branchs;

import com.example.bae.data.RequestCustome;

public class Voucher_BranchRequest {
    public static void  getBranch(RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("branchapi" , handleResponeJSON);
    }
    public static void getCount( RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("branchapi/number_detail", handleResponeJSON);
    }
}
