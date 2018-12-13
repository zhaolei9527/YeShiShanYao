package com.lingqiapp.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hss01248.frescopicker.FrescoIniter;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.DoPingBean;
import com.lingqiapp.Bean.OrderDetailBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Volley.VolleyInterface;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPickUtils;
import me.iwf.photopicker.widget.MultiPickResultView;

import static com.lingqiapp.Volley.VolleyRequest.formatUrlMap;
import static com.lingqiapp.Volley.VolleyRequest.urlmd5;

public class PingJiaPriceActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.img_3)
    ImageView img3;
    @BindView(R.id.img_4)
    ImageView img4;
    @BindView(R.id.img_5)
    ImageView img5;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.recycler_view)
    MultiPickResultView recyclerView;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_pingjia_lv)
    TextView tvPingjiaLv;
    private String orderResult;
    private String start = "5";
    private Dialog dialog;
    private ArrayList<String> imgpath = new ArrayList<>();
    private OrderDetailBean orderDetailBean;

    /**
     * 删除文件夹以及目录下的文件
     *
     * @param pPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public void deleteDirWihtFile(final File dir) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (dir == null || !dir.exists() || !dir.isDirectory()) {
                    return;
                }
                for (File file : dir.listFiles()) {
                    if (file.isFile()) {
                        file.delete(); // 删除所有文件
                    } else if (file.isDirectory()) {
                        deleteDirWihtFile(file); // 递规的方式删除文件夹
                    }
                }
                dir.delete();// 删除目录本身
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("order/retreat");
        App.getQueues().cancelAll("order/doretreat");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //onActivityResult里一行代码回调
        recyclerView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_submit_pingjia_price;
    }

    @Override
    protected void initview() {
        recyclerView = (MultiPickResultView) findViewById(R.id.recycler_view);
        recyclerView.init(this, MultiPickResultView.ACTION_SELECT, null);
        orderResult = getIntent().getStringExtra("orderResult");
        orderDetailBean = new Gson().fromJson(orderResult, OrderDetailBean.class);

        if (orderDetailBean.getOrder().getImg_feng().contains(".com")) {
            SimpleDraweeView.setImageURI(orderDetailBean.getOrder().getImg_feng());
        } else {
            SimpleDraweeView.setImageURI(UrlUtils.URL + orderDetailBean.getOrder().getImg_feng());
        }

        tvTitle.setText(orderDetailBean.getOrder().getName());
        Acp.getInstance(context).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .setDeniedMessage(getString(R.string.requstPerminssions))
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        PhotoPickUtils.init(getApplicationContext(), new FrescoIniter());//第二个参数根据具体依赖库而定
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(context, getString(R.string.Thepermissionapplicationisrejected), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
        } else {
            EasyToast.showShort(context, getString(R.string.Networkexception));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_1:
                start = "1";
                img1.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img2.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                img3.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                img4.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                img5.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                tvPingjiaLv.setText(getString(R.string.Bad_review));
                break;
            case R.id.img_2:
                start = "2";
                img1.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img2.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img3.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                img4.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                img5.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                tvPingjiaLv.setText(getString(R.string.evaluation));
                break;
            case R.id.img_3:
                start = "3";
                img1.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img2.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img3.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img4.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                img5.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                tvPingjiaLv.setText(getString(R.string.evaluation));
                break;
            case R.id.img_4:
                start = "4";

                img1.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img2.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img3.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img4.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img5.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                tvPingjiaLv.setText(getString(R.string.High_praise));
                break;
            case R.id.img_5:
                start = "5";

                img1.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img2.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img3.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img4.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                img5.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                tvPingjiaLv.setText(getString(R.string.High_praise));
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_submit:
                submit();

                break;
            default:
                break;
        }
    }

    /**
     * 提交退换货
     */
    private void submit() {
        final ArrayList<String> photos = recyclerView.getPhotos();
        Log.e("SubmitReturnPriceActivi", photos.toString());
        ArrayList<File> datas = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        if (!photos.isEmpty()) {
            dialog.show();
            for (int i = 0; i < photos.size(); i++) {
                File file = new File(photos.get(i));
                names.add("p_img[]");
                datas.add(file);
            }
            orderDoretreat(orderDetailBean.getOrder().getOrderid(), datas, names);
        } else {
            orderDoretreat(orderDetailBean.getOrder().getOrderid(), datas, names);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 退换申请
     */
    private void orderDoretreat(String id, List<File> imgs, List<String> names) {
        final HashMap<String, String> params = new HashMap<>(6);
        params.put("oid", id);
        params.put("star", start);
        if (!TextUtils.isEmpty(editText.getText().toString().trim())) {
            params.put("pcontent", editText.getText().toString().trim());
        } else {
            params.put("pcontent", getString(R.string.not_evaluation_content));
        }
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        String s = formatUrlMap(params, false, false);
        String s1 = urlmd5(s, UrlUtils.KEY);
        params.put("pwd", s1);
        Utils.uploadMultipart(context, UrlUtils.BASE_URL + "order/do_ping"+ App.LanguageTYPEHTTP, names, imgs, params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("SubmitReturnPriceActivi", result);
                try {
                    DoPingBean doPingBean = new Gson().fromJson(result, DoPingBean.class);
                    EasyToast.showShort(context, doPingBean.getMsg());
                    if ("1".equals(String.valueOf(doPingBean.getStatus()))) {
                        setResult(200);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    EasyToast.showShort(context, getString(R.string.Abnormalserver));
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
