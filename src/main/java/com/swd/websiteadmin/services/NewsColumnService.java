package com.swd.websiteadmin.services;

import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.NewsColumn;

import java.util.List;

public interface NewsColumnService {

    int insert(NewsColumn record);

    int updateByPrimaryKey(NewsColumn record);

    PageInfo<NewsColumn> selectAll(Integer currentPage,Integer pagenums);

    NewsColumn selectByPrimaryKey(Integer columnid);

    List<NewsColumn> selectAllForState();

}
