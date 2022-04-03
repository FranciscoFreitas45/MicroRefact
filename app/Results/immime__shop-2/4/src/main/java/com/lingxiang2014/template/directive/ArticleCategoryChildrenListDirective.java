package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.lingxiang2014.entity.ArticleCategory;
import com.lingxiang2014.service.ArticleCategoryService;
import com.lingxiang2014.util.FreemarkerUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("articleCategoryChildrenListDirective")
public class ArticleCategoryChildrenListDirective extends BaseDirective{

 private  String ARTICLE_CATEGORY_ID_PARAMETER_NAME;

 private  String VARIABLE_NAME;

@Resource(name = "articleCategoryServiceImpl")
 private  ArticleCategoryService articleCategoryService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    Long articleCategoryId = FreemarkerUtils.getParameter(ARTICLE_CATEGORY_ID_PARAMETER_NAME, Long.class, params);
    ArticleCategory articleCategory = articleCategoryService.find(articleCategoryId);
    List<ArticleCategory> articleCategories;
    if (articleCategoryId != null && articleCategory == null) {
        articleCategories = new ArrayList<ArticleCategory>();
    } else {
        boolean useCache = useCache(env, params);
        String cacheRegion = getCacheRegion(env, params);
        Integer count = getCount(params);
        if (useCache) {
            articleCategories = articleCategoryService.findChildren(articleCategory, count, cacheRegion);
        } else {
            articleCategories = articleCategoryService.findChildren(articleCategory, count);
        }
    }
    setLocalVariable(VARIABLE_NAME, articleCategories, env, body);
}


}