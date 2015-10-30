package com.meetu.cloud.object;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;

/**
 * Created by mac on 15/8/14.
 */
public class ObjUser extends AVUser{

    //用户类型
    private int userType;
    //用户是否是Vip用户
    private boolean  isVipUser;
    //用户是否已完成验证
    private boolean  isVerifyUser;
    //用户是否已完善个人信息
    private boolean  isCompleteUserInfo;
    //昵称
    private String nameNick;
    //真实名称
    private String nameReal;
    //性别
    private int gender;
    //生日
    private long birthday;
    //星座
    private String constellation;
    //学校
    private String school;
    //学校所在地编码
    private String schoolLocation;
    //专业编号
    private int majorNum;
    //专业名称
    private String majorStr;
    //专业分类编号
    private int majorClaNum;
    //专业分类名称
    private String majorClaStr;
    //家乡
    private String hometown;
    //切圆后的头像
    private AVFile profileClip;
    //原始头像
    private AVFile profileOrign;

    public ObjUser(){}

    public static String objName(){
        return "_User";
    }

    public int getUserType() {
        return this.getInt("userType");
    }

    public void setUserType(int userType) {
        this.put("userType", userType);
    }

    public boolean isVipUser() {
        return this.getBoolean("isVipUser");
    }

    public void setIsVipUser(boolean isVipUser) {
        this.put("isVipUser", isVipUser);
    }

    public boolean isVerifyUser() {
        return this.getBoolean("isVerifyUser");
    }

    public void setIsVerifyUser(boolean isVerifyUser) {
        this.put("isVerifyUser", isVerifyUser);
    }

    public boolean isCompleteUserInfo() {
        return this.getBoolean("isCompleteUserInfo");
    }

    public void setIsCompleteUserInfo(boolean isCompleteUserInfo) {
        this.put("isCompleteUserInfo", isCompleteUserInfo);
    }

    public String getNameNick() {
        return this.getString("nameNick");
    }

    public void setNameNick(String nameNick) {
        this.put("nameNick", nameNick);
    }

    public String getNameReal() {
        return this.getString("nameReal");
    }

    public void setNameReal(String nameReal) {
        this.put("nameReal", nameReal);
    }

    public int getGender() {
        return this.getInt("gender");
    }

    public void setGender(int gender) {
        this.put("gender", gender);
    }

    public long getBirthday() {
        return this.getLong("birthday");
    }

    public void setBirthday(long birthday) {
        this.put("birthday", birthday);
    }

    public String getConstellation() {
        return this.getString("constellation");
    }

    public void setConstellation(String constellation) {
        this.put("constellation", constellation);
    }

    public String getSchool() {
        return this.getString("school");
    }

    public void setSchool(String school) {
        this.put("school", school);
    }

    public String getSchoolLocation() {
        return this.getString("schoolLocation");
    }

    public void setSchoolLocation(String schoolLocation) {
        this.put("schoolLocation", schoolLocation);
    }

    public int getMajorNum() {
        return this.getInt("majorNum");
    }

    public void setMajorNum(int majorNum) {
        this.put("majorNum", majorNum);
    }

    public String getMajorStr() {
        return this.getString("majorStr");
    }

    public void setMajorStr(String majorStr) {
        this.put("majorStr", majorStr);
    }

    public int getMajorClaNum() {
        return this.getInt("majorClaNum");
    }

    public void setMajorClaNum(int majorClaNum) {
        this.put("majorClaNum", majorClaNum);
    }

    public String getMajorClaStr() {
        return this.getString("majorClaStr");
    }

    public void setMajorClaStr(String majorClaStr) {
        this.put("majorClaStr", majorClaStr);
    }

    public String getHometown() {
        return this.getString("hometown");
    }

    public void setHometown(String hometown) {
        this.put("hometown", hometown);
    }

    public AVFile getProfileClip() {
        return this.getAVFile("profileClip");
    }

    public void setProfileClip(AVFile profileClip) {
        this.put("profileClip", profileClip);
    }

    public AVFile getProfileOrign() {
        return this.getAVFile("profileOrign");
    }

    public void setProfileOrign(AVFile profileOrign) {
        this.put("profileOrign", profileOrign);
    }
}
