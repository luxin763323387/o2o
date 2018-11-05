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

    /**
     * 通过productId查询你
     * @param productId
     * @return
     */
    Product queryProductId(Long productId);

    /**
     * 更新商品
     * @param product
     * @return
     */
    int updateProduct(Product product);


}
