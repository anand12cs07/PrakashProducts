package com.andyapp.prakashproducts.Adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.andyapp.prakashproducts.Models.ItemModel;
import com.andyapp.prakashproducts.R;

import java.util.ArrayList;

public class DeatailPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<ItemModel> itemModels;

    public DeatailPagerAdapter(Context mContext, ArrayList<ItemModel> itemModels) {
        this.mContext = mContext;
        this.itemModels = itemModels;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return itemModels.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.viewpager_content_detail, container, false);
        ImageView img = (ImageView) itemView.findViewById(R.id.viewPager_itemImage);
        TextView name = (TextView) itemView.findViewById(R.id.viewPager_itemName);
        TextView weight = (TextView) itemView.findViewById(R.id.viewPager_itemWeight);
        TextView price = (TextView) itemView.findViewById(R.id.viewPager_itemPrice);
        TextView dimension = (TextView) itemView.findViewById(R.id.viewPager_itemDimension);

        Glide.with(mContext).load(itemModels.get(position).getItemImage()).into(img);
        name.setText(itemModels.get(position).getItemName());
        weight.setText(itemModels.get(position).getItemWeight());
        price.setText(itemModels.get(position).getItemPrice());
        dimension.setText(itemModels.get(position).getItemSize());

        container.addView(itemView);

        return itemView;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
