package com.cn.lx.web.Shopadmin;

import com.cn.lx.dto.ImageHolder;
import com.cn.lx.dto.ProductExecution;
import com.cn.lx.entity.Product;
import com.cn.lx.entity.ProductCategory;
import com.cn.lx.entity.ProductImg;
import com.cn.lx.entity.Shop;
import com.cn.lx.enums.ProductStateEnum;
import com.cn.lx.exceptions.ProductOperationException;
import com.cn.lx.service.ProductCategoryService;
import com.cn.lx.service.ProductService;
import com.cn.lx.util.CodeUtil;
import com.cn.lx.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Steven Lu
 * @date 2018/11/1 -15:40
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    //支持上传商品详情图的最大数量
    private static final int IMAGEMAXCOUNT = 6;

    @RequestMapping(value = "/getproductlistbyshop" ,method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getproductListByShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //获取前台传过来的页码
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        //获取前台每页展示商品的数量上限
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        //获取session中的店铺Id
        Shop currentShop  = (Shop) request.getSession().getAttribute("currentShop");
        if((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)){
            // 获取传入的需要检索的条件，包括是否需要从某个商品类别以及模糊查找商品名去筛选某个店铺下的商品列表
            // 筛选的条件可以进行排列组合
            long productCategoryId = HttpServletRequestUtil.getInt(request,"productCategoryId");
            String productCategoryName = HttpServletRequestUtil.getString(request,"productName");
            Product productCondition = compactProductCondition(currentShop.getShopId(),productCategoryId,productCategoryName);
            //传入查询条件进行分页查询，返回相应商品列表以及总数
            ProductExecution pe = productService.getProductList(productCondition,pageIndex,pageSize);
            modelMap.put("productList",pe.getProductList());
            modelMap.put("count",pe.getCount());
            modelMap.put("success",toString());
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    private Product compactProductCondition(Long shopId, long productCategoryId, String productCategoryName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        //若有指定类别则添加进去
        if(productCategoryId >-1){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        //若有商品名模糊的则添加
        if(productCategoryName != null){
            productCondition.setProductName(productCategoryName);
        }
        return productCondition;
    }

    /**
     * 通过商品Id查询
     * @param productId
     * @return
     */
    @RequestMapping(value ="/getproductbyid",method = RequestMethod.GET)
    @ResponseBody
    //@RequestParam 解析productId
    private Map<String,Object> getPrductById(@RequestParam Long productId){
        Map<String,Object> modelMap = new HashMap<>();
        //非空判断
        if(productId > -1){
            //获取店铺信息
            Product product = productService.getProductById(productId);
            //获取该店铺的商品列表信息
            List<ProductCategory> productCategoryList = productCategoryService.
                    getProductCategoryList(product.getShop().getShopId());
            modelMap.put("product",product);
            modelMap.put("productCategoryList",productCategoryList);
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","没有商品Id");
        }
        return modelMap;
    }

    /**
     * 添加商品
     * @param request
     * @return
     */
    @RequestMapping(value = "/addproduct",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //引入验证码
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }

        //接受前端参数的变量的初始化，包括商品，缩略图，详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null; //定义实例
        String productStr = HttpServletRequestUtil.getString(request, "productStr"); //从request中获取信息
        MultipartHttpServletRequest multipartRequest = null; //处理文件流
        ImageHolder thumbnail = null;   //定义缩略图的名字
        List<ImageHolder> productImgList = new ArrayList<>(); //保存商品详情图文件流列表和名字列表
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver( //从requestSession中获取文件流
                request.getSession().getServletContext());
        try{
            //若请求中存在文件流，则取出相关的文件(包括缩略图和详情图)
            if(multipartResolver.isMultipart(request)){
                multipartRequest = (MultipartHttpServletRequest) request;
                //取出缩略图并构建ImageHolder对象
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(),thumbnailFile.getInputStream());
                //取出详情图列表比构建List<ImageHolder>列表对象，最多支持6张图片上传
                for(int i = 0; i < IMAGEMAXCOUNT; i++){
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest
                            .getFile("productImg"+i);
                    if(productImgFile != null){
                        //若去除的第i个详情图片文件流不为空，则将其加入详情列表
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),productImgFile.getInputStream());
                        productImgList.add(productImg);
                    }else{
                        //若取出的第i个详情图片文件流为空，则终止循环
                        break;
                    }
                }
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg","上传图片不能为空");
                return modelMap;
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
        try{
            //尝试获取前端传过来的表单String流并转换成其他Product实体类
            product = mapper.readValue(productStr,Product.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }

        //若Product信息，缩略图及详情图列表为非空，则开始进行商品的添加
        if(product != null && thumbnail != null && productImgList.size() > 0){
            try {
                //从session中获取当前店铺Id并赋值给product，减少对前端数据的依赖，防止用户修改id上传文件
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);
                //执行添加操作
                ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.toString());
                }
            }catch (ProductOperationException e){
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入商品信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyProduct (HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //商品编辑页面时调用上下架操作的时候调用
        //前者为惊喜验证码判断，后者则跳过验证码判断
        boolean statusChange = HttpServletRequestUtil.getBoolean(request,"statusChange");
        //进行验证码判断
        if(!statusChange && !CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入了错误的验证码");
            return modelMap;
        }
        //接受前端参数的变量初始化，包括商品，缩略图，详情列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //若请求中存在文件流，则取出相关文件(包括缩略图和详情图)
        try{
            if(multipartResolver.isMultipart(request)){
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;//向下转型
                //取出缩略图并构建ImageHolder对象
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
                if(thumbnailFile != null){
                    thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(),thumbnailFile.getInputStream());
                }
                //取出详情图并构建列表List<ImageHolder>列表对象,最多支持上传6张
                for(int i = 0; i < IMAGEMAXCOUNT; i++){
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest
                            .getFile("productImg"+i);
                    if(productImgFile !=null){
                        //若取出的第i个详情图问件流不为空，贼将其加入详情图列表
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),thumbnailFile.getInputStream());
                        productImgList.add(productImg);
                    }else {
                        //若取出第i个详情图片文件流为空，终止循环
                        break;
                    }
                }
            }
            else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "上传图片不能为空");
                return modelMap;
            }
        } catch (Exception e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
        try{
            String productStr = HttpServletRequestUtil.getString(request,"productStr");
            //从前端传过来的表单String流并将其转换成Product实体类
                product = mapper.readValue(productStr,Product.class);
            } catch (Exception e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        //非空判断
        if(product != null){
            try{
                //从session中获取当前店铺的id并赋值给product，减少对前端数据的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);
                //开始进行商品
                ProductExecution pe = productService.modifyProduct(product,thumbnail,productImgList);
                if(pe.getState() == ProductStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (RuntimeException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入店铺");
        }
        return modelMap;
    }

}
