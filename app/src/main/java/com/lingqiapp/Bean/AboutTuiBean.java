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
 * @date 2018/9/27
 * 功能描述：
 */
public class AboutTuiBean {


    /**
     * status : 1
     * ulist : [{"id":"465","is_hui":"1","ni_name":"领七用户","add_time":"1538010358"},{"id":"462","is_hui":"1","ni_name":"火云邪神","add_time":"1537422552"}]
     */

    private int status;
    private List<UlistBean> ulist;

    public static List<AboutTuiBean> arrayAboutTuiBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<AboutTuiBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<UlistBean> getUlist() {
        return ulist;
    }

    public void setUlist(List<UlistBean> ulist) {
        this.ulist = ulist;
    }

    public static class UlistBean {
        /**
         * id : 465
         * is_hui : 1
         * ni_name : 领七用户
         * add_time : 1538010358
         */

        private String id;
        private String is_hui;
        private String ni_name;
        private String add_time;

        public static List<UlistBean> arrayUlistBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<UlistBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_hui() {
            return is_hui;
        }

        public void setIs_hui(String is_hui) {
            this.is_hui = is_hui;
        }

        public String getNi_name() {
            return ni_name;
        }

        public void setNi_name(String ni_name) {
            this.ni_name = ni_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
