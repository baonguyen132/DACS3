package com.example.bae.ui.Voucher.ItemChooseBranch;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bae.R;

public class ItemChooseBranchFragment extends Fragment {

    private ItemChooseBranchViewModel mViewModel;

    public static ItemChooseBranchFragment newInstance() {
        return new ItemChooseBranchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_choose_branch, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ItemChooseBranchViewModel.class);
        // TODO: Use the ViewModel
    }

}