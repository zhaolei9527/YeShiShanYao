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
 * @date 2018/1/3
 * 功能描述：
 */
public class FBBean {

    /**
     * secretType : 0
     * icon : https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=200472444210722&height=200&width=200&ext=1545960285&hash=AeS5Te4k-VrRf4_6
     * userID : 200472444210722
     * resume : null
     * nickname : Lei Zhao
     * token : EAANICKq2sUwBANs0LGLaVPAqKr8n8ThMU5kNgf3ZBLefAN1WaDDn8mFb3J0eGR6XlnBufiRcfYETWhFHWbRWRiZCa8A9IZCgYBZCL0j9Sm3uFWiimvbGfHJhSDPaIPhtf6tUZCnOCZBgZAU4DCvE8fLSJD8awvJtUoGWIuTkxcS8LXGdQsPImR35zy0aZCu7bdbtJpMS7E265EQzvmRPijXZB
     * expiresTime : 1542855576339
     * expiresIn : 5183998
     * gender : 1
     * snsUserUrl : null
     */

    private String secretType;
    private String icon;
    private String userID;
    private String resume;
    private String nickname;
    private String token;
    private long expiresTime;
    private int expiresIn;
    private String gender;
    private String snsUserUrl;

    public static List<FBBean> arrayFBBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<FBBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getSecretType() {
        return secretType;
    }

    public void setSecretType(String secretType) {
        this.secretType = secretType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSnsUserUrl() {
        return snsUserUrl;
    }

    public void setSnsUserUrl(String snsUserUrl) {
        this.snsUserUrl = snsUserUrl;
    }
}
