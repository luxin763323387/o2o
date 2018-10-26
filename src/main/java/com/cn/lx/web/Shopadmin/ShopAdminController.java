package com.cn.lx.web.Shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Steven Lu
 * @date 2018/10/22 -15:52
 */
@Controller
@RequestMapping(value = "shopadmin", method = {RequestMethod.GET})
/**
 * 主要用来解析路由并转发到相应的html中
 */
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        // 转发至店铺注册/编辑页面
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }
}
