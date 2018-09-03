package com.cwh.minesweeper.presenter;

/**
 * Created by chenweihu on 2018/8/9 0009.
 */

public interface IMineFieldPresenter {
    void setClickMode(int mode);
    void backToMenu();
    void notifyBlockClick(int position);
    void notifyClickOpenedBlock(int position);
    void onDestroyView();

    void startGame();
    void notifyBlockLongClick(int index);
}
