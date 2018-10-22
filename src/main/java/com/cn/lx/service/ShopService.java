package com.cn.lx.service;

import com.cn.lx.dto.ShopExecution;
import com.cn.lx.entity.Shop;

import java.io.File;

/**
 * @author Steven Lu
 * @date 2018/10/16 -16:18
 */
public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
