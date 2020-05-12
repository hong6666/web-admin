package com.swd.websiteadmin.services;

import com.swd.websiteadmin.bean.ProductSeason;

import java.util.List;

public interface ProductSeasonService {

    int insert(ProductSeason record);

    ProductSeason selectByPrimaryKey(Integer seasonid);

    int updateByPrimaryKey(ProductSeason record);

    List<ProductSeason> selectAllForSeason();

}
