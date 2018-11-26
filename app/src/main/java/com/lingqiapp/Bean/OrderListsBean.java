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
public class OrderListsBean {

    /**
     * status : 1
     * list : [{"id":"352","orderid":"20181011103424582849","totalprice":"30","uid":"461","status":"2","paytime":null,"addressid":"183","addtime":"1539225264","number":"1","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"22","cid":"247","l_num":null,"title":"爱美丽","img_feng":"/Public/uploads/goods/2018-09-26/5bab23c7ba090.png","price":"30"},{"id":"353","orderid":"20181011103424700869","totalprice":"39","uid":"461","status":"2","paytime":null,"addressid":"183","addtime":"1539225264","number":"1","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"25","cid":"248","l_num":null,"title":"细胞美容学","img_feng":"/Public/uploads/goods/2018-09-26/5bab24b8ddec8.png","price":"39"},{"id":"354","orderid":"20181011103424501055","totalprice":"45","uid":"461","status":"2","paytime":null,"addressid":"183","addtime":"1539225264","number":"1","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"24","cid":"249","l_num":null,"title":"生殖美学原理","img_feng":"/Public/uploads/goods/2018-09-26/5bab24dd36ee8.jpg","price":"45"},{"id":"346","orderid":"20181011102526543518","totalprice":"156","uid":"461","status":"2","paytime":null,"addressid":"183","addtime":"1539224726","number":"4","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"25","cid":"156","l_num":null,"title":"细胞美容学","img_feng":"/Public/uploads/goods/2018-09-26/5bab24b8ddec8.png","price":"39"},{"id":"347","orderid":"20181011102526351284","totalprice":"90","uid":"461","status":"2","paytime":null,"addressid":"183","addtime":"1539224726","number":"2","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"24","cid":"157","l_num":null,"title":"生殖美学原理","img_feng":"/Public/uploads/goods/2018-09-26/5bab24dd36ee8.jpg","price":"45"},{"id":"348","orderid":"20181011102526983795","totalprice":"90","uid":"461","status":"2","paytime":null,"addressid":"183","addtime":"1539224726","number":"2","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"24","cid":"220","l_num":null,"title":"生殖美学原理","img_feng":"/Public/uploads/goods/2018-09-26/5bab24dd36ee8.jpg","price":"45"},{"id":"349","orderid":"20181011102526894613","totalprice":"90","uid":"461","status":"2","paytime":null,"addressid":"183","addtime":"1539224726","number":"3","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"22","cid":"239","l_num":null,"title":"爱美丽","img_feng":"/Public/uploads/goods/2018-09-26/5bab23c7ba090.png","price":"30"},{"id":"350","orderid":"20181011102526497265","totalprice":"100","uid":"461","status":"2","paytime":null,"addressid":"183","addtime":"1539224726","number":"1","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"40","cid":"241","l_num":null,"title":"123456","img_feng":"/Public/uploads/goods/2018-09-25/5ba99e61e1c80.jpg","price":"100"},{"id":"345","orderid":"20181011100520665081","totalprice":"30","uid":"461","status":"1","paytime":null,"addressid":"183","addtime":"1539223520","number":"1","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"22","cid":"238","l_num":null,"title":"爱美丽","img_feng":"/Public/uploads/goods/2018-09-26/5bab23c7ba090.png","price":"30"},{"id":"344","orderid":"20181011100430848553","totalprice":"30","uid":"461","status":"1","paytime":null,"addressid":"183","addtime":"1539223470","number":"1","yemoney":null,"paytype":null,"payment":null,"fhtime":null,"exp":null,"expnum":null,"fhbeizhu":null,"paybeizhu":null,"shtime":null,"sheng":"河南省","shi":"郑州市","xian":"中原区","address":"asdadasfdasdasfasfasf","tel":"17629345001","name":"asdasdasasd","is_ziti":"1","gid":"22","cid":"237","l_num":null,"title":"爱美丽","img_feng":"/Public/uploads/goods/2018-09-26/5bab23c7ba090.png","price":"30"}]
     */

    private int status;
    private List<ListBean> list;

    public static List<OrderListsBean> arrayOrderListsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderListsBean>>() {
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
         * id : 352
         * orderid : 20181011103424582849
         * totalprice : 30
         * uid : 461
         * status : 2
         * paytime : null
         * addressid : 183
         * addtime : 1539225264
         * number : 1
         * yemoney : null
         * paytype : null
         * payment : null
         * fhtime : null
         * exp : null
         * expnum : null
         * fhbeizhu : null
         * paybeizhu : null
         * shtime : null
         * sheng : 河南省
         * shi : 郑州市
         * xian : 中原区
         * address : asdadasfdasdasfasfasf
         * tel : 17629345001
         * name : asdasdasasd
         * is_ziti : 1
         * gid : 22
         * cid : 247
         * l_num : null
         * title : 爱美丽
         * img_feng : /Public/uploads/goods/2018-09-26/5bab23c7ba090.png
         * price : 30
         */

        private String id;
        private String orderid;
        private String totalprice;
        private String uid;
        private String status;
        private Object paytime;
        private String addressid;
        private String addtime;
        private String number;
        private Object yemoney;
        private Object paytype;
        private Object payment;
        private Object fhtime;
        private Object exp;
        private Object expnum;
        private Object fhbeizhu;
        private Object paybeizhu;
        private Object shtime;
        private String sheng;
        private String shi;
        private String xian;
        private String address;
        private String tel;
        private String name;
        private String is_ziti;
        private String gid;
        private String cid;
        private Object l_num;
        private String title;
        private String img_feng;
        private String price;

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

        public Object getPaytime() {
            return paytime;
        }

        public void setPaytime(Object paytime) {
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

        public Object getYemoney() {
            return yemoney;
        }

        public void setYemoney(Object yemoney) {
            this.yemoney = yemoney;
        }

        public Object getPaytype() {
            return paytype;
        }

        public void setPaytype(Object paytype) {
            this.paytype = paytype;
        }

        public Object getPayment() {
            return payment;
        }

        public void setPayment(Object payment) {
            this.payment = payment;
        }

        public Object getFhtime() {
            return fhtime;
        }

        public void setFhtime(Object fhtime) {
            this.fhtime = fhtime;
        }

        public Object getExp() {
            return exp;
        }

        public void setExp(Object exp) {
            this.exp = exp;
        }

        public Object getExpnum() {
            return expnum;
        }

        public void setExpnum(Object expnum) {
            this.expnum = expnum;
        }

        public Object getFhbeizhu() {
            return fhbeizhu;
        }

        public void setFhbeizhu(Object fhbeizhu) {
            this.fhbeizhu = fhbeizhu;
        }

        public Object getPaybeizhu() {
            return paybeizhu;
        }

        public void setPaybeizhu(Object paybeizhu) {
            this.paybeizhu = paybeizhu;
        }

        public Object getShtime() {
            return shtime;
        }

        public void setShtime(Object shtime) {
            this.shtime = shtime;
        }

        public String getSheng() {
            return sheng;
        }

        public void setSheng(String sheng) {
            this.sheng = sheng;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getXian() {
            return xian;
        }

        public void setXian(String xian) {
            this.xian = xian;
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

        public Object getL_num() {
            return l_num;
        }

        public void setL_num(Object l_num) {
            this.l_num = l_num;
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
