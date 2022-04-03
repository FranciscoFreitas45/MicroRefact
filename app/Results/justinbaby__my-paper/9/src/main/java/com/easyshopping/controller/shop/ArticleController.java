package com.easyshopping.controller.shop;
 import javax.annotation.Resource;
import com.easyshopping.Pageable;
import com.easyshopping.ResourceNotFoundException;
import com.easyshopping.entity.ArticleCategory;
import com.easyshopping.service.ArticleCategoryService;
import com.easyshopping.service.ArticleService;
import com.easyshopping.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller("shopArticleController")
@RequestMapping("/article")
public class ArticleController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "articleServiceImpl")
 private  ArticleService articleService;

@Resource(name = "articleCategoryServiceImpl")
 private  ArticleCategoryService articleCategoryService;

@Resource(name = "searchServiceImpl")
 private  SearchService searchService;


@RequestMapping(value = "/hits/{id}", method = RequestMethod.GET)
@ResponseBody
public Long hits(Long id){
    return articleService.viewHits(id);
}


@RequestMapping(value = "/search", method = RequestMethod.GET)
public String search(String keyword,Integer pageNumber,ModelMap model){
    if (StringUtils.isEmpty(keyword)) {
        return ERROR_VIEW;
    }
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("articleKeyword", keyword);
    model.addAttribute("page", searchService.search(keyword, pageable));
    return "shop/article/search";
}


@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
public String list(Long id,Integer pageNumber,ModelMap model){
    ArticleCategory articleCategory = articleCategoryService.find(id);
    if (articleCategory == null) {
        throw new ResourceNotFoundException();
    }
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("articleCategory", articleCategory);
    model.addAttribute("page", articleService.findPage(articleCategory, null, pageable));
    return "/shop/article/list";
}


}