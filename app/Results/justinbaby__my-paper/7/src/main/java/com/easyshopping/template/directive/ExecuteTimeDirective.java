package com.easyshopping.template.directive;
 import java.io.IOException;
import java.util.Map;
import com.easyshopping.interceptor.ExecuteTimeInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("executeTimeDirective")
public class ExecuteTimeDirective extends BaseDirective{

 private  String VARIABLE_NAME;


@SuppressWarnings("rawtypes")
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    if (requestAttributes != null) {
        Long executeTime = (Long) requestAttributes.getAttribute(ExecuteTimeInterceptor.EXECUTE_TIME_ATTRIBUTE_NAME, RequestAttributes.SCOPE_REQUEST);
        if (executeTime != null) {
            setLocalVariable(VARIABLE_NAME, executeTime, env, body);
        }
    }
}


}