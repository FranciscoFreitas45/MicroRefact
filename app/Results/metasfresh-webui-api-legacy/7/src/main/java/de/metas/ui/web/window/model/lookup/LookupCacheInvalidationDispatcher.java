package de.metas.ui.web.window.model.lookup;
 import com.google.common.collect.ImmutableSet;
import de.metas.cache.CacheMgt;
import de.metas.cache.ICacheResetListener;
import de.metas.cache.model.CacheInvalidateMultiRequest;
import de.metas.cache.model.CacheInvalidateRequest;
import de.metas.util.Services;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxListenerManager.TrxEventTiming;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.ad.trx.api.OnTrxMissingPolicy;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
@Component
public class LookupCacheInvalidationDispatcher implements ICacheResetListener{

 private  String TRXPROP_TableNamesToInvalidate;

 private  Executor async;

 private  Set<String> tableNames;


public Set<String> extractTableNames(CacheInvalidateMultiRequest multiRequest){
    if (multiRequest.isResetAll()) {
        // not relevant for our lookups
        return ImmutableSet.of();
    }
    return multiRequest.getRequests().stream().filter(// not relevant for our lookups
    request -> !request.isAll()).map(CacheInvalidateRequest::getTableNameEffective).filter(Objects::nonNull).collect(ImmutableSet.toImmutableSet());
}


public Set<String> toSet(){
    return ImmutableSet.copyOf(tableNames);
}


public void addTableNames(Collection<String> tableNamesToAdd){
    tableNames.addAll(tableNamesToAdd);
}


@Override
public long reset(CacheInvalidateMultiRequest multiRequest){
    final ITrxManager trxManager = Services.get(ITrxManager.class);
    final ITrx currentTrx = trxManager.getThreadInheritedTrx(OnTrxMissingPolicy.ReturnTrxNone);
    if (trxManager.isNull(currentTrx)) {
        async.execute(() -> resetNow(extractTableNames(multiRequest)));
    } else {
        final TableNamesToResetCollector collector = currentTrx.getProperty(TRXPROP_TableNamesToInvalidate, trx -> {
            final TableNamesToResetCollector c = new TableNamesToResetCollector();
            trx.getTrxListenerManager().newEventListener(TrxEventTiming.AFTER_COMMIT).registerHandlingMethod(innerTrx -> {
                final Set<String> tableNames = c.toSet();
                if (tableNames.isEmpty()) {
                    return;
                }
                async.execute(() -> resetNow(tableNames));
            });
            return c;
        });
        collector.addTableNames(extractTableNames(multiRequest));
    }
    // not relevant
    return 1;
}


public void resetNow(Set<String> tableNames){
    if (tableNames.isEmpty()) {
        return;
    }
    LookupDataSourceFactory.instance.cacheInvalidateOnRecordsChanged(tableNames);
}


@PostConstruct
public void postConstruct(){
    CacheMgt.get().addCacheResetListener(this);
}


}