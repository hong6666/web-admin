package com.swd.websiteadmin.services;

import com.swd.websiteadmin.bean.PublicInfo;

public interface PublicInfoService {

    int updateByPrimaryKey(PublicInfo record);

    PublicInfo selectByPrimaryKey(Integer publicinfoid);

    int insert(PublicInfo record);
}
