package com.cn.lx.dao;

import com.cn.lx.entity.Product;

/**
 * @author Steven Lu
 * @date 2018/10/31 -13:35
 */
public interface ProductDao {
    /**
     * 增加商品
     * @param product
     * @return
     */
    int insertProduct (Product product);
}
