package com.swd.websiteadmin.controllers;

import com.swd.websiteadmin.bean.ProductSkus;
import com.swd.websiteadmin.services.ProductSkusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manage")
public class ProductSkusController {

    @Autowired
    private ProductSkusService productSkusService;

    @PostMapping("/productSkus/insertAll")
    public ResponseEntity<?> InsertSkus(@RequestBody ProductSkus productSkus){
        int result = productSkusService.insert(productSkus);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("新增成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("新增失败", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productSkus/{skusId}")
    public ResponseEntity<ProductSkus> SelectSkus(@PathVariable("skusId") Integer skusId){
        ProductSkus productSkus = productSkusService.selectByPrimaryKey(skusId);
        if (productSkus != null){
            return new ResponseEntity<>(productSkus, HttpStatus.OK);
        }else {//返回空的数据到前台
            return new ResponseEntity<>(new ProductSkus(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/productSkus/updateAll")
    public ResponseEntity<?> UpdateSkus(@RequestBody ProductSkus productSkus){
        int result = productSkusService.updateByPrimaryKey(productSkus);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("修改失败", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productSkusAll")
    public ResponseEntity<List<ProductSkus>> SelectSku(){
        List<ProductSkus> Skus = productSkusService.selectAllForSku();
        return new ResponseEntity<>(Skus, HttpStatus.OK);
    }



    @GetMapping("/skusForSeason/{seasonid}")
    public ResponseEntity<List<ProductSkus>> SelectSkusForSeason(@PathVariable("seasonid") Integer seasonid){
        List<ProductSkus> skuses = productSkusService.selectAllForSeason(seasonid);
        if (skuses != null){
            return new ResponseEntity<>(skuses, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productSkus/")
    public ResponseEntity<ProductSkus> SelectoneSkus(@RequestParam("skusId") Integer skusId){
        ProductSkus productSkus = productSkusService.selectByPrimaryKey(skusId);
        if (productSkus != null){
            return new ResponseEntity<>(productSkus, HttpStatus.OK);
        }else {//返回空的数据到前台
            return new ResponseEntity<>(new ProductSkus(), HttpStatus.BAD_REQUEST);
        }
    }
}
