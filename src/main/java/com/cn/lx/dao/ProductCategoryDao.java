package com.cn.lx.dao;


import com.cn.lx.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/27 -21:49
 */
public interface ProductCategoryDao {
    /**
     * 通过shopId 查询商品类别
     *
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 批量增加商品类别
     *
     * @param productCategoriesList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoriesList);

    /**
     * 删除商品类别
     *
     * @param productCategoryId
     * @param shopId            必须shopId也要正确才能删除
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
