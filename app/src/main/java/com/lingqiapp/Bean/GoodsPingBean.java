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
public class GoodsPingBean {


    /**
     * status : 1
     * hao_ping_num : 1
     * zhong_ping_num : 0
     * cha_ping_num : 0
     * res : [{"id":"1","uid":"2","orderid":"20181018092143439862","star":"5","pcontent":"很满意的一次购物","gid":"45","aid":null,"hcontent":null,"p_img":["/Public/uploads/oder_ping/2018-10-18/5bc7e74cbf7fb.jpg","/Public/uploads/oder_ping/2018-10-18/5bc7e74cc1f0c.jpg","/Public/uploads/oder_ping/2018-10-18/5bc7e74cc4235.jpg"],"addtime":"1539827534","stu":"1","hftime":null,"ni_name":"打手","img":"/Public/uploads/touxiang/2018-10-18/5bc7e857526d9.jpg"}]
     */

    private String status;
    private String hao_ping_num;
    private String zhong_ping_num;
    private String cha_ping_num;
    private List<ResBean> res;

    public static List<GoodsPingBean> arrayGoodsPingBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GoodsPingBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHao_ping_num() {
        return hao_ping_num;
    }

    public void setHao_ping_num(String hao_ping_num) {
        this.hao_ping_num = hao_ping_num;
    }

    public String getZhong_ping_num() {
        return zhong_ping_num;
    }

    public void setZhong_ping_num(String zhong_ping_num) {
        this.zhong_ping_num = zhong_ping_num;
    }

    public String getCha_ping_num() {
        return cha_ping_num;
    }

    public void setCha_ping_num(String cha_ping_num) {
        this.cha_ping_num = cha_ping_num;
    }

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * id : 1
         * uid : 2
         * orderid : 20181018092143439862
         * star : 5
         * pcontent : 很满意的一次购物
         * gid : 45
         * aid : null
         * hcontent : null
         * p_img : ["/Public/uploads/oder_ping/2018-10-18/5bc7e74cbf7fb.jpg","/Public/uploads/oder_ping/2018-10-18/5bc7e74cc1f0c.jpg","/Public/uploads/oder_ping/2018-10-18/5bc7e74cc4235.jpg"]
         * addtime : 1539827534
         * stu : 1
         * hftime : null
         * ni_name : 打手
         * img : /Public/uploads/touxiang/2018-10-18/5bc7e857526d9.jpg
         */

        private String id;
        private String uid;
        private String orderid;
        private String star;
        private String pcontent;
        private String gid;
        private Object aid;
        private Object hcontent;
        private String addtime;
        private String stu;
        private Object hftime;
        private String ni_name;
        private String img;
        private List<String> p_img;

        public static List<ResBean> arrayResBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ResBean>>() {
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

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getPcontent() {
            return pcontent;
        }

        public void setPcontent(String pcontent) {
            this.pcontent = pcontent;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public Object getAid() {
            return aid;
        }

        public void setAid(Object aid) {
            this.aid = aid;
        }

        public Object getHcontent() {
            return hcontent;
        }

        public void setHcontent(Object hcontent) {
            this.hcontent = hcontent;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getStu() {
            return stu;
        }

        public void setStu(String stu) {
            this.stu = stu;
        }

        public Object getHftime() {
            return hftime;
        }

        public void setHftime(Object hftime) {
            this.hftime = hftime;
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

        public List<String> getP_img() {
            return p_img;
        }

        public void setP_img(List<String> p_img) {
            this.p_img = p_img;
        }
    }
}
