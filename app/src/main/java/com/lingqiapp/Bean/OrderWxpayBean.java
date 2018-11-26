package com.lingqiapp.Bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/30
 * 功能描述：
 */
public class OrderWxpayBean {


    /**
     * stu : 1
     * data : {"appId":"wxf01c8a76202a8f4e","mch_id":"1515626841","nonceStr":"ewawhj7i54fpz7d8cbpif9cof7tzb0pu","package":"Sign=WXPay","prepay_id":"wx12131622530704597551f5cf0270161101","timeStamp":1539321454,"sign":"2100"}
     */

    private int stu;
    private DataBean data;

    public static List<OrderWxpayBean> arrayOrderWxpayBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderWxpayBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStu() {
        return stu;
    }

    public void setStu(int stu) {
        this.stu = stu;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appId : wxf01c8a76202a8f4e
         * mch_id : 1515626841
         * nonceStr : ewawhj7i54fpz7d8cbpif9cof7tzb0pu
         * package : Sign=WXPay
         * prepay_id : wx12131622530704597551f5cf0270161101
         * timeStamp : 1539321454
         * sign : 2100
         */

        private String appId;
        private String mch_id;
        private String nonceStr;
        @SerializedName("package")
        private String packageX;
        private String prepay_id;
        private String timeStamp;
        private String sign;

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
