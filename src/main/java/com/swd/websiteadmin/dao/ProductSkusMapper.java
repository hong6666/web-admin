package com.swd.websiteadmin.dao;

import com.swd.websiteadmin.bean.ProductSkus;

import java.util.List;

public interface ProductSkusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_skus
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer skusid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_skus
     *
     * @mbggenerated
     */
    int insert(ProductSkus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_skus
     *
     * @mbggenerated
     */
    ProductSkus selectByPrimaryKey(Integer skusid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_skus
     *
     * @mbggenerated
     */
    List<ProductSkus> selectAllForSeason(Integer seasonid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_skus
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ProductSkus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_column
     *
     * @mbggenerated
     */
    List<ProductSkus> selectAllForSku();
}