package com.swd.websiteadmin.controllers;
import com.swd.websiteadmin.bean.ProductPictures;
import com.swd.websiteadmin.bean.ProductSkus;
import com.swd.websiteadmin.services.ProductSkusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manage")
public class ProductPicturesController {

    @Autowired
    private com.swd.websiteadmin.services.ProductPictrueService ProductPictrueService;

    @Autowired
    private ProductSkusService productSkusService;

    @PostMapping("/productPictures/insertAll")
    public ResponseEntity<?> InsertPictrues(HttpServletRequest req){
        List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("file");
        String fileName = files.get(0).getOriginalFilename();
        int result = ProductPictrueService.insert(null);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("新增成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("新增失败", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productPicturesAll")
    public ResponseEntity<List<ProductPictures>> SelectPictures(){
        List<ProductPictures> Pictures = ProductPictrueService.selectAllForPictures();
        return new ResponseEntity<>(Pictures, HttpStatus.OK);
    }

    @GetMapping("/productPictures/{picturesId}")
    public ResponseEntity<ProductPictures> SelectPictures(@PathVariable("picturesId") String picturesId ){
        ProductPictures productPictures = ProductPictrueService.selectByPrimaryKey(picturesId);
        if (productPictures != null){
            return new ResponseEntity<>(productPictures, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ProductPictures(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/productPictures")
    public ResponseEntity<?> UpdatePictures(@RequestBody ProductPictures productPictures){
        int result = ProductPictrueService.updateByPrimaryKey(productPictures);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("修改失败", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productPictures/sku/{skusid}")
    public ResponseEntity<List<ProductPictures>> SelectPicturesForSku(@PathVariable("skusid") Integer skusid){
        List<ProductPictures> productses = ProductPictrueService.selectAllForSkus(skusid);
        if (productses != null){
            return new ResponseEntity<>(productses, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }





    @GetMapping("/selectPictures/{skusid}" )
    public ResponseEntity<List<ProductPictures>> SelectPicturesForSkuTwo(@PathVariable("skusid") Integer skusid){
        List<ProductPictures> productses = ProductPictrueService.selectAllForSkus(skusid);
        if (productses != null){
            return new ResponseEntity<>(productses, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productimg/")
    public ResponseEntity<List<ProductPictures>> SelectPicturesimg(@PathVariable("skusid") Integer skusid){
        List<ProductPictures> productses = ProductPictrueService.selectAllForSkus(skusid);
        if (productses != null){
            return new ResponseEntity<>(productses, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/productimg/{skusid}")
    public ResponseEntity<List<ProductPictures>> SelectPicturesimgtwo(@PathVariable("skusid") Integer skusid){
        List<ProductPictures> productses = ProductPictrueService.selectAllForSkus(skusid);
        if (productses != null){
            return new ResponseEntity<>(productses, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/productPictures/updateAll")
    public ResponseEntity<?> UpdateImg(@RequestBody ProductPictures productPictures){
        int result = ProductPictrueService.updateByPrimaryKey(productPictures);
        if (result != -1 && result != 0){
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("修改失败", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/productimgtwo/{picturesid}")
    public ResponseEntity<ProductPictures> SelectPicturestwo(@PathVariable("picturesid") String picturesId ){
        ProductPictures productPictures = ProductPictrueService.selectByPrimaryKey(picturesId);
        if (productPictures != null){
            return new ResponseEntity<>(productPictures, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ProductPictures(), HttpStatus.BAD_REQUEST);
        }
    }




}
