package com.cn.lx.service;

import com.cn.lx.BaseTest;
import com.cn.lx.dto.ImageHolder;
import com.cn.lx.dto.ProductExecution;
import com.cn.lx.entity.Product;
import com.cn.lx.entity.ProductCategory;
import com.cn.lx.entity.Shop;
import com.cn.lx.enums.ProductCategoryStateEnum;
import com.cn.lx.enums.ProductStateEnum;
import com.cn.lx.exceptions.ShopOperationException;
import com.cn.lx.util.PageCalculator;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Steven Lu
 * @date 2018/11/1 -14:32
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    @Ignore
    public void testAddProduct() throws ShopOperationException, FileNotFoundException{
        //创建shopId为5L且productCategoryId为3的商品变量赋值并给成员变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(5L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(3L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductCategoryStateEnum.SUCCESS.getState());
        //创建缩略图文件流
        File thumbnailFile = new File("D:/Image/naicha.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(),is); //传入图片流进入ImageHolder 进行构建
        //创建两个商品详情图文件流并将他们添加到详情图列表
        File productImg1 = new File("D:/Image/naicha.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("D:/Image/laop2.jpeg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));
        //添加商品并验证
        ProductExecution pe = productService.addProduct(product,thumbnail,productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
    }

    @Test
    public void testModifyProduct() throws ShopOperationException,FileNotFoundException{
        //创建shopId为5L且productCategoryId为3的商品变量赋值并给成员变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(5L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(3L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        //创建缩略图文件流
        File thumbnailFile = new File("D:/Image/naicha.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(),is);
        //创建两个商品详情图文件流并将他们的添加到详情图列表中
        File productImg1 = new File("D:/Image/laop2.jpeg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("D:/Image/laop2.jpeg");
        InputStream is2 = new FileInputStream(productImg1);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));
        //添加商品验证
        ProductExecution pe = productService.modifyProduct(product,thumbnail,productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
    }
}
