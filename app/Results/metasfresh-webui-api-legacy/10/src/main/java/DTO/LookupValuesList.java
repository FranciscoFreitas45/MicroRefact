package DTO;
 import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableSet;
import de.metas.util.GuavaCollectors;
import de.metas.util.lang.RepoIdAware;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
public class LookupValuesList implements Iterable<LookupValue>{

 public  LookupValuesList EMPTY;

 private  ImmutableListMultimap<Object,LookupValue> valuesById;

 private  boolean ordered;

 private  DebugProperties debugProperties;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public Set<Integer> getKeysAsInt(){
    return valuesById.values().stream().map(LookupValue::getIdAsInt).collect(ImmutableSet.toImmutableSet());
}


public Collection<LookupValue> getValues(){
    return valuesById.values();
}


public Set<Object> getKeys(){
    return valuesById.keySet();
}


public LookupValue getById(Object id){
    final ImmutableList<LookupValue> values = valuesById.get(normalizeId(id));
    return values.isEmpty() ? null : values.get(0);
}


public Collector<LookupValue,?,LookupValuesList> collect(DebugProperties debugProperties){
    final Supplier<ImmutableListMultimap.Builder<Object, LookupValue>> supplier = ImmutableListMultimap.Builder::new;
    final BiConsumer<ImmutableListMultimap.Builder<Object, LookupValue>, LookupValue> accumulator = (builder, item) -> builder.put(item.getId(), item);
    final BinaryOperator<ImmutableListMultimap.Builder<Object, LookupValue>> combiner = (builder1, builder2) -> builder1.putAll(builder2.build());
    final Function<ImmutableListMultimap.Builder<Object, LookupValue>, LookupValuesList> finisher = (builder) -> build(builder, debugProperties);
    return Collector.of(supplier, accumulator, combiner, finisher);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/collect"))

.queryParam("debugProperties",debugProperties);
Collector<LookupValue,?,LookupValuesList> aux = restTemplate.getForObject(builder.toUriString(),Collector<LookupValue,?,LookupValuesList>.class);
return aux;
}


}