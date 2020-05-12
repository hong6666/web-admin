package com.swd.websiteadmin.dao;

import com.swd.websiteadmin.bean.Position;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PositionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer positionid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbggenerated
     */
    int insert(Position record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbggenerated
     */
    Position selectByPrimaryKey(Integer positionid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbggenerated
     */
    List<Position> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Position record);

    List<Position> selectAllShow();

    List<Position> selectByNameOrWorkPlace(String nameorworkplace);

    int selectSort();

    List<Position> selectByColumnId(Integer columnid);
}