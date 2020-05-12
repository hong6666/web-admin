package com.swd.websiteadmin.services.servicesImpl;

import com.swd.websiteadmin.bean.PublicInfo;
import com.swd.websiteadmin.dao.PublicInfoMapper;
import com.swd.websiteadmin.services.PublicInfoService;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class PublicInfoServiceImpl implements PublicInfoService {
    @Autowired
    private PublicInfoMapper publicInfoMapper;
    private Logger logger = LogUtil.looger(this.getClass());
    @Override
    public int updateByPrimaryKey(PublicInfo record) {
        if (record != null){
                try{
                    int result = publicInfoMapper.updateByPrimaryKey(record);//这里的返回值并不是指更新操作影响了多少行
                    logger.info("更新页脚信息成功");                           //而是匹配了多少条适合条件的行数。
                    return result;
                }catch (Exception e){
                    logger.error("更新信息错误："+e.getMessage());
                    return -1;
                }
        }else {
            logger.info("传入到service层的对象为空。");
            return -1;
        }
    }

    @Override
    public PublicInfo selectByPrimaryKey(Integer publicinfoid) {
        if (publicinfoid >= 1 && publicinfoid != null){
            return publicInfoMapper.selectByPrimaryKey(publicinfoid);
        }else {
            logger.info("传入到service层的id为空或者不正确");
         return null;
        }
    }

    
    
    
    
    
    
    
    
    @Override
    public int insert(PublicInfo record) {
        return 0;
    }
}
