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


}