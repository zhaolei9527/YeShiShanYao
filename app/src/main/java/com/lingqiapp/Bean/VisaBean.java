package com.lingqiapp.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * com.lingqiapp.Bean
 *
 * @author 赵磊
 * @date 2018/11/28
 * 功能描述：
 */
public class VisaBean {

    /**
     * Result : Repeat transaction
     * Succeed : 5
     * Amount : 0.01
     * MD5info : 7492A5826AD160C39B938A4533C01B48
     * Currency : 2
     * ReturnURL : http://danyh.t.100help.net/visa/pay_return.php
     * BillNo : 1543382053382952
     */

    private String Result;
    private int Succeed;
    private String Amount;
    private String MD5info;
    private String Currency;
    private String ReturnURL;
    private String BillNo;

    public static List<VisaBean> arrayVisaBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<VisaBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public int getSucceed() {
        return Succeed;
    }

    public void setSucceed(int Succeed) {
        this.Succeed = Succeed;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getMD5info() {
        return MD5info;
    }

    public void setMD5info(String MD5info) {
        this.MD5info = MD5info;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    public String getReturnURL() {
        return ReturnURL;
    }

    public void setReturnURL(String ReturnURL) {
        this.ReturnURL = ReturnURL;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String BillNo) {
        this.BillNo = BillNo;
    }
}
