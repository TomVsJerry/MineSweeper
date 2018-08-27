package com.cwh.minesweeper.utils;

/**
 * Created by chenweihu on 2018/8/27 0027.
 */

public class TimeUtils {

    public static String fomatTime(int time) {
        String result = null;
        int cent = time / 60;
        int second = time % 60;
        String c = String.format("%02d",cent);
        String s = String.format("%02d",second);
        result = c + ":" + s;
        return result;
    }
}
