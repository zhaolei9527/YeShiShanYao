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
 * @date 2018/9/25
 * 功能描述：
 */
public class ChangeBankBean {


    /**
     * status : 0
     * msg : 请输入正确银行卡号
     */

    private int status;
    private String msg;

    public static List<ChangeBankBean> arrayChangeBankBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ChangeBankBean>>() {
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
