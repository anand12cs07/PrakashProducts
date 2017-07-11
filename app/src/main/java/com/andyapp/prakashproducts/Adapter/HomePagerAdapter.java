package com.andyapp.prakashproducts.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.andyapp.prakashproducts.R;

public class HomePagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int[] mResource = {R.drawable.toolbarimg, R.drawable.toolbarimg1, R.drawable.toolbarimg2, R.drawable.toolbarimg3};

    public HomePagerAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.viewpager_content_home, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.viewpager_img);
        Glide.with(mContext).load(mResource[position%4]).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
