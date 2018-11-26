package com.lingqiapp.Activity;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.LoginBean;
import com.lingqiapp.Fragment.CartFragment;
import com.lingqiapp.Fragment.ClassifyFragment;
import com.lingqiapp.Fragment.HomeFragment;
import com.lingqiapp.Fragment.MeFragment;
import com.lingqiapp.Fragment.NewsFragment;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.View.CustomViewPager;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sakura.bottomtabbar.BottomTabBar;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_content)
    CustomViewPager flContent;
    @BindView(R.id.BottomTabBar)
    sakura.bottomtabbar.BottomTabBar BottomTabBar;
    @BindView(R.id.tv_Cartnum)
    TextView tvCartnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.READ_PHONE_STATE
                                , Manifest.permission.CAMERA)
                        .setDeniedMessage(getString(R.string.requstPerminssions))
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        try {
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            cm.setText(TimePickerView.ZFB);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(MainActivity.this, getString(R.string.Thepermissionapplicationisrejected), Toast.LENGTH_SHORT).show();
                    }
                });
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new CartFragment());
        fragments.add(new MeFragment());
        CustomViewPager viewpager = (CustomViewPager) findViewById(R.id.fl_content);
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        ((BottomTabBar) findViewById(R.id.BottomTabBar)).setPadding(0, 0, 0, 0);
        ((BottomTabBar) findViewById(R.id.BottomTabBar))
                .initFragmentorViewPager(viewpager)
                .setImgSize(getResources().getDimension(R.dimen.x19), getResources().getDimension(R.dimen.y16))
                .setChangeColor(getResources().getColor(R.color.bgtitle), getResources().getColor(R.color.text666))
                .setDividerHeight(3)
                .isShowDivider(true)
                .setFontSize(12)
                .setDividerColor(getResources().getColor(R.color.bgea))
                .addTabItem(getString(R.string.home), getResources().getDrawable(R.mipmap.bot01_after), getResources().getDrawable(R.mipmap.bot01_before))
                .addTabItem(getString(R.string.recommen), getResources().getDrawable(R.mipmap.bot02_after), getResources().getDrawable(R.mipmap.bot02_before))
                .addTabItem(getString(R.string.fenlei), getResources().getDrawable(R.mipmap.fenlei01), getResources().getDrawable(R.mipmap.fenlei02))
                .addTabItem(getString(R.string.shopping_cart), getResources().getDrawable(R.mipmap.bot03_after), getResources().getDrawable(R.mipmap.bot03_before))
                .addTabItem(getString(R.string.Personal_center), getResources().getDrawable(R.mipmap.bot05_after), getResources().getDrawable(R.mipmap.bot05_before))
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, View view) {
                        if (position == 3 || position == 4) {
                            if (TextUtils.isEmpty((String) SpUtil.get(MainActivity.this, "uid", ""))) {
                                EasyToast.showShort(MainActivity.this, getString(R.string.Please_login_first));
                                finish();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        }
                        if (!TextUtils.isEmpty((String) SpUtil.get(MainActivity.this, "uid", ""))) {
                            // indexCatr();
                        }

                    }
                })
                .commit();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    private String account;
    private String password;
    private String wxopenid;


    @Override
    protected void onResume() {
        super.onResume();
        account = (String) SpUtil.get(context, "tel", "");
        password = (String) SpUtil.get(context, "password", "");
        wxopenid = (String) SpUtil.get(context, "wxopenid", "");
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
            getLogin(account, password, "");
            Log.e("FlashActivity", "常规登录");
        } else if (!TextUtils.isEmpty(wxopenid)) {
            getLogin("", "", wxopenid);
            Log.e("FlashActivity", "wx登录");
        }
    }

    /**
     * 登录获取
     */
    private void getLogin(final String tel, final String password, String openid) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("email", tel);
        params.put("password", password);
        if (!TextUtils.isEmpty(openid)) {
            params.put("openid", openid);
        }
        Log.e("LoginActivity", "params:" + params);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/dologin", "login/dologin", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("LoginActivity", decode);
                try {
                    LoginBean loginBean = new Gson().fromJson(decode, LoginBean.class);
                    if ("1".equals(loginBean.getStatus())) {
                        SpUtil.putAndApply(context, "uid", loginBean.getUser().getId().toString());
                        SpUtil.putAndApply(context, "username", loginBean.getUser().getNi_name());
                        SpUtil.putAndApply(context, "money", loginBean.getUser().getMoney());
                        SpUtil.putAndApply(context, "img", loginBean.getUser().getImg());
                        SpUtil.putAndApply(context, "lv", loginBean.getUser().getIs_hui());
                        SpUtil.putAndApply(context, "password", password);
                        SpUtil.putAndApply(context, "tel", "" + loginBean.getUser().getEmail());
                        SpUtil.putAndApply(context, "zw_count", "" + loginBean.getZw_count());
                    } else {
                        EasyToast.showShort(context, getString(R.string.Login_failed_login));
                        startActivity(new Intent(context, LoginActivity.class));
                        finish();
                    }
                    decode = null;
                    loginBean = null;
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
