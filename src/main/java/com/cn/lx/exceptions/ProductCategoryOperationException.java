package com.cn.lx.exceptions;

/**
 * @author Steven Lu
 * @date 2018/10/17 -16:16
 */
public class ProductCategoryOperationException extends RuntimeException{


    private static final long serialVersionUID = -6798955313074272839L;

    public ProductCategoryOperationException(String msg){
        super(msg);
    }

}
