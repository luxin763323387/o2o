package com.cn.lx.service;

import com.cn.lx.dto.ShopExecution;
import com.cn.lx.entity.Shop;
import com.cn.lx.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

/**
 * @author Steven Lu
 * @date 2018/10/16 -16:18
 */
public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName)throws ShopOperationException;
}
