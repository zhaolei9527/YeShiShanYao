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
 * @date 2018/11/26
 * 功能描述：
 */
public class GoodsCateBean {

    /**
     * status : 1
     * res : [{"id":"1","title":"口红","pid":"0","keywords":"口红","description":"口红","sort":"0","addtime":"1542875500","ltype":"1","img":null,"child":[{"id":"2","title":"MAC","pid":"1","keywords":"MAC","description":"MAC","sort":"0","addtime":"1542875600","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba27e589f8.jpg"},{"id":"3","title":"SYL","pid":"1","keywords":"SYL","description":"SYL","sort":"0","addtime":"1542875700","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba38f21728.png"},{"id":"8","title":"卡姿兰","pid":"1","keywords":"卡姿兰","description":"卡姿兰","sort":"0","addtime":"1543202944","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba4fd05dc0.jpg"},{"id":"9","title":"阿玛尼","pid":"1","keywords":"阿玛尼","description":"阿玛尼","sort":"0","addtime":"1543202962","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba50cd7168.jpg"}]},{"id":"4","title":"眼霜","pid":"0","keywords":"眼霜","description":"眼霜","sort":"0","addtime":"1542875738","ltype":"1","img":null,"child":[{"id":"5","title":"雅诗兰黛","pid":"4","keywords":"雅诗兰黛","description":"雅诗兰黛","sort":"0","addtime":"1542875777","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba4dd7b0c0.jpg"},{"id":"6","title":"欧莱雅","pid":"4","keywords":"欧莱雅","description":"欧莱雅","sort":"0","addtime":"1543202890","ltype":"1","img":null},{"id":"7","title":"资生堂","pid":"4","keywords":"资生堂","description":"资生堂","sort":"0","addtime":"1543202913","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba4ee877f8.jpg"}]},{"id":"10","title":"水乳","pid":"0","keywords":"水乳","description":"水乳","sort":"0","addtime":"1543203010","ltype":"1","img":null,"child":[{"id":"11","title":"欧莱雅","pid":"10","keywords":"欧莱雅","description":"欧莱雅","sort":"0","addtime":"1543203105","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba51d60310.jpg"}]}]
     */

    private String status;
    private List<ResBean> res;

    public static List<GoodsCateBean> arrayGoodsCateBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GoodsCateBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * id : 1
         * title : 口红
         * pid : 0
         * keywords : 口红
         * description : 口红
         * sort : 0
         * addtime : 1542875500
         * ltype : 1
         * img : null
         * child : [{"id":"2","title":"MAC","pid":"1","keywords":"MAC","description":"MAC","sort":"0","addtime":"1542875600","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba27e589f8.jpg"},{"id":"3","title":"SYL","pid":"1","keywords":"SYL","description":"SYL","sort":"0","addtime":"1542875700","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba38f21728.png"},{"id":"8","title":"卡姿兰","pid":"1","keywords":"卡姿兰","description":"卡姿兰","sort":"0","addtime":"1543202944","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba4fd05dc0.jpg"},{"id":"9","title":"阿玛尼","pid":"1","keywords":"阿玛尼","description":"阿玛尼","sort":"0","addtime":"1543202962","ltype":"1","img":"/Public/uploads/2018-11-26/5bfba50cd7168.jpg"}]
         */

        private String id;
        private String title;
        private String pid;
        private String keywords;
        private String description;
        private String sort;
        private String addtime;
        private String ltype;
        private Object img;
        private List<ChildBean> child;

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

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getLtype() {
            return ltype;
        }

        public void setLtype(String ltype) {
            this.ltype = ltype;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * id : 2
             * title : MAC
             * pid : 1
             * keywords : MAC
             * description : MAC
             * sort : 0
             * addtime : 1542875600
             * ltype : 1
             * img : /Public/uploads/2018-11-26/5bfba27e589f8.jpg
             */

            private String id;
            private String title;
            private String pid;
            private String keywords;
            private String description;
            private String sort;
            private String addtime;
            private String ltype;
            private String img;

            public static List<ChildBean> arrayChildBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ChildBean>>() {
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

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getLtype() {
                return ltype;
            }

            public void setLtype(String ltype) {
                this.ltype = ltype;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
