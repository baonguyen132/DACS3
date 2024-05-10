package com.example.bae.data;

import android.content.Context;

import com.google.firebase.storage.FirebaseStorage;

public class FirebaseCustome {


    private static FirebaseCustome firebaseCustome ;
    protected String urlFirebase = "https://firebasestorage.googleapis.com/v0/b/baebase-15fff.appspot.com/o/" ;
    protected String urlFirebaseStorage = urlFirebase+"StorageImage%2F";

    protected FirebaseStorage firebaseStorage  ;

    public static void init(Context context){
        firebaseCustome = new FirebaseCustome() ;
        firebaseCustome.firebaseStorage = FirebaseStorage.getInstance() ;
    }

    public static FirebaseCustome getInstance() {
        if(firebaseCustome == null){
            firebaseCustome = new FirebaseCustome() ;
        }
        return firebaseCustome ;
    }

    public String getUrlFirebase() {
        return urlFirebase;
    }

    public void setUrlFirebase(String urlFirebase) {
        this.urlFirebase = urlFirebase;
    }

    public String getUrlFirebaseStorage() {
        return urlFirebaseStorage;
    }

    public void setUrlFirebaseStorage(String urlFirebaseStorage) {
        this.urlFirebaseStorage = urlFirebaseStorage;
    }


    public FirebaseStorage getFirebaseStorage() {
        return firebaseStorage;
    }

    public void setFirebaseStorage(FirebaseStorage firebaseStorage) {
        this.firebaseStorage = firebaseStorage;
    }
}
