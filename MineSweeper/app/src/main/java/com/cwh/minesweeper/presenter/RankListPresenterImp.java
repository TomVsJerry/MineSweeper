package com.cwh.minesweeper.presenter;

import android.content.Context;

import com.cwh.minesweeper.view.IRankListView;

/**
 * Created by chenweihu on 2018/9/4 0004.
 */

public class RankListPresenterImp implements IRankListPresenter {

    private Context mContext;
    private IRankListView iRankListView;
    public RankListPresenterImp(Context context,IRankListView view){
        this.mContext = context;
        this.iRankListView = view;
    }
    @Override
    public void initView(int rankType) {

    }
}
