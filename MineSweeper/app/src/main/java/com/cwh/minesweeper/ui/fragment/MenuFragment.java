package com.cwh.minesweeper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.presenter.MenuPresenterImp;
import com.cwh.minesweeper.view.IMenuView;
import com.cwh.minesweeper.ui.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenweihu on 2018/8/10 0010.
 */

public class MenuFragment extends Fragment implements IMenuView {

    private MenuPresenterImp mMenuPresenterImp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        mMenuPresenterImp = new MenuPresenterImp(this);
        return view;
    }

    @OnClick({R.id.btn_new_game,R.id.btn_open_setting})
    public void startNewGame(View view){
        switch (view.getId()){
            case R.id.btn_new_game:
                mMenuPresenterImp.createNewGame();
                break;
            case R.id.btn_open_setting:
                mMenuPresenterImp.openGameSetting();
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onExitGame() {
        getActivity().finish();
    }

    @Override
    public void onCreateNewGame() {
        ((MainActivity)getActivity()).startNewGame();
    }

    @Override
    public void onResumeGame() {

    }

    @Override
    public void onOpenHelp() {

    }

    @Override
    public void onShareGame() {

    }

    @Override
    public void onOpenGameSetting() {
        ((MainActivity)getActivity()).openGameSetting();
    }

    @Override
    public void onOpenRankList() {

    }
}
