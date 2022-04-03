package com.lingxiang2014.controller.admin;
 import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.entity.Article;
import com.lingxiang2014.entity.ArticleCategory;
import com.lingxiang2014.service.ArticleCategoryService;
import com.lingxiang2014.DTO.Message;
@Controller("adminArticleCategoryController")
@RequestMapping("/admin/article_category")
public class ArticleCategoryController extends BaseController{

@Resource(name = "articleCategoryServiceImpl")
 private  ArticleCategoryService articleCategoryService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("articleCategoryTree", articleCategoryService.findTree());
    return "/admin/article_category/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    ArticleCategory articleCategory = articleCategoryService.find(id);
    model.addAttribute("articleCategoryTree", articleCategoryService.findTree());
    model.addAttribute("articleCategory", articleCategory);
    model.addAttribute("children", articleCategoryService.findChildren(articleCategory));
    return "/admin/article_category/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(ArticleCategory articleCategory,Long parentId,RedirectAttributes redirectAttributes){
    articleCategory.setParent(articleCategoryService.find(parentId));
    if (!isValid(articleCategory)) {
        return ERROR_VIEW;
    }
    articleCategory.setTreePath(null);
    articleCategory.setGrade(null);
    articleCategory.setChildren(null);
    articleCategory.setArticles(null);
    articleCategoryService.save(articleCategory);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(ArticleCategory articleCategory,Long parentId,RedirectAttributes redirectAttributes){
    articleCategory.setParent(articleCategoryService.find(parentId));
    if (!isValid(articleCategory)) {
        return ERROR_VIEW;
    }
    if (articleCategory.getParent() != null) {
        ArticleCategory parent = articleCategory.getParent();
        if (parent.equals(articleCategory)) {
            return ERROR_VIEW;
        }
        List<ArticleCategory> children = articleCategoryService.findChildren(parent);
        if (children != null && children.contains(parent)) {
            return ERROR_VIEW;
        }
    }
    articleCategoryService.update(articleCategory, "treePath", "grade", "children", "articles");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(ModelMap model){
    model.addAttribute("articleCategoryTree", articleCategoryService.findTree());
    return "/admin/article_category/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long id){
    ArticleCategory articleCategory = articleCategoryService.find(id);
    if (articleCategory == null) {
        return ERROR_MESSAGE;
    }
    Set<ArticleCategory> children = articleCategory.getChildren();
    if (children != null && !children.isEmpty()) {
        return Message.error("admin.articleCategory.deleteExistChildrenNotAllowed");
    }
    Set<Article> articles = articleCategory.getArticles();
    if (articles != null && !articles.isEmpty()) {
        return Message.error("admin.articleCategory.deleteExistArticleNotAllowed");
    }
    articleCategoryService.delete(id);
    return SUCCESS_MESSAGE;
}


}