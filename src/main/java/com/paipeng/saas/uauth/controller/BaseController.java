package com.paipeng.saas.uauth.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {
    protected static Logger logger;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public BaseController() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
        logger.trace("BaseController: " + this.getClass().getSimpleName());
        if ((RequestContextHolder.getRequestAttributes()) != null) {
            response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        } else {
            logger.trace("BaseController: getRequestAttributes");
        }
    }
}
