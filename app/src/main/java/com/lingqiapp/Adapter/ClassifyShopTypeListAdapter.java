package com.lingqiapp.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lingqiapp.Bean.GoodsCateBean;
import com.lingqiapp.R;
import com.lingqiapp.View.WenguoyiRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 赵磊
 * @date 2018/11/13
 * 功能描述：首页商品列表适配器，包括了头部轮播，横向推荐列表，以及竖向热门列表
 */
public class ClassifyShopTypeListAdapter extends RecyclerView.Adapter<ClassifyShopTypeListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<GoodsCateBean.ResBean> datas = new ArrayList();
    WenguoyiRecycleView rvShopList;


    public ArrayList<GoodsCateBean.ResBean> getDatas() {
        return datas;
    }

    public ClassifyShopTypeListAdapter(Context context, List<GoodsCateBean.ResBean> goodsCateBean, WenguoyiRecycleView rvShopList) {
        this.mContext = context;
        this.rvShopList = rvShopList;
        this.datas.addAll(goodsCateBean);
    }

    public void setDatas(List<GoodsCateBean.ResBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_classify_shop_type_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    private int ischeck = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvShoptype.setText(datas.get(position).getTitle());

        holder.tvShoptype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ischeck = position;
                rvShopList.setAdapter(new ClassifyShopListAdapter(mContext, datas, position));
                notifyDataSetChanged();
            }
        });

        if (position == ischeck) {
            holder.tvShoptypeBg.setVisibility(View.VISIBLE);
            holder.tvShoptype.setTextColor(mContext.getColor(R.color.white));
        } else {
            holder.tvShoptypeBg.setVisibility(View.GONE);
            holder.tvShoptype.setTextColor(mContext.getColor(R.color.text666));
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shoptype)
        TextView tvShoptype;
        @BindView(R.id.tv_shoptype_bg)
        TextView tvShoptypeBg;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
