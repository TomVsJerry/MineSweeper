package com.cwh.minesweeper.presenter;

import android.content.Context;

import com.cwh.minesweeper.utils.SharedPreferenceUtils;
import com.cwh.minesweeper.view.ISettingView;

/**
 * Created by chenweihu on 2018/8/20 0020.
 */

public class SettingPresenterImp implements ISettingPresenter {
    public ISettingView iSettingView;
    public Context mContext;

    public SettingPresenterImp(ISettingView view, Context context) {
        this.iSettingView = view;
        this.mContext = context;
    }

    @Override
    public void initSetting() {
        int level = 0;
        int gameLevel = SharedPreferenceUtils.getInstance().getValue(mContext, "game_level", level);
        int blockSize = SharedPreferenceUtils.getInstance().getValue(mContext, "block_size", level);
        iSettingView.onGameLevelSettingInit(gameLevel);
        iSettingView.onBlockSizeSettingInit(blockSize);
    }

    @Override
    public void setGameLevel() {
        int level = 0;
        int gameLevel = SharedPreferenceUtils.getInstance().getValue(mContext, "game_level", level);
        iSettingView.onGameLevelSetting(gameLevel);
    }

    @Override
    public void setGameMapSize() {
        iSettingView.onMapSizeSetting();
    }

    @Override
    public void setBlockSize() {
        int level = 0;
        int blockSize = SharedPreferenceUtils.getInstance().getValue(mContext, "block_size", level);
        iSettingView.onBlockSizeSetting(blockSize);
    }

    @Override
    public void saveBlockSize(int size) {
        SharedPreferenceUtils.getInstance().setValue(mContext, "block_size", size);
    }

    @Override
    public void saveGameLevel(int level) {
        SharedPreferenceUtils.getInstance().setValue(mContext, "game_level", level);
    }
}
