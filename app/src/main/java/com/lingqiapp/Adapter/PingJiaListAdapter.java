package com.lingqiapp.Adapter;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingqiapp.Bean.GoodsPingBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.DateUtils;
import com.lingqiapp.Utils.PixelUtils;
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
public class PingJiaListAdapter extends RecyclerView.Adapter<PingJiaListAdapter.ViewHolder> {

    private Activity mContext;

    private ArrayList<GoodsPingBean.ResBean> datas = new ArrayList();

    public ArrayList<GoodsPingBean.ResBean> getDatas() {
        return datas;
    }

    public PingJiaListAdapter(Activity context, GoodsPingBean homeBean) {
        this.mContext = context;
        datas.addAll(homeBean.getRes());
    }

    public void setDatas(ArrayList<GoodsPingBean.ResBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_pingjia_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.sdvPingjia.setImageURI(UrlUtils.URL + datas.get(position).getImg());
        holder.tvPingjiaName.setText(datas.get(position).getNi_name());
        holder.tvPingjiaContent.setText(datas.get(position).getPcontent());
        holder.tvPingjiaTime.setText(DateUtils.getMillon(Long.parseLong(datas.get(position).getAddtime()) * 1000));

        String xing = datas.get(position).getStar();
        Double i = Double.parseDouble(xing);
        for (int i1 = 0; i1 < 5; i1++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setPadding(PixelUtils.dip2px(mContext, 3), 0, 0, 0);
            if (i1 < i) {
                imageView.setBackgroundResource(R.mipmap.pingjia1);
            } else {
                imageView.setBackgroundResource(R.mipmap.pingjia2);
            }
            holder.llStar.addView(imageView);
        }

        if (!datas.get(position).getP_img().isEmpty()) {
            holder.llImgs.setVisibility(View.VISIBLE);
            for (int i1 = 0; i1 < datas.get(position).getP_img().size(); i1++) {
                if (i1 == 0) {
                    holder.SimpleDraweeView1.setImageURI(UrlUtils.URL + datas.get(position).getP_img().get(0));
                } else if (i1 == 1) {
                    holder.SimpleDraweeView2.setImageURI(UrlUtils.URL + datas.get(position).getP_img().get(1));
                } else {
                    holder.SimpleDraweeView3.setImageURI(UrlUtils.URL + datas.get(position).getP_img().get(2));
                }
            }
        } else {
            holder.llImgs.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv_pingjia)
        MYSimpleDraweeView sdvPingjia;
        @BindView(R.id.tv_pingjia_name)
        TextView tvPingjiaName;
        @BindView(R.id.ll_star)
        LinearLayout llStar;
        @BindView(R.id.tv_pingjia_time)
        TextView tvPingjiaTime;
        @BindView(R.id.tv_pingjia_content)
        TextView tvPingjiaContent;
        @BindView(R.id.SimpleDraweeView1)
        MYSimpleDraweeView SimpleDraweeView1;
        @BindView(R.id.SimpleDraweeView2)
        MYSimpleDraweeView SimpleDraweeView2;
        @BindView(R.id.SimpleDraweeView3)
        MYSimpleDraweeView SimpleDraweeView3;
        @BindView(R.id.ll_imgs)
        LinearLayout llImgs;
        @BindView(R.id.ll_has_pingjia)
        LinearLayout llHasPingjia;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
