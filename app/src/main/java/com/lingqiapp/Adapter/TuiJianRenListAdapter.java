package com.lingqiapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingqiapp.Bean.AboutTuiBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 赵磊 on 2017/9/20.
 */

public class TuiJianRenListAdapter extends RecyclerView.Adapter<TuiJianRenListAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<AboutTuiBean.UlistBean> datas = new ArrayList();

    public ArrayList<AboutTuiBean.UlistBean> getDatas() {
        return datas;
    }

    public TuiJianRenListAdapter(List<AboutTuiBean.UlistBean> list, Context context) {
        this.datas = (ArrayList<AboutTuiBean.UlistBean>) list;
        this.mContext = context;
    }

    public void setDatas(ArrayList datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tuijianren_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (datas.get(position).getIs_hui().equals("1")) {
            holder.imgLv.setBackground(mContext.getResources().getDrawable(R.mipmap.putonghy));
            holder.tvLv.setText("普通会员");
        } else {
            holder.imgLv.setBackground(mContext.getResources().getDrawable(R.mipmap.zuanshihy));
            holder.tvLv.setText("领七会员");
        }
        holder.tvUsername.setText(datas.get(position).getNi_name());
        holder.tvTime.setText(DateUtils.getDay(Long.parseLong(datas.get(position).getAdd_time()) * 1000));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_lv)
        ImageView imgLv;
        @BindView(R.id.tv_lv)
        TextView tvLv;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
