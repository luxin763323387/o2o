package com.cn.lx.web.frontend;

import com.cn.lx.entity.Product;
import com.cn.lx.service.ProductService;
import com.cn.lx.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Steven Lu
 * @date 2018/11/15 -19:54
 */
@Controller
@RequestMapping(value = "/frontend")
public class ProductDetailController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/listproductdetailpageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listProductDetailPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        long productId = HttpServletRequestUtil.getInt(request,"productId");
        Product product = null;
        //空值判断
        if(productId != -1){
            //获取商品信息，包含详情图
            product = productService.getProductById(productId);
            modelMap.put("product",product);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty productId");
        }
        return modelMap;
    }
}
