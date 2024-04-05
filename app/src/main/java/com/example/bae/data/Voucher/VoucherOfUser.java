package com.example.bae.data.Voucher;

import android.util.Log;

import com.example.bae.data.CartOfUser.CartOfUser;
import com.example.bae.data.CartOfUser.CartOfUserItem;
import com.example.bae.data.SharedPreferences.DataLocalManager;

import java.util.HashMap;

public class VoucherOfUser {
    private static VoucherOfUser instance ;
    private HashMap<String , VoucherData> listvoucherOfUser ;

    public static void init(){
        instance = DataLocalManager.getVoucherOfUser() ;
        if(instance == null){
            instance = new VoucherOfUser() ;
            instance.listvoucherOfUser = new HashMap<String , VoucherData>() ;
        }

    }
    public static VoucherOfUser getInstance() {
        if(instance == null){
            instance = new VoucherOfUser() ;
        }
        return instance ;
    }

    public static void putVoucher(VoucherData voucherData){
        VoucherOfUser.getInstance().listvoucherOfUser.put(voucherData.getId() , voucherData);
        DataLocalManager.setVoucherOfUser(VoucherOfUser.getInstance());
    }

    public static void setListvoucherOfUser(HashMap<String, VoucherData> listvoucherOfUser) {
        VoucherOfUser.getInstance().listvoucherOfUser = listvoucherOfUser ;
        DataLocalManager.setVoucherOfUser(VoucherOfUser.getInstance());
    }

    public static HashMap<String, VoucherData> getVoucherOfUser() {
        return VoucherOfUser.getInstance().listvoucherOfUser;
    }

    public static void removeItem(String idKey){
        VoucherOfUser.getInstance().listvoucherOfUser.remove(idKey) ;
        VoucherOfUser.init();
    }

    public static void removeVoucherOfUser(){
        DataLocalManager.removeVoucherOfUser();
        VoucherOfUser.getInstance().listvoucherOfUser = null ;
    }

}
