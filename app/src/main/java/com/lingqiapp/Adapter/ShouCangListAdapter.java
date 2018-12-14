package com.lingqiapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lingqiapp.Bean.ShouCangBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.View.MYSimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵磊 on 2017/9/20.
 */

public class ShouCangListAdapter extends RecyclerView.Adapter<ShouCangListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ShouCangBean.ListBean> datas = new ArrayList();

    public ArrayList<ShouCangBean.ListBean> getDatas() {
        return datas;
    }

    public ShouCangListAdapter(List<ShouCangBean.ListBean> list, Context context) {
        this.datas = (ArrayList<ShouCangBean.ListBean>) list;
        this.mContext = context;
        dialog = Utils.showLoadingDialog(context);
        dialog.dismiss();
    }

    public void setDatas(ArrayList<ShouCangBean.ListBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shoucang_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    private Dialog dialog;

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (datas.get(position).getImg_feng().contains("com")) {
            holder.SimpleDraweeView.setImageURI("https://"+datas.get(position).getImg_feng());
        } else {
            holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg_feng());
        }
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_money.setText("€" + datas.get(position).getPrice());

        holder.btnIsChoosed.setTag(new Integer(position));//把组件的状态更新为一个合法的状态值

        holder.btnIsChoosed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    datas.get(position).setCheck(true);
                    int ischecked = 0;
                    for (int i = 0; i < datas.size(); i++) {
                        if (datas.get(i).isCheck()) {
                            ischecked = ischecked + 1;
                        }
                    }
                    if (ischecked == datas.size()) {
                        holder.btnIsChoosed.getContext().sendBroadcast(new Intent("shoucangChoosedAll").putExtra("Choosed", true));
                    }
                } else {
                    datas.get(position).setCheck(false);
                    holder.btnIsChoosed.getContext().sendBroadcast(new Intent("shoucangChoosedAll").putExtra("Choosed", false));
                }

            }
        });

        Log.d("ShopCarListAdapter", datas.toString());
        if (datas.get(position).isCheck()) {
            holder.btnIsChoosed.setChecked(true);
        } else {
            holder.btnIsChoosed.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public MYSimpleDraweeView SimpleDraweeView;
        public TextView tv_title;
        public TextView tv_money;
        public TextView btn_shuliang;
        public CheckBox btnIsChoosed;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.SimpleDraweeView = (MYSimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
            this.btn_shuliang = (TextView) rootView.findViewById(R.id.btn_shuliang);
            this.btnIsChoosed = (CheckBox) rootView.findViewById(R.id.btnIsChoosed);
            this.btnIsChoosed.setTag(new Integer(-2));//这里
        }
    }

}
