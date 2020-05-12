package com.swd.websiteadmin.controllers;


import com.swd.websiteadmin.bean.ProductSeason;
import com.swd.websiteadmin.services.ProductSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class ProductSeasonController {
    @Autowired
    private ProductSeasonService productSeasonService;

    @PostMapping("/productSeason")
    public ResponseEntity<?> InsertSeason(@RequestBody ProductSeason productSeason){
        int result = productSeasonService.insert(productSeason);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("新增成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("新增失败", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productSeason/{seasonId}")
    public ResponseEntity<ProductSeason> SelectSeason(@PathVariable("seasonId") Integer seasonId){
        ProductSeason productSeason = productSeasonService.selectByPrimaryKey(seasonId);
        if (productSeason != null){
            return new ResponseEntity<>(productSeason, HttpStatus.OK);
        }else {//返回空的数据到前台
            return new ResponseEntity<>(new ProductSeason(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/productSeason")
    public ResponseEntity<?> UpdateSeason(@RequestBody ProductSeason productSeason){
        int result = productSeasonService.updateByPrimaryKey(productSeason);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("修改失败", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productSeasonAll")
    public ResponseEntity<List<ProductSeason>> SelectSeason(){
        List<ProductSeason> Season = productSeasonService.selectAllForSeason();
        return new ResponseEntity<>(Season, HttpStatus.OK);
    }


}
