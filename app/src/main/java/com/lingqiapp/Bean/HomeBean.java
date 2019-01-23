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
public class HomeBean {

    /**
     * status : 1
     * list : [{"id":"23","title":"细胞美容","img_feng":"/Public/uploads/goods/2018-09-26/5bab230ab1008.png","price":"4980","xiaoliang":"63"},{"id":"22","title":"爱美丽","img_feng":"/Public/uploads/goods/2018-09-26/5bab23c7ba090.png","price":"980","xiaoliang":"69"},{"id":"25","title":"细胞美容学","img_feng":"/Public/uploads/goods/2018-09-26/5bab24b8ddec8.png","price":"39","xiaoliang":"29"},{"id":"24","title":"生殖美学原理","img_feng":"/Public/uploads/goods/2018-09-26/5bab24dd36ee8.jpg","price":"45","xiaoliang":"32"},{"id":"26","title":"靓帝CY细胞美容生殖美学_专用胸牌10件组合_2018新春特价","img_feng":"/Public/uploads/goods/2018-09-26/5bab251f3ff70.jpg","price":"100","xiaoliang":"17"},{"id":"40","title":"123456","img_feng":"/Public/uploads/goods/2018-09-25/5ba99e61e1c80.jpg","price":"123123","xiaoliang":null}]
     * lun_img : [{"id":"1","img":null,"addtime":"1503906558","url":"www.cn77.cn","title":"首页轮播图","sort":"0"},{"id":"2","img":"/Public/uploads/img/2018-09-21/5ba450c0a5938.png","addtime":"1537495232","url":"www.cn77.cn","title":"轮播图","sort":"0"},{"id":"3","img":"/Public/uploads/img/2018-09-21/5ba4524783a40.png","addtime":"1537495623","url":"www.cn77.cn","title":"轮播图","sort":"3"}]
     * goods_pai : [{"id":"23","img_feng":"/Public/uploads/goods/2018-09-26/5bab230ab1008.png","zhou":"1"},{"id":"22","img_feng":"/Public/uploads/goods/2018-09-26/5bab23c7ba090.png","zhou":"2"},{"id":"25","img_feng":"/Public/uploads/goods/2018-09-26/5bab24b8ddec8.png","zhou":"3"},{"id":"24","img_feng":"/Public/uploads/goods/2018-09-26/5bab24dd36ee8.jpg","zhou":"4"},{"id":"30","img_feng":"/Public/uploads/goods/2018-09-26/5bab250117ed0.png","zhou":"5"},{"id":"26","img_feng":"/Public/uploads/goods/2018-09-26/5bab251f3ff70.jpg","zhou":"6"},{"id":"40","img_feng":"/Public/uploads/goods/2018-09-25/5ba99e61e1c80.jpg","zhou":"7"}]
     */

    private int status;
    private List<ListBean> list;
    private List<LunImgBean> lun_img;
    private List<GoodsPaiBean> goods_pai;

    public static List<HomeBean> arrayHomeBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<HomeBean>>() {
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

    public List<LunImgBean> getLun_img() {
        return lun_img;
    }

    public void setLun_img(List<LunImgBean> lun_img) {
        this.lun_img = lun_img;
    }

    public List<GoodsPaiBean> getGoods_pai() {
        return goods_pai;
    }

    public void setGoods_pai(List<GoodsPaiBean> goods_pai) {
        this.goods_pai = goods_pai;
    }

    public static class ListBean {
        /**
         * id : 23
         * title : 细胞美容
         * img_feng : /Public/uploads/goods/2018-09-26/5bab230ab1008.png
         * price : 4980
         * xiaoliang : 63
         */

        private String id;
        private String title;
        private String img_feng;
        private String price;
        private String xiaoliang;

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

        public String getXiaoliang() {
            return xiaoliang;
        }

        public void setXiaoliang(String xiaoliang) {
            this.xiaoliang = xiaoliang;
        }
    }

    public static class LunImgBean {
        /**
         * id : 1
         * img : null
         * addtime : 1503906558
         * url : www.cn77.cn
         * title : 首页轮播图
         * sort : 0
         */

        private String id;
        private String img;
        private String addtime;
        private String url;
        private String title;
        private String sort;

        public static List<LunImgBean> arrayLunImgBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<LunImgBean>>() {
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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }

    public static class GoodsPaiBean {
        /**
         * id : 23
         * img_feng : /Public/uploads/goods/2018-09-26/5bab230ab1008.png
         * zhou : 1
         */

        private String id;
        private String img_feng;
        private String zhou;

        public static List<GoodsPaiBean> arrayGoodsPaiBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<GoodsPaiBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg_feng() {
            return img_feng;
        }

        public void setImg_feng(String img_feng) {
            this.img_feng = img_feng;
        }

        public String getZhou() {
            return zhou;
        }

        public void setZhou(String zhou) {
            this.zhou = zhou;
        }
    }
}
