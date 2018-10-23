package com.cn.lx.service;

import com.cn.lx.entity.ShopCategory;

import java.util.List;


/**
 * @author Steven Lu
 * @date 2018/10/23 -14:52
 */
public interface ShopCategoryService {
    /**
     * 根据查询条件获取ShopCategory列表
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
