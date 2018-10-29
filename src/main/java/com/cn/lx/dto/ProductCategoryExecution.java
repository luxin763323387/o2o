package com.cn.lx.dto;

import com.cn.lx.entity.ProductCategory;
import com.cn.lx.enums.ProductCategoryStateEnum;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/29 -16:06
 */
public class ProductCategoryExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;


    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(){}

    //操作失败的商品列表的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    //操作成功的商品列表的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,List<ProductCategory> productCategoryList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
