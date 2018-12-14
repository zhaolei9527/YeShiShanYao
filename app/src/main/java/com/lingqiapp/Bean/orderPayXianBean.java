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
 * @date 2018/12/14
 * 功能描述：
 */
public class orderPayXianBean {


    /**
     * status : 1
     * msg : 线下支付提交成功
     */

    private int status;
    private String msg;

    public static List<orderPayXianBean> arrayorderPayXianBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<orderPayXianBean>>() {
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
}
