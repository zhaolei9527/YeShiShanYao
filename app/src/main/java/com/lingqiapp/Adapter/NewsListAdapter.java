package com.lingqiapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lingqiapp.Activity.NewsDetailsActivity;
import com.lingqiapp.Bean.NewsListBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.DateUtils;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.View.MYSimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 赵磊 on 2017/9/20.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<NewsListBean.ResBean> datas = new ArrayList();

    public ArrayList<NewsListBean.ResBean> getDatas() {
        return datas;
    }

    public NewsListAdapter(List<NewsListBean.ResBean> list, Context context) {
        this.datas = (ArrayList<NewsListBean.ResBean>) list;
        this.mContext = context;
    }

    public void setDatas(ArrayList datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg());
        Log.e("NewsListAdapter", UrlUtils.URL + datas.get(position).getImg());
        holder.tvTitle.setText(datas.get(position).getTitle());
        holder.tvTime.setText(DateUtils.getMillon(Long.parseLong(datas.get(position).getAdd_time()) * 1000));
        holder.tvContent.setText("" + datas.get(position).getDescription());
        holder.flItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, NewsDetailsActivity.class).putExtra("id",datas.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.SimpleDraweeView)
        MYSimpleDraweeView SimpleDraweeView;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.fl_item)
        FrameLayout flItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
