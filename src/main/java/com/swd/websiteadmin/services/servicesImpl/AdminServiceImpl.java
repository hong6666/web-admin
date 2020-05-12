package com.swd.websiteadmin.services.servicesImpl;

import com.swd.websiteadmin.bean.Admin;
import com.swd.websiteadmin.dao.AdminMapper;
import com.swd.websiteadmin.services.AdminService;
import com.swd.websiteadmin.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    private Logger logger = LogUtil.looger(this.getClass());


    @Override
    public Admin selectByPrimaryKey(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Admin> selectAll() {
        return adminMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        if(record != null){
            try {
                return adminMapper.updateByPrimaryKey(record);
            }catch (Exception e){
                return -1;
            }
        }else {
            return -1;
        }

    }


    public Admin DoLogin(Admin admin){
       return adminMapper.selectByAdmin(admin);
    }
    public int GmPassword(Integer id){return adminMapper.GmPassword(id);}

    @Override
    public int insert(Admin record) {
        try{
            if (record != null){
                record.setAuth(1);
                int result = adminMapper.insert(record);
                logger.info("插入结果："+result);
                return result;
            }else {
                logger.info("传入service的admin为空");
                return -1;
            }
        }catch (Exception e){
            logger.error("插入admin出现异常:"+e.getMessage());
            return -1;
        }
    }
}
