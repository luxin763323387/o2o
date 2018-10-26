package com.cn.lx.dao;

import com.cn.lx.BaseTest;
import com.cn.lx.entity.Area;
import com.cn.lx.entity.PersonInfo;
import com.cn.lx.entity.Shop;
import com.cn.lx.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Steven Lu
 * @date 2018/10/12 -10:53
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;



    @Test
    @Ignore
    public void testQueryShopListAndCount(){
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,0,5);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表大小:"+shopList.size());
        System.out.println("店铺总数:"+count);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(2L);
        shopCondition.setShopCategory(shopCategory);
        shopList = shopDao.queryShopList(shopCondition,0,2);
        System.out.println("店铺列表大小:"+shopList.size());
        count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺总数"+count);

    }

    @Test
    @Ignore
    public void testShopQueryByShopId(){
        long shopId = 5L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop);
        System.out.println(shop.getArea().getAreaId());
        System.out.println(shop.getArea().getAreaName());
    }

    @Test
    @Ignore
    public void testInsertShop(){
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
        shop.setShopName("测试店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(5L);
        shop.setShopDesc("测试");
        shop.setShopAddr("测试");
        shop.setPhone("测试");
        shop.setShopImg("test");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }
}
