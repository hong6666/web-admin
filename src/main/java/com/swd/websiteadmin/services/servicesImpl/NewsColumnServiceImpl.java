package com.swd.websiteadmin.services.servicesImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.NewsColumn;
import com.swd.websiteadmin.dao.NewsArticleMapper;
import com.swd.websiteadmin.dao.NewsColumnMapper;
import com.swd.websiteadmin.services.NewsColumnService;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsColumnServiceImpl implements NewsColumnService {

    private Logger logger = LogUtil.looger(this.getClass());
    @Autowired
    private NewsColumnMapper newsColumnMapper;
    @Override
    public int insert(NewsColumn record) {
        if (record != null){
            try {
                int result = newsColumnMapper.insert(record);
                logger.info("插入完成，结果："+result);
                return result;
            }catch (Exception e){
                logger.error("插入出现异常:"+e.getMessage());
                return -1;
            }
        }else {
            logger.error("传入到service的对象为空");
            return -1;
        }

    }

    @Override
    public int updateByPrimaryKey(NewsColumn record) {
        if (record != null && record.getColumnid() != null){
            try {
                int result = newsColumnMapper.updateByPrimaryKey(record);
                logger.info("修改完成，结果："+result);
                return result;
            }catch (Exception e){
                logger.error("修改出现异常:"+e.getMessage());
                return -1;
            }
        }else {
            logger.error("传入到service的对象或id为空");
            return -1;
        }
    }

    @Override
    public PageInfo<NewsColumn> selectAll(Integer currentPage,Integer pagenums) {
        try {
            PageHelper.startPage( currentPage, pagenums);
            List<NewsColumn> newsColumns = newsColumnMapper.selectAll();
            PageInfo<NewsColumn> pageInfo = new PageInfo<>(newsColumns);
            return pageInfo;
        }catch (Exception e){
            logger.error("查询列表出现异常:"+e.getMessage());
            return null;
        }
    }

    @Override
    public NewsColumn selectByPrimaryKey(Integer columnid) {
        return newsColumnMapper.selectByPrimaryKey(columnid);
    }

    @Override
    public List<NewsColumn> selectAllForState() {
        return newsColumnMapper.selectAllForState();
    }


}
