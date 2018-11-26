package com.lingqiapp.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/11
 * 功能描述：
 */
public class OrderBuyBean {

    /**
     * status : 1
     * dizhi : {"id":"183","name":"asdasdasasd","province":"河南省","city":"郑州市","country":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","is_default":"1","uid":"461","add_time":"1537838074"}
     * g_num : 1
     * gid : 30
     * goods : {"id":"30","title":"靓帝e喷视液_清爽眼部肌肤_2018新春特价","price":"69","img_feng":"/Public/uploads/goods/2018-09-26/5bab250117ed0.png","kucun":"41"}
     * z_price : 69
     */

    private String status;
    private DizhiBean dizhi;
    private String g_num;
    private String gid;
    private GoodsBean goods;
    private int z_price;

    public static List<OrderBuyBean> arrayOrderBuyBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderBuyBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DizhiBean getDizhi() {
        return dizhi;
    }

    public void setDizhi(DizhiBean dizhi) {
        this.dizhi = dizhi;
    }

    public String getG_num() {
        return g_num;
    }

    public void setG_num(String g_num) {
        this.g_num = g_num;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public int getZ_price() {
        return z_price;
    }

    public void setZ_price(int z_price) {
        this.z_price = z_price;
    }

    public static class DizhiBean {
        /**
         * id : 183
         * name : asdasdasasd
         * province : 河南省
         * city : 郑州市
         * country : 中原区
         * address : asdadasfdasdasfasfasf
         * tel : 17629345001
         * is_default : 1
         * uid : 461
         * add_time : 1537838074
         */

        private String id;
        private String name;
        private String province;
        private String city;
        private String country;
        private String address;
        private String tel;
        private String is_default;
        private String uid;
        private String add_time;

        public static List<DizhiBean> arrayDizhiBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DizhiBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
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
    }

    public static class GoodsBean {
        /**
         * id : 30
         * title : 靓帝e喷视液_清爽眼部肌肤_2018新春特价
         * price : 69
         * img_feng : /Public/uploads/goods/2018-09-26/5bab250117ed0.png
         * kucun : 41
         */

        private String id;
        private String title;
        private String price;
        private String img_feng;
        private String kucun;

        public static List<GoodsBean> arrayGoodsBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<GoodsBean>>() {
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImg_feng() {
            return img_feng;
        }

        public void setImg_feng(String img_feng) {
            this.img_feng = img_feng;
        }

        public String getKucun() {
            return kucun;
        }

        public void setKucun(String kucun) {
            this.kucun = kucun;
        }
    }
}
