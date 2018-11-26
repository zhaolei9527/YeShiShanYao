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
     * status : 0
     * udate : {"id":"461","img":"/Public/uploads/headimg/default_img.png","tel":"17629345001","ni_name":"靓帝女神"}
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
         * id : 461
         * img : /Public/uploads/headimg/default_img.png
         * tel : 17629345001
         * ni_name : 靓帝女神
         */

        private String id;
        private String img;
        private String tel;
        private String ni_name;

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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getNi_name() {
            return ni_name;
        }

        public void setNi_name(String ni_name) {
            this.ni_name = ni_name;
        }
    }
}
