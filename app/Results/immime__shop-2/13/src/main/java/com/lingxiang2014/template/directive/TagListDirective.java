package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.entity.Tag;
import com.lingxiang2014.service.TagService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("tagListDirective")
public class TagListDirective extends BaseDirective{

 private  String VARIABLE_NAME;

@Resource(name = "tagServiceImpl")
 private  TagService tagService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    List<Tag> tags;
    boolean useCache = useCache(env, params);
    String cacheRegion = getCacheRegion(env, params);
    Integer count = getCount(params);
    List<Filter> filters = getFilters(params, Tag.class);
    List<Order> orders = getOrders(params);
    if (useCache) {
        tags = tagService.findList(count, filters, orders, cacheRegion);
    } else {
        tags = tagService.findList(count, filters, orders);
    }
    setLocalVariable(VARIABLE_NAME, tags, env, body);
}


}