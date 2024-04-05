package com.example.bae.ui.Voucher.VoucherOfUser;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bae.R;
import com.example.bae.ui.Voucher.VoucherOfUser.ItemVoucherOfUser.ItemVoucherOfUserViewModel;

public class ItemVoucherOfUserFragment extends Fragment {

    private ItemVoucherOfUserViewModel mViewModel;

    public static ItemVoucherOfUserFragment newInstance() {
        return new ItemVoucherOfUserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_voucher_of_user, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ItemVoucherOfUserViewModel.class);
        // TODO: Use the ViewModel
    }

}