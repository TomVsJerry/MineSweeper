package com.cwh.minesweeper.bean;

/**
 * Created by chenweihu on 2018/8/9 0009.
 */

public class Block {
    private BlockState mBlockState;
    private int mAroundMineCount;
    private int hasMine;
    private int _X;
    private int _Y;
    
    public BlockState getmBlockState() {
        return mBlockState;
    }

    public void setmBlockState(BlockState mBlockState) {
        this.mBlockState = mBlockState;
    }

    public int getmAroundMineCount() {
        return mAroundMineCount;
    }

    public void setmAroundMineCount(int mAroundMineCount) {
        this.mAroundMineCount = mAroundMineCount;
    }

    public int getHasMine() {
        return hasMine;
    }

    public void setHasMine(int hasMine) {
        this.hasMine = hasMine;
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

    enum BlockState {
        BLOCK_STATE_INIT, BLOCK_STATE_MARKED, BLOCK_STATE_OPEN
    }
}
