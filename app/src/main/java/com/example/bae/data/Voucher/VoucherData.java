package com.example.bae.data.Voucher;

import com.example.bae.data.Voucher_Branchs.Voucher_BranchData;

import java.io.Serializable;

public class VoucherData implements Serializable {
    private String id,name_voucher, code_voucher, point , id_branch  ;

    public VoucherData(String id, String name_voucher, String code_voucher, String point, String id_branch) {
        this.id = id;
        this.name_voucher = name_voucher;
        this.code_voucher = code_voucher;
        this.point = point;
        this.id_branch = id_branch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_voucher() {
        return name_voucher;
    }

    public void setName_voucher(String name_voucher) {
        this.name_voucher = name_voucher;
    }

    public String getCode_voucher() {
        return code_voucher;
    }

    public void setCode_voucher(String code_voucher) {
        this.code_voucher = code_voucher;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getId_branch() {
        return id_branch;
    }
    public void setId_branch(String id_branch) {
        this.id_branch = id_branch;
    }
}
