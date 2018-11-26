package com.lingqiapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lingqiapp.Bean.ZhanNeiBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 赵磊 on 2017/9/20.
 */

public class ZhanNeiListAdapter extends RecyclerView.Adapter<ZhanNeiListAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<ZhanNeiBean.ListBean> datas = new ArrayList();

    public ArrayList<ZhanNeiBean.ListBean> getDatas() {
        return datas;
    }

    public ZhanNeiListAdapter(List<ZhanNeiBean.ListBean> list, Context context) {
        this.datas = (ArrayList<ZhanNeiBean.ListBean>) list;
        this.mContext = context;
    }

    public void setDatas(ArrayList datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_zhanneixiaoxi, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvContent.setText(datas.get(position).getContent());
        holder.tvTime.setText(DateUtils.getMillon(Long.parseLong(datas.get(position).getAddtime()) * 1000));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.fl_item)
        FrameLayout flItem;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
