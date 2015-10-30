package com.meetu.cloud.callback;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

import java.util.List;

/**
 * Created by mac on 15/9/14.
 */
public interface ObjFunObjectsCallback {
    void callback(List<AVObject> objects, AVException e);
}
