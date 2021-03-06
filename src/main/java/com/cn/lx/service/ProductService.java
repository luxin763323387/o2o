package com.cn.lx.service;


import com.cn.lx.dto.ImageHolder;
import com.cn.lx.dto.ProductExecution;
import com.cn.lx.entity.Product;
import com.cn.lx.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/31 -15:48
 */
public interface ProductService {
    /**
     * 添加商品信息及图片处理
     * @param product
     * @param thumbnail 缩略图
     * @param productImgList 详情图
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException;

    /**
     * 通过Id查询Product
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 查询列表并分页，通过店铺查询/商品类别查询/商品名(模糊)/状态查询
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition,int pageIndex, int pageSize);

    /**
     * 修改店铺信息，缩略图及详情图
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
        throws ProductOperationException;
        }
