package com.swd.websiteadmin.services;

import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.Position;

import java.util.List;

/**
 * @program: ideagit
 * @Date: 2019/11/11 13:30
 * @Author: lhh
 * @Description:
 */

public interface PositionService {

    PageInfo<Position> selectAll(Integer currentPage);

    List<Position> selectAllPosition();

    Position selectByPrimaryKey(Integer positionid);

    int insert(Position record);

    int updateByPrimaryKey(Position record);

    PageInfo<Position> selectAllShow(Integer currentPage);

    PageInfo<Position> selectByNameOrWorkPlace(String nameorworkplace,Integer currentPage);

    int selectSort();

    PageInfo<Position> selectByColumnId(Integer columnid,Integer currentPage);

}
