package de.metas.ui.web.document.filter.sql;
 import java.util.Map;
import com.google.common.collect.ImmutableMap;
import de.metas.util.GuavaCollectors;
import de.metas.util.NumberUtils;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
public class SqlDocumentFilterConverterContext {

 public  SqlDocumentFilterConverterContext EMPTY;

 private  ImmutableMap<String,Object> params;


public ImmutableMap<String,Object> toImmutableMap(Map<String,Object> map){
    if (map instanceof ImmutableMap) {
        return (ImmutableMap<String, Object>) map;
    } else {
        return map.entrySet().stream().filter(entry -> entry.getKey() != null && entry.getValue() != null).collect(GuavaCollectors.toImmutableMap());
    }
}


public int getPropertyAsInt(String name,int defaultValue){
    final ImmutableMap<String, Object> params = getParams();
    final Object value = params.get(name);
    return NumberUtils.asInt(value, defaultValue);
}


public SqlDocumentFilterConverterContext ofMap(Map<String,Object> map){
    if (map == null || map.isEmpty()) {
        return EMPTY;
    }
    return new SqlDocumentFilterConverterContext(toImmutableMap(map));
}


}