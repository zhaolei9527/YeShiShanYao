package com.lingqiapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingqiapp.Bean.TixianLogBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.DateUtils;
import com.lingqiapp.View.CommomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 赵磊 on 2017/9/20.
 */

public class TiXianListAdapter extends RecyclerView.Adapter<TiXianListAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<TixianLogBean.ListBean> datas = new ArrayList();

    public ArrayList<TixianLogBean.ListBean> getDatas() {
        return datas;
    }

    public TiXianListAdapter(List<TixianLogBean.ListBean> list, Context context) {
        this.datas = (ArrayList<TixianLogBean.ListBean>) list;
        this.mContext = context;
    }

    public void setDatas(ArrayList datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tixianjilu, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvBank.setText(datas.get(position).getBank() + "  " + mContext.getString(R.string.tail) + " (" + datas.get(position).getNo().substring(datas.get(position).getNo().length() - 4)
                + ")");

        holder.tvMoney.setText(datas.get(position).getTx_money() + "元");

        holder.tvTime.setText(DateUtils.getDay(Long.parseLong(datas.get(position).getAdd_time()) * 1000));

        if ("1".equals("" + datas.get(position).getStu())) {
            holder.tvMsg.setText(mContext.getString(R.string.Application_successful));
            holder.imgMsg.setVisibility(View.GONE);
        } else if ("-1".equals("" + datas.get(position).getStu())) {
            holder.tvMsg.setText(mContext.getString(R.string.Apply_for_failure));
            holder.imgMsg.setVisibility(View.VISIBLE);
        } else {
            holder.tvMsg.setText(mContext.getString(R.string.In_the_application));
            holder.imgMsg.setVisibility(View.GONE);
        }

        holder.imgMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new CommomDialog(mContext, R.style.dialog, datas.get(position).getDesc(), new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, final boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                        }
                    }
                }).setTitle(mContext.getString(R.string.prompt)).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bank)
        TextView tvBank;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.img_msg)
        ImageView imgMsg;
        @BindView(R.id.tv_msg)
        TextView tvMsg;
        @BindView(R.id.fl_item)
        FrameLayout flItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
