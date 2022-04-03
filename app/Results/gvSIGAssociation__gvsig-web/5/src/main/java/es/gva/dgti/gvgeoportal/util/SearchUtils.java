package es.gva.dgti.gvgeoportal.util;
 import java.util.Map;
import java.util.Set;
import org.gvnix.web.datatables.util.QuerydslUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.path.PathBuilder;
public class SearchUtils {

 private  Logger LOGGER;

 private  String SEPARATOR_FIELDS;

 private  String SEPARATOR_FIELDS_ESCAPED;

 private  String IS_NULL;

 private  String IS_NOT_NULL;

@SuppressWarnings("rawtypes")
 private  Map<Class,Set<String>> persistentTransientProperties;


public BooleanBuilder createPropertyExpression(BooleanBuilder searchExpression,String paramName,String propertyName,Class<?> propertyClass,Map<String,String> params,PathBuilder<?> entity,ConversionService conversionService_dtt){
    boolean enabled;
    Object searchObj = null;
    try {
        enabled = !params.get(paramName).isEmpty();
    } catch (Exception e) {
        enabled = false;
    }
    if (enabled) {
        searchObj = conversionService_dtt.convert(params.get(paramName), propertyClass);
        searchExpression.and(QuerydslUtils.createObjectExpression(entity, propertyName, searchObj, conversionService_dtt));
    }
    return searchExpression;
}


}