package com.swd.websiteadmin.utils;

import com.swd.websiteadmin.bean.ProductPictures;
import com.swd.websiteadmin.bean.UeditorImage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ImgUploadUtil {

    @Autowired
    private  RequestUrl requestUrl;

   private static Logger looger = LogUtil.looger(ImgUploadUtil.class);

    public static String UploadImgToFile(MultipartFile uplodafile, HttpServletRequest req,String path,String path2,RequestUrl requestUrl) throws IOException{
        String path1 = path+path2;
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String data = simp.format(new Date());
        File folder = new File(path1);
        if (!folder.isDirectory()){ //判断目录是否存在
            folder.mkdirs();
        }
        String hisPath = path1+"history\\";
        File historyFile = new File(hisPath);//创建一个历史文件夹存储之前上传的图片
        if (!historyFile.isDirectory()){ //判断目录是否存在
            historyFile.mkdirs();
        }
        String fileName = uplodafile.getOriginalFilename();//获取上传的文件名字
        looger.info("上传路径为："+path1+fileName);
        File file = new File(path1 + fileName);
        if (file.exists()){//如果文件夹中图片存在
            String prefix = fileName.substring(0,fileName.lastIndexOf("."));
            String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
            String newName = hisPath+(prefix+"-"+data)+suffix;//新名字
            file.renameTo(new File(newName));//将之前的文件上传到历史文件夹中--history
            uplodafile.transferTo(new File(path1+fileName));//上传新文件（沿用之前的旧名字）
            String filePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/upload/"+path2+fileName;
            if (requestUrl.requestUrl != null && !"".equals(requestUrl.requestUrl)){
                filePath = req.getScheme()+"://"+requestUrl.requestUrl+"/upload/"+path2+fileName;
            }
            looger.info("上传成功，访问路径为:"+filePath);
            return filePath;
        }else {
            uplodafile.transferTo(new File(path1+fileName));//上传新文件（沿用之前的旧名字）
            String filePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/upload/"+path2+fileName;
            if (requestUrl.requestUrl != null && !"".equals(requestUrl.requestUrl)){
                filePath = req.getScheme()+"://"+requestUrl.requestUrl+"/upload/"+path2+fileName;
            }
            looger.info("上传成功，访问路径为:"+filePath);
            return filePath;
        }
    }

    public static ProductPictures UploadImgToDB(MultipartFile multipartFile, HttpServletRequest req,ProductPictures pictures,String path1,String path2,RequestUrl requestUrl) throws IOException {
        String realPath = path1+path2;
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String data = simp.format(new Date());
            File folder = new File(realPath);
            if (!folder.isDirectory()){ //判断目录是否存在
                folder.mkdirs();
            }
            String oldName = multipartFile.getOriginalFilename();//获取上传的文件名字
            String prefix = oldName.substring(0,oldName.lastIndexOf("."));
            String suffix = oldName.substring(oldName.lastIndexOf("."),oldName.length());//获取文件后缀
            String newName = data+suffix;
            String RandomId = RandomUtil.RandomImgName();
            looger.info("上传后的图片名称："+newName);
            multipartFile.transferTo(new File(folder,newName));
            String filePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/upload/"+path2+newName;
            if (requestUrl.requestUrl != null && !"".equals(requestUrl.requestUrl)){
                filePath = req.getScheme()+"://"+requestUrl.requestUrl+"/upload/"+path2+newName;
            }
            looger.info("访问路径“:"+filePath);
            pictures.setPicturesid(RandomId);
            pictures.setPicturesname(prefix+data);
            pictures.setPicturesurl(filePath);
            return pictures;

    }

    public static UeditorImage UploadImgForAtricle(HttpServletRequest req,String path1,String path2,RequestUrl requestUrl) throws IOException {
        UeditorImage image = new UeditorImage();
        String realPath = path1+path2;
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String data = simp.format(new Date());
        File folder = new File(realPath);
        if (!folder.isDirectory()){ //判断目录是否存在
            folder.mkdirs();
        }
        List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("upfile");
            String fileName = files.get(0).getOriginalFilename();
            looger.info("接收到上传数据，图片名称为 ：" + fileName);
        String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());//获取文件后缀
        String newName = data+suffix;
        looger.info("上传后的图片名称："+newName);
        files.get(0).transferTo(new File(folder,newName));
        String filePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/upload/"+path2+newName;
        if (requestUrl.requestUrl != null && !"".equals(requestUrl.requestUrl)){
            filePath = req.getScheme()+"://"+requestUrl.requestUrl+"/upload/"+path2+newName;
        }
        looger.info("访问路径“:"+filePath);
            image.setUrl(filePath);
            image.setState("SUCCESS");
            image.setOriginal(fileName);
            image.setTitle(fileName);
            return image;
    }

    public static String UploadImgThum(MultipartFile file,HttpServletRequest req,String path,String path2,RequestUrl requestUrl) throws IOException {
        String realPath = path+path2;
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String data = simp.format(new Date());
        File folder = new File(realPath);
        if (!folder.isDirectory()){ //判断目录是否存在
            folder.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        looger.info("接收到上传数据，图片名称为 ：" + fileName);
        String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());//获取文件后缀
        String newName = data+suffix;
        looger.info("上传后的图片名称："+newName);
        file.transferTo(new File(folder,newName));
        String filePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/upload/"+path2+newName;
        if (requestUrl.requestUrl != null && !"".equals(requestUrl.requestUrl)){
            filePath = req.getScheme()+"://"+requestUrl.requestUrl+"/upload/"+path2+newName;
        }
        looger.info("访问路径“:"+filePath);
        return filePath;
    }
}
