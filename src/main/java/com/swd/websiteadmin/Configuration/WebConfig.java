package com.swd.websiteadmin.Configuration;


import com.swd.websiteadmin.utils.UploadPathConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private UploadPathConfig pathConfig;//路径配置文件

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:"+pathConfig.rootPath);

        registry.addResourceHandler("/brand/**")
                .addResourceLocations("file:"+pathConfig.thymeleafPath);

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    //登陆校验拦截器，不开则代表不校验
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/manage/**").excludePathPatterns("/manage").excludePathPatterns("/manage/login");
        super.addInterceptors(registry);
    }
}
