package com.cn.lx.enums;



/**
 * @author Steven Lu
 * @date 2018/10/31 -15:59
 */
public enum ProductStateEnum {
    OFFLINE(-1,"非法商品"),DOWN(0,"下架"),SUCCESS(1,"上线"),INNER_ERROR(-1001,"操作失败"),EMPTY(-1002,"商品为空");
    private int state;
    private String stateInfo;

    private ProductStateEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }


    public String getStateInfo() {
        return stateInfo;
    }

    public static ProductStateEnum stateof(int index){
        for(ProductStateEnum state:values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
