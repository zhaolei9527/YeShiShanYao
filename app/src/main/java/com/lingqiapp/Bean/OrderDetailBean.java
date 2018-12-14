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
 * @date 2017/12/18
 * 功能描述：
 */
public class OrderDetailBean {

    /**
     * status : 1
     * order : {"id":"664","orderid":"20181214174208201843","totalprice":"50.00","uid":"15","status":"1","paytime":null,"addressid":"35","addtime":"1544780528","number":"1","yemoney":null,"paytype":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"country":"西班牙","address":"1545641216546","tel":"1545465456","name":"asdasdas","xing":null,"is_ziti":"1","gid":"71","cid":"849","l_num":null,"ltype":"3","ont0":null,"onts0":null,"ont1":null,"onts1":null,"ont2":null,"onts2":null,"ont3":null,"onts3":null,"no":"没有收到款","title":"Cazlan Little Red Lip and Lipstick Value Persistent Moisturizing, Non-decoloring and Non-staining Cup","img_feng":"/Public/uploads/2018-11-29/5bff400fc6810.jpg","price":"50.00"}
     */

    private int status;
    private OrderBean order;

    public static List<OrderDetailBean> arrayOrderDetailBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderDetailBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * id : 664
         * orderid : 20181214174208201843
         * totalprice : 50.00
         * uid : 15
         * status : 1
         * paytime : null
         * addressid : 35
         * addtime : 1544780528
         * number : 1
         * yemoney : null
         * paytype : null
         * fhtime : null
         * exp : null
         * expnum : null
         * fhbeizhu : null
         * paybeizhu : null
         * shtime : null
         * country : 西班牙
         * address : 1545641216546
         * tel : 1545465456
         * name : asdasdas
         * xing : null
         * is_ziti : 1
         * gid : 71
         * cid : 849
         * l_num : null
         * ltype : 3
         * ont0 : null
         * onts0 : null
         * ont1 : null
         * onts1 : null
         * ont2 : null
         * onts2 : null
         * ont3 : null
         * onts3 : null
         * no : 没有收到款
         * title : Cazlan Little Red Lip and Lipstick Value Persistent Moisturizing, Non-decoloring and Non-staining Cup
         * img_feng : /Public/uploads/2018-11-29/5bff400fc6810.jpg
         * price : 50.00
         */

        private String id;
        private String orderid;
        private String totalprice;
        private String uid;
        private String status;
        private String paytime;
        private String addressid;
        private String addtime;
        private String number;
        private String yemoney;
        private String paytype;
        private String fhtime;
        private String exp;
        private String expnum;
        private String fhbeizhu;
        private String paybeizhu;
        private String shtime;
        private String country;
        private String address;
        private String tel;
        private String name;
        private String xing;
        private String is_ziti;
        private String gid;
        private String cid;
        private String l_num;
        private String ltype;
        private String ont0;
        private String onts0;
        private String ont1;
        private String onts1;
        private String ont2;
        private String onts2;
        private String ont3;
        private String onts3;
        private String no;
        private String title;
        private String img_feng;
        private String price;

        public static List<OrderBean> arrayOrderBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<OrderBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getAddressid() {
            return addressid;
        }

        public void setAddressid(String addressid) {
            this.addressid = addressid;
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

        public String getYemoney() {
            return yemoney;
        }

        public void setYemoney(String yemoney) {
            this.yemoney = yemoney;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getFhtime() {
            return fhtime;
        }

        public void setFhtime(String fhtime) {
            this.fhtime = fhtime;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public String getExpnum() {
            return expnum;
        }

        public void setExpnum(String expnum) {
            this.expnum = expnum;
        }

        public String getFhbeizhu() {
            return fhbeizhu;
        }

        public void setFhbeizhu(String fhbeizhu) {
            this.fhbeizhu = fhbeizhu;
        }

        public String getPaybeizhu() {
            return paybeizhu;
        }

        public void setPaybeizhu(String paybeizhu) {
            this.paybeizhu = paybeizhu;
        }

        public String getShtime() {
            return shtime;
        }

        public void setShtime(String shtime) {
            this.shtime = shtime;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getXing() {
            return xing;
        }

        public void setXing(String xing) {
            this.xing = xing;
        }

        public String getIs_ziti() {
            return is_ziti;
        }

        public void setIs_ziti(String is_ziti) {
            this.is_ziti = is_ziti;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getL_num() {
            return l_num;
        }

        public void setL_num(String l_num) {
            this.l_num = l_num;
        }

        public String getLtype() {
            return ltype;
        }

        public void setLtype(String ltype) {
            this.ltype = ltype;
        }

        public String getOnt0() {
            return ont0;
        }

        public void setOnt0(String ont0) {
            this.ont0 = ont0;
        }

        public String getOnts0() {
            return onts0;
        }

        public void setOnts0(String onts0) {
            this.onts0 = onts0;
        }

        public String getOnt1() {
            return ont1;
        }

        public void setOnt1(String ont1) {
            this.ont1 = ont1;
        }

        public String getOnts1() {
            return onts1;
        }

        public void setOnts1(String onts1) {
            this.onts1 = onts1;
        }

        public String getOnt2() {
            return ont2;
        }

        public void setOnt2(String ont2) {
            this.ont2 = ont2;
        }

        public String getOnts2() {
            return onts2;
        }

        public void setOnts2(String onts2) {
            this.onts2 = onts2;
        }

        public String getOnt3() {
            return ont3;
        }

        public void setOnt3(String ont3) {
            this.ont3 = ont3;
        }

        public String getOnts3() {
            return onts3;
        }

        public void setOnts3(String onts3) {
            this.onts3 = onts3;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
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
