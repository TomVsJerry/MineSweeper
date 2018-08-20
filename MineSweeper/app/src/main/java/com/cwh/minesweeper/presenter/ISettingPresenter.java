package com.cwh.minesweeper.presenter;

/**
 * Created by chenweihu on 2018/8/20 0020.
 */

public interface ISettingPresenter {

    void initSetting();
    void setGameLevel();

    void saveGameLevel(int level);

    void setGameMapSize();

    void setBlockSize();

    void saveBlockSize(int size);
}
