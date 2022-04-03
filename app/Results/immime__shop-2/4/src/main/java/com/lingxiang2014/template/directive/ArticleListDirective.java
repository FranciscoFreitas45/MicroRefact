package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.entity.Article;
import com.lingxiang2014.entity.ArticleCategory;
import com.lingxiang2014.entity.Tag;
import com.lingxiang2014.service.ArticleCategoryService;
import com.lingxiang2014.service.ArticleService;
import com.lingxiang2014.service.TagService;
import com.lingxiang2014.util.FreemarkerUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.lingxiang2014.Interface.TagService;
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