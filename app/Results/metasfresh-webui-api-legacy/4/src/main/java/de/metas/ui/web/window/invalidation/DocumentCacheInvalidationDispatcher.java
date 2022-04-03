package de.metas.ui.web.window.invalidation;
 import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.ad.trx.api.OnTrxMissingPolicy;
import org.adempiere.util.lang.IAutoCloseable;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.slf4j.Logger;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;
import de.metas.cache.CacheMgt;
import de.metas.cache.ICacheResetListener;
import de.metas.cache.model.CacheInvalidateMultiRequest;
import de.metas.cache.model.CacheInvalidateRequest;
import de.metas.logging.LogManager;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class DocumentCacheInvalidationDispatcher implements ICacheResetListener{

 private  Logger logger;

 private  DocumentCollection documents;

 private  IViewsRepository viewsRepository;

 private  Executor async;

 private  String name;

 private  DocumentToInvalidateMap documents;


public void resetAsync(){
    final DocumentToInvalidateMap documents = this.documents;
    // just to prevent adding more events
    this.documents = null;
    logger.trace("Flushing {} collected requests for on `{}`", documents.size(), name);
    if (documents.isEmpty()) {
        return;
    }
    DocumentCacheInvalidationDispatcher.this.resetAsync(documents);
}


public Executor createAsyncExecutor(){
    final CustomizableThreadFactory asyncThreadFactory = new CustomizableThreadFactory(DocumentCacheInvalidationDispatcher.class.getSimpleName());
    asyncThreadFactory.setDaemon(true);
    return Executors.newSingleThreadExecutor(asyncThreadFactory);
}


@Override
public long reset(CacheInvalidateMultiRequest request){
    final ITrxManager trxManager = Services.get(ITrxManager.class);
    final ITrx currentTrx = trxManager.getThreadInheritedTrx(OnTrxMissingPolicy.ReturnTrxNone);
    if (trxManager.isActive(currentTrx)) {
        final CacheInvalidateMultiRequestsCollector collector = currentTrx.getPropertyAndProcessAfterCommit(CacheInvalidateMultiRequestsCollector.class.getName(), () -> new CacheInvalidateMultiRequestsCollector(currentTrx.getTrxName()), CacheInvalidateMultiRequestsCollector::resetAsync);
        collector.collect(request);
    } else {
        new CacheInvalidateMultiRequestsCollector().collect(request).resetAsync();
    }
    // not relevant
    return 1;
}


public void resetNow(DocumentToInvalidateMap documentsToInvalidate){
    logger.trace("resetNow: {}", documentsToInvalidate);
    try (final IAutoCloseable c = documents.getWebsocketPublisher().temporaryCollectOnThisThread()) {
        documentsToInvalidate.toCollection().forEach(documents::invalidate);
    }
    // 
    final TableRecordReferenceSet rootRecords = documentsToInvalidate.getRootRecords();
    viewsRepository.notifyRecordsChanged(rootRecords);
}


@PostConstruct
public void postConstruct(){
    CacheMgt.get().addCacheResetListener(this);
}


public void collect(CacheInvalidateRequest request){
    logger.trace("Collecting request on `{}`: {}", name, request);
    final TableRecordReference rootDocumentRef = request.getRootRecordOrNull();
    if (rootDocumentRef == null) {
        return;
    }
    // 
    // If we are still collecting document, we will collect this event.
    // If not, we will have to fire this event directly.
    final DocumentToInvalidateMap documentsToCollect = this.documents;
    final DocumentToInvalidateMap documents;
    final boolean autoflush;
    if (documentsToCollect != null) {
        documents = documentsToCollect;
        autoflush = false;
    } else {
        // Basically this shall not happen, but for some reason it's happening.
        // So, for that case, instead of just ignoring event, better to fire it directly.
        documents = new DocumentToInvalidateMap();
        autoflush = true;
    }
    // 
    final DocumentToInvalidate documentToInvalidate = documents.getDocumentToInvalidate(rootDocumentRef);
    final String childTableName = request.getChildTableName();
    if (childTableName == null) {
        documentToInvalidate.invalidateDocument();
    } else if (request.isAllRecords()) {
        documentToInvalidate.invalidateAllIncludedDocuments(childTableName);
        // NOTE: as a workaround to solve the problem of https://github.com/metasfresh/metasfresh-webui-api/issues/851,
        // we are invalidating the whole root document to make sure that in case there were any virtual columns on header,
        // those get refreshed too.
        documentToInvalidate.invalidateDocument();
    } else {
        final int childRecordId = request.getChildRecordId();
        documentToInvalidate.addIncludedDocument(childTableName, childRecordId);
    }
    // 
    if (autoflush && !documents.isEmpty()) {
        logger.trace("Auto-flushing {} collected requests for on `{}`", documents.size(), name);
        DocumentCacheInvalidationDispatcher.this.resetAsync(documents);
    }
}


}