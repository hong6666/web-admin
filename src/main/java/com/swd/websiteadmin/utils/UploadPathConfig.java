package com.swd.websiteadmin.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
public class UploadPathConfig {

    @Value("${upload.rootPath}")
    public  String rootPath;

    @Value("${upload.thymeleafpath}")
    public  String thymeleafPath;

    @Value("${upload.thumImgPath}")
    public  String thumImgPath;

    @Value("${upload.brandArticle}")
    public  String brandArticlePath;

}
