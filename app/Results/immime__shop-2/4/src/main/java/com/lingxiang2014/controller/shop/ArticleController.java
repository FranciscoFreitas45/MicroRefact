package com.lingxiang2014.controller.shop;
 import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.ResourceNotFoundException;
import com.lingxiang2014.entity.ArticleCategory;
import com.lingxiang2014.service.ArticleCategoryService;
import com.lingxiang2014.service.ArticleService;
@Controller("shopArticleController")
@RequestMapping("/article")
public class ArticleController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "articleServiceImpl")
 private  ArticleService articleService;

@Resource(name = "articleCategoryServiceImpl")
 private  ArticleCategoryService articleCategoryService;


@RequestMapping(value = "/hits/{id}", method = RequestMethod.GET)
@ResponseBody
public Long hits(Long id){
    return articleService.viewHits(id);
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