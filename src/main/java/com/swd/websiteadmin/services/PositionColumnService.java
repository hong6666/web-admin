package com.swd.websiteadmin.services;


import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.PositionColumn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionColumnService {

    int insert(PositionColumn record);

    int updateByPrimaryKey(PositionColumn record);

    PositionColumn selectByPrimaryKey(Integer columnid);

    PageInfo<PositionColumn> selectAll(Integer currentPage);

    List<PositionColumn> selectAllPositionColumn();
}
