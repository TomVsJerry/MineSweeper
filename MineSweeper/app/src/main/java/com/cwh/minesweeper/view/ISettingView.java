package com.cwh.minesweeper.view;

/**
 * Created by chenweihu on 2018/8/20 0020.
 */

public interface ISettingView {
    void onGameLevelSetting(int gameLevel);

    void onGameLevelSettingInit(int level);

    void onBlockSizeSetting(int blockSize);

    void onBlockSizeSettingInit(int blockSize);

    void onMapSizeSetting();

}
