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
import com.example.bae.data.Battery.BatteryRequest;
import com.example.bae.data.RequestCustome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


        BatteryRequest batteryRequest = new BatteryRequest() ;
        batteryRequest.getDataFromServe(new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                ArrayList<BatteryData> batteryData = new ArrayList<>() ;
                JSONArray jsonArray = response.getJSONArray("data") ;
                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    batteryData.add(new BatteryData(jsonObject.getString("id") , jsonObject.getString("name_battery") , jsonObject.getString("size") , jsonObject.getString("shape") , jsonObject.getString("point") , jsonObject.getString("image"))) ;
                }


                listBattey.setAdapter(new BatteryAdapter(getContext() , batteryData));
            }
        });



        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BatteryViewModel.class);
        // TODO: Use the ViewModel
    }

}