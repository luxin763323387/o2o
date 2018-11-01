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
}
