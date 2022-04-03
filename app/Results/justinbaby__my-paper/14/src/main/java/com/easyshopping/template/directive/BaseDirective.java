package com.easyshopping.template.directive;
 import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Order.Direction;
import com.easyshopping.util.FreemarkerUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import com.easyshopping.DTO.Filter;
public class BaseDirective implements TemplateDirectiveModel{

 private  String USE_CACHE_PARAMETER_NAME;

 private  String CACHE_REGION_PARAMETER_NAME;

 private  String ID_PARAMETER_NAME;

 private  String COUNT_PARAMETER_NAME;

 private  String ORDER_BY_PARAMETER_NAME;

 private  String ORDER_BY_ITEM_SEPARATOR;

 private  String ORDER_BY_FIELD_SEPARATOR;


public void setLocalVariable(String name,Object value,Environment env,TemplateDirectiveBody body){
    TemplateModel sourceVariable = FreemarkerUtils.getVariable(name, env);
    FreemarkerUtils.setVariable(name, value, env);
    body.render(env.getOut());
    FreemarkerUtils.setVariable(name, sourceVariable, env);
}


public void setLocalVariables(Map<String,Object> variables,Environment env,TemplateDirectiveBody body){
    Map<String, Object> sourceVariables = new HashMap<String, Object>();
    for (String name : variables.keySet()) {
        TemplateModel sourceVariable = FreemarkerUtils.getVariable(name, env);
        sourceVariables.put(name, sourceVariable);
    }
    FreemarkerUtils.setVariables(variables, env);
    body.render(env.getOut());
    FreemarkerUtils.setVariables(sourceVariables, env);
}


public boolean useCache(Environment env,Map<String,TemplateModel> params){
    Boolean useCache = FreemarkerUtils.getParameter(USE_CACHE_PARAMETER_NAME, Boolean.class, params);
    return useCache != null ? useCache : true;
}


public List<Filter> getFilters(Map<String,TemplateModel> params,Class<?> type,String ignoreProperties){
    List<Filter> filters = new ArrayList<Filter>();
    PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(type);
    for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
        String propertyName = propertyDescriptor.getName();
        Class<?> propertyType = propertyDescriptor.getPropertyType();
        if (!ArrayUtils.contains(ignoreProperties, propertyName) && params.containsKey(propertyName)) {
            Object value = FreemarkerUtils.getParameter(propertyName, propertyType, params);
            filters.add(Filter.eq(propertyName, value));
        }
    }
    return filters;
}


public Long getId(Map<String,TemplateModel> params){
    return FreemarkerUtils.getParameter(ID_PARAMETER_NAME, Long.class, params);
}


public Integer getCount(Map<String,TemplateModel> params){
    return FreemarkerUtils.getParameter(COUNT_PARAMETER_NAME, Integer.class, params);
}


public List<Order> getOrders(Map<String,TemplateModel> params,String ignoreProperties){
    String orderBy = StringUtils.trim(FreemarkerUtils.getParameter(ORDER_BY_PARAMETER_NAME, String.class, params));
    List<Order> orders = new ArrayList<Order>();
    if (StringUtils.isNotEmpty(orderBy)) {
        String[] orderByItems = orderBy.split(ORDER_BY_ITEM_SEPARATOR);
        for (String orderByItem : orderByItems) {
            if (StringUtils.isNotEmpty(orderByItem)) {
                String property = null;
                Direction direction = null;
                String[] orderBys = orderByItem.split(ORDER_BY_FIELD_SEPARATOR);
                if (orderBys.length == 1) {
                    property = orderBys[0];
                } else if (orderBys.length >= 2) {
                    property = orderBys[0];
                    try {
                        direction = Direction.valueOf(orderBys[1]);
                    } catch (IllegalArgumentException e) {
                        continue;
                    }
                } else {
                    continue;
                }
                if (!ArrayUtils.contains(ignoreProperties, property)) {
                    orders.add(new Order(property, direction));
                }
            }
        }
    }
    return orders;
}


public String getCacheRegion(Environment env,Map<String,TemplateModel> params){
    String cacheRegion = FreemarkerUtils.getParameter(CACHE_REGION_PARAMETER_NAME, String.class, params);
    return cacheRegion != null ? cacheRegion : env.getTemplate().getName();
}


}