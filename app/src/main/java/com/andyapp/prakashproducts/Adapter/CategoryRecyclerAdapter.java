package com.andyapp.prakashproducts.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.andyapp.prakashproducts.R;


public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private int resource;
    private OnitemClickLisener position;

    private String[] titleArray;

    private String[] subtitleArray;

    private int[] imageResourceArray = {R.drawable.toolbarimg, R.drawable.toolbarimg1, R.drawable.toolbarimg2,
            R.drawable.toolbarimg3, R.drawable.toolbarimg1, R.drawable.toolbarimg};

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title, subtitle;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.card_parent_title);
            subtitle = (TextView) itemView.findViewById(R.id.card_parent_subtitle);
            imageView = (ImageView) itemView.findViewById(R.id.card_parent_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            position.getItemPosition(getAdapterPosition());
        }

    }

    public void getOnItemClickLisener(OnitemClickLisener position) {
        this.position = position;
    }

    public CategoryRecyclerAdapter(Context context, int resource) {
        this.context = context;
        this.resource = resource;
        titleArray = context.getResources().getStringArray(R.array.category_title);
        subtitleArray = context.getResources().getStringArray(R.array.category_subtitle);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(resource, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(titleArray[position]);
        holder.subtitle.setText(subtitleArray[position]);
        Glide.with(context).load(imageResourceArray[position]).thumbnail(0.5f).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return titleArray.length;
    }

    public interface OnitemClickLisener {
        void getItemPosition(int position);
    }
}
