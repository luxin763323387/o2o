package com.cn.lx.dao;

import com.cn.lx.entity.ProductImg;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/31 -13:43
 */
public interface ProductImgDao {

    /**
     * 批量增加商品详情图
     * @param productImg
     * @return
     */
    int batchInsertProductImg (List<ProductImg> productImg);

    /**
     * 根据商品Id删除商品详情图
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(Long productId);

    /**
     * 根据productImgId查询详情图
     * @param productImgId
     * @return
     */
    List<ProductImg> queryProductImgList (long productImgId);
}
