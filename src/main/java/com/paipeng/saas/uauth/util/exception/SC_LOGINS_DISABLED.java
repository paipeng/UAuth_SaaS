package com.paipeng.saas.uauth.util.exception;

/**
 * 499 已禁止登录到此服务器。
 */
public class SC_LOGINS_DISABLED extends CustomException {
    public SC_LOGINS_DISABLED() {
        super("499 SC_LOGINS_DISABLED");
    }
}
