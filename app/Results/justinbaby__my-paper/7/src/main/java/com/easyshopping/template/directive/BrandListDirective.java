package com.easyshopping.template.directive;
 import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Brand;
import com.easyshopping.service.BrandService;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("brandListDirective")
public class BrandListDirective extends BaseDirective{

 private  String VARIABLE_NAME;

@Resource(name = "brandServiceImpl")
 private  BrandService brandService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    List<Brand> brands;
    boolean useCache = useCache(env, params);
    String cacheRegion = getCacheRegion(env, params);
    Integer count = getCount(params);
    List<Filter> filters = getFilters(params, Brand.class);
    List<Order> orders = getOrders(params);
    if (useCache) {
        brands = brandService.findList(count, filters, orders, cacheRegion);
    } else {
        brands = brandService.findList(count, filters, orders);
    }
    setLocalVariable(VARIABLE_NAME, brands, env, body);
}


}