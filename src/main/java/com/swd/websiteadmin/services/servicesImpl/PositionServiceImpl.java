package com.swd.websiteadmin.services.servicesImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.Position;
import com.swd.websiteadmin.dao.PositionMapper;
import com.swd.websiteadmin.services.PositionService;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: ideagit
 * @Date: 2019/11/11 13:38
 * @Author: lhh
 * @Description:
 */
@Service
public class PositionServiceImpl implements PositionService {

    private Logger logger = LogUtil.looger(this.getClass());

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public PageInfo<Position> selectAll(Integer currentPage) {
        PageHelper.startPage(currentPage,10,"positionSort desc");//下面紧跟查询语句
        List<Position> positions = positionMapper.selectAll();
        PageInfo<Position> pageInfo = new PageInfo<>(positions);
        return pageInfo;
    }

    @Override
    public List<Position> selectAllPosition() {
        return positionMapper.selectAll();
    }

    @Override
    public Position selectByPrimaryKey(Integer positionid) {
        if(positionid >= 1){
            return positionMapper.selectByPrimaryKey(positionid);
        }else{
            logger.error("查询失败，传入的id不合法");
            return null;
        }

    }

    @Override
    public int insert(Position record) {
        try{
            if(record != null){
                record.setCreatetime(new Date());
                int result = positionMapper.insert(record);
                logger.info("插入position: "+result);
                return result;
            }else{
                logger.info("传入到service的Position对象为空");
                return -1;
            }
        }catch(Exception e){
            logger.error("插入失败："+e.getMessage());
            return -1;
        }
    }

    @Override
    public int updateByPrimaryKey(Position record) {
        try{
            if(record != null && record.getPositionid() != null && record.getPositionid() != 0){
                int result=positionMapper.updateByPrimaryKey(record);
                logger.info("更新完成，更新结果："+result);
                return result;
            }
        }catch(Exception e){
            logger.error("更新失败:"+e.getMessage());
            return -1;
        }
        logger.info("传入到updateByPrimaryKey的Position对象为空");
        return 0;
    }

    @Override
    public PageInfo<Position> selectAllShow(Integer currentPage) {
        PageHelper.startPage(currentPage,10,"positionSort desc");//下面紧跟查询语句
        List<Position> positions = positionMapper.selectAllShow();
        PageInfo<Position> pageInfo = new PageInfo<>(positions);
        return pageInfo;
    }


    @Override
    /**
     * 查询后的分页
     */
    public PageInfo<Position> selectByNameOrWorkPlace(String nameorworkplace,Integer currentPage) {
        PageHelper.startPage(currentPage,10,"positionSort desc");//下面紧跟查询语句
        List<Position> positions = positionMapper.selectByNameOrWorkPlace(nameorworkplace);
        PageInfo<Position> pageInfo = new PageInfo<>(positions);
        return pageInfo;
    }

    @Override
    public int selectSort() {
        int result = 0;
        try {
            result =  positionMapper.selectSort();
            return result;
        }catch (Exception e){
            return result;
        }
    }

    @Override
    public PageInfo<Position> selectByColumnId(Integer columnid,Integer currentPage) {
        PageHelper.startPage(currentPage,10,"positionSort desc");//下面紧跟查询语句
        List<Position> positions = positionMapper.selectByColumnId(columnid);
        PageInfo<Position> pageInfo = new PageInfo<>(positions);
        return pageInfo;
    }
}
