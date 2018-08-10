package com.cwh.minesweeper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.presenter.IMineFieldPresenter;
import com.cwh.minesweeper.presenter.MineFieldPresenterImp;
import com.cwh.minesweeper.ui.activity.MainActivity;
import com.cwh.minesweeper.view.IMineFieldView;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenweihu on 2018/8/9 0009.
 */

public class MineFieldFragment extends Fragment implements IMineFieldView {
    private static final String TAG = "MineFieldFragment";
    private IMineFieldPresenter iMineFieldPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine_field, container, false);
        ButterKnife.bind(this, view);
        iMineFieldPresenter = new MineFieldPresenterImp(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        int count = 10;//view.getMeasuredWidth() / 30;
        Log.d(TAG, "count = " + count);
        GridView gridView = (GridView) view.findViewById(R.id.gv_field);
        GridViewAdapter gridAdapter = new GridViewAdapter();
        gridAdapter.setCount(count);
        gridView.setVerticalSpacing(0);
        gridView.setHorizontalSpacing(0);
        gridView.setNumColumns(count);
        gridView.setAdapter(gridAdapter);
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_change_mode, R.id.btn_back_to_menu})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_mode:
                iMineFieldPresenter.setClickMode(0);
                break;
            case R.id.btn_back_to_menu:
                iMineFieldPresenter.backToMenu();
                break;
        }
    }

    @Override
    public void onBackToMenu() {
        ((MainActivity) getActivity()).backToMenu();
    }

    @Override
    public void onModeChange(int mode) {

    }

    private class GridViewAdapter extends BaseAdapter {
        private int count;

        public void setCount(int c) {
            this.count = c;
        }

        @Override
        public int getCount() {
            return count * count;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View btn = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, null);
            return btn;
        }
    }
}
