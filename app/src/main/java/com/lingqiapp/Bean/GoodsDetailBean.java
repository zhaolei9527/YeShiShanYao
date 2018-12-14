package com.lingqiapp.Bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/8
 * 功能描述：
 */
public class GoodsDetailBean {

    /**
     * goods : {"id":"77","title":"Fu Guang water cup","img":["/Public/uploads/2018-12-08/5c0b852cb63f2.jpg","/Public/uploads/2018-12-08/5c0b852ead7c2.jpg"],"addtime":"1544258864","kucun":"9986","price":"69.00","is_gui":"1","xiaoliang":"2"}
     * gg : [{"id":"758","gid":"77","title":"Color","sx":[{"id":"4128","gid":null,"title":"black","gnid":"758"},{"id":"4129","gid":null,"title":"orange","gnid":"758"}]},{"id":"759","gid":"77","title":"Capacity","sx":[{"id":"4130","gid":null,"title":"500ml","gnid":"759"},{"id":"4131","gid":null,"title":"1000ml","gnid":"759"}]}]
     * is_cang : 0
     * pj : {"0":{"id":"36","uid":"40","orderid":"20181211154656626547","star":"5","pcontent":"哈哈哈哈哈哈","gid":"77","aid":null,"hcontent":null,"p_img":[""],"addtime":"1544514727","stu":"1","hftime":null,"ni_name":"吴先森","img":"/Public/uploads/headers/2018-12-11/2018_12_11_16_28_12_31332.jpg"},"1":{"id":"36","uid":"40","orderid":"20181211154656626547","star":"5","pcontent":"哈哈哈哈哈哈","gid":"77","aid":null,"hcontent":null,"p_img":[""],"addtime":"1544514727","stu":"1","hftime":null,"ni_name":"吴先森","img":"/Public/uploads/headers/2018-12-11/2018_12_11_16_28_12_31332.jpg"},"couant":"1"}
     * status : 1
     */

    private GoodsBean goods;
    private String is_cang;
    private PjBean pj;
    private int status;
    private List<GgBean> gg;

    public static List<GoodsDetailBean> arrayGoodsDetailBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GoodsDetailBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public String getIs_cang() {
        return is_cang;
    }

    public void setIs_cang(String is_cang) {
        this.is_cang = is_cang;
    }

    public PjBean getPj() {
        return pj;
    }

