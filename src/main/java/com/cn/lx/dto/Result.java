package com.cn.lx.dto;

/**
 * @author Steven Lu
 * @date 2018/10/27 -23:14
 */

import com.cn.lx.entity.Shop;

/**
 * 封装json对象，所有返回结果都是用他
 * @param <T>
 */
public class Result<T> {
    //是否成功标志
    private boolean success;
    //成功是返回数据
    private T data;
    //错误信息
    private String errorMsg;
    private int errorCode;

    //成功时的构造器
    public Result(boolean success,T data){
        this.success = success;
        this.data = data;
    }

    //失败时的构造器
    public Result(boolean success, int errorCode, String errorMsg){
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
