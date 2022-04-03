package org.jeecgframework.web.cgform.engine.tag;
 import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
@Component("mutiLangTag")
public class MutiLangTag implements TemplateDirectiveModel{

 private  Logger LOG;


@SuppressWarnings("unchecked")
public String getAttribute(Map params,String name){
    String value = null;
    if (params.containsKey(name)) {
        TemplateModel paramValue = (TemplateModel) params.get(name);
        try {
            value = ((TemplateScalarModel) paramValue).getAsString();
        } catch (TemplateModelException e) {
            LOG.error("get attribute '{}' error", name, e);
        }
    }
    return value;
}


public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    // 多语言key
    String langKey = getAttribute(params, "langKey");
    if (langKey == null) {
        throw new TemplateException("Can not find attribute 'name' in data tag", env);
    }
    String langArg = getAttribute(params, "langArg");
    // update-begin--author:scott Date:20170219 for:class声明类，减少内存占用写法修改------
    MutiLangServiceI mutiLangService = ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
    // update-end--author:scott Date:20170219 for:class声明类，减少内存占用写法修改------
    String lang_context = mutiLangService.getLang(langKey, langArg);
    Writer out = env.getOut();
    out.append(lang_context);
}


}