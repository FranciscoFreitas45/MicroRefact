package com.easyshopping.template.directive;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Article;
import com.easyshopping.entity.ArticleCategory;
import com.easyshopping.entity.Tag;
import com.easyshopping.service.ArticleCategoryService;
import com.easyshopping.service.ArticleService;
import com.easyshopping.service.TagService;
import com.easyshopping.util.FreemarkerUtils;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("articleListDirective")
public class ArticleListDirective extends BaseDirective{

 private  String ARTICLE_CATEGORY_ID_PARAMETER_NAME;

 private  String TAG_IDS_PARAMETER_NAME;

 private  String VARIABLE_NAME;

@Resource(name = "articleServiceImpl")
 private  ArticleService articleService;

@Resource(name = "articleCategoryServiceImpl")
 private  ArticleCategoryService articleCategoryService;

@Resource(name = "tagServiceImpl")
 private  TagService tagService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    Long articleCategoryId = FreemarkerUtils.getParameter(ARTICLE_CATEGORY_ID_PARAMETER_NAME, Long.class, params);
    Long[] tagIds = FreemarkerUtils.getParameter(TAG_IDS_PARAMETER_NAME, Long[].class, params);
    ArticleCategory articleCategory = articleCategoryService.find(articleCategoryId);
    List<Tag> tags = tagService.findList(tagIds);
    List<Article> articles;
    if ((articleCategoryId != null && articleCategory == null) || (tagIds != null && tags.isEmpty())) {
        articles = new ArrayList<Article>();
    } else {
        boolean useCache = useCache(env, params);
        String cacheRegion = getCacheRegion(env, params);
        Integer count = getCount(params);
        List<Filter> filters = getFilters(params, Article.class);
        List<Order> orders = getOrders(params);
        if (useCache) {
            articles = articleService.findList(articleCategory, tags, count, filters, orders, cacheRegion);
        } else {
            articles = articleService.findList(articleCategory, tags, count, filters, orders);
        }
    }
    setLocalVariable(VARIABLE_NAME, articles, env, body);
}


}