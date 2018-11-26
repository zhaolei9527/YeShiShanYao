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
public class LianXiBean {
    /**
     * status : 1
     * kfrx : {"id":"1","title":"客服热线","content":"18638035535","addtime":null,"img":"/Public/uploads/flow/2018-09-20/5ba2ffebe3af8.png","keywords":"客服热线","description":"客服热线"}
     * email : {"id":"2","title":"邮箱","content":"962870@qq.com","addtime":null,"img":"/Public/uploads/flow/2018-09-20/5ba3004f5d688.png","keywords":"邮箱","description":"邮箱"}
     * website : {"id":"3","title":"企业网站","content":"www.lingqi.com","addtime":null,"img":"/Public/uploads/flow/2018-09-20/5ba301f713308.png","keywords":"企业网站","description":"企业网站"}
     * wechat : {"id":"4","title":"微信公众号","content":"lingqi","addtime":null,"img":"/Public/uploads/flow/2018-09-20/5ba30570d2d70.png","keywords":"微信公众号","description":"微信公众号"}
     * address : {"id":"5","title":"地址","content":"河南省郑州市金水区经三路东风路","addtime":null,"img":"/Public/uploads/flow/2018-09-20/5ba302612e4a0.png","keywords":"地址","description":"地址"}
     */

    private int status;
    private KfrxBean kfrx;
    private EmailBean email;
    private WebsiteBean website;
    private WechatBean wechat;
    private AddressBean address;

    public static List<LianXiBean> arrayLianXiBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<LianXiBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public KfrxBean getKfrx() {
        return kfrx;
    }

    public void setKfrx(KfrxBean kfrx) {
        this.kfrx = kfrx;
    }

    public EmailBean getEmail() {
        return email;
    }

    public void setEmail(EmailBean email) {
        this.email = email;
    }

    public WebsiteBean getWebsite() {
        return website;
    }

    public void setWebsite(WebsiteBean website) {
        this.website = website;
    }

    public WechatBean getWechat() {
        return wechat;
    }

    public void setWechat(WechatBean wechat) {
        this.wechat = wechat;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public static class KfrxBean {
        /**
         * id : 1
         * title : 客服热线
         * content : 18638035535
         * addtime : null
         * img : /Public/uploads/flow/2018-09-20/5ba2ffebe3af8.png
         * keywords : 客服热线
         * description : 客服热线
         */

        private String id;
        private String title;
        private String content;
        private Object addtime;
        private String img;
        private String keywords;
        private String description;

        public static List<KfrxBean> arrayKfrxBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<KfrxBean>>() {
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getAddtime() {
            return addtime;
        }

        public void setAddtime(Object addtime) {
            this.addtime = addtime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
    }

    public static class EmailBean {
        /**
         * id : 2
         * title : 邮箱
         * content : 962870@qq.com
         * addtime : null
         * img : /Public/uploads/flow/2018-09-20/5ba3004f5d688.png
         * keywords : 邮箱
         * description : 邮箱
         */

        private String id;
        private String title;
        private String content;
        private Object addtime;
        private String img;
        private String keywords;
        private String description;

        public static List<EmailBean> arrayEmailBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<EmailBean>>() {
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getAddtime() {
            return addtime;
        }

        public void setAddtime(Object addtime) {
            this.addtime = addtime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
    }

    public static class WebsiteBean {
        /**
         * id : 3
         * title : 企业网站
         * content : www.lingqi.com
         * addtime : null
         * img : /Public/uploads/flow/2018-09-20/5ba301f713308.png
         * keywords : 企业网站
         * description : 企业网站
         */

        private String id;
        private String title;
        private String content;
        private Object addtime;
        private String img;
        private String keywords;
        private String description;

        public static List<WebsiteBean> arrayWebsiteBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<WebsiteBean>>() {
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getAddtime() {
            return addtime;
        }

        public void setAddtime(Object addtime) {
            this.addtime = addtime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
    }

    public static class WechatBean {
        /**
         * id : 4
         * title : 微信公众号
         * content : lingqi
         * addtime : null
         * img : /Public/uploads/flow/2018-09-20/5ba30570d2d70.png
         * keywords : 微信公众号
         * description : 微信公众号
         */

        private String id;
        private String title;
        private String content;
        private Object addtime;
        private String img;
        private String keywords;
        private String description;

        public static List<WechatBean> arrayWechatBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<WechatBean>>() {
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getAddtime() {
            return addtime;
        }

        public void setAddtime(Object addtime) {
            this.addtime = addtime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
    }

    public static class AddressBean {
        /**
         * id : 5
         * title : 地址
         * content : 河南省郑州市金水区经三路东风路
         * addtime : null
         * img : /Public/uploads/flow/2018-09-20/5ba302612e4a0.png
         * keywords : 地址
         * description : 地址
         */

        private String id;
        private String title;
        private String content;
        private Object addtime;
        private String img;
        private String keywords;
        private String description;

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

        public Object getAddtime() {
            return addtime;
        }

        public void setAddtime(Object addtime) {
            this.addtime = addtime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
    }
}
