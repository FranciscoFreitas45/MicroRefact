package de.metas.ui.web.window.descriptor;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache.CCacheStats;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
public class SimpleLookupDescriptorTemplate implements LookupDataSourceFetcher,LookupDescriptor{


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx)


@Override
public List<CCacheStats> getCacheStats(){
    return ImmutableList.of();
}


@Override
public LookupDataSourceFetcher getLookupDataSourceFetcher(){
    return this;
}


@Override
public LookupSource getLookupSourceType(){
    return LookupSource.list;
}


@Override
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx)


@Override
public boolean isHighVolume(){
    // NOTE: method will never be called because isCached() == true
    return false;
}


@Override
public String getCachePrefix(){
    // NOTE: method will never be called because isCached() == true
    return null;
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public boolean isNumericKey()


@Override
public void cacheInvalidate(){
}


@Override
public boolean isCached(){
    return true;
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingById(Object id){
    return LookupDataSourceContext.builderWithoutTableName();
}


@Override
public boolean hasParameters(){
    return !getDependsOnFieldNames().isEmpty();
}


@Override
public Set<String> getDependsOnFieldNames()


@Override
public LookupDataSourceContext.Builder newContextForFetchingList(){
    return LookupDataSourceContext.builderWithoutTableName();
}


}