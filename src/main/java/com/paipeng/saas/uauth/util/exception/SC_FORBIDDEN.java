package com.paipeng.saas.uauth.util.exception;

/**
 * Created by david on 2017/5/8.
 */

/**
 * 403 访问别人公司没有权限
 */
public class SC_FORBIDDEN extends CustomException {
    public SC_FORBIDDEN() {
        super("403 SC_FORBIDDEN");
    }
}
