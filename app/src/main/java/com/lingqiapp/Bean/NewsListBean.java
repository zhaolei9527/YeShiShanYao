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
public class NewsListBean {

    /**
     * status : 1
     * res : [{"id":"99","title":"女人吃猪蹄能抗衰老？营养师：真正让你保持年轻的，是这4类食物","add_time":"1511081504","keywords":"衰老是每个人都非常恐惧的事情","img":"/Public/uploads/2018-11-22/5bf687fc5e3d0.png","view":"90","cid":"15","description":"衰老是每个人都非常恐惧的事情，但是毕竟岁月无情，我们的皮肤和身体一直都在不断的衰老。那么，我们有什么办法可以延缓衰老呢？"},{"id":"191","title":"男明星留胡子堪比整容, 王宝强帅到没边, 陈坤像换了一张脸","add_time":"1534583267","keywords":"留胡子","img":"/Public/uploads/News/2018-10-29/5bd6ad697094e.png","view":"303","cid":"14","description":"男明星留胡子堪比整容"},{"id":"186","title":"崇拜着另一个\u201c自己\u201d 熊黛林心中的女神居然是王菲","add_time":"1533635285","keywords":"崇拜着另一个\u201c自己\u201d 熊黛林心中的女神居然是王菲","img":"/Public/uploads/News/2018-10-29/5bd6ba991e14b.png","view":"47","cid":"14","description":"YOKA：您心中的YOKA时尚网在时尚圈的位置是怎样的？\r\n\r\n熊黛林：YOKA在我心中是时尚垂直网站中的领军者，更像是一位带头人。\r\n\r\nYOKA：你希望在yoka看到什么内容？\r\n\r\n熊黛林：当然是看到一些更权威的时尚资讯啦~也希望能在这样一个大型网站上看到更多有关我自己的时尚资讯~(*^__^*)"},{"id":"156","title":"时尚圈那些违背主流审美的\u201c瑕疵\u201d美","add_time":"1521512152","keywords":"时尚圈","img":"/Public/uploads/News/2018-10-11/5bbeb0e3551f2.jpg","view":"156","cid":"14","description":"其实，缺陷就是缺陷，它不会平白无故就变成优点，能够将缺陷变成特点的是你的内心。"},{"id":"130","title":"如何让男人疯狂地爱上你，而且愿意付出一生？","add_time":"1516253523","keywords":"让男人疯狂爱上你","img":"/Public/uploads/News/2018-10-11/5bbeb9e67f595.jpg","view":"89","cid":"8","description":"　如此，才能心想事成\u2014\u2014让男人疯狂爱上你，并且拥有一段美好而持久的亲密关系，最大限度拥有和享受爱与被爱的权利。"},{"id":"207","title":"万圣节大型撞脸现场拒绝尴尬，美即面膜为你打造独一无二吸睛\u201c女侠Style\u201d","add_time":"1540796607","keywords":"万圣节大型撞脸现场拒绝尴尬，美即面膜为你打造独一无二吸睛\u201c女侠Style\u201d","img":"/Public/uploads/2018-11-23/5bf793d7ec2e8.png","view":"0","cid":"10","description":"　Halloween is coming! 现在的万圣节夜晚，你也许不会半夜被穿着巫师袍的小孩大喊Trick or treat敲门要糖果，但一定会被票圈里晒出千篇一律裂嘴、血泪\u201c万圣妆\u201d的女孩刷了屏。在这一年一度的狂欢party来临之际，作为时尚小仙女的关晓彤也开始\u201c脑阔疼\u201d：如何逃离朋友圈大型撞脸尴尬现场?如何玩出与众不同的\u201c万圣脸\u201d新花样?"},{"id":"235","title":"智能健身硬件商茄子科技获百万级美元融资 小米参投","add_time":"1540798285","keywords":"智能健身硬件商茄子科技获百万级美元融资 小米参投","img":"/Public/uploads/News/2018-10-29/5bd6b74dcb9b9.jpg","view":"0","cid":"15","description":"智能健身硬件商茄子科技获百万级美元融资 小米参投"},{"id":"196","title":"科技邂逅时尚 大数据报告登场","add_time":"1539224861","keywords":"时尚","img":"/Public/uploads/News/2018-10-11/5bbeb51dbf4d2.jpg","view":"0","cid":"14","description":"时尚是什么？\r\n\r\n没有人能给出一个毫无瑕疵，不具争议的定义。\r\n\r\n普通人的印象里，和时尚相关的词往往是：奢侈品，时装周，皮草，腕表，巴黎，米兰，走秀，大片\u2026\u2026也有人说，时尚是一种潮流，一种生活方式，一种直抵人心的观念。"},{"id":"236","title":"养老护理服务商\u201c小柏家护\u201d获3000万元Pre-A轮融资","add_time":"1540798374","keywords":"小柏家护","img":"/Public/uploads/News/2018-10-29/5bd6b7a675ee8.jpg","view":"0","cid":"15","description":"【内容摘要】为解决家庭养老模式中常见的价格偏高、护工不专业、缺乏监管等问题，小柏家护自建护工团队，目前规模已近千人。"},{"id":"237","title":"VEECO ZHAO 2019春夏新品预览会：独自在家的时光","add_time":"1540798438","keywords":"VEECO ZHAO","img":"/Public/uploads/News/2018-10-29/5bd6b7e6628f8.jpg","view":"0","cid":"14","description":"2018年10月18日，独立设计师品牌VEECO ZHAO于北京今格艺术体验馆举办了2019春夏新品预览会。在这所富有人文气息的艺术馆中，设计师赵晗羽用服装为VEECO ZHAO女孩们构筑了一个温柔明媚春日空间。"}]
     * cate : [{"id":"8","cate_name":"情感"},{"id":"17","cate_name":"美妆"},{"id":"14","cate_name":"时尚"},{"id":"15","cate_name":"护理"}]
     * msg : 查询数据成功，返回数据
     */

    private String status;
    private String msg;
    private List<ResBean> res;
    private List<CateBean> cate;

    public static List<NewsListBean> arrayNewsListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<NewsListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public List<CateBean> getCate() {
        return cate;
    }

    public void setCate(List<CateBean> cate) {
        this.cate = cate;
    }

    public static class ResBean {
        /**
         * id : 99
         * title : 女人吃猪蹄能抗衰老？营养师：真正让你保持年轻的，是这4类食物
         * add_time : 1511081504
         * keywords : 衰老是每个人都非常恐惧的事情
         * img : /Public/uploads/2018-11-22/5bf687fc5e3d0.png
         * view : 90
         * cid : 15
         * description : 衰老是每个人都非常恐惧的事情，但是毕竟岁月无情，我们的皮肤和身体一直都在不断的衰老。那么，我们有什么办法可以延缓衰老呢？
         */

        private String id;
        private String title;
        private String add_time;
        private String keywords;
        private String img;
        private String view;
        private String cid;
        private String description;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class CateBean {
        /**
         * id : 8
         * cate_name : 情感
         */

        private String id;
        private String cate_name;

        public static List<CateBean> arrayCateBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<CateBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
