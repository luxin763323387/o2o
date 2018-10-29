package com.cn.lx.exceptions;

/**
 * @author Steven Lu
 * @date 2018/10/17 -16:16
 */
public class ProductCategoryException extends RuntimeException{


    private static final long serialVersionUID = -9150735653564748885L;

    public ProductCategoryException(String msg){
        super(msg);
    }

}
