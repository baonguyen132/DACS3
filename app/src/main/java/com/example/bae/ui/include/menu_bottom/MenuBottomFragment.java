package com.example.bae.ui.include.menu_bottom;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bae.Interface.replaceFragement;
import com.example.bae.R;
import com.example.bae.ui.Sub.SubFragment;
import com.example.bae.ui.home.HomeFragment;
import com.example.bae.ui.shorts.shortsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MenuBottomFragment extends Fragment implements replaceFragement {

    private MenuBottomViewModel mViewModel;
    private BottomNavigationView bottomNavigationView ;

    private FloatingActionButton fab ;
    private View view ;
    public static MenuBottomFragment newInstance() {
        return new MenuBottomFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view  =  inflater.inflate(R.layout.include_menu_bottom, container, false);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        fab = view.findViewById(R.id.fab);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId() ;
                if(id == R.id.home){
                    replaceFragement(new HomeFragment() , getActivity().getSupportFragmentManager());
                }
                else if(id == R.id.shorts){
                    replaceFragement(new shortsFragment() , getActivity().getSupportFragmentManager());
                }
                else if(id == R.id.subscriptions){
                    replaceFragement(new SubFragment() , getActivity().getSupportFragmentManager());
                }


                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });


        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MenuBottomViewModel.class);
        // TODO: Use the ViewModel
    }


    private void showBottomDialog() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        ListView listView = dialog.findViewById(R.id.listview);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);




        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

}