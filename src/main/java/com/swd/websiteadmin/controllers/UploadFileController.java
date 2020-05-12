package com.swd.websiteadmin.controllers;

import com.swd.websiteadmin.bean.ProductPictures;
import com.swd.websiteadmin.bean.UeditorImage;
import com.swd.websiteadmin.services.ProductPictrueService;
import com.swd.websiteadmin.utils.ImgUploadUtil;
import com.swd.websiteadmin.utils.PublicMsg;
import com.swd.websiteadmin.utils.RequestUrl;
import com.swd.websiteadmin.utils.UploadPathConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/manage/upload")
public class UploadFileController {

    @Autowired
    private ProductPictures productPictures;
    @Autowired
    private ProductPictrueService productPictrueService;

    @Autowired
    private UploadPathConfig pathConfig;//路径配置文件

    @Autowired
    private RequestUrl requestUrl;

    @PostMapping("/imgToFile/{path}")
    public ResponseEntity<?> uploadImgToFile(@RequestParam("file") MultipartFile uplodafile, HttpServletRequest req, @PathVariable("path") String path){
        try{
            String filepath = ImgUploadUtil.UploadImgToFile(uplodafile,req,pathConfig.rootPath,path+"/",requestUrl);
            return new ResponseEntity<>("上传成功,访问地址："+filepath, HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity<>("上传失败", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("上传失败", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/imgToDB/{path}/{skusid}")//画册功能的接口
    public void uploadImgToDB(@RequestPart("file") MultipartFile[] uplodafile, HttpServletRequest req, @PathVariable("path") String path,@PathVariable("skusid") Integer skusid){
        try {
            for (int i = 0; i < uplodafile.length; i++) {
                ProductPictures pictures = ImgUploadUtil.UploadImgToDB(uplodafile[i], req,this.productPictures,pathConfig.rootPath,path+"/",requestUrl);
                pictures.setSkusid(skusid);
                pictures.setState(1);//上传默认为1
                pictures.setPicturessort(1);
                int result = productPictrueService.insert(pictures);
                System.out.println(result);
            }
        } catch (IOException e) {
                e.printStackTrace(); 
        }
    }

    @RequestMapping("/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }

    @RequestMapping("/insertImg")
    public ResponseEntity<UeditorImage> uploadImgForArticle(HttpServletRequest req){
        UeditorImage image = new UeditorImage();
        try {
             image= ImgUploadUtil.UploadImgForAtricle(req,pathConfig.rootPath,pathConfig.brandArticlePath,requestUrl);
        } catch (IOException e) {
            e.printStackTrace();
            image.setState("FAIL");
        }
        return new ResponseEntity<>(image,HttpStatus.OK);
    }
}
