package com.swd.websiteadmin.services.servicesImpl;

import com.swd.websiteadmin.bean.ProductSkus;
import com.swd.websiteadmin.dao.ProductSkusMapper;
import com.swd.websiteadmin.services.ProductSkusService;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductSkusServiceImpl implements ProductSkusService {

    private Logger logger = LogUtil.looger(this.getClass());
    @Resource
    private ProductSkusMapper productSkusMapper;
    @Override
    public int insert(ProductSkus record) {
        try{
            if (record != null){
                    int result = productSkusMapper.insert(record);
                    logger.info("插入skus--套装类别："+result);
                    return result;
            }else {
                logger.info("传入到service的ProductSkus对象为空");
                return -1;
            }
        }catch (Exception e){
            logger.error("插入失败："+e.getMessage());
            return -1;
        }
    }

    @Override
    public ProductSkus selectByPrimaryKey(Integer skusid) {
        if (skusid >=1 ){
            return productSkusMapper.selectByPrimaryKey(skusid);
        }else {
            logger.error("查询失败，传入的id不合法");
            return null;
        }

    }

    @Override
    public int updateByPrimaryKey(ProductSkus record) {
        try{
            if (record != null && record.getSkusid() != null && record.getSkusid() != 0){
                int result = productSkusMapper.updateByPrimaryKey(record);
                logger.info("skus--套装类别更新完成，更新结果："+result);
                return result;
            }
        }catch (Exception e){
            logger.error("skus--套装类别更新失败:"+e.getMessage());
            return -1;
        }
        logger.info("传入到updateByPrimaryKey的ProductSkus对象为空");
        return 0;
    }

    @Override
    public List<ProductSkus> selectAllForSeason(Integer seasonid) {
        try{
            if (seasonid != null ){
                List<ProductSkus> productSkuses = productSkusMapper.selectAllForSeason(seasonid);
                logger.info("查询所有该季节下的套装类别");
                return productSkuses;
            }
        }catch (Exception e){
            logger.error("查询季节下的套装为空:"+e.getMessage());
            return null;
        }
        logger.info("selectAllForSeason传入的id为空或者不合法");
        return null;
    }

    @Override
    public List<ProductSkus> selectAllForSku() {
        try {
            return productSkusMapper.selectAllForSku();
        }catch (Exception e){
            logger.error("查询列表出现异常:"+e.getMessage());
            return null;
        }
    }
}
