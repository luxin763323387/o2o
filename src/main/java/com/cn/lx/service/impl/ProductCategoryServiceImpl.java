package com.cn.lx.service.impl;

import com.cn.lx.dao.ProductCategoryDao;
import com.cn.lx.dao.ShopCategoryDao;
import com.cn.lx.entity.ProductCategory;
import com.cn.lx.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/27 -22:55
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }
}
