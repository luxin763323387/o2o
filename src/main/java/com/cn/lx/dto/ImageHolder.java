package com.cn.lx.dto;

import java.io.InputStream;

/**
 * @author Steven Lu
 * @date 2018/10/31 -16:32
 */
public class ImageHolder {
    private String imageName;
    private InputStream image;

    //通过构造器给成员变量赋值
    public ImageHolder(String imageName, InputStream image){
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
