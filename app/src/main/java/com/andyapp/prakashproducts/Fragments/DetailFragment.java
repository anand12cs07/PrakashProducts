package com.andyapp.prakashproducts.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andyapp.prakashproducts.Adapter.DeatailPagerAdapter;
import com.andyapp.prakashproducts.Models.ItemModel;
import com.andyapp.prakashproducts.R;

import java.util.ArrayList;

public class DetailFragment extends Fragment {

    private ArrayList<ItemModel> itemModels;
    private int position;
    private View view;

    public static String TAG = "detailfragment_tag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagerDetail);

        Bundle bundle = getArguments();

        itemModels = (ArrayList<ItemModel>) bundle.getSerializable(ItemsFragment.TAG_ITEM_MODELS);
        position = bundle.getInt(ItemsFragment.TAG_ITEM_POSITION, 0);

        DeatailPagerAdapter deatailPagerAdapter = new DeatailPagerAdapter(getActivity(), itemModels);
        viewPager.setAdapter(deatailPagerAdapter);
        viewPager.setCurrentItem(position);

        return view;
    }

}
