package com.andyapp.prakashproducts.Adapter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CustomItemDecorator extends RecyclerView.ItemDecoration {

    private final int mVerticalSpaceHeight;

    public CustomItemDecorator(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)
            outRect.bottom = mVerticalSpaceHeight;
        outRect.left = mVerticalSpaceHeight + 20;
        outRect.right = mVerticalSpaceHeight + 20;

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}
