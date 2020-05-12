package com.swd.websiteadmin.controllers;

import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.*;
import com.swd.websiteadmin.services.*;
import com.swd.websiteadmin.utils.ImgUploadUtil;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HeaderController {

    @Autowired
    private NewsAtricleService atricleService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PublicInfoService publicInfoService;

    @Autowired
    private com.swd.websiteadmin.services.ProductPictrueService ProductPictrueService;

    @Autowired
    private ProductSkusService productSkusService;

    @Autowired
    private PositionColumnService positionColumnService;

    public static Logger looger = LogUtil.looger(ImgUploadUtil.class);

    @GetMapping("/newsIndex")
    public ModelAndView NewsIndex(){//新闻首页
        ModelAndView view = new ModelAndView();
        view.setViewName("newsIndex");
        PageInfo<NewsArticle> pageInfo = atricleService.selectForStateAndColumnid(1,4,1);//默认查第1~10条数据
        PageInfo<NewsArticle> pageInfo1 = atricleService.selectForStateAndColumnid(1,4,2);//默认查第1~10条数据
        PageInfo<NewsArticle> pageInfo2 = atricleService.selectForStateAndColumnid(1,4,3);//默认查第1~10条数据
        view.addObject("pageInfo",pageInfo);
        view.addObject("pageInfo1",pageInfo1);
        view.addObject("pageInfo2",pageInfo2);
        return view;
    }

    @GetMapping("/about")
    public ModelAndView About(){//企业介绍
        ModelAndView view = new ModelAndView();
        view.setViewName("about");
        return view;
    }

    @GetMapping("/newsList/{current}")
    public ModelAndView NewsList(@PathVariable("current") Integer current){//品牌动态
        ModelAndView view = new ModelAndView();
        view.setViewName("newsList");
        PageInfo<NewsArticle> pageInfo = atricleService.selectForStateAndColumnid(current,10,1);//默认查第1~10条数据
        view.addObject("pageInfo",pageInfo);
        return view;
    }

    @GetMapping("/onlineService")
    public ModelAndView OnlineService(){//在线服务
        ModelAndView view = new ModelAndView();
        view.setViewName("onlineService");
        return view;
    }

    @GetMapping("/cooperation")
    public ModelAndView Cooperation(){//合作共赢
        ModelAndView view = new ModelAndView();
        view.setViewName("cooperation");
        return view;
    }

    @GetMapping("/case")
    public ModelAndView Case11(){//合作案例
        ModelAndView view = new ModelAndView();
        view.setViewName("case");
        return view;
    }

    @GetMapping("/zhihui")
    public ModelAndView ZhiHui(){//智慧校服
        ModelAndView view = new ModelAndView();
        view.setViewName("zhihui");
        return view;
    }

    @GetMapping("/productlist")
    public ModelAndView ProductList(){//产品画册
        ModelAndView view = new ModelAndView();
        view.setViewName("productlist");
        return view;
    }

    @GetMapping("/publicWelfare")
    public ModelAndView PublicWelfare(){//公益资讯
        ModelAndView view = new ModelAndView();
        view.setViewName("publicWelfare");
        return view;
    }

    @GetMapping("/culture")
    public ModelAndView Culture(){//校服文化
        ModelAndView view = new ModelAndView();
        view.setViewName("culture");
        return view;
    }

    @GetMapping("/human")
    public ModelAndView Human(){//加入我们
        ModelAndView view = new ModelAndView();
        view.setViewName("human");
        return view;
    }

    @GetMapping("/publicInfo/{id}")
    public ResponseEntity<PublicInfo> SelectPublicInfo(@PathVariable("id") int id){
        PublicInfo publicInfo = publicInfoService.selectByPrimaryKey(id);
        if (publicInfo != null){
            return new ResponseEntity<>(publicInfo, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new PublicInfo(),HttpStatus.BAD_REQUEST);
        }
    }

    /*
    下面是招聘功能
     */

    //前台分页显示全部岗位
    @GetMapping("/position/page/{pagesize}")
    public ModelAndView SelectForPage(@PathVariable("pagesize") Integer currentPage){
        PageInfo<Position> pageInfo = null;
        try{
            pageInfo = positionService.selectAllShow(currentPage);
        }catch (Exception e){
            System.out.println("前台分页显示全部岗位无数据");
        }
        ModelAndView view = new ModelAndView();
        List<PositionColumn> positionColumns = positionColumnService.selectAllPositionColumn();
        view.addObject("positionColumns",positionColumns);
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




    //前台查询岗位
    @GetMapping("/searchposition/page/{pagesize}")
    public ModelAndView SerchPosition(@RequestParam("nameorworkplace") String nameorworkplace,
                                      @PathVariable("pagesize") Integer currentPage){
        nameorworkplace = nameorworkplace.trim();
        if (nameorworkplace.equals("南昌")){nameorworkplace = "1";}
        if (nameorworkplace.equals("鄱阳")){nameorworkplace = "2";}
        PageInfo<Position> pageInfo = null;
        List<PositionColumn> positionColumns = null;
        try {
            pageInfo = positionService.selectByNameOrWorkPlace(nameorworkplace,currentPage);
            positionColumns = positionColumnService.selectAllPositionColumn();
        }catch (Exception e){
            ModelAndView view = new ModelAndView();
            view.addObject("pageInfo",new PageInfo<Position>());
            view.addObject("nameorworkplace",nameorworkplace);
            view.addObject("positionColumns",new ArrayList<PositionColumn>());
            view.setViewName("recruitSerchPosition");
            return view;
        }
        ModelAndView view = new ModelAndView();
        view.addObject("pageInfo",pageInfo);
        view.addObject("nameorworkplace",nameorworkplace);
        view.addObject("positionColumns",positionColumns);
        view.setViewName("recruitSerchPosition");
        return view;
    }

    //前台点击栏目查询岗位
    @GetMapping("/searchpositionByColumn/page/{pagesize}")
    public ModelAndView SerchPositionByColumnId(@RequestParam("columnid") Integer columnid,
                                                @PathVariable("pagesize") Integer currentPage){
        PageInfo<Position> pageInfo = null;
        List<PositionColumn> positionColumns = null;
        try {
            pageInfo = positionService.selectByColumnId(columnid,currentPage);
            positionColumns = positionColumnService.selectAllPositionColumn();
        }catch (Exception e){
            ModelAndView view = new ModelAndView();
            view.addObject("pageInfo",new PageInfo<Position>());
            view.addObject("positionColumns",new ArrayList<PositionColumn>());
            view.addObject("columnid",columnid);
            view.setViewName("recruitSerchPositionByColumnId");
            return view;
        }
        ModelAndView view = new ModelAndView();
        view.addObject("pageInfo",pageInfo);
        view.addObject("positionColumns",positionColumns);
        view.addObject("columnid",columnid);
        view.setViewName("recruitSerchPositionByColumnId");
        return view;
    }

    /*
    招聘结束
     */

    /*
    下面是画册功能
     */
    @GetMapping("/productPictures/skus/{skusid}")
    public ResponseEntity<List<ProductPictures>> SelectPicturesForall(@PathVariable("skusid") Integer skusid){
        List<ProductPictures> productses = ProductPictrueService.selectAllForSkustwo(skusid);
        if (productses != null){
            return new ResponseEntity<>(productses,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/selectSku/{seasonid}" )
    public ResponseEntity<List<ProductSkus>> SelectSkusForSeason(@PathVariable("seasonid") Integer seasonid){
        List<ProductSkus> skuses = productSkusService.selectAllForSeason(seasonid);
        if (skuses != null){
            return new ResponseEntity<>(skuses, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    画册结束
     */
}
