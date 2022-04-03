package com.easyshopping.controller.shop;
 import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.easyshopping.Pageable;
import com.easyshopping.ResourceNotFoundException;
import com.easyshopping.entity.Attribute;
import com.easyshopping.entity.Brand;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.Product.OrderType;
import com.easyshopping.entity.ProductCategory;
import com.easyshopping.entity.Promotion;
import com.easyshopping.entity.Tag;
import com.easyshopping.service.BrandService;
import com.easyshopping.service.ProductCategoryService;
import com.easyshopping.service.ProductService;
import com.easyshopping.service.PromotionService;
import com.easyshopping.service.SearchService;
import com.easyshopping.service.TagService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.easyshopping.Interface.PromotionService;
import com.easyshopping.Interface.TagService;
import com.easyshopping.Interface.SearchService;
@Controller("shopProductController")
@RequestMapping("/product")
public class ProductController extends BaseController{

@Resource(name = "productServiceImpl")
 private  ProductService productService;

@Resource(name = "productCategoryServiceImpl")
 private  ProductCategoryService productCategoryService;

@Resource(name = "brandServiceImpl")
 private  BrandService brandService;

@Resource(name = "promotionServiceImpl")
 private  PromotionService promotionService;

@Resource(name = "tagServiceImpl")
 private  TagService tagService;

@Resource(name = "searchServiceImpl")
 private  SearchService searchService;


@RequestMapping(value = "/hits/{id}", method = RequestMethod.GET)
@ResponseBody
public Long hits(Long id){
    return productService.viewHits(id);
}


@RequestMapping(value = "/search", method = RequestMethod.GET)
public String search(String keyword,BigDecimal startPrice,BigDecimal endPrice,OrderType orderType,Integer pageNumber,Integer pageSize,ModelMap model){
    if (StringUtils.isEmpty(keyword)) {
        return ERROR_VIEW;
    }
    Pageable pageable = new Pageable(pageNumber, pageSize);
    model.addAttribute("orderTypes", OrderType.values());
    model.addAttribute("productKeyword", keyword);
    model.addAttribute("startPrice", startPrice);
    model.addAttribute("endPrice", endPrice);
    model.addAttribute("orderType", orderType);
    model.addAttribute("page", searchService.search(keyword, startPrice, endPrice, orderType, pageable));
    return "shop/product/search";
}


@RequestMapping(value = "/history", method = RequestMethod.GET)
@ResponseBody
public List<Product> history(Long[] ids){
    return productService.findList(ids);
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Long brandId,Long promotionId,Long[] tagIds,BigDecimal startPrice,BigDecimal endPrice,OrderType orderType,Integer pageNumber,Integer pageSize,HttpServletRequest request,ModelMap model){
    Brand brand = brandService.find(brandId);
    Promotion promotion = promotionService.find(promotionId);
    List<Tag> tags = tagService.findList(tagIds);
    Pageable pageable = new Pageable(pageNumber, pageSize);
    model.addAttribute("orderTypes", OrderType.values());
    model.addAttribute("brand", brand);
    model.addAttribute("promotion", promotion);
    model.addAttribute("tags", tags);
    model.addAttribute("startPrice", startPrice);
    model.addAttribute("endPrice", endPrice);
    model.addAttribute("orderType", orderType);
    model.addAttribute("pageNumber", pageNumber);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("page", productService.findPage(null, brand, promotion, tags, null, startPrice, endPrice, true, true, null, false, null, null, orderType, pageable));
    return "/shop/product/list";
}


}