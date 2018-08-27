package com.cwh.minesweeper.presenter;

/**
 * Created by chenweihu on 2018/8/9 0009.
 */

public interface IMineFieldPresenter {
    public void setClickMode(int mode);
    public void backToMenu();
    public void notifyBlockClick(int position);
    public void onDestroyView();
}
