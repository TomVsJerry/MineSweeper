package com.cwh.minesweeper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.presenter.SettingPresenterImp;
import com.cwh.minesweeper.ui.activity.MainActivity;
import com.cwh.minesweeper.ui.dialog.BlockSizeSettingDialog;
import com.cwh.minesweeper.ui.dialog.GameLevelDialog;
import com.cwh.minesweeper.utils.Constant;
import com.cwh.minesweeper.view.ISettingView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenweihu on 2018/8/17 0017.
 */

public class SettingFragment extends Fragment implements ISettingView {


    private SettingPresenterImp mSettingPresenterImp;

    @Bind(R.id.tv_game_level)
    TextView tvGameLevel;
    @Bind(R.id.tv_block_size)
    TextView tvBlockSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        mSettingPresenterImp = new SettingPresenterImp(this, getContext());
        mSettingPresenterImp.initSetting();
        return view;
    }

    @OnClick({R.id.item_level_setting, R.id.item_map_setting, R.id.item_block_setting, R.id.btn_back_to_menu})
    public void onSettingItemClicked(View view) {
        switch (view.getId()) {
            case R.id.item_level_setting:
                mSettingPresenterImp.setGameLevel();
                break;
            case R.id.item_map_setting:
                break;
            case R.id.item_block_setting:
                mSettingPresenterImp.setBlockSize();
                break;
            case R.id.btn_back_to_menu:
                ((MainActivity) getActivity()).backToMenu();
                break;
        }
    }


    @Override
    public void onGameLevelSetting(int gameLevel) {
        GameLevelDialog gameLevelDialog = new GameLevelDialog(getContext(), gameLevel, new GameLevelDialog.OnLevelItemClickedListener() {
            @Override
            public void onGameLevelClicked(int level) {
                mSettingPresenterImp.saveGameLevel(level);
                setGameLevel(level);
            }
        });
        gameLevelDialog.show();
    }

    private void setGameLevel(int level) {
        switch (level) {
            case 0:
                tvGameLevel.setText(R.string.low_level);
                break;
            case 1:
                tvGameLevel.setText(R.string.medium_level);
                break;
            case 2:
                tvGameLevel.setText(R.string.high_level);
                break;
            case 3:
                tvGameLevel.setText(R.string.self_define_level);
                break;

        }
    }

    @Override
    public void onMapSizeSetting() {

    }

    @Override
    public void onBlockSizeSetting(int blockSize) {
        BlockSizeSettingDialog blockSizeSettingDialog = new BlockSizeSettingDialog(getContext(), blockSize, new BlockSizeSettingDialog.OnBlockSizeConfirmedListener() {
            @Override
            public void onBlockSizeConfirmed(int size) {
                mSettingPresenterImp.saveBlockSize(size);
                onBlockSizeSettingInit(size);
            }
        });
        blockSizeSettingDialog.show();
    }

    @Override
    public void onBlockSizeSettingInit(int blockSize) {
        tvBlockSize.setText("" + (blockSize + Constant.MIN_BLOCK_SIZE));
    }

    @Override
    public void onGameLevelSettingInit(int level) {
        setGameLevel(level);
    }
}
