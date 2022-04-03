package de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
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
@ToString(exclude = "lookupDataSources")
public class CacheInvalidationGroup {

 private  String tableName;

 private  List<WeakReference<LookupDataSource>> lookupDataSources;


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


public void addLookupDataSource(LookupDataSource lookupDataSource){
    synchronized (lookupDataSources) {
        lookupDataSources.add(new WeakReference<>(lookupDataSource));
    }
}


}