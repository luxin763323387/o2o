package com.cn.lx.dao;

import com.cn.lx.BaseTest;
import com.cn.lx.entity.Product;
import com.cn.lx.entity.ProductCategory;
import com.cn.lx.entity.ProductImg;
import com.cn.lx.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author Steven Lu
 * @date 2018/10/31 -14:46
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;


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

    @Test
    public void testBQueryProductList() {
        Product productCondition = new Product();
        // 分页查询，预期返回三条结果
        List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
        assertEquals(3, productList.size());
        // 查询名称为测试的商品总数
        int count = productDao.queryProductCount(productCondition);
        assertEquals(28, count);
        // 使用商品名称模糊查询，预期返回两条结果
        productCondition.setProductName("JU");
        productCondition.setEnableStatus(1);
        productList = productDao.queryProductList(productCondition, 0, 28);
        assertEquals(1, productList.size());
        count = productDao.queryProductCount(productCondition);
        assertEquals(1, count);
    }
    @Test
    @Ignore
    public void testCQueryByProductById() throws Exception{
        long productId = 1L;
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(productId);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setImgDesc("测试图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(productId);
        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2,effectNum);
        Product product = productDao.queryProductById(productId);
        assertEquals(2,product.getProductImgList().size());
        effectNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2,effectNum);
    }

    @Test

    public void TestDUpdateProduct() throws Exception{
        Product product = new Product();
        ProductCategory pc = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(5L);
        pc.setProductCategoryId(16L);
        product.setProductId(3L);
        product.setShop(shop);
        product.setProductName("第二个产品2");
        product.setProductCategory(pc);
        int effectedNum = productDao.updateProduct(product);
        assertEquals(1,effectedNum);
    }

}
