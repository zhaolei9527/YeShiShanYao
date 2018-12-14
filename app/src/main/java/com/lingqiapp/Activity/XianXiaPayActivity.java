package com.lingqiapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hss01248.frescopicker.FrescoIniter;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.OrderXianBean;
import com.lingqiapp.Bean.orderPayXianBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPickUtils;

/**
 * com.lingqiapp.Activity
 *
 * @author 赵磊
 * @date 2018/12/14
 * 功能描述：
 */
public class XianXiaPayActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_bank_name1)
    TextView tvBankName1;
    @BindView(R.id.tv_bank_num1)
    TextView tvBankNum1;
    @BindView(R.id.tv_bank_bank1)
    TextView tvBankBank1;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_bank_name2)
    TextView tvBankName2;
    @BindView(R.id.tv_bank_num2)
    TextView tvBankNum2;
    @BindView(R.id.tv_bank_bank2)
    TextView tvBankBank2;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.tv_bank_name3)
    TextView tvBankName3;
    @BindView(R.id.tv_bank_num3)
    TextView tvBankNum3;
    @BindView(R.id.tv_bank_bank3)
    TextView tvBankBank3;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Dialog dialog;
    private Dialog dialogResult;
    private String pic = "";

    @Override
    protected int setthislayout() {
        return R.layout.activity_xianxia_pay;
    }

    @Override
    protected void initview() {
        dialog = Utils.showLoadingDialog(context);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoPickUtils.onActivityResult(requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
            @Override
            public void onPickSuccess(final ArrayList<String> photos, int requestCode) {
                switch (requestCode) {
                    case 505:
                        dialogResult = Utils.showLoadingDialog(context);
                        dialogResult.show();
                        pic = photos.get(0);
                        List<File> imgfiles = new ArrayList<>();
                        List<String> imgnames = new ArrayList<>();
                        imgfiles.add(new File(pic));
                        imgnames.add("p_img");
                        userDoinfo(imgnames, imgfiles);
                        break;
                    default:
                        break;
                }
                Log.e("MyMessageActivity", photos.get(0));
            }

            @Override
            public void onPreviewBack(ArrayList<String> photos, int requestCode) {
            }

            @Override
            public void onPickFail(String error, int requestCode) {
            }

            @Override
            public void onPickCancle(int requestCode) {
            }
        });
    }


    @Override
    protected void initListener() {
        PhotoPickUtils.init(getApplicationContext(), new FrescoIniter());//第二个参数根据具体依赖库而定
        btnSubmit.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        dialog.show();
        getOrderXian();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 提交凭证
     */
    private void userDoinfo(List<String> imgnames, List<File> imgs) {
        final HashMap<String, String> params = new HashMap<>(2);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("type", "3");
        params.put("ltype", App.LanguageTYPE);
        params.put("oid", String.valueOf(getIntent().getStringExtra("oid")));
        Log.e("MyMessageActivity", params.toString());
        VolleyRequest.uploadMultipart(context, UrlUtils.BASE_URL + "order/pay_xian" + App.LanguageTYPEHTTP, imgnames, imgs, params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyMessageActivity", result);
                try {
                    dialogResult.dismiss();
                    orderPayXianBean orderPayXianBean = new Gson().fromJson(result, orderPayXianBean.class);

                    if (orderPayXianBean.getStatus() == 1) {
                        finish();
                    } else {
                        EasyToast.showShort(context, orderPayXianBean.getMsg());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialogResult.dismiss();
                error.printStackTrace();
            }
        });
    }

    /**
     * 登录获取
     */
    private void getOrderXian() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("LoginActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/xian" + App.LanguageTYPEHTTP, "order/xian", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                String decode = result;
                Log.e("LoginActivity", decode);
                try {

                    OrderXianBean orderXianBean = new Gson().fromJson(decode, OrderXianBean.class);

                    if (TextUtils.isEmpty(orderXianBean.getConf().getKaihu1())) {
                        ll1.setVisibility(View.GONE);
                    }

                    if (TextUtils.isEmpty(orderXianBean.getConf().getKaihu2())) {
                        ll2.setVisibility(View.GONE);
                    }

                    if (TextUtils.isEmpty(orderXianBean.getConf().getKaihu3())) {
                        ll3.setVisibility(View.GONE);
                    }

                    tvBankName1.setText(orderXianBean.getConf().getZ_name1());
                    tvBankName2.setText(orderXianBean.getConf().getZ_name2());
                    tvBankName3.setText(orderXianBean.getConf().getZ_name3());
                    tvBankNum1.setText(orderXianBean.getConf().getZhuan1());
                    tvBankNum2.setText(orderXianBean.getConf().getZhuan2());
                    tvBankNum3.setText(orderXianBean.getConf().getZhuan3());
                    tvBankBank1.setText(orderXianBean.getConf().getKaihu1());
                    tvBankBank2.setText(orderXianBean.getConf().getKaihu2());
                    tvBankBank3.setText(orderXianBean.getConf().getKaihu3());
                    tvContent.setText(orderXianBean.getConf().getX_content());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                PhotoPickUtils.startPick().setPhotoCount(1).setShowCamera(true).start(this, 505);
                break;
            default:
                break;
        }
    }
}
