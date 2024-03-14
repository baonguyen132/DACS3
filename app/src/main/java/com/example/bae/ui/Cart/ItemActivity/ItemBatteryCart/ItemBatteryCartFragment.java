package com.example.bae.ui.Cart.ItemActivity.ItemBatteryCart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bae.R;

public class ItemBatteryCartFragment extends Fragment {

    private ItemBatteryCartViewModel mViewModel;

    public static ItemBatteryCartFragment newInstance() {
        return new ItemBatteryCartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_battery_cart, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ItemBatteryCartViewModel.class);
        // TODO: Use the ViewModel
    }

}