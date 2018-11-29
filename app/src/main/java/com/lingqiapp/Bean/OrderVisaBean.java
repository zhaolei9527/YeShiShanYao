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
 * @date 2018/11/27
 * 功能描述：
 */
public class OrderVisaBean {

    /**
     * status : 1
     * udate : {"name":"qweqwesadas","tel":"18454654","country":"西班牙","address":"asdasd15616"}
     * zmoney : 148
     * payorder : 1543371889151525
     */

    private int status;
    private UdateBean udate;
    private int zmoney;
    private String payorder;

    public static List<OrderVisaBean> arrayOrderVisaBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderVisaBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UdateBean getUdate() {
        return udate;
    }

    public void setUdate(UdateBean udate) {
        this.udate = udate;
    }

    public int getZmoney() {
        return zmoney;
    }

    public void setZmoney(int zmoney) {
        this.zmoney = zmoney;
    }

    public String getPayorder() {
        return payorder;
    }

    public void setPayorder(String payorder) {
        this.payorder = payorder;
    }

    public static class UdateBean {
        /**
         * name : qweqwesadas
         * tel : 18454654
         * country : 西班牙
         * address : asdasd15616
         */

        private String name;
        private String tel;
        private String country;
        private String address;

        public static List<UdateBean> arrayUdateBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<UdateBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
