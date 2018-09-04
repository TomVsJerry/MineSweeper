package com.cwh.minesweeper.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.ui.fragment.MenuFragment;
import com.cwh.minesweeper.ui.fragment.MineFieldFragment;
import com.cwh.minesweeper.ui.fragment.RankListFragment;
import com.cwh.minesweeper.ui.fragment.SettingFragment;

import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    private long exitStartTime = 0;

    private MineFieldFragment mMineFieldFragment;
    private MenuFragment mMenuFragment;
    private SettingFragment mSettingFragment;
    private RankListFragment mRankListFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMineFieldFragment = new MineFieldFragment();
        mMenuFragment = new MenuFragment();
        mSettingFragment = new SettingFragment();
        mRankListFragment = new RankListFragment();
        mFragmentManager = getSupportFragmentManager();
        backToMenu();
    }

    @Override
    public void onBackPressed() {
        long startTime = System.currentTimeMillis();
        if (exitStartTime == 0 || startTime - exitStartTime > 3000) {
            showMoreBackToExitToast();
            exitStartTime = startTime;
        } else if (startTime - exitStartTime < 3000) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void startNewGame() {
        mFragmentManager.beginTransaction().replace(R.id.fl_game, mMineFieldFragment).commit();
    }

    public void onStartNewGame(){
        mFragmentManager.beginTransaction().replace(R.id.fl_game, mMineFieldFragment).commit();
    }
    public void openGameSetting() {
        mFragmentManager.beginTransaction().replace(R.id.fl_game, mSettingFragment).commit();
    }

    public void openRankList(){
        mFragmentManager.beginTransaction().replace(R.id.fl_game, mRankListFragment).commit();
    }

    public void backToMenu(){
        mFragmentManager.beginTransaction().replace(R.id.fl_game, mMenuFragment).commit();
    }

    public void showMoreBackToExitToast() {
        Toast.makeText(this, R.string.more_back_to_exit_toast, Toast.LENGTH_LONG).show();
    }
}
