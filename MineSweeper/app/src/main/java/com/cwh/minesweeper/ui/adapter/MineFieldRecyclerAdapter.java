package com.cwh.minesweeper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.bean.Block;

import java.util.ArrayList;

/**
 * Created by chenweihu on 2018/8/17 0017.
 */

public class MineFieldRecyclerAdapter extends RecyclerView.Adapter<MineFieldRecyclerAdapter.MineFieldRecyclerAdapterHolder> {

    private ArrayList<Block> mBlockList;
    private Context mContext;

    public MineFieldRecyclerAdapter(Context context, ArrayList<Block> list) {
        this.mBlockList = list;
        this.mContext = context;
    }

    @Override
    public MineFieldRecyclerAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_field_reyclerview, parent, false);
        MineFieldRecyclerAdapterHolder holder = new MineFieldRecyclerAdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MineFieldRecyclerAdapterHolder holder, int position) {
        holder.button.setText("" + position);
    }

    @Override
    public int getItemCount() {
        return mBlockList.size();
    }

    class MineFieldRecyclerAdapterHolder extends RecyclerView.ViewHolder {
        Button button;

        public MineFieldRecyclerAdapterHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.btn_mine_item);
        }
    }
}
