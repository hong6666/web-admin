package com.swd.websiteadmin.bean;

public class ProductSkus {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_skus.skusId
     *
     * @mbggenerated
     */
    private Integer skusid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_skus.skusName
     *
     * @mbggenerated
     */
    private String skusname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_skus.season
     *
     * @mbggenerated
     */
    private Integer season;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_skus.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_skus.skusId
     *
     * @return the value of product_skus.skusId
     *
     * @mbggenerated
     */
    public Integer getSkusid() {
        return skusid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_skus.skusId
     *
     * @param skusid the value for product_skus.skusId
     *
     * @mbggenerated
     */
    public void setSkusid(Integer skusid) {
        this.skusid = skusid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_skus.skusName
     *
     * @return the value of product_skus.skusName
     *
     * @mbggenerated
     */
    public String getSkusname() {
        return skusname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_skus.skusName
     *
     * @param skusname the value for product_skus.skusName
     *
     * @mbggenerated
     */
    public void setSkusname(String skusname) {
        this.skusname = skusname == null ? null : skusname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_skus.season
     *
     * @return the value of product_skus.season
     *
     * @mbggenerated
     */
    public Integer getSeason() {
        return season;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_skus.season
     *
     * @param season the value for product_skus.season
     *
     * @mbggenerated
     */
    public void setSeason(Integer season) {
        this.season = season;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_skus.state
     *
     * @return the value of product_skus.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_skus.state
     *
     * @param state the value for product_skus.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }
}