package com.meetu.cloud.callback;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

/**
 * Created by mac on 15/9/14.
 */
public interface ObjFunObjectCallback {
    void callback(AVObject object, AVException e);
}
