package de.metas.ui.web.window.model.lookup;
 import java.util.List;
import java.util.Optional;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache.CCacheStats;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
public interface LookupDataSourceFetcher {

 private LookupValue LOOKUPVALUE_NULL;


public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx)
;

public List<CCacheStats> getCacheStats(){
    return ImmutableList.of();
}
;

public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx)
;

public String getCachePrefix()
;

public Optional<String> getLookupTableName()
;

public Optional<WindowId> getZoomIntoWindowId()
;

public boolean isNumericKey()
;

public void cacheInvalidate()
;

public boolean isCached()
;

public LookupDataSourceContext.Builder newContextForFetchingById(Object id)
;

public LookupDataSourceContext.Builder newContextForFetchingList()
;

}