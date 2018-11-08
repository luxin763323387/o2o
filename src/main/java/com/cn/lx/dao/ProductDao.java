package com.cn.lx.dao;

import com.cn.lx.dto.ImageHolder;
import com.cn.lx.dto.ProductExecution;
import com.cn.lx.entity.Product;
import com.cn.lx.exceptions.ProductOperationException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Steven Lu
 * @date 2018/10/31 -13:35
 */
public interface ProductDao {

    /**
     * 增加商品
     * @param product
     * @return
     */
    int insertProduct (Product product);

    /**
     * 通过productId查询你
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 查询商品列表并分页，可输入的条件有： 商品名（模糊），商品状态，店铺Id,商品类别
     * @param productCondition
     * @param rowIndex 从第几行开始取
     * @param pageSize 返回的条数是多少
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition,
                                   @Param("rowIndex")int rowIndex,@Param("pageSize") int pageSize);

    /**
     * 返回queryProductList的总数
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition")Product productCondition);
    /**
     * 更新商品
     * @param product
     * @return
     */
    int updateProduct(Product product);


}
