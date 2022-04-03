package com.easyshopping.template.directive;
 import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.entity.Seo;
import com.easyshopping.entity.Seo.Type;
import com.easyshopping.service.SeoService;
import com.easyshopping.util.FreemarkerUtils;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.easyshopping.Interface.SeoService;
@Component("seoDirective")
public class SeoDirective extends BaseDirective{

 private  String TYPE_PARAMETER_NAME;

 private  String VARIABLE_NAME;

@Resource(name = "seoServiceImpl")
 private  SeoService seoService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    Type type = FreemarkerUtils.getParameter(TYPE_PARAMETER_NAME, Type.class, params);
    Seo seo;
    boolean useCache = useCache(env, params);
    String cacheRegion = getCacheRegion(env, params);
    if (useCache) {
        seo = seoService.find(type, cacheRegion);
    } else {
        seo = seoService.find(type);
    }
    setLocalVariable(VARIABLE_NAME, seo, env, body);
}


}