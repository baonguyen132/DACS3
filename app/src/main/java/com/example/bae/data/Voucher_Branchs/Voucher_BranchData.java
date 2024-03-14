package com.example.bae.data.Voucher_Branchs;

import java.io.Serializable;

public class Voucher_BranchData implements Serializable {
    String id , name , count ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Voucher_BranchData(String id, String name, String count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}
