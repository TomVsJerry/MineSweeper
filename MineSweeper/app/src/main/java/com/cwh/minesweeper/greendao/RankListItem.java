package com.cwh.minesweeper.greendao;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


/**
 * Created by chenweihu on 2018/9/3 0003.
 */
@Entity()
public class RankListItem implements Comparable<RankListItem> {
    @Id(autoincrement = true)
    private Long id;
    private Integer rankType;//排名类型
    private Long costTime;//花费时间
    private String date;//生成记录时间

    @Generated(hash = 16708827)
    public RankListItem(Long id, Integer rankType, Long costTime, String date) {
        this.id = id;
        this.rankType = rankType;
        this.costTime = costTime;
        this.date = date;
    }

    @Generated(hash = 466312213)
    public RankListItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRankType() {
        return rankType;
    }

    public void setRankType(Integer rankType) {
        this.rankType = rankType;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(@NonNull RankListItem rankListItem) {
        return (int) (rankListItem.costTime - this.costTime);
    }
}
