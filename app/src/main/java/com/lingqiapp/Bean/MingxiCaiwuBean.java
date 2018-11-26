package com.lingqiapp.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/23
 * 功能描述：
 */
public class MingxiCaiwuBean {

    /**
     * status : 1
     * list : [{"id":"15","uid":"461","add_time":"1538010857","change_num":"50.00","over_num":"199850.00","stu":"4","aid":"1","cause":"提现成功！"},{"id":"14","uid":"461","add_time":"1538010851","change_num":"50.00","over_num":"199850.00","stu":"4","aid":"1","cause":"提现成功！"},{"id":"13","uid":"461","add_time":"1538010843","change_num":"50.00","over_num":"199850.00","stu":"4","aid":"1","cause":"提现成功！"},{"id":"12","uid":"461","add_time":"1538010474","change_num":"100000.00","over_num":"199850.00","stu":"3","aid":"1","cause":"后台更改"}]
     */

    private int status;
    private List<ListBean> list;

    public static List<MingxiCaiwuBean> arrayMingxiCaiwuBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MingxiCaiwuBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 15
         * uid : 461
         * add_time : 1538010857
         * change_num : 50.00
         * over_num : 199850.00
         * stu : 4
         * aid : 1
         * cause : 提现成功！
         */

        private String id;
        private String uid;
        private String add_time;
        private String change_num;
        private String over_num;
        private String stu;
        private String aid;
        private String cause;

        public static List<ListBean> arrayListBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ListBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getChange_num() {
            return change_num;
        }

        public void setChange_num(String change_num) {
            this.change_num = change_num;
        }

        public String getOver_num() {
            return over_num;
        }

        public void setOver_num(String over_num) {
            this.over_num = over_num;
        }

        public String getStu() {
            return stu;
        }

        public void setStu(String stu) {
            this.stu = stu;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }
    }
}
