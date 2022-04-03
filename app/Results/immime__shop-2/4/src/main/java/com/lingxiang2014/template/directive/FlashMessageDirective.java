package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import com.lingxiang2014.Message;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("flashMessageDirective")
public class FlashMessageDirective extends BaseDirective{

 public  String FLASH_MESSAGE_ATTRIBUTE_NAME;

 private  String VARIABLE_NAME;


@SuppressWarnings("rawtypes")
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    if (requestAttributes != null) {
        Message message = (Message) requestAttributes.getAttribute(FLASH_MESSAGE_ATTRIBUTE_NAME, RequestAttributes.SCOPE_REQUEST);
        if (body != null) {
            setLocalVariable(VARIABLE_NAME, message, env, body);
        } else {
            if (message != null) {
                Writer out = env.getOut();
                out.write("$.message(\"" + message.getType() + "\", \"" + message.getContent() + "\");");
            }
        }
    }
}


}