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


public Collector<JSONLookupValue,?,JSONLookupValuesList> collect(){
    final Supplier<List<JSONLookupValue>> supplier = ArrayList::new;
    final BiConsumer<List<JSONLookupValue>, JSONLookupValue> accumulator = List::add;
    final BinaryOperator<List<JSONLookupValue>> combiner = (l, r) -> {
        l.addAll(r);
        return l;
    };
    final Function<List<JSONLookupValue>, JSONLookupValuesList> finisher = JSONLookupValuesList::ofJSONLookupValuesList;
    return Collector.of(supplier, accumulator, combiner, finisher);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/collect"))

Collector<JSONLookupValue,?,JSONLookupValuesList> aux = restTemplate.getForObject(builder.toUriString(),Collector<JSONLookupValue,?,JSONLookupValuesList>.class);
return aux;
}


}