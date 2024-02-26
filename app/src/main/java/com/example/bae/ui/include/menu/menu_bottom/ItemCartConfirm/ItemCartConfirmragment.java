package com.example.bae.ui.include.menu.menu_bottom.ItemCartConfirm;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bae.R;

public class ItemCartConfirmragment extends Fragment {

    private ItemCartConfirmragmentViewModel mViewModel;

    public static ItemCartConfirmragment newInstance() {
        return new ItemCartConfirmragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_cart_confirmragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ItemCartConfirmragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}