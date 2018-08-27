package com.cwh.minesweeper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.bean.Block;
import com.cwh.minesweeper.presenter.IMineFieldPresenter;
import com.cwh.minesweeper.presenter.MineFieldPresenterImp;
import com.cwh.minesweeper.ui.activity.MainActivity;
import com.cwh.minesweeper.ui.adapter.MineFieldRecyclerAdapter;
import com.cwh.minesweeper.ui.adapter.MineRecyclerViewDecoration;
import com.cwh.minesweeper.ui.dialog.GameEndDialog;
import com.cwh.minesweeper.utils.Constant;
import com.cwh.minesweeper.utils.SharedPreferenceUtils;
import com.cwh.minesweeper.view.IMineFieldView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenweihu on 2018/8/9 0009.
 */

public class MineFieldFragment extends Fragment implements IMineFieldView {
    private static final String TAG = "MineFieldFragment";
    private IMineFieldPresenter iMineFieldPresenter;

    @Bind(R.id.id_recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_face)
    ImageView ivFace;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine_field, container, false);
        ButterKnife.bind(this, view);
        iMineFieldPresenter = new MineFieldPresenterImp(this, getContext());
        return view;
    }


    @Override
    public void onInitView(ArrayList<Block> list, int widthCount, int heightCount, int blockSize, IMineFieldPresenter presenter) {
        ViewGroup.LayoutParams layoutParams = mRecyclerView.getLayoutParams();
        layoutParams.width = widthCount * (blockSize + Constant.MIN_BLOCK_SIZE + 8);
        layoutParams.height = heightCount * (blockSize + Constant.MIN_BLOCK_SIZE + 8);
        mRecyclerView.setLayoutParams(layoutParams);
        mRecyclerView.setAdapter(new MineFieldRecyclerAdapter(getContext(), list, presenter));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), widthCount));
        mRecyclerView.addItemDecoration(new MineRecyclerViewDecoration(getContext()));
    }

    @Override
    public void onGameEndSucced() {
        ivFace.setBackgroundResource(R.mipmap.face1);
        GameEndDialog mGameEnddialog = new GameEndDialog(getContext(), GameEndDialog.DILOG_TITLE_TYPE_FAILED, new GameEndDialog.OnGameEndDilogClickedListener() {

            @Override
            public void onGameEndDilogCancel() {

            }

            @Override
            public void onGameEndDilogConfirm() {
                Toast.makeText(getContext(),"重新开始新游戏",Toast.LENGTH_SHORT).show();
            }
        });
        mGameEnddialog.setCanceledOnTouchOutside(false);
        mGameEnddialog.show();
    }

    @Override
    public void onGameEndFailed(ArrayList<Block> list, int position) {
        ivFace.setBackgroundResource(R.mipmap.face2);
        GameEndDialog mGameEnddialog = new GameEndDialog(getContext(), GameEndDialog.DILOG_TITLE_TYPE_FAILED, new GameEndDialog.OnGameEndDilogClickedListener() {

            @Override
            public void onGameEndDilogCancel() {

            }

            @Override
            public void onGameEndDilogConfirm() {
                Toast.makeText(getContext(),"重新开始新游戏",Toast.LENGTH_SHORT).show();
            }
        });
        mGameEnddialog.setCanceledOnTouchOutside(false);
        mGameEnddialog.show();
    }

    @Override
    public void onTimeUpdate(String str) {
        tvTime.setText(str);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        iMineFieldPresenter.onDestroyView();
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
}
