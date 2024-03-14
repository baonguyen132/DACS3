package com.example.bae.data.Voucher;

public class VoucherData {
    private String id,name_voucher, code_voucher, point , id_branch_voucher ;

    public VoucherData(String id, String name_voucher, String code_voucher, String point, String id_branch_voucher) {
        this.id = id;
        this.name_voucher = name_voucher;
        this.code_voucher = code_voucher;
        this.point = point;
        this.id_branch_voucher = id_branch_voucher;
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

    public String getId_branch_voucher() {
        return id_branch_voucher;
    }

    public void setId_branch_voucher(String id_branch_voucher) {
        this.id_branch_voucher = id_branch_voucher;
    }
}
