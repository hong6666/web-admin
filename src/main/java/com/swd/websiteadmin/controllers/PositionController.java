package com.swd.websiteadmin.controllers;

import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.Position;
import com.swd.websiteadmin.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ideagit
 * @Date: 2019/11/11 13:28
 * @Author: lhh
 * @Description:
 */
@RestController
@RequestMapping("/manage")
public class PositionController {

    @Autowired
    private PositionService positionService;



    @GetMapping("/position/{positionId}")
    public ResponseEntity<Position> SelectPosition(@PathVariable("positionId") Integer positionId){
        Position position = positionService.selectByPrimaryKey(positionId);
        if (position != null){
            return new ResponseEntity<>(position,HttpStatus.OK);
        }else {//返回空的数据到前台
            return new ResponseEntity<>(new Position(),HttpStatus.BAD_REQUEST);
        }
    }

    //前台分页显示全部岗位
    @GetMapping("/position/page/{pagesize}")
    public ModelAndView SelectForPage(@PathVariable("pagesize") Integer currentPage){
        PageInfo<Position> pageInfo = null;
        try{
            pageInfo = positionService.selectAllShow(currentPage);
        }catch (Exception e){
        }
        ModelAndView view = new ModelAndView();
        view.addObject("pageInfo",pageInfo);
        view.setViewName("recruit");
        return view;
    }

    //前台跳转详情页
    @GetMapping("/position/recruitdetals/{positionid}")
    public ModelAndView SelectRecruitDetals(@PathVariable("positionid") Integer positionId){
        Position position = positionService.selectByPrimaryKey(positionId);
        ModelAndView view = new ModelAndView();
        view.addObject("position",position);
        view.setViewName("recruitdetals");
        return view;
    }

}
