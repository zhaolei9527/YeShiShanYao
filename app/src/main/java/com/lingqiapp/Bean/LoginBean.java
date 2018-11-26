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
 * @date 2017/11/29
 * 功能描述：
 */
public class LoginBean {

    /**
     * status : 1
     * msg : 登录成功
     * user : {"id":"15","ni_name":"领七用户","img":"/Public/uploads/headimg/default_img.png","email":"975976959@qq.com","is_show":"0","is_hui":"1","password":"14e1b600b1fd579f47433b88e8d85291","add_time":"1543197579","status":"1","rand":null,"is_del":"1","last_login_time":null,"money":"0.00","total_push_money":"0.00","pid":null,"city":null,"openid_qq":null,"openid_qq_type":null,"openid_wx":null,"check_in_time":null,"erweima":"./Public/userqrcode/975976959@qq.com-p.png","unionid":null,"app_qq_openid":null,"hui_star":null,"hui_end":null,"country":null,"ltype":null,"emailid":null}
     * zw_count : 0
     */

    private String status;
    private String msg;
    private UserBean user;
    private String zw_count;

    public static List<LoginBean> arrayLoginBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<LoginBean>>() {
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getZw_count() {
        return zw_count;
    }

    public void setZw_count(String zw_count) {
        this.zw_count = zw_count;
    }

    public static class UserBean {
        /**
         * id : 15
         * ni_name : 领七用户
         * img : /Public/uploads/headimg/default_img.png
         * email : 975976959@qq.com
         * is_show : 0
         * is_hui : 1
         * password : 14e1b600b1fd579f47433b88e8d85291
         * add_time : 1543197579
         * status : 1
         * rand : null
         * is_del : 1
         * last_login_time : null
         * money : 0.00
         * total_push_money : 0.00
         * pid : null
         * city : null
         * openid_qq : null
         * openid_qq_type : null
         * openid_wx : null
         * check_in_time : null
         * erweima : ./Public/userqrcode/975976959@qq.com-p.png
         * unionid : null
         * app_qq_openid : null
         * hui_star : null
         * hui_end : null
         * country : null
         * ltype : null
         * emailid : null
         */

        private String id;
        private String ni_name;
        private String img;
        private String email;
        private String is_show;
        private String is_hui;
        private String password;
        private String add_time;
        private String status;
        private Object rand;
        private String is_del;
        private Object last_login_time;
        private String money;
        private String total_push_money;
        private Object pid;
        private Object city;
        private Object openid_qq;
        private Object openid_qq_type;
        private Object openid_wx;
        private Object check_in_time;
        private String erweima;
        private Object unionid;
        private Object app_qq_openid;
        private Object hui_star;
        private Object hui_end;
        private Object country;
        private Object ltype;
        private Object emailid;

        public static List<UserBean> arrayUserBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<UserBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getIs_hui() {
            return is_hui;
        }

        public void setIs_hui(String is_hui) {
            this.is_hui = is_hui;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getRand() {
            return rand;
        }

        public void setRand(Object rand) {
            this.rand = rand;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public Object getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(Object last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTotal_push_money() {
            return total_push_money;
        }

        public void setTotal_push_money(String total_push_money) {
            this.total_push_money = total_push_money;
        }

        public Object getPid() {
            return pid;
        }

        public void setPid(Object pid) {
            this.pid = pid;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getOpenid_qq() {
            return openid_qq;
        }

        public void setOpenid_qq(Object openid_qq) {
            this.openid_qq = openid_qq;
        }

        public Object getOpenid_qq_type() {
            return openid_qq_type;
        }

        public void setOpenid_qq_type(Object openid_qq_type) {
            this.openid_qq_type = openid_qq_type;
        }

        public Object getOpenid_wx() {
            return openid_wx;
        }

        public void setOpenid_wx(Object openid_wx) {
            this.openid_wx = openid_wx;
        }

        public Object getCheck_in_time() {
            return check_in_time;
        }

        public void setCheck_in_time(Object check_in_time) {
            this.check_in_time = check_in_time;
        }

        public String getErweima() {
            return erweima;
        }

        public void setErweima(String erweima) {
            this.erweima = erweima;
        }

        public Object getUnionid() {
            return unionid;
        }

        public void setUnionid(Object unionid) {
            this.unionid = unionid;
        }

        public Object getApp_qq_openid() {
            return app_qq_openid;
        }

        public void setApp_qq_openid(Object app_qq_openid) {
            this.app_qq_openid = app_qq_openid;
        }

        public Object getHui_star() {
            return hui_star;
        }

        public void setHui_star(Object hui_star) {
            this.hui_star = hui_star;
        }

        public Object getHui_end() {
            return hui_end;
        }

        public void setHui_end(Object hui_end) {
            this.hui_end = hui_end;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Object getLtype() {
            return ltype;
        }

        public void setLtype(Object ltype) {
            this.ltype = ltype;
        }

        public Object getEmailid() {
            return emailid;
        }

        public void setEmailid(Object emailid) {
            this.emailid = emailid;
        }
    }
}
