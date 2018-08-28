package com.cwh.minesweeper.view;

import com.cwh.minesweeper.bean.Block;
import com.cwh.minesweeper.presenter.IMineFieldPresenter;

import java.util.ArrayList;

/**
 * Created by chenweihu on 2018/8/9 0009.
 */

public interface IMineFieldView {
    void onBackToMenu();
    void onModeChange(int mode);
    void onInitView(ArrayList<Block> list, int widthCount, int heightCount, int blockSize, int mineCount,IMineFieldPresenter presenter);

    void onGameEndSucced();

    void onGameEndFailed(ArrayList<Block> list,int position);
    void onTimeUpdate(String str);

    void openBlankBlockEdge(int position);

    void updateRemainMineCount(int count);
}
