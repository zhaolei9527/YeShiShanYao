package com.lingqiapp.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lingqiapp.Bean.GoodsCateBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 赵磊
 * @date 2018/11/13
 * 功能描述：首页商品列表适配器，包括了头部轮播，横向推荐列表，以及竖向热门列表
 */
public class ClassifyShopListAdapter extends RecyclerView.Adapter<ClassifyShopListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<GoodsCateBean.ResBean> datas = new ArrayList();
    private int position = 0;

    public ArrayList<GoodsCateBean.ResBean> getDatas() {
        return datas;
    }

    public ClassifyShopListAdapter(Context context, List<GoodsCateBean.ResBean> resBean, int position) {
        this.mContext = context;
        this.datas.addAll(resBean);
        this.position = position;
    }

    public void setDatas(List<GoodsCateBean.ResBean> datas) {
        this.datas.addAll(datas);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_classsify_body_list_shop_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String title = datas.get(this.position).getChild().get(position).getTitle();
        String img = datas.get(this.position).getChild().get(position).getImg();
        holder.tvTitleShop.setText(title);
        holder.imgShop.setImageURI(UrlUtils.URL + img);
    }

    @Override
    public int getItemCount() {
        return datas.get(position).getChild().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_shop)
        SimpleDraweeView imgShop;
        @BindView(R.id.tv_title_shop)
        TextView tvTitleShop;
        @BindView(R.id.ll_shop1)
        LinearLayout llShop1;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
