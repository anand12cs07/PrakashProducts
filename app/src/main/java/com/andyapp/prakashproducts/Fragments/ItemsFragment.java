package com.andyapp.prakashproducts.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andyapp.prakashproducts.Adapter.ItemRecyclerAdapter;
import com.andyapp.prakashproducts.HomeActivity;
import com.andyapp.prakashproducts.Models.ItemModel;
import com.andyapp.prakashproducts.R;

import java.util.ArrayList;

public class ItemsFragment extends Fragment implements ItemRecyclerAdapter.OnitemClickLisener {
    View view;
    public static String TAG_ITEM_MODELS = "item_item_models";
    public static String TAG_ITEM_POSITION = "item_item_position";
    public static String TAG = "itemsfragment_tag";

    private int position;
    private Toolbar toolbar;
    private String[] toolbarTitle;
    private String[] toolbarSubTitle;
    private ArrayList<ItemModel> itemModels;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarTitle = getResources().getStringArray(R.array.category_title);
        toolbarSubTitle = getResources().getStringArray(R.array.category_subtitle);
        toolbar = ((HomeActivity)getActivity()).getToolbar();

        Bundle bundle = getArguments();
        itemModels = (ArrayList<ItemModel>) bundle.getSerializable(HomeFragment.BUNDLE_LIST_TAG);
        position = bundle.getInt(HomeFragment.BUNDLE_POSITION_TAG);

        toolbar.setTitle(toolbarTitle[position]);
        toolbar.setSubtitle(toolbarSubTitle[position]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_items, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_items);

        ItemRecyclerAdapter adapter = new ItemRecyclerAdapter(getActivity(), itemModels);
        adapter.getOnItemClickLisener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void getItemPosition(int position) {
        Bundle bundle = new Bundle();
        DetailFragment detailFragment = new DetailFragment();
        bundle.putSerializable(TAG_ITEM_MODELS, itemModels);
        bundle.putInt(TAG_ITEM_POSITION, position);
        detailFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, detailFragment).addToBackStack(null).commit();

    }
}
