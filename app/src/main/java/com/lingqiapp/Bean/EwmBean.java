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
 * @date 2018/9/20
 * 功能描述：
 */
public class EwmBean {

    /**
     * status : 1
     * erweima : ./Public/userqrcode/-p.png
     * jiang : {"jiang":"被推荐人使用您的推荐码详情描述被推荐人，使用您的推荐码详情描述被推荐人."}
     */

    private int status;
    private String erweima;
    private JiangBean jiang;

    public static List<EwmBean> arrayEwmBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<EwmBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErweima() {
        return erweima;
    }

    public void setErweima(String erweima) {
        this.erweima = erweima;
    }

    public JiangBean getJiang() {
        return jiang;
    }

    public void setJiang(JiangBean jiang) {
        this.jiang = jiang;
    }

    public static class JiangBean {
        /**
         * jiang : 被推荐人使用您的推荐码详情描述被推荐人，使用您的推荐码详情描述被推荐人.
         */

        private String jiang;

        public static List<JiangBean> arrayJiangBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<JiangBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getJiang() {
            return jiang;
        }

        public void setJiang(String jiang) {
            this.jiang = jiang;
        }
    }
}
