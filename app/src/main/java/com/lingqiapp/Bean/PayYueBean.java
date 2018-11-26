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
 * @date 2018/9/28
 * 功能描述：
 */
public class PayYueBean {


    /**
     * status : 1
     * msg : 支付成功
     */

    private int status;
    private String msg;

    public static List<PayYueBean> arrayPayYueBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<PayYueBean>>() {
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
