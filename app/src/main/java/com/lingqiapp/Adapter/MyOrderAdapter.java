package com.lingqiapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lingqiapp.Activity.PayActivity;
import com.lingqiapp.App;
import com.lingqiapp.Bean.CodeBean;
import com.lingqiapp.Bean.OrderListsBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.View.CommomDialog;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by 赵磊 on 2017/9/20.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<OrderListsBean.ListBean> datas = new ArrayList();
    private Dialog dialog;

    public ArrayList<OrderListsBean.ListBean> getDatas() {
        return datas;
    }


    public MyOrderAdapter(Context context, List<OrderListsBean.ListBean> list) {
        this.datas.addAll(list);
        this.mContext = context;
    }

    public void setDatas(List<OrderListsBean.ListBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_form_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv_order_form_time.setText(mContext.getString(R.string.The_order_no) + datas.get(position).getOrderid());
        holder.tv_order_content.setText(mContext.getString(R.string.total_of) + datas.get(position).getNumber() + mContext.getString(R.string.Item_total) + ":€" + datas.get(position).getTotalprice());
        holder.ll_oreders.removeAllViews();
        final String stu = datas.get(position).getStatus();
        if ("1".equals(stu)) {
            holder.tv_order_type.setText(mContext.getString(R.string.For_the_payment));
            holder.btn_pay_order.setVisibility(View.VISIBLE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.VISIBLE);
        } else if ("2".equals(stu)) {
            holder.tv_order_type.setText(mContext.getString(R.string.To_send_the_goods));
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
        } else if ("3".equals(stu)) {
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.VISIBLE);
            holder.tv_order_type.setText(mContext.getString(R.string.For_the_goods));
        } else if ("4".equals(stu)) {
            holder.tv_order_type.setText(mContext.getString(R.string.To_evaluate));
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
        } else {
            holder.tv_order_type.setText(mContext.getString(R.string.Has_been_cancelled));
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
        }

        if ("10".equals(stu)) {
            ViewGroup.LayoutParams layoutParams = holder.ll_or.getLayoutParams();
            layoutParams.height = 0;
            holder.ll_or.setLayoutParams(layoutParams);
        }

        View item_oreder_layout = View.inflate(mContext, R.layout.item_orederlist_layout, null);

        SimpleDraweeView SimpleDraweeView = (com.facebook.drawee.view.SimpleDraweeView) item_oreder_layout.findViewById(R.id.SimpleDraweeView);

        if (datas.get(position).getImg_feng().contains("com")) {
            SimpleDraweeView.setImageURI(datas.get(position).getImg_feng());
        } else {
            SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg_feng());
        }
        TextView tv_title = (TextView) item_oreder_layout.findViewById(R.id.tv_title);
        tv_title.setText(datas.get(position).getTitle());
        TextView tv_price = (TextView) item_oreder_layout.findViewById(R.id.tv_price);
        tv_price.setText("€" + datas.get(position).getPrice());
        TextView tv_num = (TextView) item_oreder_layout.findViewById(R.id.tv_num);
        tv_num.setText("  X" + datas.get(position).getNumber());
        holder.ll_oreders.addView(item_oreder_layout);

        holder.btn_pay_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = Utils.showLoadingDialog(mContext);
                dialog.show();
                mContext.startActivity(new Intent(mContext, PayActivity.class)
                        .putExtra("orderid", datas.get(position).getOrderid())
                        .putExtra("monsy", datas.get(position).getTotalprice())
                );
                dialog.dismiss();
            }
        });

        holder.btn_delete_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommomDialog(mContext, R.style.dialog, mContext.getString(R.string.cancel_the_order), new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            orderCancel(datas.get(position).getId());
                            datas.get(position).setStatus("10");
                            notifyDataSetChanged();
                        }
                    }
                }).setTitle(mContext.getString(R.string.prompt)).show();
            }
        });

        holder.btn_isget_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datas.get(position).setStatus("10");
                notifyItemChanged(position);
                orderReceipt(datas.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_order_form_time;
        public TextView tv_order_type;
        public TextView tv_order_content;
        public Button btn_pay_order;
        public Button btn_isget_order;
        public LinearLayout ll_oreders;
        public LinearLayout ll_or;

        public Button btn_delete_order;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.btn_delete_order = (Button) rootView.findViewById(R.id.btn_delete_order);
            this.tv_order_form_time = (TextView) rootView.findViewById(R.id.tv_order_form_time);
            this.tv_order_type = (TextView) rootView.findViewById(R.id.tv_order_type);
            this.tv_order_content = (TextView) rootView.findViewById(R.id.tv_order_content);
            this.btn_pay_order = (Button) rootView.findViewById(R.id.btn_pay_order);
            this.btn_isget_order = (Button) rootView.findViewById(R.id.btn_isget_order);
            this.ll_oreders = (LinearLayout) rootView.findViewById(R.id.ll_oreders);
            this.ll_or = (LinearLayout) rootView.findViewById(R.id.ll_or);
        }
    }

    /**
     * 订单取消
     */
    private void orderCancel(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("id", id);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/quxiao"+ App.LanguageTYPEHTTP, "order/quxiao", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    CodeBean orderCancelBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(orderCancelBean.getStatus()))) {
                        Toast.makeText(mContext, mContext.getString(R.string.Cancel_success), Toast.LENGTH_SHORT).show();
                    } else {
                        EasyToast.showShort(mContext, orderCancelBean.getMsg());
                    }
                    result = null;
                    orderCancelBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
            }
        });
    }


    /**
     * 确认收货
     */
    private void orderReceipt(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("id", id);
        Log.e("MyOrderAdapter", "params:" + params);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/queren"+ App.LanguageTYPEHTTP, "order/queren", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    CodeBean suckleCartDelBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(suckleCartDelBean.getStatus()))) {
                        EasyToast.showShort(mContext, mContext.getString(R.string.receipt_successful));
                    } else {
                        EasyToast.showShort(mContext, mContext.getString(R.string.receipt_failure));
                    }
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

}
