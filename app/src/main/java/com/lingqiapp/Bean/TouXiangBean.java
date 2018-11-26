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
 * @date 2018/9/29
 * 功能描述：
 */
public class TouXiangBean {


    /**
     * status : 1
     * msg : 修改头像成功
     * udata : {"img":"/Public/uploads/touxiang/2018-09-29/5baf2360b4848.jpg"}
     */

    private int status;
    private String msg;
    private UdataBean udata;

    public static List<TouXiangBean> arrayTouXiangBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<TouXiangBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UdataBean getUdata() {
        return udata;
    }

    public void setUdata(UdataBean udata) {
        this.udata = udata;
    }

    public static class UdataBean {
        /**
         * img : /Public/uploads/touxiang/2018-09-29/5baf2360b4848.jpg
         */

        private String img;

        public static List<UdataBean> arrayUdataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<UdataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
