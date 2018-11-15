package com.cn.lx.web.frontend;

import com.cn.lx.dto.ProductExecution;
import com.cn.lx.entity.Product;
import com.cn.lx.entity.ProductCategory;
import com.cn.lx.entity.Shop;
import com.cn.lx.service.ProductCategoryService;
import com.cn.lx.service.ProductService;
import com.cn.lx.service.ShopService;
import com.cn.lx.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Steven Lu
 * @date 2018/11/14 -0:27
 */
@Controller
@RequestMapping(value = "/frontend")
public class ShopDetailController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 根据店铺信息获取该店铺下的商品列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/listshopdetailpageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShopDetailPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //获取前台的shopId
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        Shop shop = null;
        List<ProductCategory> productCategoryList =null;
        if(shopId !=-1){
            //获取店铺Id为shopId的店铺信息
            shop = shopService.getByShopId(shopId);
            //获取店铺下的商品列表
            productCategoryList = productCategoryService.getProductCategoryList(shopId);
            modelMap.put("shop",shop);
            modelMap.put("productCategoryList",productCategoryList);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty shopId");
        }
        return modelMap;
    }

    /**
     * 根据条件峰列出改店铺下面的所有商品
     * @param request
     * @return
     */
    @RequestMapping(value = "/listproductsbyshop",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listProductsByShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //获取pageIndex,pageSize,shopId
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        //空值判断
        if ((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {
            // 尝试获取商品类别Id
            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
            // 尝试获取模糊查找的商品名
            String productName = HttpServletRequestUtil.getString(request, "productName");
            // 组合查询条件
            Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
            // 按照传入的查询条件以及分页信息返回相应商品列表以及总数
            ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }


    private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        //非空
        if(productCategoryId !=-1){
            //查询商品类别下的商品列表
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if(productName !=null){
            //查询包含名字的店铺列表
            productCondition.setProductName(productName);
        }
        //只允许显示上架的商品
        productCondition.setEnableStatus(1);
        return productCondition;
    }
}
