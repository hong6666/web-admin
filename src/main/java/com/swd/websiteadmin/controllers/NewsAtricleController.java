package com.swd.websiteadmin.controllers;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.NewsArticle;
import com.swd.websiteadmin.services.NewsAtricleService;
import com.swd.websiteadmin.utils.ImgUploadUtil;
import com.swd.websiteadmin.utils.LogUtil;
import com.swd.websiteadmin.utils.RequestUrl;
import com.swd.websiteadmin.utils.UploadPathConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("")
public class NewsAtricleController {

    @Autowired
    private NewsAtricleService atricleService;

    @Autowired
    private NewsArticle newsArticle;

    @Autowired
    private UploadPathConfig pathConfig;//路径配置文件

    private Logger logger = LogUtil.looger(this.getClass());

    @Autowired
    private RequestUrl requestUrl;

    @GetMapping("article/{articleid}")
    public ResponseEntity<NewsArticle> SelectAtricle(@PathVariable("articleid") String articleid){
        NewsArticle article = atricleService.selectByPrimaryKey(articleid);
        if (article != null){
            return new ResponseEntity<>(article, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new NewsArticle(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/manage/article/{articleid}")
    public ResponseEntity<NewsArticle> SelectAtricleForId(@PathVariable("articleid") String articleid){
        NewsArticle article = atricleService.selectByPrimaryKey(articleid);
        if (article != null){
            return new ResponseEntity<>(article, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new NewsArticle(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/manage/article")
    public ResponseEntity<?> InsertAtricle(@RequestBody NewsArticle article){
        int result = atricleService.insert(article);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("添加成功",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("添加失败",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/manage/article")
    public ResponseEntity<?> UpdateAtricle( MultipartFile file, HttpServletRequest req){
        if(file != null){
            try {
                String thumUrl = ImgUploadUtil.UploadImgThum(file, req, pathConfig.rootPath, pathConfig.thumImgPath,requestUrl);
                newsArticle.setThumbnail(thumUrl);
            } catch (IOException e) {
                logger.error("更新缩略图失败:"+e.getMessage());
                return new ResponseEntity<>("更新缩略图失败:"+e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        String articletitle = req.getParameter("articletitle");//文章标题
        String columnid = req.getParameter("columnid");//文章分类
        String articlecontext = req.getParameter("articlecontext");//文章正文
        String state = req.getParameter("state");//文章状态
        String articlesort = req.getParameter("articlesort");//文章排序
        String articleinfo = req.getParameter("articleinfo");//文章简介
        String articleid = req.getParameter("articleid");//文章简介
        String newsDate = req.getParameter("createtime");
        if(newsDate != null && !"".equals(newsDate)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parse = null;
            try {
                parse= simpleDateFormat.parse(newsDate);
            } catch (ParseException e) {
                logger.error("转换字符串成date错误");
                e.printStackTrace();
            }
            newsArticle.setCreatetime(parse);
        }
        newsArticle.setIntroduction(articleinfo);
        newsArticle.setArticlesort(Integer.parseInt(articlesort));
        newsArticle.setColumnid(Integer.parseInt(columnid));
        newsArticle.setState(Integer.parseInt(state));
        newsArticle.setArticlecontext(articlecontext);
        newsArticle.setArticleid(articleid);
        newsArticle.setArticletitle(articletitle);

        int result = atricleService.updateByPrimaryKey(newsArticle);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("修改成功",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("修改失败",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/manage/article/page/{pagesize}")
    public ResponseEntity<?> SelectForPage(@PathVariable("pagesize") Integer currentPage){
        try {
            PageInfo<NewsArticle> pageInfo = atricleService.selectAll(currentPage);
            return new ResponseEntity<>(pageInfo,HttpStatus.OK);
        }catch (Exception e){
         return new ResponseEntity<>(new PageInfo<>(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/manage/article/thymeleaf")
    public ResponseEntity<String> CreateArticle(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        try {
            String result = atricleService.CreateArticle(file, req);
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("发布异常"+e.getMessage(),HttpStatus.OK);
        }
    }

    @GetMapping("/manage/article/thymeleaf/{newsId}")
    public ResponseEntity<String> ReCreate(@PathVariable("newsId") String newsId,HttpServletRequest req){
        if (newsId != null && !"".equals(newsId)){
            String result = atricleService.ReCreateArticle(newsId, req);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("文章id查询异常",HttpStatus.OK);
        }
    }


}
