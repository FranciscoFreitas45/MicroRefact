package de.metas.ui.web.cache;
 import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.common.collect.ImmutableMap;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode
public class ETag {

 private  long version;

 private  ImmutableMap<String,String> attributes;

 private  String _etagString;


public ETag overridingAttributes(Map<String,String> overridingAttributes){
    if (overridingAttributes.isEmpty()) {
        return this;
    }
    if (attributes.isEmpty()) {
        return new ETag(version, ImmutableMap.copyOf(overridingAttributes));
    }
    final Map<String, String> newAttributes = new HashMap<>(attributes);
    newAttributes.putAll(overridingAttributes);
    return new ETag(version, ImmutableMap.copyOf(newAttributes));
}


public ETag of(long version,Map<String,String> attributes){
    return new ETag(version, ImmutableMap.copyOf(attributes));
}


public String toETagString(){
    String etagString = _etagString;
    if (etagString == null) {
        _etagString = etagString = buildETagString();
    }
    return etagString;
}


@Override
public String toString(){
    return toETagString();
}


public String buildETagString(){
    final StringBuilder etagString = new StringBuilder();
    etagString.append("v=").append(version);
    if (!attributes.isEmpty()) {
        final String attributesStr = attributes.entrySet().stream().sorted(Comparator.comparing(entry -> entry.getKey())).map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("#"));
        etagString.append("#").append(attributesStr);
    }
    return etagString.toString();
}


}