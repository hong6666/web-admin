package com.swd.websiteadmin.services.servicesImpl;


import com.swd.websiteadmin.bean.ProductSeason;
import com.swd.websiteadmin.dao.ProductSeasonMapper;
import com.swd.websiteadmin.services.ProductSeasonService;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductSeasonImpl implements ProductSeasonService {
    private Logger logger = LogUtil.looger(this.getClass());
    @Resource
    private ProductSeasonMapper productSeasonMapper;
    @Override
    public int insert(ProductSeason record) {
        try{
            if (record != null){
                int result = productSeasonMapper.insert(record);
                logger.info("插入Season--套装类别："+result);
                return result;
            }else {
                logger.info("传入到service的ProductSeason对象为空");
                return -1;
            }
        }catch (Exception e){
            logger.error("插入失败："+e.getMessage());
            return -1;
        }
    }

    @Override
    public ProductSeason selectByPrimaryKey(Integer seasonid) {
        if (seasonid >=1 ){
            return productSeasonMapper.selectByPrimaryKey(seasonid);
        }else {
            logger.error("查询失败，传入的id不合法");
            return null;
        }
    }

    @Override
    public int updateByPrimaryKey(ProductSeason record) {
        try{
            if (record != null && record.getSeasonid() != null && record.getSeasonid() != 0){
                int result = productSeasonMapper.insert(record);
                logger.info("Season--季节类别更新完成，更新结果："+result);
                return result;
            }
        }catch (Exception e){
            logger.error("Season--套装类别更新失败:"+e.getMessage());
            return -1;
        }
        logger.info("传入到updateByPrimaryKey的ProductSeason对象为空");
        return 0;
    }

    @Override
    public List<ProductSeason> selectAllForSeason() {
        try {
            return productSeasonMapper.selectAllForSeason();
        }catch (Exception e){
            logger.error("查询列表出现异常:"+e.getMessage());
            return null;
        }
    }

}
