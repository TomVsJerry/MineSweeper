package com.cwh.minesweeper.presenter;

import com.cwh.minesweeper.view.IMenuView;

/**
 * Created by chenweihu on 2018/8/9 0009.
 */

public class MenuPresenterImp implements IMenuPresenter {

    private IMenuView iMenuView;

    public MenuPresenterImp(IMenuView view) {
        this.iMenuView = view;
    }

    @Override
    public void exitGame() {
        iMenuView.onExitGame();
    }

    @Override
    public void createNewGame() {
        iMenuView.onCreateNewGame();
    }

    @Override
    public void resumeGame() {
        iMenuView.onResumeGame();
    }

    @Override
    public void openHelp() {
        iMenuView.onOpenHelp();
    }

    @Override
    public void shareGame() {
        iMenuView.onShareGame();
    }

    @Override
    public void openGameSetting() {
        iMenuView.onOpenGameSetting();
    }

    @Override
    public void showRankList() {
        iMenuView.onOpenRankList();
    }
}
