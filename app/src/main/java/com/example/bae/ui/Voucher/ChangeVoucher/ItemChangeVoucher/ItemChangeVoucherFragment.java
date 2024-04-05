package com.example.bae.ui.Voucher.ChangeVoucher.ItemChangeVoucher;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bae.R;

public class ItemChangeVoucherFragment extends Fragment {

    private ItemChangeVoucherViewModel mViewModel;

    public static ItemChangeVoucherFragment newInstance() {
        return new ItemChangeVoucherFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_change_voucher, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ItemChangeVoucherViewModel.class);
        // TODO: Use the ViewModel
    }

}