package de.metas.ui.web.window.datatypes;
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
@Immutable
@EqualsAndHashCode(exclude = "debugProperties")
public class LookupValuesList implements Iterable<LookupValue>{

 public  LookupValuesList EMPTY;

 private  ImmutableListMultimap<Object,LookupValue> valuesById;

 private  boolean ordered;

@Getter
 private  DebugProperties debugProperties;


public LookupValuesList ordered(boolean ordered){
    return this.ordered != ordered ? new LookupValuesList(valuesById, ordered, debugProperties) : this;
}


public Set<Integer> getKeysAsInt(){
    return valuesById.values().stream().map(LookupValue::getIdAsInt).collect(ImmutableSet.toImmutableSet());
}


public Collection<LookupValue> getValues(){
    return valuesById.values();
}


public boolean isEmpty(){
    return valuesById.isEmpty() && debugProperties.isEmpty();
}


public LookupValuesList offsetAndLimit(int offset,int maxSize){
    final int offsetEffective = offset <= 0 ? 0 : offset;
    final long maxSizeEffective = maxSize <= 0 ? Long.MAX_VALUE : maxSize;
    if (offsetEffective <= 0 && valuesById.size() <= maxSizeEffective) {
        return this;
    }
    return stream().skip(offsetEffective).limit(maxSizeEffective).collect(collect(debugProperties));
}


public Set<Object> getKeys(){
    return valuesById.keySet();
}


public LookupValuesList fromCollection(Collection<? extends LookupValue> lookupValues){
    if (lookupValues.isEmpty()) {
        return EMPTY;
    }
    final ImmutableListMultimap<Object, LookupValue> valuesById = lookupValues.stream().collect(GuavaCollectors.toImmutableListMultimap(LookupValue::getId));
    final boolean ordered = true;
    return new LookupValuesList(valuesById, ordered, DebugProperties.EMPTY);
}


public LookupValuesList filter(Predicate<LookupValue> filter,int offset,int maxSize){
    final int offsetEffective = offset <= 0 ? 0 : offset;
    final long maxSizeEffective = maxSize <= 0 ? Long.MAX_VALUE : maxSize;
    return stream().filter(filter).skip(offsetEffective).limit(maxSizeEffective).collect(collect(debugProperties));
}


public LookupValuesList fromNullable(LookupValue lookupValue){
    if (lookupValue == null) {
        return EMPTY;
    }
    final ImmutableListMultimap<Object, LookupValue> valuesById = ImmutableListMultimap.of(lookupValue.getId(), lookupValue);
    final boolean ordered = true;
    return new LookupValuesList(valuesById, ordered, DebugProperties.EMPTY);
}


@Override
public Iterator<LookupValue> iterator(){
    return getValues().iterator();
}


public boolean containsId(Object id){
    return valuesById.containsKey(normalizeId(id));
}


public T transform(Function<LookupValuesList,T> transformation){
    return transformation.apply(this);
}


public LookupValuesList removeAll(LookupValuesList valuesToRemove){
    // If nothing to remove, we can return this
    if (valuesToRemove.isEmpty()) {
        return this;
    }
    // If this list is empty, we can return it
    if (valuesById.isEmpty()) {
        return this;
    }
    // Create a new values map which does not contain the the values to be removed
    final ImmutableListMultimap<Object, LookupValue> valuesByIdNew = valuesById.entries().stream().filter(entry -> !valuesToRemove.containsId(entry.getKey())).collect(GuavaCollectors.toImmutableListMultimap());
    // If nothing was filtered out, we can return this
    if (valuesById.size() == valuesByIdNew.size()) {
        return this;
    }
    // 
    return new LookupValuesList(valuesByIdNew, ordered, debugProperties);
}


public LookupValuesList notOrdered(){
    return ordered(false);
}


public LookupValuesList build(ImmutableListMultimap.Builder<Object,LookupValue> valuesByIdBuilder,DebugProperties debugProperties){
    final ImmutableListMultimap<Object, LookupValue> valuesById = valuesByIdBuilder.build();
    if (valuesById.isEmpty() && (debugProperties == null || debugProperties.isEmpty())) {
        return EMPTY;
    }
    final boolean ordered = true;
    final LookupValuesList result = new LookupValuesList(valuesById, ordered, debugProperties);
    return result;
}


public Stream<LookupValue> stream(){
    return getValues().stream();
}


public LookupValue getById(Object id){
    final ImmutableList<LookupValue> values = valuesById.get(normalizeId(id));
    return values.isEmpty() ? null : values.get(0);
}


public Object normalizeId(Object id){
    if (id == null) {
        return null;
    } else if (id instanceof RepoIdAware) {
        return ((RepoIdAware) id).getRepoId();
    } else {
        return id;
    }
}


public LookupValuesList limit(int maxSize){
    final long maxSizeEffective = maxSize <= 0 ? Long.MAX_VALUE : maxSize;
    if (valuesById.size() <= maxSizeEffective) {
        return this;
    }
    return stream().limit(maxSize).collect(collect(debugProperties));
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("values", valuesById.values()).add("ordered", ordered).add("debug", debugProperties.isEmpty() ? null : debugProperties).toString();
}


public Collector<LookupValue,?,LookupValuesList> collect(DebugProperties debugProperties){
    final Supplier<ImmutableListMultimap.Builder<Object, LookupValue>> supplier = ImmutableListMultimap.Builder::new;
    final BiConsumer<ImmutableListMultimap.Builder<Object, LookupValue>, LookupValue> accumulator = (builder, item) -> builder.put(item.getId(), item);
    final BinaryOperator<ImmutableListMultimap.Builder<Object, LookupValue>> combiner = (builder1, builder2) -> builder1.putAll(builder2.build());
    final Function<ImmutableListMultimap.Builder<Object, LookupValue>, LookupValuesList> finisher = (builder) -> build(builder, debugProperties);
    return Collector.of(supplier, accumulator, combiner, finisher);
}


public LookupValuesList addIfAbsent(LookupValue lookupValue){
    if (valuesById.containsKey(lookupValue.getId())) {
        return this;
    } else {
        final ImmutableListMultimap<Object, LookupValue> valuesByIdNew = ImmutableListMultimap.<Object, LookupValue>builder().putAll(valuesById).put(lookupValue.getId(), lookupValue).build();
        return new LookupValuesList(valuesByIdNew, ordered, debugProperties);
    }
}


public boolean isOrdered(){
    return ordered;
}


}