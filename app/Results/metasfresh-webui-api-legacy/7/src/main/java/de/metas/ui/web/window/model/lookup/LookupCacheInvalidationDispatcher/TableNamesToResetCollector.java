package de.metas.ui.web.window.model.lookup.LookupCacheInvalidationDispatcher;
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
public class TableNamesToResetCollector {

 private  Set<String> tableNames;


public Set<String> toSet(){
    return ImmutableSet.copyOf(tableNames);
}


public void addTableNames(Collection<String> tableNamesToAdd){
    tableNames.addAll(tableNamesToAdd);
}


}