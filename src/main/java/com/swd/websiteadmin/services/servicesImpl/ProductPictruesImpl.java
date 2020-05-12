package com.swd.websiteadmin.services.servicesImpl;

import com.swd.websiteadmin.bean.ProductPictures;
import com.swd.websiteadmin.dao.ProductPicturesMapper;
import com.swd.websiteadmin.services.ProductPictrueService;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductPictruesImpl implements ProductPictrueService {
    private Logger logger = LogUtil.looger(this.getClass());

    @Resource
    private ProductPicturesMapper productPicturesMapper;
    @Override
    public int insert(ProductPictures record) {
        try{
            if (record != null){
                int result = productPicturesMapper.insert(record);
                logger.info("插入类别："+result);
                return result;
            }else {
                logger.info("传入到service的ProductPictures对象为空");
                return -1;
            }
        }catch (Exception e){
            logger.error("插入失败："+e.getMessage());
            return -1;
        }
    }

    @Override
    public ProductPictures selectByPrimaryKey(String picturesId) {
        if (picturesId != null ){
            return productPicturesMapper.selectByPrimaryKey(picturesId);
        }else {
            logger.error("查询失败，传入的id不合法");
            return null;
        }
    }


    @Override
    public int updateByPrimaryKey(ProductPictures record) {
        if (record != null){
            try{
                int result = productPicturesMapper.updateByPrimaryKey(record);
                logger.info("更新产品校服成功");
                return result;
            }catch (Exception e){
                logger.error("更新校服信息错误："+e.getMessage());
                return -1;
            }
        }else {
            logger.info("传入到service层的对象为空。");
            return 0;
        }
    }

    @Override
    public List<ProductPictures> selectAllForSkus(Integer skusid) {
        try{
            if (skusid != null && skusid != 0){
                List<ProductPictures> productPictures = productPicturesMapper.selectAllForSkus(skusid);
                logger.info("查询所有产品校服");
                return productPictures;
            }
        }catch (Exception e){
            logger.error("查询产品校服为空:"+e.getMessage());
            return null;
        }
        logger.info("selectAllForSkus传入的id为空或者不合法");
        return null;
    }

    @Override
    public List<ProductPictures> selectAllForSkustwo(Integer skusid) {
        try{
            if (skusid != null && skusid != 0){
                List<ProductPictures> productPictures = productPicturesMapper.selectAllForSkustwo(skusid);
                logger.info("查询所有产品校服");
                return productPictures;
            }
        }catch (Exception e){
            logger.error("查询产品校服为空:"+e.getMessage());
            return null;
        }
        logger.info("selectAllForSkustwo传入的id为空或者不合法");
        return null;
    }

    @Override
    public List<ProductPictures> selectAllForPictures() {
        try {
            return productPicturesMapper.selectAllForPictures();
        }catch (Exception e){
            logger.error("查询列表出现异常:"+e.getMessage());
            return null;
        }
    }


}
