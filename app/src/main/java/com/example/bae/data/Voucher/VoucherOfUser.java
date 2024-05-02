package com.example.bae.data.Voucher;

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

    public static void putItemVoucher(VoucherData voucherData){
        VoucherOfUser.getInstance().listvoucherOfUser.put(voucherData.getId() , voucherData);
        DataLocalManager.setVoucherOfUser(VoucherOfUser.getInstance());
    }

    public static boolean removeItemVoucher(String idKey){
        try {
            VoucherOfUser.getInstance().listvoucherOfUser.remove(idKey) ;
            DataLocalManager.setVoucherOfUser(VoucherOfUser.getInstance());
            return true ;
        }
        catch (Exception e){
            return false ;
        }
    }

    public static void removeVoucherOfUser(){
        DataLocalManager.removeVoucherOfUser();
        VoucherOfUser.init();
    }

    public static void setListvoucherOfUser(HashMap<String, VoucherData> listvoucherOfUser) {
        VoucherOfUser.getInstance().listvoucherOfUser = listvoucherOfUser ;
        DataLocalManager.setVoucherOfUser(VoucherOfUser.getInstance());
    }

    public static HashMap<String, VoucherData> getVoucherOfUser() {
        return VoucherOfUser.getInstance().listvoucherOfUser;
    }
}
