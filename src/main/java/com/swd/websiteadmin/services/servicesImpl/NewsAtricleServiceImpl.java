package com.swd.websiteadmin.services.servicesImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.NewsArticle;
import com.swd.websiteadmin.dao.NewsArticleMapper;
import com.swd.websiteadmin.services.NewsAtricleService;
import com.swd.websiteadmin.utils.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NewsAtricleServiceImpl implements NewsAtricleService {

    @Autowired
    private UploadPathConfig pathConfig;//路径配置文件

    @Autowired
    private NewsArticle newsArticle;

    @Autowired
    RedisTemplate redisTemplate;

    private Logger logger = LogUtil.looger(this.getClass());

    @Autowired
    private NewsArticleMapper articleMapper;

    @Autowired
    private RequestUrl requestUrl;

    @Override
    public NewsArticle selectByPrimaryKey(String articleid) {
        if (articleid != null && !"".equals(articleid)){
            return articleMapper.selectByPrimaryKey(articleid);
        }else {
            logger.info("传入到selectByPrimaryKey的id为空。");
            return null;
        }

    }

    //后台展示列表（查询所有）
    @Override
    public PageInfo<NewsArticle> selectAll(Integer currentPage) {
        PageHelper.startPage(currentPage,10,"articleSort desc,createTime desc");//下面紧跟查询语句
        List<NewsArticle> articles = articleMapper.selectAll();//分页后要紧跟着查询语句，
        PageInfo<NewsArticle> pageInfo = new PageInfo<>(articles);//返回页面的信息。
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKey(NewsArticle record) {
        if (record != null && !"".equals(record.getArticleid())){
            try{
                int result = articleMapper.updateByPrimaryKey(record);
                if (result == 0){
                    logger.info("插入不成功，结果:"+result);
                    return result;
                }
                return result;
            }catch (Exception e){
                logger.error("更新文章出现异常:"+e.getMessage());
                return -1;
            }
        }
        else {
            logger.info("传入到NewsAtricleServiceImpl的对象为空或者id为空");
            return -1;
        }
    }

    @Override
    public int insert(NewsArticle record) {
        if (record != null ){
            try{
                int result = articleMapper.insert(record);
                if (result == 0){
                    logger.info("插入不成功，结果:"+result);
                    return result;
                }
                return result;
            }catch (Exception e){
                logger.error("插入文章出现异常:"+e.getMessage());
                return -1;
            }
        }
        else {
            logger.info("传入到NewsAtricleServiceImpl的对象为空");
            return -1;
        }
    }

    //创建文章
    @Override
    public String CreateArticle(MultipartFile file, HttpServletRequest req) throws IOException {
        String thumUrl = ImgUploadUtil.UploadImgThum(file, req, pathConfig.rootPath,pathConfig.thumImgPath,requestUrl);//存放缩略图
        logger.info("文章缩略图地址："+thumUrl);
        String atricleId = RandomUtil.RandomImgName();//文章id
        String articletitle = req.getParameter("articletitle");//文章标题
        String columnid = req.getParameter("columnid");//文章分类
        String articlecontext = req.getParameter("articlecontext");//文章正文
        String state = req.getParameter("state");//文章状态
        String articlesort = req.getParameter("articlesort");//文章排序
        if (articlesort.equals("")){//页面中不填排序的话，默认为1
            articlesort = "1";
        }
        String articleinfo = req.getParameter("articleinfo");//文章简介
        newsArticle.setArticletitle(articletitle);
        newsArticle.setArticlecontext(articlecontext);
        newsArticle.setState(Integer.parseInt(state));
        newsArticle.setColumnid(Integer.parseInt(columnid));
        newsArticle.setArticlesort(Integer.parseInt(articlesort));
        newsArticle.setThumbnail(thumUrl);
        newsArticle.setCreatetime(new Date());
        newsArticle.setIntroduction(articleinfo);
        newsArticle.setArticleid(atricleId);
        //获取文章名字
        String articleName = ArticleThymeleaf.createThymeleaf(pathConfig.thymeleafPath,newsArticle);
        if (articleName.equals("")){
            logger.info("重新生成失败");
            return "重新生成失败";
        }
        String articleUrl = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/brand/"+articleName;
        if(requestUrl.requestUrl != null && !"".equals(requestUrl.requestUrl)){
            articleUrl = req.getScheme()+"://"+requestUrl.requestUrl+"/brand/"+articleName;
        }
        logger.info("发布后生成的文章地址:"+articleUrl);
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String data = dateFormat.format(new Date());//创建文章时间
        newsArticle.setArticleurl(articleUrl);
        logger.info("开始插入文章");
        int result = insert(newsArticle);
        if (result != -1 && result != 0){
            logger.info("发布成功");
            return "发布成功";
        }else {
            logger.info("发布失败");
            return "发布失败";
        }
    }

    //前台展示列表（查询状态为发布）
    @Override
    public PageInfo<NewsArticle> selectForStateAndColumnid(Integer currentPage,Integer pagenums,Integer columnid) {

            PageHelper.startPage(currentPage,pagenums,"articleSort desc,createTime desc");//下面紧跟查询语句
            List<NewsArticle> articles = articleMapper.selectForStateAndColumnid(columnid);//分页后要紧跟着查询语句，
            PageInfo<NewsArticle> pageInfo = new PageInfo<>(articles);//返回页面的信息。

            return pageInfo;


    }

    @Override
    public int selectSort() {//获取当前最大排序
        int result = 0;
        try{
             result = articleMapper.selectSort();
             return result;
        }catch (Exception e){
            return result;
        }
    }

    @Override
    public String ReCreateArticle(String newsId,HttpServletRequest req) {
        NewsArticle news = selectByPrimaryKey(newsId);
        String oldUrl = news.getArticleurl();
        oldUrl = oldUrl.substring(oldUrl.lastIndexOf("/")+1);//从虚拟路径中截取出旧文件名
        String newUrl = pathConfig.thymeleafPath + oldUrl;//拼接成物理文件路径。接下来判断文件是否存在
        File file = new File(newUrl);
        if (file.exists()){//如果文件存在，则直接返回错误信息（文件已存在，请勿重复发布）
            return "文件已存在，请勿重复发布";
        }//如果文件不存在，则重新调用创建一个新的文章页面，然后将地址返回给news对象
        try {
            String articleName = ArticleThymeleaf.createThymeleaf(pathConfig.thymeleafPath,news);
            if (articleName.equals("")){
                logger.info("重新生成失败");
                return "重新生成失败";
            }
            String articleUrl = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/brand/"+articleName;
            if(requestUrl.requestUrl != null && !"".equals(requestUrl.requestUrl)){
                articleUrl = req.getScheme()+"://"+requestUrl.requestUrl+"/brand/"+articleName;
            }
            news.setArticleurl(articleUrl);
            int result = updateByPrimaryKey(news);
            if (result == 0 || result == -1){
                return "重新发布失败";
            }
            return "重新发布成功";
        }catch (Exception e){
            logger.error(e.getMessage());
            return "重新发布异常："+e.getMessage();
        }
    }

}
