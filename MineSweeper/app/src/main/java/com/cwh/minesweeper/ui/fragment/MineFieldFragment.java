package com.cwh.minesweeper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.bean.Block;
import com.cwh.minesweeper.presenter.IMineFieldPresenter;
import com.cwh.minesweeper.presenter.MineFieldPresenterImp;
import com.cwh.minesweeper.ui.activity.MainActivity;
import com.cwh.minesweeper.ui.adapter.MineFieldRecyclerAdapter;
import com.cwh.minesweeper.ui.adapter.MineRecyclerViewDecoration;
import com.cwh.minesweeper.utils.Constant;
import com.cwh.minesweeper.utils.SharedPreferenceUtils;
import com.cwh.minesweeper.view.IMineFieldView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenweihu on 2018/8/9 0009.
 */

public class MineFieldFragment extends Fragment implements IMineFieldView {
    private static final String TAG = "MineFieldFragment";
    private IMineFieldPresenter iMineFieldPresenter;
    private ArrayList<Block> mBlockList;
    private int widthCount = 10;
    private int heightCount = 10;
    private int mMineCout = (int) (widthCount * heightCount * 0.2);
    @Bind(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine_field, container, false);
        ButterKnife.bind(this, view);
        iMineFieldPresenter = new MineFieldPresenterImp(this);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        mBlockList = new ArrayList<>();
        int count = widthCount * heightCount;
        // 1. 初始化方块
        for (int i = 0; i < count; i++) {
            Block block = new Block(i, widthCount, heightCount);
            mBlockList.add(block);

        }
        // 2. 初始化雷区
        Random random = new Random(System.currentTimeMillis());
        float rate =  ((float)mMineCout) / (widthCount * heightCount);
        for (int i = 0, c = 0; c < mMineCout && i < mBlockList.size(); i++) {
            if ((mBlockList.size() - i) <= (mMineCout - c)) {
                for (int j = i; j < mBlockList.size(); j++) {
                    Block block = mBlockList.get(j);
                    block.setMine(true);
                }
                break;
            }
            Block block = mBlockList.get(i);
            if (random.nextFloat() <= rate) {
                block.setMine(true);
                c++;
            }
        }
        // 3. 初始化方块周围雷区数量
        for (int i = 0; i < mBlockList.size(); i++) {
            int mineCount = 0;
            Block block = mBlockList.get(i);
            int x = block.get_X();
            int y = block.get_Y();
            if (x - 1 >= 0 && y - 1 >= 0) {//左上
                int position = (y - 1) * widthCount + (x - 1);
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (y - 1 >= 0) {//上
                int position = (y - 1) * widthCount + x;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x + 1 < widthCount && y - 1 >= 0) {//右上
                int position = (y - 1) * widthCount + x + 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x - 1 >= 0) {//左
                int position = y * widthCount + x - 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x + 1 < widthCount) {//右
                int position = y * widthCount + x + 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x - 1 >= 0 && y + 1 < heightCount) {//左下
                int position = (y + 1) * widthCount + x - 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (y + 1 < heightCount) {//下
                int position = (y + 1) * widthCount + x;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x + 1 < widthCount && y + 1 < heightCount) {//右下
                int position = (y + 1) * widthCount + x + 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            block.setmAroundMineCount(mineCount);
        }
    }


    private void initView(View view) {
        ViewGroup.LayoutParams layoutParams = mRecyclerView.getLayoutParams();
        int blockSize = SharedPreferenceUtils.getInstance().getValue(getContext(), SharedPreferenceUtils.BLOCK_SIZE, 20);
        layoutParams.width = widthCount * (blockSize + Constant.MIN_BLOCK_SIZE + 8);
        layoutParams.height = heightCount * (blockSize + Constant.MIN_BLOCK_SIZE + 8);
        mRecyclerView.setLayoutParams(layoutParams);

        mRecyclerView.setAdapter(new MineFieldRecyclerAdapter(getContext(), mBlockList));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), widthCount));
        mRecyclerView.addItemDecoration(new MineRecyclerViewDecoration(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_change_mode, R.id.btn_back_to_menu})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_mode:
                iMineFieldPresenter.setClickMode(0);
                break;
            case R.id.btn_back_to_menu:
                iMineFieldPresenter.backToMenu();
                break;
        }
    }

    @Override
    public void onBackToMenu() {
        ((MainActivity) getActivity()).backToMenu();
    }

    @Override
    public void onModeChange(int mode) {

    }
}
