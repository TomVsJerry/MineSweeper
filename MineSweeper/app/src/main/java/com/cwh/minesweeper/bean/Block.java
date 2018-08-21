package com.cwh.minesweeper.bean;

/**
 * Created by chenweihu on 2018/8/9 0009.
 */

public class Block {
    public static final int BLOCK_STATE_INIT = 0;// 0 正常状态
    public static final int BLOCK_STATE_FLAG = 1;// 1 旗帜状态
    public static final int BLOCK_STATE_QUESTION = 2;//2 问号状态
    public static final int BLOCK_STATE_OPEN = 3;// 3 打开状态
    private int mBlockState;
    private int mAroundMineCount;
    private boolean isMine;
    private int index;
    private int _X;
    private int _Y;

    public Block(int i, int widthCount, int heightCount) {
        this.index = i;
        this._X = i % widthCount;
        this._Y = i / widthCount;
        mBlockState = BLOCK_STATE_INIT;
        isMine = false;
    }

    public int getIndex() {
        return index;
    }

    public boolean isMine() {
        return isMine;
    }
    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getmBlockState() {
        return mBlockState;
    }

    public void setmBlockState(int mBlockState) {
        this.mBlockState = mBlockState;
    }

    public int getmAroundMineCount() {
        return mAroundMineCount;
    }

    public void setmAroundMineCount(int mAroundMineCount) {
        this.mAroundMineCount = mAroundMineCount;
    }


    public int get_X() {
        return _X;
    }

    public void set_X(int _X) {
        this._X = _X;
    }

    public int get_Y() {
        return _Y;
    }

    public void set_Y(int _Y) {
        this._Y = _Y;
    }
}
