package com.andyapp.prakashproducts.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.andyapp.prakashproducts.Models.ItemModel;
import com.andyapp.prakashproducts.R;

import java.util.ArrayList;


public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.MyViewHolder> {

    private Context context;
    private int resource;
    private OnitemClickLisener position;
    private ArrayList<ItemModel> itemModels;
    private ImageView img;

    public ItemRecyclerAdapter(Context context, ArrayList<ItemModel> itemModels) {
        this.context = context;
        this.itemModels = itemModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                resource = R.layout.card_child_left;
                break;
            case 1:
                resource = R.layout.card_child_right;
                break;
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int count = 0;
        holder.title.setText(itemModels.get(position).getItemName());
        holder.weight.setText(itemModels.get(position).getItemPrice());
        Glide.with(context).load(itemModels.get(position).getItemSmallImage()).into(holder.img);
        holder.progressBar.setVisibility(View.GONE);

        holder.like.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show();
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    public void getOnItemClickLisener(OnitemClickLisener position) {
        this.position = position;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title, weight;
        private ImageButton like, share;
        private ProgressBar progressBar;
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.card_child_title);
            weight = (TextView) itemView.findViewById(R.id.card_child_subtitle);
            img = (ImageView) itemView.findViewById(R.id.card_child_img);
            like = (ImageButton) itemView.findViewById(R.id.card_child_like);
            share = (ImageButton) itemView.findViewById(R.id.card_child_share);
            progressBar = (ProgressBar)itemView.findViewById(R.id.card_child_progress);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            position.getItemPosition(getAdapterPosition());
        }
    }


    public interface OnitemClickLisener {
        void getItemPosition(int position);
    }


}
