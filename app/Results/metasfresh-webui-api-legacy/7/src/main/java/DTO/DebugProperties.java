package DTO;
 import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import com.google.common.collect.ImmutableMap;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
public class DebugProperties {

 public  DebugProperties EMPTY;

 private  ImmutableMap<String,Object> map;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/withProperty"))

.queryParam("propertyName",propertyName);
.queryParam("propertyValue",propertyValue);
DebugProperties aux = restTemplate.getForObject(builder.toUriString(),DebugProperties.class);
return aux;
}


}