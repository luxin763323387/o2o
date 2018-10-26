package com.cn.lx.service.impl;

import com.cn.lx.dao.ShopDao;
import com.cn.lx.dto.ShopExecution;
import com.cn.lx.entity.Shop;
import com.cn.lx.enums.ShopStateEnum;
import com.cn.lx.exceptions.ShopOperationException;
import com.cn.lx.service.ShopService;
import com.cn.lx.util.ImageUtil;
import com.cn.lx.util.PageCalculator;
import com.cn.lx.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/16 -16:21
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;


    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        //将页码转换成行码
        int rowIndex = PageCalculator.calcuateRowIndex(pageIndex,pageSize);
        //根据条件查询，调用店铺相关的列表
        List<Shop> shopList = shopDao.queryShopList(shopCondition,rowIndex,pageSize);
        //根据相同的条件查询，返回店铺的总数
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if(shopList != null){
            se.setShopList(shopList);
            se.setCount(count);
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
        if(shop == null || shop.getShopId()==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            //1 判断是否要处理图片
            try{
            if (shopImgInputStream != null && fileName !=null && !"".equals(fileName)){
                Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                if(tempShop.getShopImg() != null){
                    ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                }
                addShopImg(shop,shopImgInputStream,fileName);
            }
            //2 更新店铺信息
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.updateShop(shop);
            if(effectedNum <= 0){
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }else{
                shop = shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS,shop);
            }}catch (Exception e){
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException {
        //空值判断
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if(effectedNum <= 0){
                throw new ShopOperationException("店铺创建失败");
            }else{
                if(shopImgInputStream != null){
                    // 存储图片
                    try{
                        addShopImg(shop, shopImgInputStream,fileName);
                    }catch (Exception e){
                        throw new ShopOperationException("addShop error:" + e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if(effectedNum <= 0){
                        throw new ShopOperationException("更新图片地址失败失败");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
}

    private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
        shop.setShopImg(shopImgAddr);
    }
}
