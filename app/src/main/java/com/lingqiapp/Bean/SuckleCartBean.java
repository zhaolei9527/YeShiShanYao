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
 * @date 2017/12/11
 * 功能描述：
 */
public class SuckleCartBean {

    /**
     * status : 1
     * list : [{"id":"826","uid":"15","gid":"77","addtime":"1544771954","number":"1","type":"1","z_money":"69","price":"69.00","ltype":"3","gcid":"3479","yuanjia":"59.00","cnt0":"Color","cnt1":"Capacity","cnt2":null,"cnt3":null,"cnts0":"black","cnts1":"500ml","cnts2":null,"cnts3":null,"title":"Fu Guang water cup","img_feng":"/Public/uploads/2018-12-08/5c0b852a5e143.jpg","is_guo":0,"guige":[{"id":"4128","gid":null,"title":"black","gnid":"758","titles":"Color"},{"id":"4130","gid":null,"title":"500ml","gnid":"759","titles":"Capacity"}]},{"id":"824","uid":"15","gid":"71","addtime":"1544771830","number":"2","type":"1","z_money":"100","price":"50.00","ltype":"3","gcid":null,"yuanjia":"100.00","cnt0":null,"cnt1":null,"cnt2":null,"cnt3":null,"cnts0":null,"cnts1":null,"cnts2":null,"cnts3":null,"title":"Cazlan Little Red Lip and Lipstick Value Persistent Moisturizing, Non-decoloring and Non-staining Cup","img_feng":"/Public/uploads/2018-11-29/5bff400fc6810.jpg","is_guo":0},{"id":"798","uid":"15","gid":"77","addtime":"1544756805","number":"3","type":"1","z_money":"267","price":"89.00","ltype":"3","gcid":"3480","yuanjia":"79.00","cnt0":"Color","cnt1":"Capacity","cnt2":null,"cnt3":null,"cnts0":"black","cnts1":"1000ml","cnts2":null,"cnts3":null,"title":"Fu Guang water cup","img_feng":"/Public/uploads/2018-12-08/5c0b852a5e143.jpg","is_guo":0,"guige":[{"id":"4128","gid":null,"title":"black","gnid":"758","titles":"Color"},{"id":"4131","gid":null,"title":"1000ml","gnid":"759","titles":"Capacity"}]},{"id":"782","uid":"15","gid":"46","addtime":"1544685708","number":"1","type":"1","z_money":"480","price":"480.00","ltype":"3","gcid":null,"yuanjia":"560.00","cnt0":null,"cnt1":null,"cnt2":null,"cnt3":null,"cnts0":null,"cnts1":null,"cnts2":null,"cnts3":null,"title":"Saint Laurent (YSL) YSL Saint Laurent lady lipstick lipstick circular tube shine12 cut male color 52 Star color thousand song ewe square tube 13# orange red","img_feng":"/Public/uploads/2018-11-26/5bfbaa54dafe8.jpg","is_guo":0}]
     */

    private int status;

    private List<ListBean> list;

    public static List<SuckleCartBean> arraySuckleCartBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SuckleCartBean>>() {
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
         * id : 826
         * uid : 15
         * gid : 77
         * addtime : 1544771954
         * number : 1
         * type : 1
         * z_money : 69
         * price : 69.00
         * ltype : 3
         * gcid : 3479
         * yuanjia : 59.00
         * cnt0 : Color
         * cnt1 : Capacity
         * cnt2 : null
         * cnt3 : null
         * cnts0 : black
         * cnts1 : 500ml
         * cnts2 : null
         * cnts3 : null
         * title : Fu Guang water cup
         * img_feng : /Public/uploads/2018-12-08/5c0b852a5e143.jpg
         * is_guo : 0
         * guige : [{"id":"4128","gid":null,"title":"black","gnid":"758","titles":"Color"},{"id":"4130","gid":null,"title":"500ml","gnid":"759","titles":"Capacity"}]
         */

        private String id;
        private String uid;
        private String gid;
        private String addtime;
        private String number;
        private String type;
        private String z_money;
        private String price;
        private String ltype;
        private String gcid;
        private String yuanjia;
        private String cnt0;
        private String cnt1;
        private String cnt2;
        private String cnt3;
        private String cnts0;
        private String cnts1;
        private String cnts2;
        private String cnts3;
        private String title;
        private String img_feng;
        private int is_guo;
        private List<GuigeBean> guige;


        public boolean isCheck() {
            return Check;
        }

        public void setCheck(boolean check) {
            Check = check;
        }

        private boolean Check;
        
        
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getZ_money() {
            return z_money;
        }

        public void setZ_money(String z_money) {
            this.z_money = z_money;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLtype() {
            return ltype;
        }

        public void setLtype(String ltype) {
            this.ltype = ltype;
        }

        public String getGcid() {
            return gcid;
        }

        public void setGcid(String gcid) {
            this.gcid = gcid;
        }

        public String getYuanjia() {
            return yuanjia;
        }

        public void setYuanjia(String yuanjia) {
            this.yuanjia = yuanjia;
        }

        public String getCnt0() {
            return cnt0;
        }

        public void setCnt0(String cnt0) {
            this.cnt0 = cnt0;
        }

        public String getCnt1() {
            return cnt1;
        }

        public void setCnt1(String cnt1) {
            this.cnt1 = cnt1;
        }

        public String getCnt2() {
            return cnt2;
        }

        public void setCnt2(String cnt2) {
            this.cnt2 = cnt2;
        }

        public String getCnt3() {
            return cnt3;
        }

        public void setCnt3(String cnt3) {
            this.cnt3 = cnt3;
        }

        public String getCnts0() {
            return cnts0;
        }

        public void setCnts0(String cnts0) {
            this.cnts0 = cnts0;
        }

        public String getCnts1() {
            return cnts1;
        }

        public void setCnts1(String cnts1) {
            this.cnts1 = cnts1;
        }

        public String getCnts2() {
            return cnts2;
        }

        public void setCnts2(String cnts2) {
            this.cnts2 = cnts2;
        }

        public String getCnts3() {
            return cnts3;
        }

        public void setCnts3(String cnts3) {
            this.cnts3 = cnts3;
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

        public int getIs_guo() {
            return is_guo;
        }

        public void setIs_guo(int is_guo) {
            this.is_guo = is_guo;
        }

        public List<GuigeBean> getGuige() {
            return guige;
        }

        public void setGuige(List<GuigeBean> guige) {
            this.guige = guige;
        }

        public static class GuigeBean {
            /**
             * id : 4128
             * gid : null
             * title : black
             * gnid : 758
             * titles : Color
             */

            private String id;
            private String gid;
            private String title;
            private String gnid;
            private String titles;

            public static List<GuigeBean> arrayGuigeBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<GuigeBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getGnid() {
                return gnid;
            }

            public void setGnid(String gnid) {
                this.gnid = gnid;
            }

            public String getTitles() {
                return titles;
            }

            public void setTitles(String titles) {
                this.titles = titles;
            }
        }
    }
}
