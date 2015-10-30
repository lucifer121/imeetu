package com.meetu.cloud.callback;

import com.avos.avoscloud.AVException;
import com.meetu.cloud.wrap.ObjExecResult;

/**
 * Created by mac on 15/9/14.
 */
public interface ObjFunEnumCallback {
    void callback(ObjExecResult result, AVException e);
}
