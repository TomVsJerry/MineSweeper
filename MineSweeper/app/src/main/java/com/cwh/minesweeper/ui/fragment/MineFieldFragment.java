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
import com.cwh.minesweeper.view.IMineFieldView;

import java.util.ArrayList;

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
    private static int widthCount = 10;
    private static int heightCount = 10;
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
        for (int i = 0; i < count; i++) {
            Block block = new Block(i, widthCount, heightCount);
            mBlockList.add(block);
        }
    }

    private void initView(View view) {
        ViewGroup.LayoutParams layoutParams = mRecyclerView.getLayoutParams();
        layoutParams.width = widthCount * (100 + 8);
        layoutParams.height = heightCount * (100 + 8);
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
