package com.cn.lx.dao;

import com.cn.lx.entity.Shop;

/**
 * @author Steven Lu
 * @date 2018/10/12 -9:43
 */
public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
