package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.lingxiang2014.entity.AdPosition;
import com.lingxiang2014.service.AdPositionService;
import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("adPositionDirective")
public class AdPositionDirective extends BaseDirective{

 private  String VARIABLE_NAME;

@Resource(name = "freeMarkerConfigurer")
 private  FreeMarkerConfigurer freeMarkerConfigurer;

@Resource(name = "adPositionServiceImpl")
 private  AdPositionService adPositionService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    AdPosition adPosition;
    Long id = getId(params);
    boolean useCache = useCache(env, params);
    String cacheRegion = getCacheRegion(env, params);
    if (useCache) {
        adPosition = adPositionService.find(id, cacheRegion);
    } else {
        adPosition = adPositionService.find(id);
    }
    if (body != null) {
        setLocalVariable(VARIABLE_NAME, adPosition, env, body);
    } else {
        if (adPosition != null && adPosition.getTemplate() != null) {
            try {
                Map<String, Object> model = new HashMap<String, Object>();
                model.put(VARIABLE_NAME, adPosition);
                Writer out = env.getOut();
                new Template("adTemplate", new StringReader(adPosition.getTemplate()), freeMarkerConfigurer.getConfiguration()).process(model, out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


}