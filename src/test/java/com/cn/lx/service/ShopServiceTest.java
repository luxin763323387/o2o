package com.cn.lx.service;

import com.cn.lx.BaseTest;
import com.cn.lx.dto.ShopExecution;
import com.cn.lx.entity.Area;
import com.cn.lx.entity.PersonInfo;
import com.cn.lx.entity.Shop;
import com.cn.lx.entity.ShopCategory;
import com.cn.lx.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author Steven Lu
 * @date 2018/10/17 -16:36
 */
public class ShopServiceTest extends BaseTest {
   @Autowired
   private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(3);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺2");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("D:/Image/naicha.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop, is,shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }

}
