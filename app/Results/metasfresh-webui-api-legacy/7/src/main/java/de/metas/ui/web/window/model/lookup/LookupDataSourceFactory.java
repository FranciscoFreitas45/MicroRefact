package de.metas.ui.web.window.model.lookup;
 import de.metas.cache.CCache;
import de.metas.cache.CCache.CCacheStats;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.ad.table.api.IADTableDAO;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_M_AttributeSetInstance;
import org.slf4j.Logger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
public class LookupDataSourceFactory {

 public  LookupDataSourceFactory instance;

 private  Logger logger;

 private  CCache<LookupDescriptor,LookupDataSource> lookupDataSourcesCache;

 private  ConcurrentHashMap<String,CacheInvalidationGroup> cacheInvalidationGroupsByTableName;

 private  String tableName;

 private  List<WeakReference<LookupDataSource>> lookupDataSources;


public List<CCacheStats> getCacheStats(){
    return lookupDataSourcesCache.values().stream().flatMap(dataSource -> dataSource.getCacheStats().stream()).distinct().sorted(Comparator.comparing(CCacheStats::getName)).collect(GuavaCollectors.toImmutableList());
}


public LookupDataSource searchByAD_Val_Rule_ID(int AD_Reference_Value_ID,int AD_Val_Rule_ID){
    final LookupDescriptor lookupDescriptor = SqlLookupDescriptor.searchByAD_Val_Rule_ID(AD_Reference_Value_ID, AD_Val_Rule_ID).provide().get();
    return getLookupDataSource(lookupDescriptor);
}


public LookupDataSource searchByColumn(String tableName,String columnname){
    final IADTableDAO adTableDAO = Services.get(IADTableDAO.class);
    final I_AD_Column column = adTableDAO.retrieveColumn(tableName, columnname);
    final LookupDescriptor lookupDescriptor = SqlLookupDescriptor.searchByAD_Val_Rule_ID(column.getAD_Reference_Value_ID(), column.getAD_Val_Rule_ID()).provide().get();
    return getLookupDataSource(lookupDescriptor);
}


public List<LookupDataSource> purgeAndGet(){
    synchronized (lookupDataSources) {
        final List<LookupDataSource> result = new ArrayList<>(lookupDataSources.size());
        for (final Iterator<WeakReference<LookupDataSource>> it = lookupDataSources.iterator(); it.hasNext(); ) {
            final LookupDataSource lookupDataSource = it.next().get();
            if (lookupDataSource == null) {
                it.remove();
                continue;
            }
            result.add(lookupDataSource);
        }
        return result;
    }
}


public LookupDataSource productAttributes(){
    final LookupDescriptor lookupDescriptor = SqlLookupDescriptor.productAttributes().provide().get();
    return getLookupDataSource(lookupDescriptor);
}


public LookupDataSource getLookupDataSource(LookupDescriptor lookupDescriptor){
    return lookupDataSourcesCache.getOrLoad(lookupDescriptor, this::createLookupDataSource);
}


public void cacheInvalidateOnRecordsChanged(Set<String> tableNames){
    tableNames.stream().map(cacheInvalidationGroupsByTableName::get).filter(Objects::nonNull).forEach(CacheInvalidationGroup::cacheInvalidate);
}


public void cacheInvalidate(){
    for (final LookupDataSource lookupDataSource : purgeAndGet()) {
        try {
            lookupDataSource.cacheInvalidate();
            logger.debug("Cache invalidated {} on {} record changed", lookupDataSource, tableName);
        } catch (final Exception ex) {
            logger.warn("Failed invalidating {}. Skipped", lookupDataSource, ex);
        }
    }
}


public LookupDataSource searchInTableLookup(String tableName){
    if (I_M_AttributeSetInstance.Table_Name.equals(tableName)) {
        return productAttributes();
    } else {
        final LookupDescriptor lookupDescriptor = SqlLookupDescriptor.searchInTable(tableName).provide().get();
        return getLookupDataSource(lookupDescriptor);
    }
}


public void addLookupDataSource(LookupDataSource lookupDataSource){
    synchronized (lookupDataSources) {
        lookupDataSources.add(new WeakReference<>(lookupDataSource));
    }
}


public CacheInvalidationGroup getCacheInvalidationGroupByTableName(String tableName){
    return cacheInvalidationGroupsByTableName.computeIfAbsent(tableName, CacheInvalidationGroup::new);
}


public LookupDataSource listByAD_Reference_Value_ID(int AD_Reference_Value_ID){
    final LookupDescriptor lookupDescriptor = SqlLookupDescriptor.listByAD_Reference_Value_ID(AD_Reference_Value_ID).provide().get();
    return getLookupDataSource(lookupDescriptor);
}


public LookupDataSource createLookupDataSource(LookupDescriptor lookupDescriptor){
    final LookupDataSourceFetcher fetcher = lookupDescriptor.getLookupDataSourceFetcher();
    final LookupDataSource lookupDataSource;
    if (fetcher.isCached()) {
        lookupDataSource = LookupDataSourceAdapter.of(fetcher);
    } else if (!lookupDescriptor.isHighVolume() && !lookupDescriptor.hasParameters()) {
        lookupDataSource = FullyCachedLookupDataSource.of(fetcher);
    } else {
        final CachedLookupDataSourceFetcherAdapter cachedFetcher = CachedLookupDataSourceFetcherAdapter.of(fetcher);
        lookupDataSource = LookupDataSourceAdapter.of(cachedFetcher);
    }
    // 
    // Register cache invalidation on depending table changed
    lookupDescriptor.getDependsOnTableNames().stream().map(this::getCacheInvalidationGroupByTableName).forEach(cacheInvalidationGroup -> cacheInvalidationGroup.addLookupDataSource(lookupDataSource));
    logger.debug("Creating lookup data source for {}: {}", lookupDescriptor, lookupDataSource);
    return lookupDataSource;
}


}