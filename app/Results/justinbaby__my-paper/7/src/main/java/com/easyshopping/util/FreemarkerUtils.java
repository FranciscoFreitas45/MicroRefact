package com.easyshopping.util;
 import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import com.easyshopping.CommonAttributes;
import com.easyshopping.EnumConverter;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.DeepUnwrap;
@SuppressWarnings("unchecked")
public class FreemarkerUtils {

 private  ConvertUtilsBean convertUtils;

/**
 * 不可实例化
 */
private FreemarkerUtils() {
}
public String process(String template,Map<String,?> model,Configuration configuration){
    if (template == null) {
        return null;
    }
    if (configuration == null) {
        configuration = new Configuration();
    }
    StringWriter out = new StringWriter();
    new Template("template", new StringReader(template), configuration).process(model, out);
    return out.toString();
}


public void setVariables(Map<String,Object> variables,Environment env){
    Assert.notNull(variables);
    Assert.notNull(env);
    for (Entry<String, Object> entry : variables.entrySet()) {
        String name = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof TemplateModel) {
            env.setVariable(name, (TemplateModel) value);
        } else {
            env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
        }
    }
}


public void setVariable(String name,Object value,Environment env){
    Assert.hasText(name);
    Assert.notNull(env);
    if (value instanceof TemplateModel) {
        env.setVariable(name, (TemplateModel) value);
    } else {
        env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
    }
}


@SuppressWarnings("rawtypes")
@Override
public Object convert(Object value,Class targetType){
    if (super.lookup(targetType) == null) {
        if (targetType.isEnum()) {
            super.register(new EnumConverter(targetType), targetType);
        } else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
            ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
            arrayConverter.setOnlyFirstToString(false);
            super.register(arrayConverter, targetType);
        }
    }
    return super.convert(value, targetType);
}


public TemplateModel getVariable(String name,Environment env){
    Assert.hasText(name);
    Assert.notNull(env);
    return env.getVariable(name);
}


public T getParameter(String name,Class<T> type,Map<String,TemplateModel> params){
    Assert.hasText(name);
    Assert.notNull(type);
    Assert.notNull(params);
    TemplateModel templateModel = params.get(name);
    if (templateModel == null) {
        return null;
    }
    Object value = DeepUnwrap.unwrap(templateModel);
    return (T) convertUtils.convert(value, type);
}


}