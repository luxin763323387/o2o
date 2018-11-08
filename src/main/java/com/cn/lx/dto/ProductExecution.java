package com.cn.lx.dto;

import com.cn.lx.entity.Product;
import com.cn.lx.enums.ProductStateEnum;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/31 -15:50
 */
public class ProductExecution {
   //结果状态
   private int state;

   //状态标识
    private String stateInfo;

    //商品数量
    private int count;

    //操作的商品(增删查改)
    private Product product;

    //操作额度商品列表(查询商品使用)
    private List<Product> productList;

    //商品操作失败的时候使用的构造器
    public ProductExecution(ProductStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //商品操作成功的时候使用的构造器
    public ProductExecution(ProductStateEnum stateEnum,Product product){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }

    //商品列表操作成功时使用的构造器
    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productList = productList;
    }

    public ProductExecution() {

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
