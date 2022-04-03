package com.lingxiang2014.template.method;
 import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import com.lingxiang2014.util.SpringUtils;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
@Component("messageMethod")
public class MessageMethod implements TemplateMethodModel{


@SuppressWarnings("rawtypes")
public Object exec(List arguments){
    if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString())) {
        String message = null;
        String code = arguments.get(0).toString();
        if (arguments.size() > 1) {
            Object[] args = arguments.subList(1, arguments.size()).toArray();
            message = SpringUtils.getMessage(code, args);
        } else {
            message = SpringUtils.getMessage(code);
        }
        return new SimpleScalar(message);
    }
    return null;
}


}