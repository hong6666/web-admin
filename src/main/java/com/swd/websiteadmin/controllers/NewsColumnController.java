package com.swd.websiteadmin.controllers;

import com.github.pagehelper.PageInfo;
import com.swd.websiteadmin.bean.NewsColumn;
import com.swd.websiteadmin.services.NewsColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class NewsColumnController {
    @Autowired
    private NewsColumnService columnService;

    @PostMapping("newsColumn")
    public ResponseEntity<?> InsertColumn(@RequestBody NewsColumn column){
        int result = columnService.insert(column);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("添加成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("添加失败",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("newsColumn")
    public ResponseEntity<?> UpdateColumn(@RequestBody NewsColumn column){
        int result = columnService.updateByPrimaryKey(column);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("修改失败",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("newsColumn")
    public ResponseEntity<?> SelectColumn(@RequestParam Integer columnid){
        NewsColumn newsColumn = columnService.selectByPrimaryKey(columnid);
        if (newsColumn != null){
            return new ResponseEntity<>(newsColumn.getColumnname(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("暂无(异常)",HttpStatus.BAD_REQUEST);
        }
    }

}
