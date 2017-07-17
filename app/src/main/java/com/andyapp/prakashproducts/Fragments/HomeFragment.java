package com.andyapp.prakashproducts.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.andyapp.prakashproducts.Adapter.CategoryRecyclerAdapter;
import com.andyapp.prakashproducts.Adapter.HomePagerAdapter;
import com.andyapp.prakashproducts.HomeActivity;
import com.andyapp.prakashproducts.Models.ItemModel;
import com.andyapp.prakashproducts.R;
import com.andyapp.prakashproducts.Utils.ApiBuilder;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener,
        CategoryRecyclerAdapter.OnitemClickLisener, View.OnTouchListener {

    private View view;
    private byte count = 0;
    private byte pagerResourceSize = 99;
    private ViewPager mviewPager;
    private Handler mhandler;
    private RecyclerView mRecyclerView;
    private boolean mRunUiThread = true;
    private Toolbar toolbar;
    private HomePagerAdapter mcustomPagerAdapter;

    public static String BUNDLE_LIST_TAG = "home_item_list";
    public static String BUNDLE_POSITION_TAG = "home_posion";
    public static String TAG = "homefragment_tag";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcustomPagerAdapter = new HomePagerAdapter(getActivity());
        toolbar = ((HomeActivity) getActivity()).getToolbar();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_home, container, false);
        mviewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_categories);

        toolbar.setTitle(getActivity().getResources().getString(R.string.title));
        toolbar.setSubtitle(getActivity().getResources().getString(R.string.sub_title));

        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(getActivity(), R.layout.card_parent);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
        mviewPager.setAdapter(mcustomPagerAdapter);
        mviewPager.setCurrentItem(25);

        adapter.getOnItemClickLisener(this);
        mviewPager.addOnPageChangeListener(this);
        mviewPager.setOnTouchListener(this);
        return view;
    }


    @Override
    public void getItemPosition(int position) {
        ArrayList<ItemModel> itemModels = new ArrayList<>();
        Bundle bundle = new Bundle();
        ItemsFragment itemsFragment = new ItemsFragment();
        switch (position) {
            case 0:
                itemModels = ApiBuilder.getBuilder().itemBeds;
                break;
            case 1:
                itemModels = ApiBuilder.getBuilder().itemChairs;
                break;
            case 2:
                itemModels = ApiBuilder.getBuilder().itemCumBeds;
                break;
            case 3:
                itemModels = ApiBuilder.getBuilder().itemDinningTables;
                break;
            case 4:
                itemModels = ApiBuilder.getBuilder().itemOfficeChairs;
                break;
            case 5:
                itemModels = ApiBuilder.getBuilder().itemSofas;
                break;
        }
        bundle.putSerializable(BUNDLE_LIST_TAG, itemModels);
        bundle.putInt(BUNDLE_POSITION_TAG, position);
        itemsFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, itemsFragment).addToBackStack(null).commit();
    }

    private void runCrousel() {
        if (mhandler == null)
            mhandler = new Handler();
        mhandler.removeCallbacks(runnable);
        mhandler.postDelayed(runnable, 3000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // runnable get stop on activity destroy
            if (!mRunUiThread)
                return;
            if (mviewPager.getCurrentItem() < pagerResourceSize)
                count++;
            else
                count = 0;
            mviewPager.setCurrentItem(count);
            // To restart handler once runnable get executed
            mhandler.postDelayed(this, 3000);

        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        count = (byte) position;
        if (!mRunUiThread) {
            mRunUiThread = true;
            runCrousel();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (!mRunUiThread) {
            mRunUiThread = true;
            runCrousel();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mRunUiThread = true;
        runCrousel();
    }

    @Override
    public void onPause() {
        super.onPause();
        mRunUiThread = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRunUiThread = false;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mRunUiThread = false;
        return false;
    }
}
