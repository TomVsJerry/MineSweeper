package com.cwh.minesweeper.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cwh.minesweeper.R;


/**
 * Created by chenweihu on 2018/8/20 0020.
 */

public class GameLevelDialog extends Dialog {

    private OnLevelItemClickedListener mOnLevelItemClickedListener;
    private RadioGroup mRGGameLevel;
    private RadioButton mRBLow, mRBMedium, mRBHigh, mRBSelfDefine;

    public GameLevelDialog(Context context, int level, OnLevelItemClickedListener listener) {
        super(context, R.style.Dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_game_level, null);
        this.mOnLevelItemClickedListener = listener;
        initView(view, level);
        setContentView(view);


        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = 500;
        win.setAttributes(lp);
    }

    private void initView(View view, int level) {
        mRGGameLevel = (RadioGroup) view.findViewById(R.id.rg_game_level);
        mRBLow = (RadioButton) view.findViewById(R.id.game_level_low);
        mRBMedium = (RadioButton) view.findViewById(R.id.game_level_medium);
        mRBHigh = (RadioButton) view.findViewById(R.id.game_level_high);
        mRBSelfDefine = (RadioButton) view.findViewById(R.id.game_level_self_define);
        setRadioChecked(level);
        mRGGameLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (mOnLevelItemClickedListener != null) {
                    switch (i) {
                        case R.id.game_level_low:
                            mOnLevelItemClickedListener.onGameLevelClicked(0);
                            break;
                        case R.id.game_level_medium:
                            mOnLevelItemClickedListener.onGameLevelClicked(1);
                            break;
                        case R.id.game_level_high:
                            mOnLevelItemClickedListener.onGameLevelClicked(2);
                            break;
                        case R.id.game_level_self_define:
                            mOnLevelItemClickedListener.onGameLevelClicked(3);
                            break;
                    }
                }
                GameLevelDialog.this.dismiss();
            }
        });
    }


    public void setRadioChecked(int level) {
        switch (level) {
            case 0:
                mRBLow.setChecked(true);
                break;
            case 1:
                mRBMedium.setChecked(true);
                break;
            case 2:
                mRBHigh.setChecked(true);
                break;
            case 3:
                mRBSelfDefine.setChecked(true);
                break;
        }
    }

    public interface OnLevelItemClickedListener {
        public void onGameLevelClicked(int level);
    }
}
