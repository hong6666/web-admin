package com.swd.websiteadmin.services;

import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.NewsArticle;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface NewsAtricleService {

    NewsArticle selectByPrimaryKey(String articleid);

    PageInfo<NewsArticle> selectAll(Integer currentPage);

    int updateByPrimaryKey(NewsArticle record);

    int insert(NewsArticle record);

    String CreateArticle(MultipartFile file, HttpServletRequest req) throws IOException;

    PageInfo<NewsArticle> selectForStateAndColumnid(Integer currentPage,Integer pagenums,Integer columnid);

    int selectSort();

    String ReCreateArticle(String newsId,HttpServletRequest req);


}
