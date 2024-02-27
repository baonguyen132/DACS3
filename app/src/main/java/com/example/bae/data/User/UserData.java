package com.example.bae.data.User;

import android.content.Context;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;

import com.example.bae.data.RequestCustome;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.Serializable;
import java.util.Map;

public class UserData  implements Serializable {
    String  id , name, cccd , dob, gender, pob , address , email ;
    int status , point ;

    public UserData(){

    }
    public UserData(String id , String email , String name , String cccd , String dob , String gender , String pob , String address , int status , int point ) {

        this.id = id ;
        this.name = name ;
        this.cccd = cccd ;
        this.dob = dob ;
        this.gender = gender ;
        this.pob = pob ;
        this.address = address ;
        this.status = status ;
        this.point = point ;
        this.email = email ;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public int getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public String getCccd() {
        return cccd;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getPob() {
        return pob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void sendOtp(String codeOtp , Context context){



        RequestCustome.RequestData("auth/sendOtp", new RequestCustome.HandleRequest() {
            @Override
            public void hanldeRequest(String respone) {
                Toast.makeText(context , respone , Toast.LENGTH_LONG).show();
            }
        }, new RequestCustome.setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("email" , getEmail());
                params.put("codeOtp" , codeOtp) ;
            }
        }) ;
    }

    public void scanQR(ActivityResultLauncher<ScanOptions> barLauncher){
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("Hãy quét mã QR trên căn cước công dân") ;
        options.setCameraId(0) ;
        options.setBeepEnabled(true) ;
        options.setOrientationLocked(true) ;
        options.setCaptureActivity(CaptureActivity.class);

        barLauncher.launch(options);
    }
}
