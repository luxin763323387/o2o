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

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private String index(){
        return "frontend/index";
    }
}