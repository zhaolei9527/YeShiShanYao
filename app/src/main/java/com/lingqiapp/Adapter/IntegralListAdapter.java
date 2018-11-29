package com.lingqiapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lingqiapp.Bean.MingxiCaiwuBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵磊 on 2017/9/20.
 */

public class IntegralListAdapter extends RecyclerView.Adapter<IntegralListAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<MingxiCaiwuBean.ListBean> datas = new ArrayList();

    public ArrayList<MingxiCaiwuBean.ListBean> getDatas() {
        return datas;
    }

    public IntegralListAdapter(List<MingxiCaiwuBean.ListBean> list, Context context) {
        this.datas = (ArrayList<MingxiCaiwuBean.ListBean>) list;
        this.mContext = context;
    }

    public void setDatas(ArrayList datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_financia, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MingxiCaiwuBean.ListBean resBean = datas.get(position);
        holder.tv_money.setText("当前余额" + resBean.getOver_num()+"元");
        holder.tv_time.setText(DateUtils.getMillon(Long.parseLong(resBean.getAdd_time()) * 1000));
        if ("1".equals(resBean.getStu())) {
            holder.tv_type.setText("消费");
            holder.tv_content.setText("-€" + resBean.getChange_num());
        } else if ("2".equals(resBean.getStu())) {
            holder.tv_type.setText("推广奖励");
            holder.tv_content.setText("+€" + resBean.getChange_num());
        }else if ("3".equals(resBean.getStu())) {
            holder.tv_type.setText("后台调整");
            holder.tv_content.setText("€" + resBean.getChange_num());
        }else if ("4".equals(resBean.getStu())) {
            holder.tv_type.setText("提现成功");
            holder.tv_content.setText("-€" + resBean.getChange_num());
        }else if ("5".equals(resBean.getStu())) {
            holder.tv_type.setText("提现失败");
            holder.tv_content.setText("+€" + resBean.getChange_num());

        }else if ("6".equals(resBean.getStu())) {
            holder.tv_type.setText("充值");
            holder.tv_content.setText("+€" + resBean.getChange_num());
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_type;
        public TextView tv_time;
        public TextView tv_content;
        public TextView tv_money;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            tv_type = (TextView) this.rootView.findViewById(R.id.tv_type);
            tv_time = (TextView) this.rootView.findViewById(R.id.tv_time);
            tv_content = (TextView) this.rootView.findViewById(R.id.tv_content);
            tv_money = (TextView) this.rootView.findViewById(R.id.tv_money);
        }

    }
}
