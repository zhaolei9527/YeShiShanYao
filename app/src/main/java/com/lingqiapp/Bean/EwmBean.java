package com.lingqiapp.Bean;

/**
 * com.lingqiapp.Bean
 *
 * @author 赵磊
 * @date 2018/9/20
 * 功能描述：
 */
public class EwmBean {

    /**
     * status : 1
     *  img : /Public/uploads/headimg/default_img.png
     * erweima : ./Public/userqrcode/17629345001-p.png
     * jiang : 被推荐人使用您的推荐码详情描述被推荐人，使用您的推荐码详情描述被推荐人
     */

    private int status;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getErweima() {
        return erweima;
    }

    public void setErweima(String erweima) {
        this.erweima = erweima;
    }

    public String getJiang() {
        return jiang;
    }

    public void setJiang(String jiang) {
        this.jiang = jiang;
    }

    private String img;
    private String erweima;
    private String jiang;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
