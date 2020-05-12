package com.swd.websiteadmin.services;

import com.swd.websiteadmin.bean.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface AdminService {

    /**
     *
     * @param id
     * @return
     */
    Admin selectByPrimaryKey(Integer id);

    /**
     *
     * @return
     */
    List<Admin> selectAll();

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Admin record);


    Admin DoLogin(Admin admin);


    int GmPassword(Integer id);

    int insert(Admin record);
}
