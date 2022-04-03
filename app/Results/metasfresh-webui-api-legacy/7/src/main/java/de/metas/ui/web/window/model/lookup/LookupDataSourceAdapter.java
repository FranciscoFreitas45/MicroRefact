package de.metas.ui.web.window.model.lookup;
 import java.util.List;
import java.util.Optional;
import org.compiere.util.Evaluatee;
import com.google.common.base.MoreObjects;
import de.metas.cache.CCache.CCacheStats;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import lombok.NonNull;
public class LookupDataSourceAdapter implements LookupDataSource{

 private  LookupDataSourceFetcher fetcher;


@Override
public List<CCacheStats> getCacheStats(){
    return fetcher.getCacheStats();
}


@Override
public DocumentZoomIntoInfo getDocumentZoomInto(int id){
    final String tableName = fetcher.getLookupTableName().orElseThrow(() -> new IllegalStateException("Failed converting id=" + id + " to " + DocumentZoomIntoInfo.class + " because the fetcher returned null tablename: " + fetcher));
    return DocumentZoomIntoInfo.of(tableName, id);
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return fetcher.getZoomIntoWindowId();
}


public LookupDataSourceAdapter of(LookupDataSourceFetcher fetcher){
    return new LookupDataSourceAdapter(fetcher);
}


@Override
public LookupValuesList findEntities(Evaluatee ctx,String filter,int firstRow,int pageLength){
    final String filterEffective;
    if (Check.isEmpty(filter, true)) {
        filterEffective = LookupDataSourceContext.FILTER_Any;
    } else {
        filterEffective = filter.trim();
    }
    final LookupDataSourceContext evalCtx = fetcher.newContextForFetchingList().setParentEvaluatee(ctx).putFilter(filterEffective, firstRow, pageLength).requiresFilterAndLimit().build();
    final LookupValuesList lookupValuesList = fetcher.retrieveEntities(evalCtx);
    return lookupValuesList;
}


@Override
public LookupValue findById(Object idObj){
    if (idObj == null) {
        return null;
    }
    // 
    // Normalize the ID to Integer/String
    final Object idNormalized = LookupValue.normalizeId(idObj, fetcher.isNumericKey());
    if (idNormalized == null) {
        return null;
    }
    // 
    // Build the validation context
    final LookupDataSourceContext evalCtx = fetcher.newContextForFetchingById(idNormalized).putFilterById(idNormalized).putShowInactive(true).build();
    // 
    // Get the lookup value
    final LookupValue lookupValue = fetcher.retrieveLookupValueById(evalCtx);
    if (lookupValue == LookupDataSourceFetcher.LOOKUPVALUE_NULL) {
        return null;
    }
    return lookupValue;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(fetcher).toString();
}


@Override
public void cacheInvalidate(){
    fetcher.cacheInvalidate();
}


}