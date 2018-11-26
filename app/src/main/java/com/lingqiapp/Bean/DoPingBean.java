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
 * @date 2018/10/12
 * 功能描述：
 */
public class DoPingBean {

    /**
     * status : 1
     * msg : 评价成功
     */

    private int status;
    private String msg;

    public static List<DoPingBean> arrayDoPingBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<DoPingBean>>() {
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
