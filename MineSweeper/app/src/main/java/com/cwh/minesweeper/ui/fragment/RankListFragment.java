package com.cwh.minesweeper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.ui.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenweihu on 2018/9/4 0004.
 */

public class RankListFragment extends Fragment {

    @Bind(R.id.rg_rank)
    RadioGroup mRgRank;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        mRgRank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.btn_back_to_menu, R.id.btn_reset_rank})
    public void onBtnClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back_to_menu:
                ((MainActivity)getActivity()).backToMenu();
                break;
            case R.id.btn_reset_rank:

                break;
        }
    }
}
