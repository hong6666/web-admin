package com.swd.websiteadmin.services;

import com.swd.websiteadmin.bean.ProductPictures;

import java.util.List;

public interface ProductPictrueService {

    int insert(ProductPictures record);

    ProductPictures selectByPrimaryKey(String picturesId);

    int updateByPrimaryKey(ProductPictures record);

    List<ProductPictures> selectAllForSkus(Integer skusid);

    List<ProductPictures> selectAllForSkustwo(Integer skusid);

    List<ProductPictures> selectAllForPictures();


}
