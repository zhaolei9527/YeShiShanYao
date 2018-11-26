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
 * @date 2018/9/26
 * 功能描述：
 */
public class GoodsSouBean {


    /**
     * status : 1
     * res : [{"id":"30","xiaoliang":"2","img_feng":"/Public/uploads/goods/2018-09-26/5bab250117ed0.png","title":"靓帝e喷视液_清爽眼部肌肤_2018新春特价","type":"1","price":"69"},{"id":"26","xiaoliang":"17","img_feng":"/Public/uploads/goods/2018-09-26/5bab251f3ff70.jpg","title":"靓帝CY细胞美容生殖美学_专用胸牌10件组合_2018新春特价","type":"1","price":"100"},{"id":"40","xiaoliang":null,"img_feng":"/Public/uploads/goods/2018-09-25/5ba99e61e1c80.jpg","title":"123456","type":"0","price":"123123"},{"id":"39","xiaoliang":null,"img_feng":"/Public/uploads/goods/2018-09-25/5ba99e1311878.jpg","title":"测试1","type":"1","price":"12"}]
     * title : 1
     */

    private String status;
    private String title;
    private List<ResBean> res;

    public static List<GoodsSouBean> arrayGoodsSouBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GoodsSouBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * id : 30
         * xiaoliang : 2
         * img_feng : /Public/uploads/goods/2018-09-26/5bab250117ed0.png
         * title : 靓帝e喷视液_清爽眼部肌肤_2018新春特价
         * type : 1
         * price : 69
         */

        private String id;
        private String xiaoliang;
        private String img_feng;
        private String title;
        private String type;
        private String price;

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

        public String getXiaoliang() {
            return xiaoliang;
        }

        public void setXiaoliang(String xiaoliang) {
            this.xiaoliang = xiaoliang;
        }

        public String getImg_feng() {
            return img_feng;
        }

        public void setImg_feng(String img_feng) {
            this.img_feng = img_feng;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
