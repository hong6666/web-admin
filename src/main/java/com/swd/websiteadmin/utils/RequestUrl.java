package com.swd.websiteadmin.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RequestUrl {

    @Value("${requestUrl}")
    public String requestUrl;
}
