package com.cn.lx.web.Shopadmin;

import com.cn.lx.dto.Result;
import com.cn.lx.entity.ProductCategory;
import com.cn.lx.entity.Shop;
import com.cn.lx.enums.ProductCategoryStateEnum;
import com.cn.lx.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author Steven Lu
 * @date 2018/10/27 -23:05
 */
@Controller
@RequestMapping(value = "shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist",method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request ){
       /* //to be removed
        Shop shop = new Shop();
        shop.setShopId(24L);
        request.getSession().setAttribute("currentShop",shop);*/

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> list = null;
        if(currentShop != null && currentShop.getShopId()>0){
            list = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true,list);
        }else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false,ps.getState(),ps.getStateInfo());
        }
    }
}
