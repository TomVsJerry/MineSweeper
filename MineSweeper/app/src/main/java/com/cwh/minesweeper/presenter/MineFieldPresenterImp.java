package com.cwh.minesweeper.presenter;

import com.cwh.minesweeper.view.IMineFieldView;

/**
 * Created by chenweihu on 2018/8/10 0010.
 */

public class MineFieldPresenterImp implements IMineFieldPresenter {

    private IMineFieldView iMineFieldView;

    public MineFieldPresenterImp(IMineFieldView view) {
        this.iMineFieldView = view;
    }

    @Override
    public void setClickMode(int mode) {
        iMineFieldView.onModeChange(mode);
    }

    @Override
    public void backToMenu() {
        iMineFieldView.onBackToMenu();
    }
}
