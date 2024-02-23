package com.example.bae.ui.Battery;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bae.R;
import com.example.bae.data.Battery.BatteryData;

import java.util.ArrayList;

public class BatteryFragment extends Fragment {

    private BatteryViewModel mViewModel;
    private ListView listBattey ;
    private View view ;

    public static BatteryFragment newInstance() {
        return new BatteryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_battery, container, false);

        listBattey = view.findViewById(R.id.l_list_battery);
        ArrayList<BatteryData> batteryData = new ArrayList<>() ;
        batteryData.add(new BatteryData("s")) ;
        batteryData.add(new BatteryData("a")) ;
        batteryData.add(new BatteryData("s")) ;

        listBattey.setAdapter(new BatteryAdapter(getContext() , batteryData));

        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BatteryViewModel.class);
        // TODO: Use the ViewModel
    }

}