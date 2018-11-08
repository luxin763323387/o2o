package com.cn.lx.util;

/**
 * @author Steven Lu
 * @date 2018/10/26 -16:48
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex,int pageSize){

        /**
         * 当pageIndex = 1,那么就是 （1-1）*pageSize （第一页）从0开始5条 （0-4）
         * 当pageIndex = 2,那么就是 （2-1）*pageSize （第二页）从5开始5条 （5-9）
         */
        return (pageIndex>0)? (pageIndex -1)*pageSize :0;
    }
}
