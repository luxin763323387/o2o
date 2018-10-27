package com.cn.lx.dao;

import com.cn.lx.BaseTest;
import com.cn.lx.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/27 -22:12
 */
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryProductCategoryList() throws Exception{
        long shopId = 24L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺的自定义类别数为:" + productCategoryList.size());
    }
}
