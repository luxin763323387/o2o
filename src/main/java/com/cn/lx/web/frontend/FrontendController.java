package com.cn.lx.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Steven Lu
 * @date 2018/11/11 -13:43
 */
@Controller
@RequestMapping(value = "/frontend")
public class FrontendController {
    //显示首页
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private String index(){
        return "frontend/index";
    }

    //显示商品列表
    @RequestMapping(value = "/shoplist",method = RequestMethod.GET)
    private String showShopList(){
        return "frontend/shoplist";
    }

    //显示店铺详情图
    @RequestMapping(value = "/shopdetail",method = RequestMethod.GET)
    private String showShopDetail(){
        return "frontend/shopdetail";
    }

    //显示商品详情图
    @RequestMapping(value = "/productdetail",method = RequestMethod.GET)
    private String showProductDetail(){
        return "frontend/productdetail";
    }
}
