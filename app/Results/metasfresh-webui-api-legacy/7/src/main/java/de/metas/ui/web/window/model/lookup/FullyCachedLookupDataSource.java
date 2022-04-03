package de.metas.ui.web.window.model.lookup;
 import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.compiere.model.I_AD_SysConfig;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache;
import de.metas.cache.CCache.CCacheStats;
import de.metas.cache.CCache.CacheMapType;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import lombok.NonNull;
public class FullyCachedLookupDataSource implements LookupDataSource{

 private  String NAME;

 private  LookupDataSourceFetcher fetcher;

 private  CCache<LookupDataSourceContext,LookupValuesList> cacheByPartition;


@Override
public List<CCacheStats> getCacheStats(){
    return ImmutableList.of(cacheByPartition.stats());
}


@Override
public DocumentZoomIntoInfo getDocumentZoomInto(int id){
    final String tableName = fetcher.getLookupTableName().orElseThrow(() -> new IllegalStateException("Failed converting id=" + id + " to " + DocumentZoomIntoInfo.class + " because the fetcher returned null tablename: " + fetcher));
    return DocumentZoomIntoInfo.of(tableName, id);
}


public LookupValuesList getLookupValuesList(Evaluatee parentEvaluatee){
    final LookupDataSourceContext evalCtx = fetcher.newContextForFetchingList().setParentEvaluatee(parentEvaluatee).putFilter(LookupDataSourceContext.FILTER_Any, FIRST_ROW, Integer.MAX_VALUE).build();
    return cacheByPartition.getOrLoad(evalCtx, fetcher::retrieveEntities);
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return fetcher.getZoomIntoWindowId();
}


public FullyCachedLookupDataSource of(LookupDataSourceFetcher fetcher){
    return new FullyCachedLookupDataSource(fetcher);
}


@Override
public LookupValuesList findEntities(Evaluatee ctx,int pageLength){
    return getLookupValuesList(ctx).limit(pageLength);
}


@Override
public LookupValue findById(Object idObj){
    final Object idNormalized = LookupValue.normalizeId(idObj, fetcher.isNumericKey());
    if (idNormalized == null) {
        return null;
    }
    final LookupValuesList partition = getLookupValuesList(Evaluatees.empty());
    return partition.getById(idNormalized);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("fetcher", fetcher).toString();
}


@Override
public void cacheInvalidate(){
    cacheByPartition.reset();
}


}