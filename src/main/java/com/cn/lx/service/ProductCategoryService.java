package com.cn.lx.service;

import com.cn.lx.entity.ProductCategory;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/27 -22:50
 */
public interface ProductCategoryService {
    /**
     * 查询指定店铺下所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);
}
