package com.cwh.minesweeper.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.cwh.minesweeper.bean.Block;
import com.cwh.minesweeper.utils.SharedPreferenceUtils;
import com.cwh.minesweeper.utils.TimeUtils;
import com.cwh.minesweeper.view.IMineFieldView;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by chenweihu on 2018/8/10 0010.
 */

public class MineFieldPresenterImp implements IMineFieldPresenter {

    private IMineFieldView iMineFieldView;
    private ArrayList<Block> mBlockList;
    private Context mContext;
    private boolean isGameEnd = true;
    private int blockSize;
    private int widthCount = 10;
    private int heightCount = 10;
    private int mMineCout = (int) (widthCount * heightCount * 0.2);
    private static final int MSG_WHAT_TIME_COUNT = 0;
    private int mGameTime = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WHAT_TIME_COUNT:
                    mGameTime++;
                    updateTimeTextView();
                    break;
            }
        }
    };

    public MineFieldPresenterImp(IMineFieldView view, Context context) {
        this.iMineFieldView = view;
        this.mContext = context;
        this.isGameEnd = false;
        initMineFieldData();
        updateTimeTextView();
    }

    private void updateTimeTextView() {
        updateTimeString();
        if(!isGameEnd){
            mHandler.sendEmptyMessageDelayed(MSG_WHAT_TIME_COUNT,1000);
        }
    }

    private void updateTimeString(){
        String str = TimeUtils.fomatTime(mGameTime);
        iMineFieldView.onTimeUpdate(str);
    }
    @Override
    public void setClickMode(int mode) {
        iMineFieldView.onModeChange(mode);
    }

    @Override
    public void backToMenu() {
        iMineFieldView.onBackToMenu();
    }

    @Override
    public void notifyBlockClick(int position) {
        Block block = mBlockList.get(position);
        if (block.isMine()) {
            mHandler.removeMessages(MSG_WHAT_TIME_COUNT);
            iMineFieldView.onGameEndFailed(mBlockList, position);
            return;
        }

    }

    @Override
    public void onDestroyView() {
        mHandler.removeMessages(MSG_WHAT_TIME_COUNT);
    }

    private void initMineFieldData() {
        mBlockList = new ArrayList<>();
        int count = widthCount * heightCount;
        // 1. 初始化方块
        for (int i = 0; i < count; i++) {
            Block block = new Block(i, widthCount, heightCount);
            mBlockList.add(block);

        }
        // 2. 初始化雷区
        Random random = new Random(System.currentTimeMillis());
        float rate = ((float) mMineCout) / (widthCount * heightCount);
        for (int i = 0, c = 0; c < mMineCout && i < mBlockList.size(); i++) {
            if ((mBlockList.size() - i) <= (mMineCout - c)) {
                for (int j = i; j < mBlockList.size(); j++) {
                    Block block = mBlockList.get(j);
                    block.setMine(true);
                }
                break;
            }
            Block block = mBlockList.get(i);
            if (random.nextFloat() <= rate) {
                block.setMine(true);
                c++;
            }
        }
        // 3. 初始化方块周围雷区数量
        for (int i = 0; i < mBlockList.size(); i++) {
            int mineCount = 0;
            Block block = mBlockList.get(i);
            int x = block.get_X();
            int y = block.get_Y();
            if (x - 1 >= 0 && y - 1 >= 0) {//左上
                int position = (y - 1) * widthCount + (x - 1);
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (y - 1 >= 0) {//上
                int position = (y - 1) * widthCount + x;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x + 1 < widthCount && y - 1 >= 0) {//右上
                int position = (y - 1) * widthCount + x + 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x - 1 >= 0) {//左
                int position = y * widthCount + x - 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x + 1 < widthCount) {//右
                int position = y * widthCount + x + 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x - 1 >= 0 && y + 1 < heightCount) {//左下
                int position = (y + 1) * widthCount + x - 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (y + 1 < heightCount) {//下
                int position = (y + 1) * widthCount + x;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            if (x + 1 < widthCount && y + 1 < heightCount) {//右下
                int position = (y + 1) * widthCount + x + 1;
                if (mBlockList.get(position).isMine()) {
                    mineCount++;
                }
            }
            block.setmAroundMineCount(mineCount);
        }
        // 4. 初始化方块的大小
        blockSize = SharedPreferenceUtils.getInstance().getValue(mContext, SharedPreferenceUtils.BLOCK_SIZE, 20);
        iMineFieldView.onInitView(mBlockList, widthCount, heightCount, blockSize,this);
    }

}
