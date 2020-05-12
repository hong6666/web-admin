package com.swd.websiteadmin.controllers;

import com.swd.websiteadmin.bean.PublicInfo;
import com.swd.websiteadmin.services.PublicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Component
@RequestMapping("/manage")
public class PublicInfoController {

    @Autowired
    private PublicInfoService publicInfoService;

    @PutMapping("/publicInfo")
    public ResponseEntity<?> UpdatePublicInfo(@RequestBody PublicInfo publicInfo){
        int result = publicInfoService.updateByPrimaryKey(publicInfo);
        if (result == 0 || result == -1){
            return new ResponseEntity<>("更新失败，请检查重试", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>("更新成功！",HttpStatus.OK);
        }
    }

    @GetMapping("/publicInfo/{id}")
    public ResponseEntity<PublicInfo> SelectPublicInfo(@PathVariable("id") int id){
        PublicInfo publicInfo = publicInfoService.selectByPrimaryKey(id);
        if (publicInfo != null){
            return new ResponseEntity<>(publicInfo,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new PublicInfo(),HttpStatus.BAD_REQUEST);
        }
    }
}
