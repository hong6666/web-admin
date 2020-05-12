package com.swd.websiteadmin.services.servicesImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.PositionColumn;
import com.swd.websiteadmin.dao.PositionColumnMapper;
import com.swd.websiteadmin.services.PositionColumnService;
import com.swd.websiteadmin.services.PositionService;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: web-admin
 * @Date: 2019/12/9 9:22
 * @Author: lhh
 * @Description:
 */
@Service
public class PositionColumnServiceImpl implements PositionColumnService {
    private Logger logger = LogUtil.looger(this.getClass());
    @Autowired
    private PositionColumnMapper positionColumnMapper;
    @Override
    public int insert(PositionColumn record) {
        if (record != null){
            try {
                int result = positionColumnMapper.insert(record);
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
    public int updateByPrimaryKey(PositionColumn record) {
        if (record != null){
            try {
                int result = positionColumnMapper.updateByPrimaryKey(record);
                logger.info("修改完成，结果："+result);
                return result;
            }catch (Exception e){
                logger.error("修改出现异常:"+e.getMessage());
                return -1;
            }
        }else {
            logger.error("传入到service的对象为空");
            return -1;
        }
    }

    @Override
    public PositionColumn selectByPrimaryKey(Integer columnid) {
        if(columnid >= 1){
            return positionColumnMapper.selectByPrimaryKey(columnid);
        }else{
            logger.error("查询失败，传入的id不合法");
            return null;
        }
    }

    @Override
    public PageInfo<PositionColumn> selectAll(Integer currentPage) {
        PageHelper.startPage(currentPage,10,"columnId asc");//下面紧跟查询语句
        List<PositionColumn> positions = positionColumnMapper.selectAll();
        PageInfo<PositionColumn> pageInfo = new PageInfo<>(positions);
        return pageInfo;
    }

    @Override
    public List<PositionColumn> selectAllPositionColumn() {
        return positionColumnMapper.selectAllShow();
    }

}
