package com.swd.websiteadmin.Configuration;

import com.swd.websiteadmin.bean.NewsArticle;
import com.swd.websiteadmin.bean.NewsArticlePictures;
import com.swd.websiteadmin.bean.ProductPictures;
import com.swd.websiteadmin.bean.UeditorImage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {

    @Bean
    @Scope("prototype")
    public ProductPictures productPictures(){
        return new ProductPictures();
    }

    @Bean
    @Scope("prototype")
    public NewsArticlePictures articlePictures(){
        return new NewsArticlePictures();
    }

    @Bean
    @Scope("prototype")
    public UeditorImage ueditorImage(){
        return new UeditorImage();
    }

    @Bean
    @Scope("prototype")
    public NewsArticle newsArticle(){
        return new NewsArticle();
    }
}
