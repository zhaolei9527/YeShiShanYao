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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lingqiapp.Bean.SuckleCartBean;
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

import static com.mob.tools.utils.Strings.getString;

/**
 * Created by 赵磊 on 2017/9/20.
 */

public class ShopCarListAdapter extends RecyclerView.Adapter<ShopCarListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SuckleCartBean.ListBean> datas = new ArrayList();
    private TextView tvMoney;

    public ArrayList<SuckleCartBean.ListBean> getDatas() {
        return datas;
    }

    public ShopCarListAdapter(List<SuckleCartBean.ListBean> list, Context context, TextView tvMoney) {
        this.datas = (ArrayList<SuckleCartBean.ListBean>) list;
        this.mContext = context;
        this.tvMoney = tvMoney;
        dialog = Utils.showLoadingDialog(context);
        dialog.dismiss();
    }

    public void setDatas(ArrayList<SuckleCartBean.ListBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gouwuche_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    private Dialog dialog;

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg_feng());
        holder.btn_shuliang.setText(datas.get(position).getNumber());
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_money.setText("￥" + datas.get(position).getPrice());
        holder.btn_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isConnected(mContext)) {
                    dialog.show();
                    joinCart(datas.get(position).getId(), holder, position);
                } else {
                    EasyToast.showShort(mContext, mContext.getString(R.string.Networkexception));
                }

            }
        });
        holder.btn_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isConnected(mContext)) {
                    dialog.show();
                    reduce(datas.get(position).getId(), holder, position);
                } else {
                    EasyToast.showShort(mContext, mContext.getString(R.string.Networkexception));
                }
            }
        });

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
                        holder.btnIsChoosed.getContext().sendBroadcast(new Intent("shopCarChoosedAll").putExtra("Choosed", true));
                    }
                } else {
                    datas.get(position).setCheck(false);
                    holder.btnIsChoosed.getContext().sendBroadcast(new Intent("shopCarChoosedAll").putExtra("Choosed", false));
                }

                double money = 0;
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).isCheck()) {
                        double v = Double.parseDouble(datas.get(i).getPrice());
                        int i1 = Integer.parseInt(datas.get(i).getNumber());
                        money = money + (v * i1);
                    }
                }
                tvMoney.setText("￥" + String.format("%.2f", money));
            }
        });

        Log.d("ShopCarListAdapter", datas.toString());
        if (datas.get(position).isCheck()) {
            holder.btnIsChoosed.setChecked(true);
        } else {
            holder.btnIsChoosed.setChecked(false);
        }


        holder.img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new CommomDialog(mContext, R.style.dialog, mContext.getString(R.string.delete_it), new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, final boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            delCar(datas.get(position).getId(), holder, position);
                            datas.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                }).setTitle(getString(R.string.prompt)).show();

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
        public SimpleDraweeView SimpleDraweeView;
        public TextView tv_title;
        public TextView tv_money;
        public TextView btn_shuliang;
        public CheckBox btnIsChoosed;
        public Button btn_jian;
        public Button btn_jia;
        public ImageView img_close;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.SimpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
            this.btn_shuliang = (TextView) rootView.findViewById(R.id.btn_shuliang);
            this.btnIsChoosed = (CheckBox) rootView.findViewById(R.id.btnIsChoosed);
            this.btn_jian = (Button) rootView.findViewById(R.id.btn_jian);
            this.btn_jia = (Button) rootView.findViewById(R.id.btn_jia);
            this.img_close = (ImageView) rootView.findViewById(R.id.img_close);
            this.btnIsChoosed.setTag(new Integer(-2));//这里
        }
    }


    /**
     * 收藏产品
     */
    private void joinCart(String id, final ViewHolder holder, final int position) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("id", id);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "cart/join_cart", "cart/join_cart", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivitycang", result);
                try {
                    dialog.dismiss();

                    int i = Integer.parseInt(holder.btn_shuliang.getText().toString());
                    i = i + 1;
                    holder.btn_shuliang.setText(String.valueOf(i));
                    datas.get(position).setNumber(String.valueOf(i));
                    double money = 0;
                    for (int i2 = 0; i2 < datas.size(); i2++) {
                        if (datas.get(i2).isCheck()) {
                            double Price = Double.parseDouble(datas.get(i2).getPrice());
                            int i1 = Integer.parseInt(datas.get(i2).getNumber());
                            money = money + (Price * i1);
                        }
                    }
                    tvMoney.setText("￥" + String.format("%.2f", money));

                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(mContext, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(mContext, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 收藏产品
     */
    private void reduce(String id, final ViewHolder holder, final int position) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("id", id);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "cart/reduce", "cart/reduce", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivitycang", result);
                try {
                    dialog.dismiss();

                    int i = Integer.parseInt(holder.btn_shuliang.getText().toString());
                    i = i - 1;
                    if (i < 1) {
                        i = 1;
                    }
                    holder.btn_shuliang.setText(String.valueOf(i));
                    datas.get(position).setNumber(String.valueOf(i));

                    double money = 0;
                    for (int i2 = 0; i2 < datas.size(); i2++) {
                        if (datas.get(i2).isCheck()) {
                            double Price = Double.parseDouble(datas.get(i2).getPrice());
                            int i1 = Integer.parseInt(datas.get(i2).getNumber());
                            money = money + (Price * i1);
                        }
                    }
                    tvMoney.setText("￥" + String.format("%.2f", money));


                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(mContext, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(mContext, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 收藏产品
     */
    private void delCar(String id, final ViewHolder holder, final int position) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("ids", id);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "cart/del_car", "cart/del_car", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivitycang", result);
                try {
                    dialog.dismiss();


                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(mContext, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(mContext, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
