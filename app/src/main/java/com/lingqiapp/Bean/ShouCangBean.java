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
public class ShouCangBean {

    /**
     * status : 1
     * list : [{"id":"11","uid":"461","gid":"22","addtime":"1538034672","title":"爱美丽","img_feng":"/Public/uploads/goods/2018-09-26/5bab23c7ba090.png","price":"980"},{"id":"10","uid":"461","gid":"23","addtime":"1538034666","title":"细胞美容","img_feng":"/Public/uploads/goods/2018-09-26/5bab230ab1008.png","price":"4980"}]
     */

    private int status;
    private List<ListBean> list;

    public static List<ShouCangBean> arrayShouCangBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ShouCangBean>>() {
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
         * id : 11
         * uid : 461
         * gid : 22
         * addtime : 1538034672
         * title : 爱美丽
         * img_feng : /Public/uploads/goods/2018-09-26/5bab23c7ba090.png
         * price : 980
         */

        private String id;
        private String uid;
        private String gid;
        private String addtime;
        private String title;
        private String img_feng;
        private String price;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private boolean isCheck;

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

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg_feng() {
            return img_feng;
        }

        public void setImg_feng(String img_feng) {
            this.img_feng = img_feng;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
