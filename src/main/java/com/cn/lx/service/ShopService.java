package com.cn.lx.service;

import com.cn.lx.dto.ImageHolder;
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
    /**
     * 根据shopCondition 分页查询返回相应的店铺列表
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
    /**
     * 通过店铺Id获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 修改店铺信息，包括图片处理
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail)throws ShopOperationException;

    /**
     * 注册店铺信息，包括图片处理
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop,  ImageHolder thumbnail)throws ShopOperationException;
}
