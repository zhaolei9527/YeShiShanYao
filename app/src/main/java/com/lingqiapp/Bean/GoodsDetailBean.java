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
     * goods : {"id":"42","title":"荣耀畅玩8C 全网通标配版 4GB+32GB 幻夜黑 移动联通电信4G全面屏手机 双卡双待","img":["/Public/uploads/2018-10-16/5bc53e3c72035.jpg"],"addtime":"1539653181","kucun":"49951","xiaoliang":"0","price":"1099.00"}
     * is_cang : 0
     * is_hui : 2
     * shen : 0
     * pj : {"0":{"id":"21","uid":"471","orderid":"20181017133717437939","star":"5","pcontent":"vdjsbhssbb","gid":"42","aid":null,"hcontent":null,"p_img":["/Public/uploads/oder_ping/2018-10-17/5bc6dc22608b4.png","/Public/uploads/oder_ping/2018-10-17/5bc6dc2262024.png"],"addtime":"1539759139","stu":"1","hftime":null,"ni_name":"程序猿","img":"/Public/uploads/headimg/default_img.png"},"1":{"id":"19","uid":"471","orderid":"20181017133723866790","star":"5","pcontent":"ghchh","gid":"42","aid":null,"hcontent":null,"p_img":["/Public/uploads/oder_ping/2018-10-17/5bc6dad3c6513.png","/Public/uploads/oder_ping/2018-10-17/5bc6dad3c806b.png"],"addtime":"1539758805","stu":"1","hftime":null,"ni_name":"程序猿","img":"/Public/uploads/headimg/default_img.png"},"count":"2"}
     * status : 1
     */

    private GoodsBean goods;
    private String is_cang;
    private String is_hui;
    private int shen;
    private PjBean pj;
    private int status;

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

    public String getIs_hui() {
        return is_hui;
    }

    public void setIs_hui(String is_hui) {
        this.is_hui = is_hui;
    }

    public int getShen() {
        return shen;
    }

    public void setShen(int shen) {
        this.shen = shen;
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

    public static class GoodsBean {
        /**
         * id : 42
         * title : 荣耀畅玩8C 全网通标配版 4GB+32GB 幻夜黑 移动联通电信4G全面屏手机 双卡双待
         * img : ["/Public/uploads/2018-10-16/5bc53e3c72035.jpg"]
         * addtime : 1539653181
         * kucun : 49951
         * xiaoliang : 0
         * price : 1099.00
         */

        private String id;
        private String title;
        private String addtime;
        private String kucun;
        private String xiaoliang;
        private String price;
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

        public String getXiaoliang() {
            return xiaoliang;
        }

        public void setXiaoliang(String xiaoliang) {
            this.xiaoliang = xiaoliang;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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
         * 0 : {"id":"21","uid":"471","orderid":"20181017133717437939","star":"5","pcontent":"vdjsbhssbb","gid":"42","aid":null,"hcontent":null,"p_img":["/Public/uploads/oder_ping/2018-10-17/5bc6dc22608b4.png","/Public/uploads/oder_ping/2018-10-17/5bc6dc2262024.png"],"addtime":"1539759139","stu":"1","hftime":null,"ni_name":"程序猿","img":"/Public/uploads/headimg/default_img.png"}
         * 1 : {"id":"19","uid":"471","orderid":"20181017133723866790","star":"5","pcontent":"ghchh","gid":"42","aid":null,"hcontent":null,"p_img":["/Public/uploads/oder_ping/2018-10-17/5bc6dad3c6513.png","/Public/uploads/oder_ping/2018-10-17/5bc6dad3c806b.png"],"addtime":"1539758805","stu":"1","hftime":null,"ni_name":"程序猿","img":"/Public/uploads/headimg/default_img.png"}
         * count : 2
         */

        @SerializedName("0")
        private _$0Bean _$0;
        @SerializedName("1")
        private _$1Bean _$1;
        private String count;

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

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public static class _$0Bean {
            /**
             * id : 21
             * uid : 471
             * orderid : 20181017133717437939
             * star : 5
             * pcontent : vdjsbhssbb
             * gid : 42
             * aid : null
             * hcontent : null
             * p_img : ["/Public/uploads/oder_ping/2018-10-17/5bc6dc22608b4.png","/Public/uploads/oder_ping/2018-10-17/5bc6dc2262024.png"]
             * addtime : 1539759139
             * stu : 1
             * hftime : null
             * ni_name : 程序猿
             * img : /Public/uploads/headimg/default_img.png
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

        public static class _$1Bean {
            /**
             * id : 19
             * uid : 471
             * orderid : 20181017133723866790
             * star : 5
             * pcontent : ghchh
             * gid : 42
             * aid : null
             * hcontent : null
             * p_img : ["/Public/uploads/oder_ping/2018-10-17/5bc6dad3c6513.png","/Public/uploads/oder_ping/2018-10-17/5bc6dad3c806b.png"]
             * addtime : 1539758805
             * stu : 1
             * hftime : null
             * ni_name : 程序猿
             * img : /Public/uploads/headimg/default_img.png
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
}
