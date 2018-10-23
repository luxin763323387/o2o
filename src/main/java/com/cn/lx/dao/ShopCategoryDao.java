package com.cn.lx.dao;

import com.cn.lx.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/23 -14:17
 */
public interface ShopCategoryDao {

    /**
     * 根据传入的查询条件返回店铺类别列表
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

}
