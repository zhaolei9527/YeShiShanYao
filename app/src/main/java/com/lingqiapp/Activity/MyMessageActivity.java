package com.lingqiapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hss01248.frescopicker.FrescoIniter;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.AboutPersonalBean;
import com.lingqiapp.Bean.TouXiangBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.View.ChangeNameDialog;
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
 * @date 2018/9/19
 * 功能描述：
 */
public class MyMessageActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    @BindView(R.id.rl_change_touxiang)
    RelativeLayout rlChangeTouxiang;
    @BindView(R.id.tv_nicheng)
    TextView tvNicheng;
    @BindView(R.id.r_change_name)
    RelativeLayout rChangeName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.rl_change_psw)
    RelativeLayout rlChangePsw;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Dialog dialog;
    private Dialog dialogResult;
    private String pic = "";

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
                        SimpleDraweeView.setImageURI("file://" + photos.get(0));
                        List<File> imgfiles = new ArrayList<>();
                        List<String> imgnames = new ArrayList<>();
                        imgfiles.add(new File(pic));
                        imgnames.add("headpic");
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
    protected int setthislayout() {
        return R.layout.activity_message_layout;
    }

    @Override
    protected void initview() {
        PhotoPickUtils.init(getApplicationContext(), new FrescoIniter());//第二个参数根据具体依赖库而定
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyToast.showShort(context, getString(R.string.Save_success));
            }
        });

        rlChangePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ChangePasswordActivity.class));
            }
        });

        rChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChangeNameDialog(context, R.style.dialog, "", new ChangeNameDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            EditText contentTxt = (EditText) dialog.findViewById(R.id.content);
                            if (TextUtils.isEmpty(contentTxt.getText().toString())) {
                                EasyToast.showShort(context, contentTxt.getHint().toString());
                                return;
                            }
                            if (contentTxt.getText().toString().length() > 12) {
                                EasyToast.showShort(context, getString(R.string.The_user_name_is_too_long));
                                return;
                            }
                            tvNicheng.setText(contentTxt.getText().toString());
                            SpUtil.putAndApply(context, "username", tvNicheng.getText());
                            aboutEditname();
                            dialog.dismiss();
                        } else {

                            dialog.dismiss();
                        }
                    }
                }).setTitle(getString(R.string.Modify_the_nickname)).show();

            }
        });

        rlChangeTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoPickUtils.startPick().setPhotoCount(1).setShowCamera(false).start((MyMessageActivity) context, 505);
            }
        });

    }


    /**
     * 更换头像
     */
    private void userDoinfo(List<String> imgnames, List<File> imgs) {
        final HashMap<String, String> params = new HashMap<>(2);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("MyMessageActivity", params.toString());
        VolleyRequest.uploadMultipart(context, UrlUtils.BASE_URL + "about/touxiang", imgnames, imgs, params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyMessageActivity", result);
                try {
                    dialogResult.dismiss();
                    TouXiangBean touXiangBean = new Gson().fromJson(result, TouXiangBean.class);
                    if (1 == touXiangBean.getStatus()) {
                        SpUtil.putAndApply(context, "img", touXiangBean.getUdata().getImg());
                        SimpleDraweeView.setImageURI(UrlUtils.URL + touXiangBean.getUdata().getImg());
                        EasyToast.showShort(context, touXiangBean.getMsg());
                    } else {
                        EasyToast.showShort(context, touXiangBean.getMsg());
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
     * 修改用户名
     */
    private void aboutEditname() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("name", tvNicheng.getText().toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/sav_name", "about/sav_name", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            getEwm();
        } else {
            EasyToast.showShort(context, getString(R.string.Networkexception));
        }

    }

    /**
     * 发送验证码
     */
    private void getEwm() {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/personal", "about/personal", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    dialog.dismiss();
                    AboutPersonalBean aboutPersonalBean = new Gson().fromJson(result, AboutPersonalBean.class);
                    if (1 == aboutPersonalBean.getStatus()) {
                        SimpleDraweeView.setImageURI(UrlUtils.URL + aboutPersonalBean.getUdate().getImg());
                        tvNicheng.setText(aboutPersonalBean.getUdate().getNi_name());
                        tvPhone.setText(aboutPersonalBean.getUdate().getEmail());
                    }
                } catch (Exception e) {
                    dialog.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
            }
        });
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
