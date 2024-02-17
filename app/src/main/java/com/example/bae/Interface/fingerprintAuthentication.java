package com.example.bae.Interface;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.Executor;

public interface fingerprintAuthentication {
    default void figerAuthen(Activity activity , fingerprintAuthentication.Succeeded succeeded , fingerprintAuthentication.Error error , fingerprintAuthentication.Failed failed){
        BiometricPrompt biometricPrompt ;
        BiometricPrompt.PromptInfo promptInfo ;

        Context context = activity.getApplicationContext() ;
        BiometricManager biometricManager = BiometricManager.from(context);
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(context , "Device doesn't have fingerprint" , Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(context , "Not working" , Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(context , "No FingerPrint Assigned" , Toast.LENGTH_LONG).show();
                break;

        }

        Executor executor = ContextCompat.getMainExecutor(context);

        biometricPrompt = new BiometricPrompt((FragmentActivity) activity, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                error.Errors();
                super.onAuthenticationError(errorCode, errString);

            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                succeeded.Succeededs();
                super.onAuthenticationSucceeded(result);
            }

            @Override
            public void onAuthenticationFailed() {
                failed.Faileds();
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Tech Projects")
                .setDescription("Use Finger To Login")
                .setDeviceCredentialAllowed(true)
                .build() ;
        biometricPrompt.authenticate(promptInfo);

    }

     interface Succeeded{
        void Succeededs();
     }
    interface Error{
        void Errors();
    }
    interface Failed{
        void Faileds();
    }


}
