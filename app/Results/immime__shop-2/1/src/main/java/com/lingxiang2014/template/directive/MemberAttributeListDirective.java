package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.lingxiang2014.entity.MemberAttribute;
import com.lingxiang2014.service.MemberAttributeService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("memberAttributeListDirective")
public class MemberAttributeListDirective extends BaseDirective{

 private  String VARIABLE_NAME;

@Resource(name = "memberAttributeServiceImpl")
 private  MemberAttributeService memberAttributeService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    List<MemberAttribute> memberAttributes;
    boolean useCache = useCache(env, params);
    String cacheRegion = getCacheRegion(env, params);
    if (useCache) {
        memberAttributes = memberAttributeService.findList(cacheRegion);
    } else {
        memberAttributes = memberAttributeService.findList();
    }
    setLocalVariable(VARIABLE_NAME, memberAttributes, env, body);
}


}