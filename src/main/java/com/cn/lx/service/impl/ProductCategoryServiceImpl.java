package com.cn.lx.service.impl;

import com.cn.lx.dao.ProductCategoryDao;
import com.cn.lx.dao.ShopCategoryDao;
import com.cn.lx.dto.ProductCategoryExecution;
import com.cn.lx.entity.ProductCategory;
import com.cn.lx.enums.ProductCategoryStateEnum;
import com.cn.lx.exceptions.ProductCategoryException;
import com.cn.lx.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryException {
        if(productCategoryList != null && productCategoryList.size() >0){
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new ProductCategoryException("batchAddProductCategory error" + e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }
}