    public void setPj(PjBean pj) {
        this.pj = pj;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<GgBean> getGg() {
        return gg;
    }

    public void setGg(List<GgBean> gg) {
        this.gg = gg;
    }

    public static class GoodsBean {
        /**
         * id : 77
         * title : Fu Guang water cup
         * img : ["/Public/uploads/2018-12-08/5c0b852cb63f2.jpg","/Public/uploads/2018-12-08/5c0b852ead7c2.jpg"]
         * addtime : 1544258864
         * kucun : 9986
         * price : 69.00
         * is_gui : 1
         * xiaoliang : 2
         */

        private String id;
        private String title;
        private String addtime;
        private String kucun;
        private String price;
        private String is_gui;
        private String xiaoliang;
        private List<String> img;

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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getKucun() {
            return kucun;
        }

        public void setKucun(String kucun) {
            this.kucun = kucun;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIs_gui() {
            return is_gui;
        }

        public void setIs_gui(String is_gui) {
            this.is_gui = is_gui;
        }

        public String getXiaoliang() {
            return xiaoliang;
        }

        public void setXiaoliang(String xiaoliang) {
            this.xiaoliang = xiaoliang;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }
    }

    public static class PjBean {
        /**
         * 0 : {"id":"36","uid":"40","orderid":"20181211154656626547","star":"5","pcontent":"哈哈哈哈哈哈","gid":"77","aid":null,"hcontent":null,"p_img":[""],"addtime":"1544514727","stu":"1","hftime":null,"ni_name":"吴先森","img":"/Public/uploads/headers/2018-12-11/2018_12_11_16_28_12_31332.jpg"}
         * 1 : {"id":"36","uid":"40","orderid":"20181211154656626547","star":"5","pcontent":"哈哈哈哈哈哈","gid":"77","aid":null,"hcontent":null,"p_img":[""],"addtime":"1544514727","stu":"1","hftime":null,"ni_name":"吴先森","img":"/Public/uploads/headers/2018-12-11/2018_12_11_16_28_12_31332.jpg"}
         * couant : 1
         */

        @SerializedName("0")
        private _$0Bean _$0;
        @SerializedName("1")
        private _$1Bean _$1;
        private String couant;

        public static List<PjBean> arrayPjBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<PjBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public _$0Bean get_$0() {
            return _$0;
        }

        public void set_$0(_$0Bean _$0) {
            this._$0 = _$0;
        }

        public _$1Bean get_$1() {
            return _$1;
        }

        public void set_$1(_$1Bean _$1) {
            this._$1 = _$1;
        }

        public String getCouant() {
            return couant;
        }

        public void setCouant(String couant) {
            this.couant = couant;
        }

        public static class _$0Bean {
            /**
             * id : 36
             * uid : 40
             * orderid : 20181211154656626547
             * star : 5
             * pcontent : 哈哈哈哈哈哈
             * gid : 77
             * aid : null
             * hcontent : null
             * p_img : [""]
             * addtime : 1544514727
             * stu : 1
             * hftime : null
             * ni_name : 吴先森
             * img : /Public/uploads/headers/2018-12-11/2018_12_11_16_28_12_31332.jpg
             */

            private String id;
            private String uid;
            private String orderid;
            private String star;
            private String pcontent;
            private String gid;
            private String aid;
            private String hcontent;
            private String addtime;
            private String stu;
            private String hftime;
            private String ni_name;
            private String img;
            private List<String> p_img;

            public static List<_$0Bean> array_$0BeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<_$0Bean>>() {
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

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getHcontent() {
                return hcontent;
            }

            public void setHcontent(String hcontent) {
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

            public String getHftime() {
                return hftime;
            }

            public void setHftime(String hftime) {
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

        public static class _$1Bean {
            /**
             * id : 36
             * uid : 40
             * orderid : 20181211154656626547
             * star : 5
             * pcontent : 哈哈哈哈哈哈
             * gid : 77
             * aid : null
             * hcontent : null
             * p_img : [""]
             * addtime : 1544514727
             * stu : 1
             * hftime : null
             * ni_name : 吴先森
             * img : /Public/uploads/headers/2018-12-11/2018_12_11_16_28_12_31332.jpg
             */

            private String id;
            private String uid;
            private String orderid;
            private String star;
            private String pcontent;
            private String gid;
            private String aid;
            private String hcontent;
            private String addtime;
            private String stu;
            private String hftime;
            private String ni_name;
            private String img;
            private List<String> p_img;

            public static List<_$1Bean> array_$1BeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<_$1Bean>>() {
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

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getHcontent() {
                return hcontent;
            }

            public void setHcontent(String hcontent) {
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

            public String getHftime() {
                return hftime;
            }

            public void setHftime(String hftime) {
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

    public static class GgBean {
        /**
         * id : 758
         * gid : 77
         * title : Color
         * sx : [{"id":"4128","gid":null,"title":"black","gnid":"758"},{"id":"4129","gid":null,"title":"orange","gnid":"758"}]
         */

        private String id;
        private String gid;
        private String title;
        private List<SxBean> sx;

        public static List<GgBean> arrayGgBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<GgBean>>() {
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

        public List<SxBean> getSx() {
            return sx;
        }

        public void setSx(List<SxBean> sx) {
            this.sx = sx;
        }

        public static class SxBean {
            /**
             * id : 4128
             * gid : null
             * title : black
             * gnid : 758
             */

            private String id;
            private String gid;
            private String title;
            private String gnid;

            public static List<SxBean> arraySxBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SxBean>>() {
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
        }
    }
}
