package com.meetu.cloud.wrap;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.avos.avoscloud.UpdatePasswordCallback;
import com.meetu.cloud.callback.ObjFunBooleanCallback;
import com.meetu.cloud.callback.ObjFunEnumCallback;
import com.meetu.cloud.callback.ObjFunObjectCallback;
import com.meetu.cloud.object.ObjUser;


/**
 * Created by mac on 15/9/9.
 */
public class ObjUserWrap {



    /**检查此手机号码是否已经注册
     *
     * @param phone 检查的手机号码
     * @param callback 回调对象
     */
    public static void phoneIsAlreadyRegister(String phone, final ObjFunEnumCallback callback){
        if (callback != null){
            AVQuery<AVObject> query = new AVQuery<AVObject>(ObjUser.objName());
            log.e("00",ObjUser.objName());
            query.whereEqualTo("mobilePhoneNumber", phone);
            log.e("0", " shoujihao "+phone);
            query.getFirstInBackground(new GetCallback<AVObject>() {
                @Override
                public void done(AVObject object, AVException e) {
                	log.e("1", "123");
                    if (object != null){
                        callback.callback(ObjExecResult.EXEC_TRUE, null);
                        log.e("2", "123");
                    }else {
                        if (e.getCode() == 101){
                            callback.callback(ObjExecResult.EXEC_FALSE, null);
                            log.e("3", "123");
                        }else {
                            callback.callback(ObjExecResult.EXEC_EXCEPTION, e);
                            log.e("4", "123");
                            log.e("4",e+"  "+e.getCode());
                           
                        }
                    }
                }
            });
        }
    }

    /**
     * 用户登录
     *
     * @param phone 登录手机号
     * @param passwd 登录密码
     * @param callback 回调对象
     */
    public static void login(String phone, String passwd, final ObjFunObjectCallback callback){
        if (callback != null){
            ObjUser.loginByMobilePhoneNumberInBackground(phone, passwd, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if (avUser != null){
                        callback.callback(avUser, null);
                    }else {
                        if (e.getCode() == 210) {
                            callback.callback(null, new AVException(1, "密码不正确"));
                        }else {
                            callback.callback(null, new AVException(2, "用户不存在"));
                        }
                    }
                }
            });
        }
    }

    /**
     * 用户注册
     * @param phone 手机号码
     * @param passwd 初始密码
     * @param smsCode 短信验证码
     * @param callback 回调对象
     *
     *
     */
    public static void register(final String phone, final String passwd, String smsCode, final ObjFunBooleanCallback callback){
        if (callback != null){
            AVOSCloud.verifySMSCodeInBackground(smsCode, phone, new AVMobilePhoneVerifyCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null){
                        ObjUser user = new ObjUser();

                        user.setUsername(ObjWrap.createId());
                        user.setMobilePhoneNumber(phone);
                        user.setPassword(passwd);

                        user.setUserType(0);
                        user.setIsVipUser(false);
                        user.setIsVerifyUser(false);
                        user.setIsCompleteUserInfo(false);

                        user.setFetchWhenSave(true);

                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null){
                                    callback.callback(true, null);
                                }else {
                                	log.e("注册失败异常", e.toString() +" "+e.getCode());
                                	
                                    callback.callback(false, new AVException(1, "注册失败"));
                                }
                            }
                        });
                    }else {
                        callback.callback(false, new AVException(0, "短信验证错误"));
                    }
                }
            });
        }
    }


    /**
     * 完善个人信息
     *
     * @param user 用户对象
     * @param callback 回调对象
     */
    public static void completeUserInfo(ObjUser user, final ObjFunBooleanCallback callback){
        if (callback != null){
            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null){
                        callback.callback(true, null);
                    }else {
                        callback.callback(false, null);
                    }
                }
            });
        }
    }

    /**
     * 获取重置密码短信
     * @param phone 手机号码
     * @param callback 回调对象
     */
    public static void requestSmsCodeForResetPasswd(String phone, final ObjFunBooleanCallback callback){
        if (callback != null){
            ObjUser.requestPasswordResetBySmsCodeInBackground(phone, new RequestMobileCodeCallback() {
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

    /**
     *
     * @param smsCode 短信验证码
     * @param newPasswd 新密码
     * @param callback 回调对象
     */
    public static void resetPasswd(String smsCode, String newPasswd, final ObjFunBooleanCallback callback){
        if (callback != null){
            ObjUser.resetPasswordBySmsCodeInBackground(smsCode, newPasswd, new UpdatePasswordCallback() {
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
