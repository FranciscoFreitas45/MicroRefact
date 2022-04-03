package de.metas.ui.web.window.datatypes;
 import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import com.google.common.collect.ImmutableMap;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@ToString
@EqualsAndHashCode
public class DebugProperties {

 public  DebugProperties EMPTY;

 private  ImmutableMap<String,Object> map;


public DebugProperties withProperty(String propertyName,Object propertyValue){
    final Object existingValue = map.get(propertyName);
    if (Objects.equals(propertyValue, existingValue)) {
        return this;
    }
    final LinkedHashMap<String, Object> newMap = new LinkedHashMap<>(map);
    if (propertyValue == null) {
        newMap.remove(propertyName);
    } else {
        newMap.put(propertyName, propertyValue);
    }
    return ofNullableMap(newMap);
}


public Map<String,Object> toMap(){
    return map;
}


public boolean isEmpty(){
    return map.isEmpty();
}


public DebugProperties ofNullableMap(Map<String,?> map){
    if (map == null || map.isEmpty()) {
        return EMPTY;
    }
    return new DebugProperties(ImmutableMap.copyOf(map));
}


}