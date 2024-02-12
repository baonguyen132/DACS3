package com.example.bae.Interface;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public interface getUserAuth {
    default FirebaseUser getUserAuth(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance() ;
        FirebaseUser user = firebaseAuth.getCurrentUser() ;
        return user ;
    }
}
