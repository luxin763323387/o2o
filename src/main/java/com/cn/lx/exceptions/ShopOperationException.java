package com.cn.lx.exceptions;

/**
 * @author Steven Lu
 * @date 2018/10/17 -16:16
 */
public class ShopOperationException extends RuntimeException{

    private static final long serialVersionUID = 2341446884822298905L;
    public ShopOperationException(String msg){
        super(msg);
    }

}
