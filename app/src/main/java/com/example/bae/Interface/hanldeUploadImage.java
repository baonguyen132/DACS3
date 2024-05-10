package com.example.bae.Interface;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bae.data.FirebaseCustome;
import com.example.bae.ui.Information.InformationActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public interface hanldeUploadImage {
     static void handle(Bitmap bitmap  ,String nameFile, String cccd , hanldeUploadImage hanldeUploadImage){

        StorageReference storeRef = FirebaseCustome.getInstance().getFirebaseStorage().getReferenceFromUrl("gs://baebase-15fff.appspot.com/StorageImage/"+cccd);
        StorageReference mountainsRef = storeRef.child(nameFile+".png" );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                hanldeUploadImage.fail();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                hanldeUploadImage.success();
            }
        });
    }

    void success() ;
     void fail() ;

}
