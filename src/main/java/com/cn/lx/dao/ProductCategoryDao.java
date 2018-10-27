package com.cn.lx.dao;


import com.cn.lx.entity.ProductCategory;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/27 -21:49
 */
public interface ProductCategoryDao {
    /**
     * 通过shopId 查询商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);
}
