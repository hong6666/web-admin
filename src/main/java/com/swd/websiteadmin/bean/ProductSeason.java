package com.swd.websiteadmin.bean;

public class ProductSeason {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_season.seasonId
     *
     * @mbggenerated
     */
    private Integer seasonid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_season.seasonName
     *
     * @mbggenerated
     */
    private String seasonname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_season.seasonId
     *
     * @return the value of product_season.seasonId
     *
     * @mbggenerated
     */
    public Integer getSeasonid() {
        return seasonid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_season.seasonId
     *
     * @param seasonid the value for product_season.seasonId
     *
     * @mbggenerated
     */
    public void setSeasonid(Integer seasonid) {
        this.seasonid = seasonid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_season.seasonName
     *
     * @return the value of product_season.seasonName
     *
     * @mbggenerated
     */
    public String getSeasonname() {
        return seasonname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_season.seasonName
     *
     * @param seasonname the value for product_season.seasonName
     *
     * @mbggenerated
     */
    public void setSeasonname(String seasonname) {
        this.seasonname = seasonname == null ? null : seasonname.trim();
    }
}