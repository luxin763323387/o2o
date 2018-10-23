package com.cn.lx.web.Shopadmin;


import com.cn.lx.dto.ShopExecution;
import com.cn.lx.entity.PersonInfo;
import com.cn.lx.entity.Shop;
import com.cn.lx.enums.ShopStateEnum;
import com.cn.lx.exceptions.ShopOperationException;
import com.cn.lx.service.ShopService;
import com.cn.lx.util.HttpServletRequestUtil;
import com.cn.lx.util.ImageUtil;
import com.cn.lx.util.PathUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Steven Lu
 * @date 2018/10/18 -13:55
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "registershop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        //1.接受并转化相应的参数，包括店铺信息及图片信息
        /**
         * 获取前端的信息转换成实体类
         */
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try{
            shop = mapper.readValue(shopStr,Shop.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }

        /**
         * 上传文件用CommonsMultipartFile
         * 获取前端的文件流放到shopImg里
         */
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","上传图片不能为空");
            return modelMap;
        }


        //2.注册店铺
        if(shop != null && shopImg !=null){
            PersonInfo owner = new PersonInfo();
            //Session TODO
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
                if(se.getState() == ShopStateEnum.CHECK.getState()){
                    modelMap.put("success",true);
                }  else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",se.getStateInfo());
                }
            } catch(ShopOperationException e){
                e.printStackTrace();
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入店铺信息");
            return modelMap;
        }
    }

   /* private static void inputStreamToFile(InputStream ins, File file){
        FileOutputStream os = null;
        try{
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer))!= -1){
                os.write(buffer,0,bytesRead);
            }
        }catch (Exception e){
            throw new RuntimeException("调用inputStreamToFile产生异常" + e.getMessage());
        }finally {
            try{
                if(os != null){//输出流
                    os.close();
                }
                if(ins != null){//输入流
                    ins.close();
                }
            }catch (IOException e){
                throw new RuntimeException("调用inputStreamToFile产生异常" + e.getMessage());
            }
        }
    }*/
}
