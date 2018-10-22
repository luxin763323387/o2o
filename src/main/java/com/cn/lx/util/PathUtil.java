package com.cn.lx.util;

/**
 * @author Steven Lu
 * @date 2018/10/12 -16:39
 */
public class PathUtil {
    private static String seperator = System.getProperty("file.separator");
    //返回项目图片的根路径
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/Image/win/";
        }else{
            basePath="D:/Image/other/";
        }
        //系统"/"替换
        basePath = basePath.replace("/",seperator);
        return basePath;
    }

    //返回项目图片的子路径
    public static String getShopImagePath(long shopId){
        String imagePath = "upload/item/shop/" + shopId +"/";
        return imagePath.replace("/",seperator);
    }
}
