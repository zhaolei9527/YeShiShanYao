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
public class OrderXianBean {

    /**
     * conf : {"z_name1":"吴先生","kaihu1":"建设银行","zhuan1":"6227002430660146","z_name2":"","kaihu2":"","zhuan2":"","z_name3":"","kaihu3":"","zhuan3":"","x_content":"This transaction is offline payment. In the transfer account provided by the transfer account, please confirm the consistency of the transfer account and the provided account several times, after confirming the consistency. After transfer, it is necessary to upload the transfer information in the form of pictures. When the staff checks the information correctly, the order will be processed immediately. Please wait slowly. Thank you for your cooperation."}
     * zmoney :
     */

    private ConfBean conf;
    private String zmoney;

    public static List<OrderXianBean> arrayOrderXianBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderXianBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public ConfBean getConf() {
        return conf;
    }

    public void setConf(ConfBean conf) {
        this.conf = conf;
    }

    public String getZmoney() {
        return zmoney;
    }

    public void setZmoney(String zmoney) {
        this.zmoney = zmoney;
    }

    public static class ConfBean {
        /**
         * z_name1 : 吴先生
         * kaihu1 : 建设银行
         * zhuan1 : 6227002430660146
         * z_name2 :
         * kaihu2 :
         * zhuan2 :
         * z_name3 :
         * kaihu3 :
         * zhuan3 :
         * x_content : This transaction is offline payment. In the transfer account provided by the transfer account, please confirm the consistency of the transfer account and the provided account several times, after confirming the consistency. After transfer, it is necessary to upload the transfer information in the form of pictures. When the staff checks the information correctly, the order will be processed immediately. Please wait slowly. Thank you for your cooperation.
         */

        private String z_name1;
        private String kaihu1;
        private String zhuan1;
        private String z_name2;
        private String kaihu2;
        private String zhuan2;
        private String z_name3;
        private String kaihu3;
        private String zhuan3;
        private String x_content;

        public static List<ConfBean> arrayConfBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ConfBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getZ_name1() {
            return z_name1;
        }

        public void setZ_name1(String z_name1) {
            this.z_name1 = z_name1;
        }

        public String getKaihu1() {
            return kaihu1;
        }

        public void setKaihu1(String kaihu1) {
            this.kaihu1 = kaihu1;
        }

        public String getZhuan1() {
            return zhuan1;
        }

        public void setZhuan1(String zhuan1) {
            this.zhuan1 = zhuan1;
        }

        public String getZ_name2() {
            return z_name2;
        }

        public void setZ_name2(String z_name2) {
            this.z_name2 = z_name2;
        }

        public String getKaihu2() {
            return kaihu2;
        }

        public void setKaihu2(String kaihu2) {
            this.kaihu2 = kaihu2;
        }

        public String getZhuan2() {
            return zhuan2;
        }

        public void setZhuan2(String zhuan2) {
            this.zhuan2 = zhuan2;
        }

        public String getZ_name3() {
            return z_name3;
        }

        public void setZ_name3(String z_name3) {
            this.z_name3 = z_name3;
        }

        public String getKaihu3() {
            return kaihu3;
        }

        public void setKaihu3(String kaihu3) {
            this.kaihu3 = kaihu3;
        }

        public String getZhuan3() {
            return zhuan3;
        }

        public void setZhuan3(String zhuan3) {
            this.zhuan3 = zhuan3;
        }

        public String getX_content() {
            return x_content;
        }

        public void setX_content(String x_content) {
            this.x_content = x_content;
        }
    }
}
