package com.paipeng.saas.uauth.util.exception;

import javax.servlet.http.HttpServletResponse;

public class CustomExceptionUtil {
    public static int exceptionToResponseStatusCode(CustomException customException) {

        /**
         * 200
         */
        if (customException instanceof SC_OK) {
            return HttpServletResponse.SC_OK;
        }

        /**
         * 201
         */
        if (customException instanceof SC_CREATED) {
            return HttpServletResponse.SC_CREATED;
        }

        /**
         * 204
         */
        if (customException instanceof SC_NO_CONTENT) {
            return HttpServletResponse.SC_NO_CONTENT;
        }
        /**
         * 400入参不正确
         */
        if (customException instanceof SC_BAD_REQUEST) {
            return HttpServletResponse.SC_BAD_REQUEST;
        }

        /**
         * 401
         */
        if (customException instanceof SC_UNAUTHORIZED) {
            return HttpServletResponse.SC_UNAUTHORIZED;
        }

        /**
         * 402 SC_PAYMENT_REQUIRED
         */
        if (customException instanceof SC_PAYMENT_REQUIRED) {
            return HttpServletResponse.SC_PAYMENT_REQUIRED;
        }

        /**
         * 403
         */
        if (customException instanceof SC_FORBIDDEN) {
            return HttpServletResponse.SC_FORBIDDEN;
        }

        /**
         * 404
         */
        if (customException instanceof SC_NOT_FOUND) {
            return HttpServletResponse.SC_NOT_FOUND;
        }

        /**
         * 405
         */
        if (customException instanceof SC_SOFTWARE_ERROR) {
            return HttpServletResponse.SC_METHOD_NOT_ALLOWED;
        }

        /**
         * 406
         */
        if (customException instanceof SC_NOT_ACCEPTABLE) {
            return HttpServletResponse.SC_NOT_ACCEPTABLE;
        }

        /**
         * 409
         */
        if (customException instanceof SC_CONFLICT) {
            return HttpServletResponse.SC_CONFLICT;
        }

        /**
         * 420 用户名或者密码错误
         */
        if (customException instanceof SC_USERNAME_PASSWORD_ERROR) {
            return 420;
        }

        /**
         * 421 用户被锁定
         */
        if (customException instanceof SC_USER_LOCKED) {
            return 421;
        }

        /**
         * 422 无效用户
         */
        if (customException instanceof SC_USER_INVALID) {
            return 422;
        }

        /**
         * 423 验证码无效
         */
        if (customException instanceof SC_VERIFICATION_CODE_INVALID) {
            return 423;
        }

        /**
         * 424 请稍后再试
         */
        if (customException instanceof SC_PLEASE_TRY_AGAIN_LATER) {
            return 424;
        }

        /**
         * 425 验证码已过期
         */
        if (customException instanceof SC_VERIFICATION_CODE_EXPIRED) {
            return 425;
        }


        /**
         * 413 图片过大
         */
        if (customException instanceof SC_REQUEST_ENTITY_TOO_LARGE) {
            return HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE;
        }


        /**
         * 460 keystore check error
         */
        if (customException instanceof SC_KEYSTORE_CHECK_ERROR) {
            return 460;
        }

        /**
         * 461 shooting mode unavailable
         */
        if (customException instanceof SC_SHOOTING_MODE_UNAVAILABLE) {
            return 461;
        }

        /**
         * 462 qr mode unavailable
         */
        if (customException instanceof SC_QR_MODE_UNAVAILABLE) {
            return 462;
        }

        /**
         * 463 devcie unavailable
         */
        if (customException instanceof SC_DEVICE_UNKNOWN) {
            return 463;
        }

        /**
         * 464  smc length error
         */
        if (customException instanceof SC_SM4_ENCRYPT_DATA_LENGTH_INVALID) {
            return 464;
        }


        /**
         * 467 设备月UDID冲突
         */
        if (customException instanceof SC_DEVICE_UDID_ERROR) {
            return 467;
        }

        /**
         * 468 软件版本为空
         */
        if (customException instanceof SC_SOFTWARE_VERSION_UNKNOWN) {
            return 468;
        }

        /**
         * 469 用户所属公司未知
         */
        if (customException instanceof SC_USER_COMPANY_UNKNOWN) {
            return 469;
        }

        /**
         * 470 udid is null
         */
        if (customException instanceof SC_UDID_IS_NULL) {
            return 470;
        }

        /**
         * 471 base64String is null
         */
        if (customException instanceof SC_BASE64STRING_IS_NULL) {
            return 471;
        }

        /**
         * 472 check failure
         */
        if (customException instanceof SC_CHECK_FAILURE) {
            return 472;
        }

        /**
         * 473 black list
         */
        if (customException instanceof SC_BLACK_LIST) {
            return 473;
        }

        /**
         * 474 white list
         */
        if (customException instanceof SC_WHITE_LIST) {
            return 474;
        }

        /**
         * 475 Product parameter error
         */
        if (customException instanceof SC_PRODUCT_PARAMTER_ERROR) {
            return 475;
        }


        /**
         * 479 focusLegnth parameter error
         */
        if (customException instanceof SC_FOCUS_LENGTH_PARAMTER_ERROR) {
            return 479;
        }


        /**
         * 481 dpi invalid
         */
        if (customException instanceof SC_DPI_INVALID) {
            return 481;
        }

        /**
         * 482 save path error
         */
        if (customException instanceof SC_SAVE_PATH_ERROR) {
            return 482;
        }

        /**
         * 483 backage path error
         */
        if (customException instanceof SC_BACKAGE_PATH_ERROR) {
            return 483;
        }

        /**
         * 484 logo path error
         */
        if (customException instanceof SC_LOGO_PATH_ERROR) {
            return 484;
        }

        /**
         * 485 not support  bmp OS/2 header formate
         */
        if (customException instanceof SC_BMP_OS2_HEADER_FORMAT_ERROR) {
            return 485;
        }


        /**
         * 492  encode failure.
         */
        if (customException instanceof SC_ENCODE_FAILURE) {
            return 492;
        }

        /**
         * 493  decode failure.
         */
        if (customException instanceof SC_DECODE_FAILURE) {
            return 493;
        }

        /**
         * 494  server hostmane unknown.
         */
        if (customException instanceof SC_SERVER_HOSTNAME_UNKNOWN) {
            return 494;
        }


        /**
         * 496 发送邮件错误
         */
        if (customException instanceof SC_SEND_EMAIL_ERROR) {
            return 496;
        }

        /**
         * 497 编码异常
         */
        if (customException instanceof SC_ENCODING_EXCEPTION) {
            return 497;
        }

        /**
         * 498 发现未知公司
         */
        if (customException instanceof SC_COMPANY_UNKNOWN) {
            return 498;
        }

        /**
         * 499 已禁止登录
         */
        if (customException instanceof SC_LOGINS_DISABLED) {
            return 499;
        }

        /**
         * 500
         */
        if (customException instanceof SC_INTERNAL_SERVER_ERROR) {
            return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }

        /**
         * 503
         */
        if (customException instanceof SC_SERVICE_UNAVAILABLE) {
            return HttpServletResponse.SC_SERVICE_UNAVAILABLE;
        }

        /**
         * 520 SM4 encode error
         */
        if (customException instanceof SC_SM4_ENCRYPT_ERROR) {
            return 520;
        }

        /**
         * 521 SM4 encode error
         */
        if (customException instanceof SC_SM4_DECRYPT_ERROR) {
            return 521;
        }


        /**
         * 526 image save error
         */
        if (customException instanceof SC_IMAGE_SAVE_ERROR) {
            return 526;
        }

        /**
         * 527 image read error
         */
        if (customException instanceof SC_IMAGE_READ_ERROR) {
            return 527;
        }

        /**
         * 556 Image format not supported
         */
        if (customException instanceof SC_IMAGE_FORMAT_INVALID) {
            return 556;
        }

        return 0;
    }
}
