package com.swd.websiteadmin.services;

import com.swd.websiteadmin.bean.ProductSkus;

import java.util.List;

public interface ProductSkusService {

    int insert(ProductSkus record);

    ProductSkus selectByPrimaryKey(Integer skusid);

    int updateByPrimaryKey(ProductSkus record);

    List<ProductSkus> selectAllForSeason(Integer seasonid);

    List<ProductSkus> selectAllForSku();



}
