package com.cwh.minesweeper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cwh.minesweeper.R;
import com.cwh.minesweeper.greendao.RankListItem;

import java.util.ArrayList;

/**
 * Created by chenweihu on 2018/9/4 0004.
 */

public class RankListAdapter extends BaseAdapter {
    private ArrayList<RankListItem> mRankListItems;
    private Context mContext;

    public RankListAdapter(ArrayList<RankListItem> list, Context context) {
        this.mRankListItems = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mRankListItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mRankListItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = null;
        ViewHolder holder = null;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_rank_list, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.tvRank.setText("" + i);
        holder.tvRecordDate.setText("" + mRankListItems.get(i).getDate());
        holder.tvCostTime.setText("" + mRankListItems.get(i).getDate());
        return view;
    }

    class ViewHolder {
        public TextView tvRank;
        public TextView tvCostTime;
        public TextView tvRecordDate;

        public ViewHolder(View view) {
            this.tvRank = (TextView) view.findViewById(R.id.tv_rank);
            this.tvCostTime = (TextView) view.findViewById(R.id.tv_cost_time);
            this.tvRecordDate = (TextView) view.findViewById(R.id.tv_record_date);
        }
    }
}
