package de.metas.ui.web.window.model.lookup;
 import java.util.List;
import java.util.Optional;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache;
import de.metas.cache.CCache.CCacheStats;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext.Builder;
import de.metas.util.Check;
public class CachedLookupDataSourceFetcherAdapter implements LookupDataSourceFetcher{

 private  String NAME;

 private  LookupDataSourceFetcher delegate;

 private  String cachePrefix;

 private  CCache<LookupDataSourceContext,LookupValuesList> cache_retrieveEntities;

 private  CCache<LookupDataSourceContext,LookupValue> cache_retrieveLookupValueById;


@Override
public List<CCacheStats> getCacheStats(){
    return ImmutableList.<CCacheStats>builder().add(cache_retrieveEntities.stats()).add(cache_retrieveLookupValueById.stats()).addAll(delegate.getCacheStats()).build();
}


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    return cache_retrieveEntities.getOrLoad(evalCtx, delegate::retrieveEntities);
}


@Override
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx){
    return cache_retrieveLookupValueById.getOrLoad(evalCtx, () -> delegate.retrieveLookupValueById(evalCtx));
}


@Override
public String getCachePrefix(){
    return cachePrefix;
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return delegate.getZoomIntoWindowId();
}


@Override
public boolean isNumericKey(){
    return delegate.isNumericKey();
}


@Override
public void cacheInvalidate(){
    cache_retrieveEntities.reset();
    cache_retrieveLookupValueById.reset();
}


@Override
public boolean isCached(){
    return true;
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingById(Object id){
    return delegate.newContextForFetchingById(id);
}


@Override
public Optional<String> getLookupTableName(){
    return delegate.getLookupTableName();
}


public CachedLookupDataSourceFetcherAdapter of(LookupDataSourceFetcher delegate){
    if (delegate instanceof CachedLookupDataSourceFetcherAdapter) {
        return (CachedLookupDataSourceFetcherAdapter) delegate;
    }
    return new CachedLookupDataSourceFetcherAdapter(delegate);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper("Cached").addValue(delegate).toString();
}


@Override
public Builder newContextForFetchingList(){
    return delegate.newContextForFetchingList();
}


}