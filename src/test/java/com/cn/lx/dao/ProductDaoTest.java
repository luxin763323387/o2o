package com.cn.lx.dao;

import com.cn.lx.BaseTest;
import com.cn.lx.entity.Product;
import com.cn.lx.entity.ProductCategory;
import com.cn.lx.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;


/**
 * @author Steven Lu
 * @date 2018/10/31 -14:46
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void testAInsertProduct(){
        Shop shop5 = new Shop();
        shop5.setShopId(5L);
        ProductCategory pc3 = new ProductCategory();
        pc3.setProductCategoryId(3L);
        //初始化三个商品实例添加进shopId为5的店铺
        //同时商品类别Id为3
        Product product1 = new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试Desc1");
        product1.setImgAddr("test1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop5);
        product1.setProductCategory(pc3);
        Product product2 = new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试Desc2");
        product2.setImgAddr("test2");
        product2.setPriority(2);
        product2.setEnableStatus(0);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop5);
        product2.setProductCategory(pc3);
        Product product3 = new Product();
        product3.setProductName("test3");
        product3.setProductDesc("测试Desc3");
        product3.setImgAddr("test3");
        product3.setPriority(3);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop5);
        product3.setProductCategory(pc3);
        //判断是否成功
        int effectedNum1 = productDao.insertProduct(product1);
        assertEquals(1,effectedNum1);
        int effectedNum2 = productDao.insertProduct(product2);
        assertEquals(1,effectedNum2);
        int effectedNum3 = productDao.insertProduct(product3);
        assertEquals(1,effectedNum3);
    }
}
