package com.swd.websiteadmin.controllers;


import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.*;
import com.swd.websiteadmin.services.*;
import com.swd.websiteadmin.utils.ImgUploadUtil;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequestMapping("/manage")
@Controller
public class ManageController {

    @Autowired
    private PublicInfoService infoService;

    @Autowired
    private NewsAtricleService atricleService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private NewsColumnService columnService;

    @Autowired
    private PositionColumnService positionColumnService;

    private static Logger logger = LogUtil.looger(ImgUploadUtil.class);


    @GetMapping("/skulist")
    public ModelAndView AddList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/skuList");
        return view;
    }

    @GetMapping("/addsku")
    public ModelAndView AddSku(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/addSku");
        return view;
    }
    @GetMapping("/updatesku")
    public ModelAndView Updatesku(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/updateSku");
        return view;
    }

    @GetMapping("/imaglist")
    public ModelAndView ImgList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/imagList");
        return view;
    }

    @GetMapping("/addimag")
    public ModelAndView addimag(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/addimag");
        return view;
    }

    @GetMapping("/updateImag")
    public ModelAndView Updateimag(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/updateImag");
        return view;
    }

    @GetMapping("index")
    public ModelAndView Manage(HttpServletRequest req){
        HttpSession session = req.getSession(true);
        Admin admin = (Admin) session.getAttribute("admin");
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/index");
        view.addObject("user",admin);
        return view;
    }

    @GetMapping("newsImg")
    public ModelAndView Insert(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/newsImg");
        return view;
    }

    @GetMapping("indexImg")
    public ModelAndView IndexImg(){
        ModelAndView model = new ModelAndView("manage/indexImg");
        return model;
    }

    @GetMapping("aboutImg")
    public ModelAndView About(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/aboutImg");
        return view;
    }

    @GetMapping("productImg")
    public ModelAndView Product(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/productImg");
        return view;
    }

    @GetMapping("onlineServiceImg")
    public ModelAndView OnlineService(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/onlineServiceImg");
        return view;
    }

    @GetMapping("cooperationImg")
    public ModelAndView CooperationImg(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/cooperationImg");
        return view;
    }

    @GetMapping("newsList/{current}")
    public ModelAndView Newslist(@PathVariable("current") Integer current){
        PageInfo<NewsArticle> pageInfo = atricleService.selectAll(current);
        if (pageInfo.getList().size() > 0){
            for (NewsArticle news:pageInfo.getList()
            ) {
                NewsColumn newsColumn = columnService.selectByPrimaryKey(news.getColumnid());
                news.setColumnname(newsColumn.getColumnname());//根据栏目id带出栏目名称（这里我在文章实体类加了个栏目名称字段，方便前台渲染，数据库字段不变）
            }
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/newsList");
        view.addObject("pageInfo",pageInfo);
        return view;
    }

    @GetMapping("updateNews/{current}")
    public ModelAndView UpdateNews(@PathVariable("current") String current){
        NewsArticle article = atricleService.selectByPrimaryKey(current);
        List<NewsColumn> newsColumns = columnService.selectAllForState();
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/updateNews");
        view.addObject("article",article);
        view.addObject("newsColumns",newsColumns);
        return view;
    }

    @GetMapping("/system")
    public ModelAndView SysSet(){
        PublicInfo publicInfo = infoService.selectByPrimaryKey(1);
        ModelAndView model = new ModelAndView("/manage/system");
        model.addObject("system",publicInfo);
        return model;
    }

    @GetMapping("ueditor")
    public ModelAndView UeditorView(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/ueditor");
        List<NewsColumn> newsColumns = columnService.selectAllForState();
        int sort = atricleService.selectSort();
        view.addObject("newsColumns",newsColumns);
        view.addObject("sort",sort+1);
        return view;
    }

    //后台管理显示全部岗位
    @GetMapping("managePosition/{pagesize}")
    public ModelAndView SelectAll(@PathVariable("pagesize") Integer currentPage){
        PageInfo<Position> pageInfo = null;
        try{
            pageInfo = positionService.selectAll(currentPage);
        }catch (Exception e){
            logger.error("出现异常:"+e.getMessage());
        }
        ModelAndView view = new ModelAndView();
        List<PositionColumn> positionColumns = positionColumnService.selectAllPositionColumn();
        view.addObject("positionColumns",positionColumns);
        view.addObject("pageInfo",pageInfo);
        view.setViewName("manage/managePosition");
        return view;
    }

    //后台点击修改跳转到修改岗位
    @GetMapping("updatePosition/{id}")
    public ModelAndView PpdatePosition(@PathVariable("id") Integer positionId){
        Position position = positionService.selectByPrimaryKey(positionId);
        ModelAndView view = new ModelAndView();
        List<PositionColumn> positionColumns = positionColumnService.selectAllPositionColumn();
        view.addObject("positionColumns",positionColumns);
        view.addObject("position",position);
        view.setViewName("manage/updatePosition");
        return view;
    }
    //后台修改岗位后跳回显示全部岗位
    @PostMapping("/updateposition")
    public ModelAndView UpdatePosition(Position position){
        ModelAndView view = new ModelAndView();
        int result = positionService.updateByPrimaryKey(position);
        if (result != -1 && result != 0){
            PageInfo<Position> pageInfo = positionService.selectAll(1);
            view.setViewName("manage/managePosition");
            view.addObject("pageInfo",pageInfo);
            List<PositionColumn> positionColumns = positionColumnService.selectAllPositionColumn();
            view.addObject("positionColumns",positionColumns);
            return view;
        }
        return view;
    }

    //跳转到增加岗位界面
    @GetMapping("/position")
    public ModelAndView InsertPositionGet(){
        ModelAndView view = new ModelAndView();
        List<PositionColumn> positionColumns = positionColumnService.selectAllPositionColumn();
        int sort = positionService.selectSort();
        view.addObject("sort",sort+1);
        view.addObject("positionColumns",positionColumns);
        view.setViewName("/manage/addPosition");
        return view;
    }


    //增加岗位
    @PostMapping("/position")
    public ModelAndView InsertPosition(Position position){
        ModelAndView view = new ModelAndView();
        System.out.println(position.getPositionsort());
        int result = positionService.insert(position);
        if (result != -1 && result != 0) {
            PageInfo<Position> pageInfo = positionService.selectAll(1);
            view.setViewName("manage/managePosition");
            view.addObject("pageInfo", pageInfo);
            List<PositionColumn> positionColumns = positionColumnService.selectAllPositionColumn();
            view.addObject("positionColumns",positionColumns);
            return view;
        }
        return view;
    }

    @GetMapping("/exit")
    public ModelAndView exitSys(HttpServletRequest req){
        HttpSession session = req.getSession(true);
        session.removeAttribute("admin");
        logger.info("清除session，成功退出，跳回登陆页面");
        return new ModelAndView("login");
    }

    @GetMapping("user")
    public ModelAndView User(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/user");
        return view;
    }

    @GetMapping("userInfo/{id}")
    public ModelAndView UserInfo(@PathVariable("id") Integer id){
        Admin admin = adminService.selectByPrimaryKey(id);
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/userInfo");
        view.addObject("user",admin);
        return view;
    }

    /**
     * 栏目页面
     * @return
     */
    @GetMapping("columnList/{index}")
    public ModelAndView SelectColumn(@PathVariable("index") Integer currentPage){
        PageInfo<NewsColumn> pageInfo = columnService.selectAll(currentPage, 10);
        ModelAndView view = new ModelAndView("/manage/columnList");
        view.addObject("pageInfo",pageInfo);
        return view;
    }

    /**
     * 编辑栏目页面
     * @param currentPage
     * @return
     */
    @GetMapping("editColumn/{index}")
    public ModelAndView editColumn(@PathVariable("index")Integer currentPage){
        NewsColumn newsColumn = columnService.selectByPrimaryKey(currentPage);
        ModelAndView view = new ModelAndView("/manage/editColumn");
        view.addObject("newsColumn",newsColumn);
        return view;
    }

    /**
     * 添加栏目页面
     * @return
     */
    @GetMapping("addColumn")
    public ModelAndView addColumn(){
        ModelAndView view = new ModelAndView("/manage/addColumn");
        return view;
    }



    //后台显示全部岗位栏目
    @GetMapping("positionColumn/{pagesize}")
    public ModelAndView SelectPositionColumn(@PathVariable("pagesize") Integer currentPage){
        PageInfo<PositionColumn> pageInfo = null;
        try{
            pageInfo = positionColumnService.selectAll(currentPage);
        }catch (Exception e){
            logger.error("出现异常:"+e.getMessage());
        }
        ModelAndView view = new ModelAndView();
        view.addObject("pageInfo",pageInfo);
        view.setViewName("manage/positionColumn");
        return view;
    }

    //后台点击修改跳转到修改岗位栏目
    @GetMapping("updatePositionColumn/{id}")
    public ModelAndView PpdatePositionColumn(@PathVariable("id") Integer positionId){
        PositionColumn positionColumn = positionColumnService.selectByPrimaryKey(positionId);
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/updatePositionColumn");
        view.addObject("positionColumn",positionColumn);
        return view;
    }

    //后台修改岗位后跳回显示全部栏目
    @PostMapping("/updatePositionColumn")
    public ModelAndView UpdatePositionColumn(PositionColumn positionColumn){
        ModelAndView view = new ModelAndView();
        int result = positionColumnService.updateByPrimaryKey(positionColumn);
        if (result != -1 && result != 0){
            PageInfo<PositionColumn> pageInfo = positionColumnService.selectAll(1);
            view.setViewName("manage/positionColumn");
            view.addObject("pageInfo",pageInfo);
            return view;
        }
        return view;
    }
    //跳转到增加栏目界面
    @GetMapping("/addPositionColumn")
    public ModelAndView AddPositionColumnGet(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/addPositionColumn");
        return view;
    }
    //增加栏目
    @PostMapping("/addPositionColumn")
    public ModelAndView AddPositionColumn(PositionColumn positionColumn){
        ModelAndView view = new ModelAndView();
        int result = positionColumnService.insert(positionColumn);
        if (result != -1 && result != 0){
            PageInfo<PositionColumn> pageInfo = positionColumnService.selectAll(1);
            view.setViewName("manage/positionColumn");
            view.addObject("pageInfo",pageInfo);
            return view;
        }
        return view;
    }

}
