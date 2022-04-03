package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.lingxiang2014.entity.Seo;
import com.lingxiang2014.entity.Seo.Type;
import com.lingxiang2014.service.SeoService;
import com.lingxiang2014.util.FreemarkerUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.lingxiang2014.Interface.SeoService;
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