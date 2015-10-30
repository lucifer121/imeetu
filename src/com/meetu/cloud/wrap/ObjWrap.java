package com.meetu.cloud.wrap;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.meetu.cloud.callback.ObjFunBooleanCallback;
import com.meetu.cloud.object.ObjUser;


import java.util.Random;

/**
 * Created by mac on 15/9/14.
 */
public class ObjWrap {

    /**
     * 获取随机ID值
     *
     * @return 生成的ID值
     */
    public static String createId(){
        StringBuffer id = new StringBuffer();
        Random random=new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            id.append(c);
        }
        id.append(System.currentTimeMillis());

        return id.toString();
    }

    /**
     * 获取短信验证码
     *
     * @param phone 电话号码
     * @param callback 回调对象
     */
    public static void requestSmsCode(String phone, final ObjFunBooleanCallback callback){
        if (callback != null){
        	

        	AVOSCloud.requestSMSCodeInBackground(phone, "MeetU", "注册", 1, new RequestMobileCodeCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        callback.callback(true, null);
                    } else {
                        callback.callback(false, e);
                    }
                }
            });
        }
    }

    /**
     * 验证短信验证码
     *
     * @param phone 手机号码
     * @param smsCode 验证码
     * @param callback 回调对象
     */
    public static void verifySmsCode(String phone, String smsCode, final ObjFunBooleanCallback callback){
        if (callback != null){
            AVOSCloud.verifySMSCodeInBackground(smsCode, phone, new AVMobilePhoneVerifyCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null){
                        callback.callback(true, null);
                    }else {
                        callback.callback(false, e);
                    }
                }
            });
        }
    }
}
