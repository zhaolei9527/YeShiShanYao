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
public class BankBean {


    /**
     * status : 1
     * res : {"id":"147","bank":"zhejiang","no":"6212262201023557228","name":"sakura","add_time":"1537860490","uid":"461","rand":null}
     */

    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    private ResBean res;

    public static List<BankBean> arrayBankBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<BankBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * id : 147
         * bank : zhejiang
         * no : 6212262201023557228
         * name : sakura
         * add_time : 1537860490
         * uid : 461
         * rand : null
         */

        private String id;
        private String bank;
        private String no;
        private String name;
        private String add_time;
        private String uid;
        private Object rand;

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

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public Object getRand() {
            return rand;
        }

        public void setRand(Object rand) {
            this.rand = rand;
        }
    }
}
