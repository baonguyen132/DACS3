package com.example.bae.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bae.R;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.ui.Voucher.ChooseBranch.ChooseBranchActivity;
import com.example.bae.ui.Voucher.VoucherOfUser.VoucherOfUserActivity;

public class HomeFragment extends Fragment  {

    private HomeViewModel mViewModel;
    private View cv_change_voucher_home , cv_voucher_of_user_home ;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    private View view ;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        UserData userData = DataLocalManager.getUser() ;
        cv_change_voucher_home = view.findViewById(R.id.cv_change_voucher_home);
        cv_voucher_of_user_home = view.findViewById(R.id.cv_voucher_of_user_home);
        cv_change_voucher_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userData != null){
                    startActivity(new Intent(getActivity() , ChooseBranchActivity.class));
                }
                else {
                    Toast.makeText(getContext() , "Bạn cần đăng nhập" , Toast.LENGTH_LONG).show();
                }
            }
        });
        cv_voucher_of_user_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userData != null){
                    startActivity(new Intent(getActivity() , VoucherOfUserActivity.class));
                }
                else {
                    Toast.makeText(getContext() , "Bạn cần đăng nhập" , Toast.LENGTH_LONG).show();
                }
            }
        });


//        UserData user = DataLocalManager.getUser() ;
//        if(user != null){
//            name.setVisibility(View.VISIBLE);
//            login.setVisibility(View.INVISIBLE);
//
//            name.setText(user.getName().replace(' ' , '\n'));
//        }
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext() , LoginActivity.class));
//
//            }
//        });




        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}