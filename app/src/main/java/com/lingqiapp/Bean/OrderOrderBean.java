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
 * @date 2018/6/9
 * 功能描述：
 */
public class OrderOrderBean {
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * status : 1
     * address : {"id":"183","name":"asdasdasasd","province":"河南省","city":"郑州市","country":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","is_default":"1","uid":"461","add_time":"1537838074"}
     * cart : [{"id":"137","uid":"461","gid":"22","addtime":"1538030700","number":"4","title":"爱美丽","img_feng":"/Public/uploads/goods/2018-09-26/5bab23c7ba090.png","price":"980","zmoney":3920},{"id":"139","uid":"461","gid":"24","addtime":"1538036775","number":"1","title":"生殖美学原理","img_feng":"/Public/uploads/goods/2018-09-26/5bab24dd36ee8.jpg","price":"45","zmoney":45}]
     * zf_money : 3965
     */

    private String msg;
    private int status;
    private AddressBean address;
    private String zf_money;
    private List<CartBean> cart;

    public static List<OrderOrderBean> arrayOrderOrderBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderOrderBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getZf_money() {
        return zf_money;
    }

    public void setZf_money(String zf_money) {
        this.zf_money = zf_money;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class AddressBean {
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

        public static List<AddressBean> arrayAddressBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<AddressBean>>() {
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

    public static class CartBean {
        /**
         * id : 137
         * uid : 461
         * gid : 22
         * addtime : 1538030700
         * number : 4
         * title : 爱美丽
         * img_feng : /Public/uploads/goods/2018-09-26/5bab23c7ba090.png
         * price : 980
         * zmoney : 3920
         */

        private String id;
        private String uid;
        private String gid;
        private String addtime;
        private String number;
        private String title;
        private String img_feng;
        private String price;
        private String zmoney;

        public static List<CartBean> arrayCartBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<CartBean>>() {
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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

        public String getZmoney() {
            return zmoney;
        }

        public void setZmoney(String zmoney) {
            this.zmoney = zmoney;
        }
    }
}
