package com.lingqiapp.Visa;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.util.MyProgressDialog;
import com.google.gson.Gson;
import com.lingqiapp.Activity.GoodPayActivity;
import com.lingqiapp.Bean.VisaBean;
import com.lingqiapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button nextBtn;
    private EditText ordernumber, cardnumber, month, year,
            cvv2, cardbank;
    private TextView amount;
    private RadioGroup radiogroup;
    private RadioButton rbjcb, rbvisa, rbmaster;
    private String newcardtype;
    private String cardnumParams;
    private String merchantMIDparams;
    private String cvv2params;
    private String monthparams;
    private String yearparams;
    private String cardbankparams;
    private String BillNoparams;
    private String Amountparams;
    private String Currencyparams;
    private String Languageparams;
    private String HASHparams;
    private String ReturnURLparams;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String country;
    private String state;
    private String city;
    private String address;
    private String zipcode;
    private String products;
    private String ipAddr;
    private String shippingFirstName;
    private String shippingLastName;
    private String shippingEmail;
    private String shippingPhone;
    private String shippingZipcode;
    private String shippingAddress;
    private String shippingCity;
    private String shippingSstate;
    private String shippingCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ipAddr = getIPAddress(this);
        initView();
        Intent intent = getIntent();
        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        country = intent.getStringExtra("country");
        state = intent.getStringExtra("state");
        city = intent.getStringExtra("city");
        address = intent.getStringExtra("address");
        zipcode = intent.getStringExtra("zipcode");
        shippingFirstName = intent.getStringExtra("shippingFirstName");
        shippingLastName = intent.getStringExtra("shippingLastName");
        shippingEmail = intent.getStringExtra("shippingEmail");
        shippingPhone = intent.getStringExtra("shippingPhone");
        shippingSstate = intent.getStringExtra("shippingSstate");
        shippingCity = intent.getStringExtra("shippingCity");
        shippingAddress = intent.getStringExtra("shippingAddress");
        shippingZipcode = intent.getStringExtra("shippingZipcode");

        nextBtn.setOnClickListener(this);
        rbvisa.setChecked(true);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rbjcb:
                        newcardtype = "3";
                        break;
                    case R.id.rbvisa:
                        newcardtype = "4";
                        break;
                    case R.id.rbmaster:
                        newcardtype = "5";
                        break;
                }
            }
        });
    }

    private void initView() {
        nextBtn = (Button) findViewById(R.id.cardnext);
        amount = (TextView) findViewById(R.id.amount);
        ordernumber = (EditText) findViewById(R.id.ordernumber);
        cardnumber = (EditText) findViewById(R.id.cardnumber);
        month = (EditText) findViewById(R.id.month);
        year = (EditText) findViewById(R.id.year);
        cvv2 = (EditText) findViewById(R.id.cvv2);
        cardbank = (EditText) findViewById(R.id.cardbank);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        rbjcb = (RadioButton) findViewById(R.id.rbjcb);
        rbvisa = (RadioButton) findViewById(R.id.rbvisa);
        rbmaster = (RadioButton) findViewById(R.id.rbmaster);
    }

    @Override
    public void onClick(View view) {
        final MyProgressDialog dialog = new MyProgressDialog(CardActivity.this);
        dialog.showLoadingProgressDialog(getString(R.string.loading));
        merchantMIDparams = "4755";
        cardnumParams = new String(Base64.encode(cardnumber.getText().toString().trim().getBytes()));
        Log.e("result", "onClick: " + newcardtype);
        cvv2params = new String(Base64.encode(cvv2.getText().toString().trim().getBytes()));
        monthparams = new String(Base64.encode(month.getText().toString().trim().getBytes()));
        yearparams = new String(Base64.encode(year.getText().toString().trim().getBytes()));
        cardbankparams = cardbank.getText().toString().trim();
        BillNoparams = getIntent().getStringExtra("oid");
        Amountparams = amount.getText().toString().trim();
        Currencyparams = "2";
        Languageparams = "en";
        ReturnURLparams = "http://danyh.t.100help.net/visa/pay_return.php";
        HASHparams = MD5Utils.getMessageDigest((merchantMIDparams + BillNoparams + Currencyparams + Amountparams + Languageparams
                + ReturnURLparams + "g{avHCmy").getBytes());
        Log.e("result", "onClick: " + HASHparams);
        products = getIntent().getStringExtra("oid");
        shippingFirstName = firstname;
        shippingLastName = lastname;
        shippingEmail = email;
        shippingPhone = phone;
        shippingZipcode = zipcode;
        shippingAddress = address;
        shippingCity = city;
        shippingSstate = state;

        shippingCountry = "CHNCN";

        Log.e("result", "onClick: ");
        AjaxParams params = new AjaxParams();
        params.put("merchantMID", merchantMIDparams);
        params.put("newcardtype", newcardtype);
        params.put("cardnum", cardnumParams);
        params.put("cvv2", cvv2params);
        params.put("month", monthparams);
        params.put("year", yearparams);
        params.put("cardbank", cardbankparams);
        params.put("BillNo", BillNoparams);
        params.put("Amount", Amountparams);
        params.put("Currency", Currencyparams);
        params.put("Language", Languageparams);
        params.put("HASH", HASHparams);
        params.put("ReturnURL", ReturnURLparams);
        Log.e("result", "onClick: " + shippingFirstName + shippingLastName + shippingEmail);
        params.put("shippingFirstName", shippingFirstName);
        params.put("shippingLastName", shippingLastName);
        params.put("shippingEmail", shippingEmail);
        params.put("shippingPhone", shippingPhone);
        params.put("shippingZipcode", shippingZipcode);
        params.put("shippingAddress", shippingAddress);
        params.put("shippingCity", shippingCity);
        params.put("shippingSstate", shippingSstate);
        params.put("shippingCountry", shippingCountry);
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("email", email);
        params.put("phone", phone);
        params.put("zipcode", zipcode);
        params.put("address", address);
        params.put("city", city);
        params.put("state", state);
        params.put("country", country);
        params.put("ipAddr", ipAddr);
        params.put("products", products);
        params.put("tradeAdd_Bturc", "http://danyh.t.100help.net/");//商户交易网址，比如，http://www.aaa.com

        FinalHttp http = new FinalHttp();
        http.post("https://www.win4mall.com/onlinepayByWin", params, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", "onSuccess: " + result);
                dialog.dismissProgressDialog();
                super.onSuccess(result);
                VisaBean visaBean = new Gson().fromJson(result, VisaBean.class);
                if (88 == visaBean.getSucceed() || 90 == visaBean.getSucceed() || 70 == visaBean.getSucceed() || 19 == visaBean.getSucceed()) {
                    startActivity(new Intent(CardActivity.this, GoodPayActivity.class)
                            .putExtra("type", "good")
                            .putExtra("msg", visaBean.getResult())
                            .putExtra("orderid", getIntent().getStringExtra("oid")));
                    finish();
                } else {
                    startActivity(new Intent(CardActivity.this, GoodPayActivity.class)
                            .putExtra("msg", visaBean.getResult())
                            .putExtra("orderid", getIntent().getStringExtra("oid")));
                    finish();
                }
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                Log.e("result", "onFailure: " + strMsg);
                dialog.dismissProgressDialog();
                super.onFailure(t, strMsg);
            }
        });
    }


    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }
}
