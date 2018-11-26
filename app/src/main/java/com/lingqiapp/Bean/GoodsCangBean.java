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
 * @date 2017/12/9
 * 功能描述：
 */
public class GoodsCangBean {

    /**
     * status : 1
     * msg : 成功收藏！
     */

    private String status;
    private String msg;

    public static List<GoodsCangBean> arrayGoodsCangBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GoodsCangBean>>() {
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
}
