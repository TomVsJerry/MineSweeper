package com.cwh.minesweeper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.bean.Block;
import com.cwh.minesweeper.presenter.IMineFieldPresenter;
import com.cwh.minesweeper.presenter.MineFieldPresenterImp;
import com.cwh.minesweeper.utils.Constant;
import com.cwh.minesweeper.utils.SharedPreferenceUtils;

import java.util.ArrayList;

/**
 * Created by chenweihu on 2018/8/17 0017.
 */

public class MineFieldRecyclerAdapter extends RecyclerView.Adapter<MineFieldRecyclerAdapter.MineFieldRecyclerAdapterHolder> {

    private static final String TAG = "RecyclerAdapter";
    private ArrayList<Block> mBlockList;
    private Context mContext;
    private IMineFieldPresenter iMineFieldPresenter;
    public MineFieldRecyclerAdapter(Context context, ArrayList<Block> list,IMineFieldPresenter presenter) {
        this.mBlockList = list;
        this.mContext = context;
        this.iMineFieldPresenter = presenter;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public MineFieldRecyclerAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder viewType = " + viewType);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_field_reyclerview, parent, false);
        MineFieldRecyclerAdapterHolder holder = new MineFieldRecyclerAdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MineFieldRecyclerAdapterHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder position = " + position);
        Block block = mBlockList.get(position);
        //holder.button.setText("(" + block.get_X() + " ," + block.get_Y() + ")");
        holder.button.setTag(block);
        //setBackground(holder.button, block);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object tag = view.getTag();
                if (tag instanceof Block) {
                    Block b = (Block) tag;
                    switch (b.getmBlockState()) {
                        case Block.BLOCK_STATE_INIT:
                            b.setmBlockState(Block.BLOCK_STATE_OPEN);
                            break;
                        case Block.BLOCK_STATE_FLAG:
                            b.setmBlockState(Block.BLOCK_STATE_QUESTION);
                            break;
                        case Block.BLOCK_STATE_QUESTION:
                            b.setmBlockState(Block.BLOCK_STATE_INIT);
                            break;
                        case Block.BLOCK_STATE_OPEN:
                            break;
                    }
                    setBackground((Button) view, b);
                    iMineFieldPresenter.notifyBlockClick(b.getIndex());
                }
            }
        });

        holder.button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Object tag = view.getTag();
                if (tag instanceof Block) {
                    Block b = (Block) tag;
                    switch (b.getmBlockState()) {
                        case Block.BLOCK_STATE_INIT:
                            b.setmBlockState(Block.BLOCK_STATE_FLAG);
                            break;
                        case Block.BLOCK_STATE_FLAG:
                            b.setmBlockState(Block.BLOCK_STATE_QUESTION);
                            break;
                        case Block.BLOCK_STATE_QUESTION:
                            b.setmBlockState(Block.BLOCK_STATE_INIT);
                            break;
                        case Block.BLOCK_STATE_OPEN:
                            break;
                    }
                    setBackground((Button) view, b);
                }
                return true;
            }
        });
    }

    public void setBackground(Button b, Block block) {
        switch (block.getmBlockState()) {
            case Block.BLOCK_STATE_INIT:
                b.setBackgroundColor(mContext.getResources().getColor(R.color.flag0));
                break;
            case Block.BLOCK_STATE_FLAG:
                b.setBackgroundResource(R.mipmap.flag1);
                break;
            case Block.BLOCK_STATE_QUESTION:
                b.setBackgroundResource(R.mipmap.flag2);
                break;
            case Block.BLOCK_STATE_OPEN:
                setOpenBackground(b, block);
                break;
        }
    }

    private void setOpenBackground(Button b, Block block) {
        if (block.isMine()) {
            b.setBackgroundResource(R.mipmap.bomb4);
        } else {
            switch (block.getmAroundMineCount()) {
                case 0:
                    break;
                case 1:
                    b.setText("" + 1);
                    b.setTextColor(mContext.getResources().getColor(R.color.color_num1));
                    break;
                case 2:
                    b.setText("" + 2);
                    b.setTextColor(mContext.getResources().getColor(R.color.color_num2));
                    break;
                case 3:
                    b.setText("" + 3);
                    b.setTextColor(mContext.getResources().getColor(R.color.color_num3));
                    break;
                case 4:
                    b.setText("" + 4);
                    b.setTextColor(mContext.getResources().getColor(R.color.color_num4));
                    break;
                case 5:
                    b.setText("" + 5);
                    b.setTextColor(mContext.getResources().getColor(R.color.color_num5));
                    break;
                case 6:
                    b.setText("" + 6);
                    b.setTextColor(mContext.getResources().getColor(R.color.color_num6));
                    break;
                case 7:
                    b.setText("" + 7);
                    b.setTextColor(mContext.getResources().getColor(R.color.color_num7));
                    break;
                case 8:
                    b.setText("" + 8);
                    b.setTextColor(mContext.getResources().getColor(R.color.color_num8));
                    break;
            }
            b.setBackgroundResource(R.mipmap.num_0);
        }
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
            ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
            int blockSize = SharedPreferenceUtils.getInstance().<Integer>getValue(mContext, SharedPreferenceUtils.BLOCK_SIZE, Constant.MIN_BLOCK_SIZE);
            layoutParams.height = blockSize + Constant.MIN_BLOCK_SIZE;
            layoutParams.width = blockSize + Constant.MIN_BLOCK_SIZE;
            button.setLayoutParams(layoutParams);
        }
    }
}
