package com.cn.lx.entity;

import java.util.Date;
import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/31 -12:58
 */
public class Product {
    //主键Id
    private Long productId;
    //商品名
    private String productName;
    //商品简介
    private String productDesc;
    //缩略图
    private String imgAddr;
    //原价
    private String normalPrice;
    //现价(推广价)
    private String promotionPrice;
    //权重
    private Integer priority;
    //商品积分
    private Integer point;
    //创建时间
    private Date createTime;
    //最后更新的时间
    private Date lastEditTime;
    //可使用的状态 0下架 1在前端展示
    private Integer enableStatus;

    //图片详情列表，跟商品是多对一关系
    private List<ProductImg> productImgList;
    //商品类别，意见商品仅属于一个商品列表(一对一)
    private ProductCategory productCategory;
    //商铺实体类，标明属于哪个店铺(一对一)
    private Shop shop;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public List<ProductImg> getProductImgList() {
        return productImgList;
    }

    public void setProductImgList(List<ProductImg> productImgList) {
        this.productImgList = productImgList;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
