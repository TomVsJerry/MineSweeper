package com.cwh.minesweeper.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cwh.minesweeper.R;

/**
 * Created by lenovo on 2018/8/27.
 */

public class GameEndDialog extends Dialog {

    private Context mContext;
    private int mType;
    private OnGameEndDilogClickedListener mOnGameEndDilogClickedListener;
    private Button btnConfirm, btnCancel;
    private TextView tvTitle;
    public static final int DILOG_TITLE_TYPE_FAILED = 0;
    public static final int DILOG_TITLE_TYPE_SUCCESSED = 1;

    protected GameEndDialog(Context context) {
        super(context);
    }

    public GameEndDialog(Context context, int type, OnGameEndDilogClickedListener listener) {
        super(context);
        this.mContext = context;
        this.mType = type;
        this.mOnGameEndDilogClickedListener = listener;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_game_level, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_game_end_title);
        btnConfirm = (Button) view.findViewById(R.id.btn_confirm);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnGameEndDilogClickedListener.onGameEndDilogConfirm();
                GameEndDialog.this.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnGameEndDilogClickedListener.onGameEndDilogCancel();
                GameEndDialog.this.dismiss();
            }
        });
        tvTitle.setText((type == DILOG_TITLE_TYPE_SUCCESSED ? R.string.title_game_end_success : R.string.title_game_end_fail));
        setContentView(view);
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = 500;
        win.setAttributes(lp);


    }


    public interface OnGameEndDilogClickedListener {
         void onGameEndDilogCancel();
         void onGameEndDilogConfirm();
    }
}
