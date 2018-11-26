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
public class TiXianBean {


    /**
     * status : 1
     * tdata : {"uid":"461","money":"0.00","tx_min":"50","ti_rate":"2"}
     * bank : {"id":"148","bank":"asdasdsad","no":"6222600260001072444","name":"adadasd","add_time":"1537863498","uid":"461","rand":null}
     */

    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    private TdataBean tdata;
    private BankBean bank;

    public static List<TiXianBean> arrayTiXianBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<TiXianBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TdataBean getTdata() {
        return tdata;
    }

    public void setTdata(TdataBean tdata) {
        this.tdata = tdata;
    }

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public static class TdataBean {
        /**
         * uid : 461
         * money : 0.00
         * tx_min : 50
         * ti_rate : 2
         */

        private String uid;
        private String money;
        private String tx_min;
        private String ti_rate;

        public static List<TdataBean> arrayTdataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<TdataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTx_min() {
            return tx_min;
        }

        public void setTx_min(String tx_min) {
            this.tx_min = tx_min;
        }

        public String getTi_rate() {
            return ti_rate;
        }

        public void setTi_rate(String ti_rate) {
            this.ti_rate = ti_rate;
        }
    }

    public static class BankBean {
        /**
         * id : 148
         * bank : asdasdsad
         * no : 6222600260001072444
         * name : adadasd
         * add_time : 1537863498
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

        public static List<BankBean> arrayBankBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<BankBean>>() {
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
