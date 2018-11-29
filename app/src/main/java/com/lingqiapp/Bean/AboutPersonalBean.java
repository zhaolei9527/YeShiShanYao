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
 * @date 2018/9/21
 * 功能描述：
 */
public class AboutPersonalBean {


    /**
     * status : 1
     * udate : {"id":"15","img":"/Public/uploads/headimg/default_img.png","email":"975976959@qq.com","ni_name":"叶氏闪耀","is_hui":"1","money":"0.00"}
     */

    private int status;
    private UdateBean udate;

    public static List<AboutPersonalBean> arrayAboutPersonalBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<AboutPersonalBean>>() {
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

    public static class UdateBean {
        /**
         * id : 15
         * img : /Public/uploads/headimg/default_img.png
         * email : 975976959@qq.com
         * ni_name : 叶氏闪耀
         * is_hui : 1
         * money : 0.00
         */

        private String id;
        private String img;
        private String email;
        private String ni_name;
        private String is_hui;
        private String money;

        public static List<UdateBean> arrayUdateBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<UdateBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNi_name() {
            return ni_name;
        }

        public void setNi_name(String ni_name) {
            this.ni_name = ni_name;
        }

        public String getIs_hui() {
            return is_hui;
        }

        public void setIs_hui(String is_hui) {
            this.is_hui = is_hui;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
