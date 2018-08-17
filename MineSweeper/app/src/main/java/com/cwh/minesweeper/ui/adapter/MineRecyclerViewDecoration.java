package com.cwh.minesweeper.ui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cwh.minesweeper.R;

/**
 * Created by chenweihu on 2018/8/17 0017.
 */

public class MineRecyclerViewDecoration extends RecyclerView.ItemDecoration {
    private int outWidth;

    public MineRecyclerViewDecoration(Context context) {
        outWidth = (int) context.getResources().getDimension(R.dimen.recycler_view_divider);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = outWidth;
        outRect.top = outWidth;
        outRect.right = outWidth;
        outRect.bottom = outWidth;
    }
}
