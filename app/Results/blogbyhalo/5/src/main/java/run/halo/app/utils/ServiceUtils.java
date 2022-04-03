package run.halo.app.utils;
 import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
public class ServiceUtils {

private ServiceUtils() {
}
@NonNull
public Map<I,V> convertToMap(Collection<D> list,Function<D,I> keyFunction,Function<D,V> valueFunction){
    Assert.notNull(keyFunction, "Key function must not be null");
    Assert.notNull(valueFunction, "Value function must not be null");
    if (CollectionUtils.isEmpty(list)) {
        return Collections.emptyMap();
    }
    Map<I, V> resultMap = new HashMap<>();
    list.forEach(data -> resultMap.putIfAbsent(keyFunction.apply(data), valueFunction.apply(data)));
    return resultMap;
}


@NonNull
public Set<I> fetchProperty(Collection<T> datas,Function<T,I> mappingFunction){
    return CollectionUtils.isEmpty(datas) ? Collections.emptySet() : datas.stream().map(mappingFunction).collect(Collectors.toSet());
}


public boolean isEmptyId(Number id){
    return id == null || id.longValue() <= 0;
}


@NonNull
public Map<I,List<D>> convertToListMap(Collection<I> ids,Collection<D> list,Function<D,I> mappingFunction){
    Assert.notNull(mappingFunction, "mapping function must not be null");
    if (CollectionUtils.isEmpty(ids) || CollectionUtils.isEmpty(list)) {
        return Collections.emptyMap();
    }
    Map<I, List<D>> resultMap = new HashMap<>();
    list.forEach(data -> resultMap.computeIfAbsent(mappingFunction.apply(data), id -> new LinkedList<>()).add(data));
    ids.forEach(id -> resultMap.putIfAbsent(id, Collections.emptyList()));
    return resultMap;
}


@NonNull
public Page<T> buildEmptyPageImpl(Page<S> page){
    Assert.notNull(page, "Page result must not be null");
    return new PageImpl<>(Collections.emptyList(), page.getPageable(), page.getTotalElements());
}


@NonNull
public Pageable buildLatestPageable(int top,String sortProperty){
    Assert.isTrue(top > 0, "Top number must not be less than 0");
    Assert.hasText(sortProperty, "Sort property must not be blank");
    return PageRequest.of(0, top, Sort.by(Sort.Direction.DESC, sortProperty));
}


}