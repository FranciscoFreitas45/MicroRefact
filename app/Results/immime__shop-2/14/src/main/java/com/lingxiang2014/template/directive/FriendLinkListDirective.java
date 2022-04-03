package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.entity.FriendLink;
import com.lingxiang2014.service.FriendLinkService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("friendLinkListDirective")
public class FriendLinkListDirective extends BaseDirective{

 private  String VARIABLE_NAME;

@Resource(name = "friendLinkServiceImpl")
 private  FriendLinkService friendLinkService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    List<FriendLink> friendLinks;
    boolean useCache = useCache(env, params);
    String cacheRegion = getCacheRegion(env, params);
    Integer count = getCount(params);
    List<Filter> filters = getFilters(params, FriendLink.class);
    List<Order> orders = getOrders(params);
    if (useCache) {
        friendLinks = friendLinkService.findList(count, filters, orders, cacheRegion);
    } else {
        friendLinks = friendLinkService.findList(count, filters, orders);
    }
    setLocalVariable(VARIABLE_NAME, friendLinks, env, body);
}


}