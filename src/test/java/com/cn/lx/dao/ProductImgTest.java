package com.cn.lx.dao;

import com.cn.lx.BaseTest;
import com.cn.lx.entity.ProductImg;
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
 * @date 2018/10/31 -14:20
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//根据命名符号来排序
public class ProductImgTest extends BaseTest {
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testABatchInsertProductImg(){
        //productId为1的商品里添加2个图片记录
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("图片描述1");
        productImg1.setPriority(1);
        productImg1.setProductId(1L);
        productImg1.setCreateTime(new Date());
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setImgDesc("图片描述2");
        productImg2.setPriority(2);
        productImg2.setProductId(1L);
        productImg2.setCreateTime(new Date());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2,effectNum); //影响的行数做验证
    }

    @Test
    public void testCDeleteProductImgByProductId(){
        //删除新增的记录
        long productId = 1L;
        int effectNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2,effectNum);
    }
}
