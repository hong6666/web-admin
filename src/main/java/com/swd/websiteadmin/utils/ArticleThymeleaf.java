package com.swd.websiteadmin.utils;

import com.swd.websiteadmin.bean.NewsArticle;
import org.slf4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;

public class ArticleThymeleaf {

    public static Logger looger = LogUtil.looger(ImgUploadUtil.class);

    public static String createThymeleaf(String path, NewsArticle news){
        try{
            String filename = RandomUtil.RandomImgName();
            filename = filename+".html";
            //创建模版加载器
            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            resolver.setPrefix("templates/");  //模板文件的所在目录
            resolver.setSuffix(".html");       //模板文件后缀
            resolver.setCharacterEncoding("UTF-8");
            //创建模板引擎
            TemplateEngine templateEngine = new TemplateEngine();
            //将加载器放入模板引擎
            templateEngine.setTemplateResolver(resolver);
            //创建字符输出流并且自定义输出文件的位置和文件名
            FileWriter writer = new FileWriter(path+filename);
            //创建Context对象(存放Model)
            Context context = new Context();
            //放入数据
            /*context.setVariable("content",content);*/
            context.setVariable("news",news);
            //创建静态文件,"articleExample"是模板html名字
            templateEngine.process("articleExample",context,writer);
            return filename;
        }catch (IOException e){
            looger.error("自动生成页面出现异常:"+e.getMessage());
            return "";
        }catch (Exception e){
            looger.error("自动生成页面出现异常:"+e.getMessage());
            return "";
        }

    }
}
