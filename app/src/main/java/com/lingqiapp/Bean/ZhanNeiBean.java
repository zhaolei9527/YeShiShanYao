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
public class ZhanNeiBean {


    /**
     * status : 1
     * list : [{"id":"35","uid":"461","title":"订单发货","content":"15256760658163635订单已发货，快递公司：中通快递，快递单号：632576303108，请注意近期内快递信息！","status":"1","type":"1","addtime":"1525686520"},{"id":"34","uid":"461","title":"订单发货","content":"15247468629355838订单已发货，快递公司：中通快递，快递单号：632223395085，请注意近期内快递信息！","status":"1","type":"1","addtime":"1524800284"},{"id":"33","uid":"461","title":"订单发货","content":"15247933366171342订单已发货，快递公司：中通  快递，快递单号：632223395085，请注意近期内快递信息！","status":"1","type":"1","addtime":"1524800233"},{"id":"32","uid":"461","title":"订单发货","content":"15226440734222684订单已发货，快递公司：中通，快递单号：631149559996，请注意近期内快递信息！","status":"1","type":"1","addtime":"1522651999"},{"id":"31","uid":"461","title":"订单发货","content":"15223292402830306订单已发货，快递公司：中通  快递，快递单号：631149559770，请注意近期内快递信息！","status":"1","type":"1","addtime":"1522399331"},{"id":"30","uid":"461","title":"订单发货","content":"15223292855850634订单已发货，快递公司：中通，快递单号：631149559770，请注意近期内快递信息！","status":"1","type":"1","addtime":"1522399277"},{"id":"29","uid":"461","title":"订单发货","content":"15215336744167453订单已发货，快递公司：中通  快递，快递单号：630820854076，请注意近期内快递信息！","status":"1","type":"1","addtime":"1521539735"},{"id":"28","uid":"461","title":"订单发货","content":"15215340383724992订单已发货，快递公司：中通快递，快递单号：630820854076，请注意近期内快递信息！","status":"1","type":"1","addtime":"1521539688"},{"id":"27","uid":"461","title":"订单发货","content":"15209346656538737订单已发货，快递公司：中通快递，快递单号：630820853962，请注意近期内快递信息！","status":"1","type":"1","addtime":"1521351336"},{"id":"26","uid":"461","title":"订单发货","content":"15210228966440480订单已发货，快递公司：中通快递，快递单号：630820853962，请注意近期内快递信息！","status":"1","type":"1","addtime":"1521351276"}]
     */

    private int status;
    private List<ListBean> list;

    public static List<ZhanNeiBean> arrayZhanNeiBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ZhanNeiBean>>() {
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
         * id : 35
         * uid : 461
         * title : 订单发货
         * content : 15256760658163635订单已发货，快递公司：中通快递，快递单号：632576303108，请注意近期内快递信息！
         * status : 1
         * type : 1
         * addtime : 1525686520
         */

        private String id;
        private String uid;
        private String title;
        private String content;
        private String status;
        private String type;
        private String addtime;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
