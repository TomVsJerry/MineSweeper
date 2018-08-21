package com.cwh.minesweeper.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.utils.Constant;

/**
 * Created by chenweihu on 2018/8/20 0020.
 */

public class BlockSizeSettingDialog extends Dialog {

    private TextView mTvBlockSizeValue;
    private SeekBar mSBBlockSize;
    private Button mBtnConfrim;
    private OnBlockSizeConfirmedListener mOnBlockSizeConfirmedListener;

    public BlockSizeSettingDialog(@NonNull Context context, int seekValue, OnBlockSizeConfirmedListener listener) {
        super(context, R.style.Dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_block_size, null);
        setContentView(view);
        initView(seekValue, view);
        this.mOnBlockSizeConfirmedListener = listener;
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = 500;
        win.setAttributes(lp);
    }

    private void initView(int seekValue, View view) {
        mTvBlockSizeValue = (TextView) view.findViewById(R.id.tv_block_size_value);
        mSBBlockSize = (SeekBar) view.findViewById(R.id.sb_block_size);
        mBtnConfrim = (Button) view.findViewById(R.id.btn_confirm);
        mBtnConfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnBlockSizeConfirmedListener != null) {
                    mOnBlockSizeConfirmedListener.onBlockSizeConfirmed(mSBBlockSize.getProgress());
                }
                BlockSizeSettingDialog.this.dismiss();
            }
        });
        mSBBlockSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mTvBlockSizeValue.setText("" + (i + Constant.MIN_BLOCK_SIZE));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSBBlockSize.setProgress(seekValue);
        mTvBlockSizeValue.setText("" + (seekValue + Constant.MIN_BLOCK_SIZE));
    }

    public interface OnBlockSizeConfirmedListener {
        void onBlockSizeConfirmed(int size);
    }
}
