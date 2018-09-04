package com.cwh.minesweeper.view;

import com.cwh.minesweeper.greendao.RankListItem;

import java.util.ArrayList;

/**
 * Created by chenweihu on 2018/9/4 0004.
 */

public interface IRankListView {
    void onInitView(ArrayList<RankListItem> list);
}
