package com.example.bae.ui.Battery.CardViewBattery;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bae.R;

public class CardViewBatteryFragment extends Fragment {

    private CardViewBatteryViewModel mViewModel;

    public static CardViewBatteryFragment newInstance() {
        return new CardViewBatteryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_view_battery, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CardViewBatteryViewModel.class);
        // TODO: Use the ViewModel
    }

}