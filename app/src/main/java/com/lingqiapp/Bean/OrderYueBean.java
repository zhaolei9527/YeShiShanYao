package com.lingqiapp.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/9
 * 功能描述：
 */
public class OrderYueBean {

    /**
     * status : 1
     * msg : 订单生成成功！
     * orderid : ["20181011091411746243"]
     */

    private int status;
    private String msg;
    private List<String> orderid;

    public static List<OrderYueBean> arrayOrderYueBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderYueBean>>() {
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

    public List<String> getOrderid() {
        return orderid;
    }

    public void setOrderid(List<String> orderid) {
        this.orderid = orderid;
    }
}
