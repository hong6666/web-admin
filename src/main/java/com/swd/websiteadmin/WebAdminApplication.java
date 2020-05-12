package com.swd.websiteadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@MapperScan("com.swd.websiteadmin.dao")
@SpringBootApplication(scanBasePackages = {"com.swd.websiteadmin"})
public class WebAdminApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebAdminApplication.class);
    }
}
