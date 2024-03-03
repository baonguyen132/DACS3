package com.example.bae.ui.Cart;

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
import com.example.bae.data.Carts.CartData;
import com.example.bae.data.Carts.CartRequest;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    private CartViewModel mViewModel;
    private View view ;
    private ListView listView ;
    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history, container, false);
        listView = view.findViewById(R.id.listCart);

        UserData user = DataLocalManager.getUser() ;
        CartRequest cartRequest = new CartRequest() ;
        cartRequest.getDataCart(user.getId(), new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                JSONArray jsonArray = response.getJSONArray("data") ;
                ArrayList<CartData> cartData = new ArrayList<>() ;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    cartData.add(new CartData(jsonObject.getString("id"),jsonObject.getString("created_at"), jsonObject.getString("token") ,jsonObject.getInt("total")));
                }

                listView.setAdapter(new CartAdapter(getContext() , cartData));
            }
        });



        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        // TODO: Use the ViewModel
    }

}