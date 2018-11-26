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
 * @date 2018/10/13
 * 功能描述：
 */
public class HuiYuanBean {

    /**
     * status : 1
     * udate : {"id":"461","ni_name":"sakura","img":"/Public/uploads/touxiang/2018-09-29/5baf245626ac0.jpg","is_hui":"2","hui_star":null,"hui_end":null}
     * ldate : {"hui":"每月享有二次0.00元拿付商品的权力"}
     * tdate : [{"id":"1","term":"一年","money":"299"},{"id":"2","term":"一个季度","money":"79"},{"id":"3","term":"一个月","money":"59"}]
     */

    private int status;
    private UdateBean udate;
    private LdateBean ldate;
    private List<TdateBean> tdate;

    public static List<HuiYuanBean> arrayHuiYuanBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<HuiYuanBean>>() {
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

    public LdateBean getLdate() {
        return ldate;
    }

    public void setLdate(LdateBean ldate) {
        this.ldate = ldate;
    }

    public List<TdateBean> getTdate() {
        return tdate;
    }

    public void setTdate(List<TdateBean> tdate) {
        this.tdate = tdate;
    }

    public static class UdateBean {
        /**
         * id : 461
         * ni_name : sakura
         * img : /Public/uploads/touxiang/2018-09-29/5baf245626ac0.jpg
         * is_hui : 2
         * hui_star : null
         * hui_end : null
         */

        private String id;
        private String ni_name;
        private String img;
        private String is_hui;
        private String hui_star;
        private String hui_end;

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

        public String getNi_name() {
            return ni_name;
        }

        public void setNi_name(String ni_name) {
            this.ni_name = ni_name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIs_hui() {
            return is_hui;
        }

        public void setIs_hui(String is_hui) {
            this.is_hui = is_hui;
        }

        public String getHui_star() {
            return hui_star;
        }

        public void setHui_star(String hui_star) {
            this.hui_star = hui_star;
        }

        public String getHui_end() {
            return hui_end;
        }

        public void setHui_end(String hui_end) {
            this.hui_end = hui_end;
        }
    }

    public static class LdateBean {
        /**
         * hui : 每月享有二次0.00元拿付商品的权力
         */

        private String hui;

        public static List<LdateBean> arrayLdateBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<LdateBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getHui() {
            return hui;
        }

        public void setHui(String hui) {
            this.hui = hui;
        }
    }

    public static class TdateBean {
        /**
         * id : 1
         * term : 一年
         * money : 299
         */

        private String id;
        private String term;
        private String money;

        public static List<TdateBean> arrayTdateBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<TdateBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
