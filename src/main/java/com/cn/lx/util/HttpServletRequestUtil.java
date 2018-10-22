package com.cn.lx.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Steven Lu
 * @date 2018/10/18 -14:07
 */
public class HttpServletRequestUtil {
    /**
     * 从request获取一个kdy
     * 在将key转换成整型，失败返回-1
     * @param request
     * @param key
     * @return
     */
    public static int getInt(HttpServletRequest request,String key){
        try{
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * 转换成长整型
     * @param request
     * @param key
     * @return
     */
    public static long getLong(HttpServletRequest request,String key){
        try{
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }
    public static Double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if (result != null) {
                result = result.trim();//去掉两侧的空格
            }
            if ("".equals(result)) {
                result = null;//如果是空就为空
            }
            return result;
        } catch (Exception e) {
            return null;//返回空值
        }
    }
}
