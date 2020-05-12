package com.swd.websiteadmin.controllers;

import com.swd.websiteadmin.bean.Admin;
import com.swd.websiteadmin.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/manage")
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    @GetMapping("/login/{id}")
    public Admin SelectAdmin(@PathVariable("id") Integer id){
        return adminService.selectByPrimaryKey(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> DoLogin(@RequestBody Admin admin, HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        Admin admin1 = adminService.DoLogin(admin);
        if (admin1 != null){
            if (admin1.getPassword().equals(admin.getPassword())){
                request.getSession().setAttribute("admin",admin1);
                map.put("S","登陆成功");
            }else {
                map.put("F","密码错误！");
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }
        }else {
            map.put("F","用户名错误或者不存在");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/login")
    public ResponseEntity<?> UpdateAdmin(@RequestBody Admin admin){
        if ("".equals(admin.getLoginname()) || "".equals(admin.getPassword())){
            return new ResponseEntity<>("用户名密码不允许修改为空",HttpStatus.OK);
        }
        int i = adminService.updateByPrimaryKey(admin);
        if (i == -1){
            return new ResponseEntity<>("更新失败", HttpStatus.BAD_REQUEST);
        }else if (i == 0){
            return new ResponseEntity<>("没有更新",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("更新成功",HttpStatus.OK);
    }

    /**
     * 重置密码，默认为123456
     * @param id
     * @return
     */
    @PutMapping("/login/{id}")
    public Integer GmPassword(@PathVariable("id") Integer id){
        return adminService.GmPassword(id);
    }

    @PostMapping("/insertLogin")
    public ResponseEntity<?> InsertAdmin(@RequestBody Admin admin){

        int i = adminService.insert(admin);
        if (i == -1){
            return new ResponseEntity<>("新增失败", HttpStatus.BAD_REQUEST);
        }else if (i == 0){
            return new ResponseEntity<>("没有更新",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("新增成功",HttpStatus.OK);
    }

}
