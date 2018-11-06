package com.cn.lx.service.impl;

import com.cn.lx.dao.ProductDao;
import com.cn.lx.dao.ProductImgDao;
import com.cn.lx.dto.ImageHolder;
import com.cn.lx.dto.ProductExecution;
import com.cn.lx.entity.Product;
import com.cn.lx.entity.ProductImg;
import com.cn.lx.enums.ProductStateEnum;
import com.cn.lx.exceptions.ProductOperationException;
import com.cn.lx.service.ProductService;
import com.cn.lx.util.ImageUtil;
import com.cn.lx.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/11/1 -13:08
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;


    @Override
    @Transactional
    //1.处理缩略图，获取或略图相对路径并赋值给product
    //2.往tb_product写入商品信息，获取productId
    //3.结合productId批量处理商品信息图
    //4.将商品详情图列表批量插入tb_product_img中
    public ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                       List<ImageHolder> productImgHolderList) throws ProductOperationException {
       //空值判断
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null){
            //给商品设置上默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认上架的状态
            product.setEnableStatus(1);
            //若商品缩略图部位空则添加
            if(thumbnail != null){
                addThumbnail(product,thumbnail);
            }
            try{
                //创建商品信息
                int effectedNum = productDao.insertProduct(product);
                if(effectedNum <= 0 ){
                    throw new ProductOperationException("创建店铺失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品失败"+e.toString());
            }
            //若商品详情图不为空则添加
            if(productImgHolderList != null && productImgHolderList.size()>0){
                addProductImgList(product,productImgHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else{
            //传参为空则返回空值错误信息
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }


    @Override
    /**
     * 返回ProductId进行查询
     */
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        return null;
    }

    /**
     * 添加缩略图
     *
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        // 获取图片存储路径，这里直接存放到相应店铺的文件夹底下
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }

    /**
     * 批量添加图片
     *
     * @param product
     * @param productImgHolderList
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        // 获取图片存储路径，这里直接存放到相应店铺的文件夹底下
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        // 遍历图片一次去处理，并添加进productImg实体类里
        for (ImageHolder productImgHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        // 如果确实是有图片需要添加的，就执行批量添加操作
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败:" + e.toString());
            }
        }
    }
}
