package com.lingqiapp.Activity;

import android.app.Dialog;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.CodeBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class AddAdressActivity extends BaseActivity implements View.OnClickListener {

    private String IsChoosed = "";
    private FrameLayout rl_back;
    private EditText et_name;
    private EditText et_phone;
    private TextView tv_check_address;
    private EditText et_addressContent;
    private CheckBox btnIsChoosed;
    private Button btn_add_address;
    private ArrayList<String> options1Items = new ArrayList<>();
    private String name;
    private String phone;
    private String addressContent;
    private String country;
    private Dialog dialog;
    private String id;

    private void ShowPickerView() {// 弹出选择器
        if (!options1Items.isEmpty()) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    country = options1Items.get(options1);
                    tv_check_address.setText(country);
                }
            })
                    .setTitleBgColor(getResources().getColor(R.color.pressedColor))
                    .setCancelColor(getResources().getColor(R.color.text))
                    .setSubmitColor(getResources().getColor(R.color.text))
                    .setTitleText(getString(R.string.checkCountry))
                    .setTitleColor(getResources().getColor(R.color.text))
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();
            pvOptions.setPicker(options1Items);
            pvOptions.show();
        }

    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_add_adress;
    }

    @Override
    protected void initview() {
        rl_back = (FrameLayout) findViewById(R.id.rl_back);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_check_address = (TextView) findViewById(R.id.tv_check_address);
        et_addressContent = (EditText) findViewById(R.id.et_addressContent);
        btnIsChoosed = (CheckBox) findViewById(R.id.btnIsChoosed);
        btn_add_address = (Button) findViewById(R.id.btn_add_address);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("id"))) {
            id = getIntent().getStringExtra("id");
            IsChoosed = getIntent().getStringExtra("IsChoosed");
            if (TextUtils.equals("1", String.valueOf(IsChoosed))) {
                btnIsChoosed.setChecked(true);
            } else {
                btnIsChoosed.setChecked(false);
            }
            name = getIntent().getStringExtra("name");
            et_name.setText(name);
            phone = getIntent().getStringExtra("tel");
            et_phone.setText(phone);
            addressContent = getIntent().getStringExtra("address");
            et_addressContent.setText(addressContent);
            country = getIntent().getStringExtra("country");
            String tx = country;
            tv_check_address.setText(tx);
        }

        options1Items.add(getString(R.string.xby));
        options1Items.add(getString(R.string.pty));
        options1Items.add(getString(R.string.yy));
        options1Items.add(getString(R.string.ydl));
        options1Items.add(getString(R.string.fy));

    }

    @Override
    protected void initListener() {
        tv_check_address.setOnClickListener(this);
        btn_add_address.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_address:
                submit();
                break;
            case R.id.tv_check_address:
                ShowPickerView();
                break;
            case R.id.rl_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, getString(R.string.addressusername), Toast.LENGTH_SHORT).show();
            return;
        }

        phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, getString(R.string.addressphone), Toast.LENGTH_SHORT).show();
            return;
        }

        if (getString(R.string.select_a).equals(tv_check_address.getText())) {
            Toast.makeText(this, getString(R.string.checkaddress), Toast.LENGTH_SHORT).show();
            return;
        }

        addressContent = et_addressContent.getText().toString().trim();
        if (TextUtils.isEmpty(addressContent)) {
            Toast.makeText(this, getString(R.string.addresscontent), Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            if (!dialog.isShowing()) {
                dialog.show();
            }
            if (!TextUtils.isEmpty(id)) {
                addressDoedit();
            } else {
                addressAdd();
            }
        } else {
            EasyToast.showShort(context, getString(R.string.Networkexception));
        }
    }

    /**
     * 添加收货地址
     */
    private void addressAdd() {
        HashMap<String, String> params = new HashMap<>(9);
        params.put("key", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("name", name);
        params.put("tel", phone);
        params.put("address", addressContent);
        params.put("country", country);
        if (btnIsChoosed.isChecked()) {
            params.put("is_default", "1");
        } else {
            params.put("is_default", "-1");
        }
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "address/add", "address/add", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("addressAdd", result);
                try {
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(codeBean.getStatus()))) {
                        EasyToast.showShort(context, getString(R.string.Addsuccess));
                        finish();
                    } else {
                        EasyToast.showShort(context, getString(R.string.Addfailure));
                    }
                    codeBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 修改收货地址
     */
    private void addressDoedit() {
        HashMap<String, String> params = new HashMap<>(9);
        params.put("key", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("id", id);
        params.put("name", name);
        params.put("tel", phone);
        params.put("address", addressContent);
        params.put("country", country);
        if (btnIsChoosed.isChecked()) {
            params.put("is_default", "1");
        } else {
            params.put("is_default", "-1");
        }
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "address/doedit", "address/doedit", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("addressAdd", result);
                try {
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(codeBean.getStatus()))) {
                        EasyToast.showShort(context, getString(R.string.Addsuccess));
                        finish();
                    } else {
                        EasyToast.showShort(context, getString(R.string.Addfailure));
                    }
                    codeBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("address/doedit");
        App.getQueues().cancelAll("address/add");
        dialog = null;
        System.gc();
    }
}
