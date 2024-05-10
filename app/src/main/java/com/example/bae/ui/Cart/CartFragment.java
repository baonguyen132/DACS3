package com.example.bae.ui.Cart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bae.R;
import com.example.bae.data.Carts.CartData;
import com.example.bae.data.Carts.CartOfUser;
import com.example.bae.data.Carts.CartRequest;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class CartFragment extends Fragment {

    private CartViewModel mViewModel;
    private View view, layouttotal , layoutConfirmed , layoutNotConfirmed ;
    private ListView listView ;
    private TextView total , confirmed , not_confirm ;

    private ImageView imageView ;

    public static CartFragment newInstance() {
        return new CartFragment();
    }
    private UserData user ;
    private CartRequest cartRequest ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history, container, false);
        listView = view.findViewById(R.id.listCart);
        total = view.findViewById(R.id.total_history);
        confirmed = view.findViewById(R.id.confirmed);
        not_confirm = view.findViewById(R.id.not_confirm);

        layouttotal = view.findViewById(R.id.layoutTotal);
        layoutConfirmed = view.findViewById(R.id.layoutConfirmed);
        layoutNotConfirmed = view.findViewById(R.id.layoutNotConfirm);
        imageView = view.findViewById(R.id.refresh);


        user = DataLocalManager.getUser() ;
        cartRequest = new CartRequest() ;


        setView();
        getTotal();
        layouttotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTotal();
            }
        });

        layoutConfirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConfirmed();
            }
        });

        layoutNotConfirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNotConfirm();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartOfUser.removeCartOfUser();
                setView();
                getTotal();
            }
        });

        return view ;
    }

    private void getTotal(){

        if(CartOfUser.getStringCartDataHashMap().size() == 0){
            cartRequest.getDataCart(user.getId(), new RequestCustome.HandleResponeJSON() {
                @Override
                public void handleResponeJSON(JSONObject response) throws JSONException {
                    createList(response) ;
                }
            });

        }
        else {

            ArrayList<CartData> cartDatas = new ArrayList<>(CartOfUser.getStringCartDataHashMap().values());
            listView.setAdapter(new CartAdapter(getContext() , cartDatas));

        }

    }

    private void getConfirmed(){
        ArrayList<CartData> cartDatas = new ArrayList<>(CartOfUser.getStringCartDataHashMap().values());
        ArrayList<CartData> cartData = new ArrayList<>();
        for (int i = 0; i < cartDatas.size(); i++) {

            if(cartDatas.get(i).getToken().equals("NULL")){
                cartData.add(cartDatas.get(i));
            }
        }
        listView.setAdapter(new CartAdapter(getContext() , cartData));

    }
    private void getNotConfirm(){
        ArrayList<CartData> cartDatas = new ArrayList<>(CartOfUser.getStringCartDataHashMap().values());
        ArrayList<CartData> cartData = new ArrayList<>();
        for (int i = 0; i < cartDatas.size(); i++) {

            if(!cartDatas.get(i).getToken().equals("NULL")){
                cartData.add(cartDatas.get(i));
            }
        }
        listView.setAdapter(new CartAdapter(getContext() , cartData));


    }

    private void createList(JSONObject response) throws JSONException {
        JSONArray jsonArray = response.getJSONArray("data") ;
        HashMap<String , CartData> cartData = new HashMap<>() ;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            cartData.put(jsonObject.getString("id") , new CartData(jsonObject.getString("id"),jsonObject.getString("created_at"), jsonObject.getString("token") , jsonObject.getString("address") ,jsonObject.getInt("total"), jsonObject.getString("image") ));
        }
        CartOfUser.setStringCartDataHashMap(cartData);
        ArrayList<CartData> cartDatas = new ArrayList<>(cartData.values());
        listView.setAdapter(new CartAdapter(getContext() , cartDatas));
    }

    private void setView(){
        cartRequest.getCount(user.getId(), new RequestCustome.HandleResponeJSON() {
            @Override
            public void handleResponeJSON(JSONObject response) throws JSONException {
                total.setText(response.getString("total"));
                confirmed.setText(response.getString("Confirmed"));
                not_confirm.setText(response.getString("notConfirm"));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        // TODO: Use the ViewModel
    }
}