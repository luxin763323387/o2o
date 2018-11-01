package com.cn.lx.dto;

import com.cn.lx.entity.Shop;
import com.cn.lx.enums.ShopStateEnum;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/16 -14:30
 */
public class ShopExecution {
    //结果状态
    private int state;

    //状态表示
    private String stateInfo;

    //店铺的数量
    private int count;

    //操作的shop的(增删改店铺使用)
    private Shop shop;

    //操作的shop的(查询店铺使用)
    private List<Shop> shopList;

    public ShopExecution(){

    }

    // 店铺操作失败的时候使用的构造器
    public ShopExecution(ShopStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 店铺操作成功的时候使用的构造器
    public ShopExecution(ShopStateEnum stateEnum,Shop shop){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    // 店铺列表操作成功的时候使用的构造器
    public ShopExecution(ShopStateEnum stateEnum,List<Shop> shop){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}

