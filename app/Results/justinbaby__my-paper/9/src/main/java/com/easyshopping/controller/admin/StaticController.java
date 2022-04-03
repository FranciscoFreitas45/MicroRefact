package com.easyshopping.controller.admin;
 import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.entity.Article;
import com.easyshopping.entity.ArticleCategory;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.ProductCategory;
import com.easyshopping.service.ArticleCategoryService;
import com.easyshopping.service.ArticleService;
import com.easyshopping.service.ProductCategoryService;
import com.easyshopping.service.ProductService;
import com.easyshopping.service.StaticService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductCategoryService;
@Controller("adminStaticController")
@RequestMapping("/admin/static")
public class StaticController extends BaseController{

@Resource(name = "articleServiceImpl")
 private  ArticleService articleService;

@Resource(name = "articleCategoryServiceImpl")
 private  ArticleCategoryService articleCategoryService;

@Resource(name = "productServiceImpl")
 private  ProductService productService;

@Resource(name = "productCategoryServiceImpl")
 private  ProductCategoryService productCategoryService;

@Resource(name = "staticServiceImpl")
 private  StaticService staticService;


@RequestMapping(value = "/build", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> build(BuildType buildType,Long articleCategoryId,Long productCategoryId,Date beginDate,Date endDate,Integer first,Integer count){
    long startTime = System.currentTimeMillis();
    if (beginDate != null) {
        Calendar calendar = DateUtils.toCalendar(beginDate);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        beginDate = calendar.getTime();
    }
    if (endDate != null) {
        Calendar calendar = DateUtils.toCalendar(endDate);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        endDate = calendar.getTime();
    }
    if (first == null || first < 0) {
        first = 0;
    }
    if (count == null || count <= 0) {
        count = 50;
    }
    int buildCount = 0;
    boolean isCompleted = true;
    if (buildType == BuildType.index) {
        buildCount = staticService.buildIndex();
    } else if (buildType == BuildType.article) {
        ArticleCategory articleCategory = articleCategoryService.find(articleCategoryId);
        List<Article> articles = articleService.findList(articleCategory, beginDate, endDate, first, count);
        for (Article article : articles) {
            buildCount += staticService.build(article);
        }
        first += articles.size();
        if (articles.size() == count) {
            isCompleted = false;
        }
    } else if (buildType == BuildType.product) {
        ProductCategory productCategory = productCategoryService.find(productCategoryId);
        List<Product> products = productService.findList(productCategory, beginDate, endDate, first, count);
        for (Product product : products) {
            buildCount += staticService.build(product);
        }
        first += products.size();
        if (products.size() == count) {
            isCompleted = false;
        }
    } else if (buildType == BuildType.other) {
        buildCount = staticService.buildOther();
    }
    long endTime = System.currentTimeMillis();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("first", first);
    map.put("buildCount", buildCount);
    map.put("buildTime", endTime - startTime);
    map.put("isCompleted", isCompleted);
    return map;
}


}