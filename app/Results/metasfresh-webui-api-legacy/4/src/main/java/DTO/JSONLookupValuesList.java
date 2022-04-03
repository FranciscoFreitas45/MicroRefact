package DTO;
 import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.window.datatypes.DebugProperties;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
public class JSONLookupValuesList {

 static  JSONLookupValuesList EMPTY;

 private  List<JSONLookupValue> values;

 private  String defaultValue;

 private  LinkedHashMap<String,Object> otherProperties;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


@JsonAnyGetter
public Map<String,Object> getOtherProperties(){
    return otherProperties == null ? ImmutableMap.of() : otherProperties;
}


public List<JSONLookupValue> getValues(){
    return values;
}


public String getDefaultValue(){
    return defaultValue;
}


public JSONLookupValuesList ofLookupValuesList(LookupValuesList lookupValues,String adLanguage){
    if (lookupValues == null || lookupValues.isEmpty()) {
        return EMPTY;
    }
    Stream<JSONLookupValue> jsonValues = lookupValues.getValues().stream().map(lookupValue -> JSONLookupValue.ofLookupValue(lookupValue, adLanguage));
    if (!lookupValues.isOrdered()) {
        jsonValues = jsonValues.sorted(Comparator.comparing(JSONLookupValue::getCaption));
    }
    final ImmutableList<JSONLookupValue> jsonValuesList = jsonValues.collect(ImmutableList.toImmutableList());
    final DebugProperties otherProperties = lookupValues.getDebugProperties();
    return new JSONLookupValuesList(jsonValuesList, otherProperties);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofLookupValuesList"))

.queryParam("lookupValues",lookupValues);
.queryParam("adLanguage",adLanguage);
JSONLookupValuesList aux = restTemplate.getForObject(builder.toUriString(),JSONLookupValuesList.class);
return aux;
}


}