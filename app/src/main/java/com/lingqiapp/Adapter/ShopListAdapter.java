package com.lingqiapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingqiapp.Activity.PriceDetailsActivity;
import com.lingqiapp.Bean.GoodsSouBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.View.MYSimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：首页商品列表适配器，包括了头部，轮播，和列表
 */
public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {

    private Activity mContext;

    private ArrayList<GoodsSouBean.ResBean> datas = new ArrayList();

    public ArrayList<GoodsSouBean.ResBean> getDatas() {
        return datas;
    }

    public ShopListAdapter(Activity context, GoodsSouBean homeBean) {
        this.mContext = context;
        datas.addAll(homeBean.getRes());
    }

    public void setDatas(ArrayList<GoodsSouBean.ResBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_shop_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("ShopListAdapter", UrlUtils.URL + datas.get(position).getImg_feng());

        if (datas.get(position).getImg_feng().contains("com")) {
            holder.simShopimg.setImageURI("https://"+datas.get(position).getImg_feng());
            Log.e("ShopListAdapter", "https://" + datas.get(position).getImg_feng());
        } else {
            holder.simShopimg.setImageURI(UrlUtils.URL + datas.get(position).getImg_feng());
            Log.e("ShopListAdapter", UrlUtils.URL + datas.get(position).getImg_feng());
        }
        holder.tvShopmoney.setText(datas.get(position).getPrice() + "元");
        holder.tvShopnum.setText(mContext.getText(R.string.salesNum) + datas.get(position).getXiaoliang());
        holder.tvShoptitle.setText(datas.get(position).getTitle());
        holder.llGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", datas.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.sim_shopimg)
        MYSimpleDraweeView simShopimg;
        @Nullable
        @BindView(R.id.tv_shoptitle)
        TextView tvShoptitle;
        @Nullable
        @BindView(R.id.tv_shopmoney)
        TextView tvShopmoney;
        @Nullable
        @BindView(R.id.tv_shopnum)
        TextView tvShopnum;
        @Nullable
        @BindView(R.id.ll_goods)
        LinearLayout llGoods;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
