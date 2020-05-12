package com.swd.websiteadmin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    public static Logger logger;

    public static Logger looger(Class<?> class1){
        logger  = LoggerFactory.getLogger(class1);
        return logger;
    }
}
