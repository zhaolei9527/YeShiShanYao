package com.lingqiapp.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.Adapter.ClassifyShopListAdapter;
import com.lingqiapp.Adapter.ClassifyShopTypeListAdapter;
import com.lingqiapp.Bean.GoodsCateBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.View.ProgressView;
import com.lingqiapp.View.SakuraLinearLayoutManager;
import com.lingqiapp.View.WenguoyiRecycleView;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * com.lingqiapp.Fragment
 *
 * @author 赵磊
 * @date 2018/11/26
 * 功能描述：
 */
public class ClassifyFragment extends BaseLazyFragment {

    Unbinder unbinder;
    @BindView(R.id.rv_shop_type_list)
    WenguoyiRecycleView rvShopTypeList;
    @BindView(R.id.rv_shop_list)
    WenguoyiRecycleView rvShopList;
    public SakuraLinearLayoutManager line1;
    private Context context;
    private ClassifyShopTypeListAdapter shopTypeAdapter;
    private ClassifyShopListAdapter shopListAdapter;
    private GridLayoutManager line2;
    private Dialog dialog;


    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        dialog = Utils.showLoadingDialog(context);
        dialog.show();
        getData();
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_classify_layout, container, false);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*
     *初始化一级分类列表
     * */
    public void initShopType(WenguoyiRecycleView rvShopTypeList, Context context, GoodsCateBean goodsCateBean,WenguoyiRecycleView rvShopList) {
        this.rvShopTypeList = rvShopTypeList;
        this.context = context;
        line1 = new SakuraLinearLayoutManager(context);
        line1.setOrientation(LinearLayoutManager.VERTICAL);
        rvShopTypeList.setLayoutManager(line1);
        rvShopTypeList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(context.getResources().getColor(R.color.colorAccent));
        rvShopTypeList.setFootLoadingView(progressView);
        getShopTypeData(goodsCateBean, rvShopList);
    }

    public void getShopTypeData(final GoodsCateBean goodsCateBean,WenguoyiRecycleView rvShopList) {
        shopTypeAdapter = new ClassifyShopTypeListAdapter(context, goodsCateBean.getRes(),rvShopList);
        rvShopTypeList.setAdapter(shopTypeAdapter);
    }

    /*
     *初始化二级分类列表
     * */
    public void initShopList(WenguoyiRecycleView rvShopList, Context context, GoodsCateBean goodsCateBean, int position) {
        this.rvShopList = rvShopList;
        this.context = context;
        line2 = new GridLayoutManager(mContext, 3);
        line2.setOrientation(LinearLayoutManager.VERTICAL);
        rvShopList.setLayoutManager(line2);
        rvShopList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(context.getResources().getColor(R.color.colorAccent));
        rvShopList.setFootLoadingView(progressView);
        getShopListData(goodsCateBean, position);
    }

    public void getShopListData(GoodsCateBean goodsCateBean, int position) {
        shopListAdapter = new ClassifyShopListAdapter(context, goodsCateBean.getRes(), 0);
        rvShopList.setAdapter(shopListAdapter);
    }


    //数据获取
    public void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("ClassifyFragment", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/cate", "goods/cate", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("ClassifyFragment", result);
                try {
                    dialog.dismiss();
                    GoodsCateBean goodsCateBean = new Gson().fromJson(result, GoodsCateBean.class);
                    if ("1".equals(goodsCateBean.getStatus())) {
                        initShopType(rvShopTypeList, context, goodsCateBean,rvShopList);
                        initShopList(rvShopList, context, goodsCateBean, 0);
                    }
                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
            }
        });

    }


}
