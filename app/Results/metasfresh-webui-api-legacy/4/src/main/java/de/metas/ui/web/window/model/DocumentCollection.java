package de.metas.ui.web.window.model;
 import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.AdMessageKey;
import de.metas.letters.model.MADBoilerPlate;
import de.metas.letters.model.MADBoilerPlate.BoilerPlateContext;
import de.metas.letters.model.MADBoilerPlate.SourceDocument;
import de.metas.logging.LogManager;
import de.metas.process.AdProcessId;
import de.metas.process.ProcessExecutionResult;
import de.metas.process.ProcessInfo;
import de.metas.report.server.OutputType;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.controller.DocumentPermissionsHelper;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.events.DocumentWebsocketPublisher;
import de.metas.ui.web.window.exceptions.DocumentNotFoundException;
import de.metas.ui.web.window.exceptions.InvalidDocumentPathException;
import de.metas.ui.web.window.invalidation.DocumentToInvalidate;
import de.metas.ui.web.window.invalidation.IncludedDocumentToInvalidate;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.lookup.DocumentZoomIntoInfo;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.adempiere.ad.persistence.TableModelLoader;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.CopyRecordFactory;
import org.adempiere.model.CopyRecordSupport;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.model.PlainContextAware;
import org.adempiere.model.RecordZoomWindowFinder;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.lang.IAutoCloseable;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
@Component
public class DocumentCollection {

 private  String SYSCONFIG_CACHE_SIZE;

 private  int DEFAULT_CACHE_SIZE;

 private  Logger logger;

 public  AdMessageKey MSG_CLONING_NOT_ALLOWED_FOR_CURRENT_WINDOW;

@Autowired
 private  DocumentDescriptorFactory documentDescriptorFactory;

@Autowired
 private  UserSession userSession;

@Autowired
 private  DocumentWebsocketPublisher websocketPublisher;

 private  Cache<DocumentKey,Document> rootDocuments;

 private  ConcurrentHashMap<String,Set<WindowId>> tableName2windowIds;

@NonNull
 private  Document document;

@NonNull
 private String filename;

@NonNull
 private String reportContentType;

@NonNull
 private byte[] reportData;

 private  DocumentType documentType;

 private  DocumentId documentTypeId;

 private  DocumentId documentId;

 private  Integer _hashcode;


public DocumentDescriptorFactory getDocumentDescriptorFactory(){
    return documentDescriptorFactory;
}


public BoilerPlateContext createBoilerPlateContext(DocumentPath documentPath){
    if (documentPath == null) {
        return BoilerPlateContext.EMPTY;
    }
    final Document document = getDocumentReadonly(documentPath);
    final SourceDocument sourceDocument = new DocumentAsTemplateSourceDocument(document);
    return MADBoilerPlate.createEditorContext(sourceDocument);
}


public Document retrieveRootDocumentFromRepository(DocumentKey documentKey){
    final DocumentEntityDescriptor entityDescriptor = getDocumentEntityDescriptor(documentKey.getWindowId());
    if (documentKey.getDocumentId().isNew()) {
        throw new InvalidDocumentPathException("documentId cannot be NEW");
    }
    final Document document = DocumentQuery.ofRecordId(entityDescriptor, documentKey.getDocumentId()).setChangesCollector(NullDocumentChangesCollector.instance).retriveDocumentOrNull();
    if (document == null) {
        throw new DocumentNotFoundException(documentKey.getDocumentPath());
    }
    return document;
}


public boolean isValidDocumentPath(DocumentPath documentPath){
    return documentPath != null && documentPath.getWindowId().isInt() && documentPath.getDocumentId().isInt();
}


public DocumentWebsocketPublisher getWebsocketPublisher(){
    return websocketPublisher;
}


public R forRootDocumentWritable(DocumentPath documentPathOrNew,IDocumentChangesCollector changesCollector,Function<Document,R> rootDocumentProcessor){
    final DocumentPath rootDocumentPathOrNew = documentPathOrNew.getRootDocumentPath();
    final Document lockHolder;
    final boolean isNewRootDocument;
    final DocumentKey rootDocumentKey;
    if (rootDocumentPathOrNew.isNewDocument()) {
        final Document newRootDocument = createRootDocument(rootDocumentPathOrNew, changesCollector);
        lockHolder = newRootDocument;
        rootDocumentKey = DocumentKey.ofRootDocumentPath(newRootDocument.getDocumentPath());
        isNewRootDocument = true;
    } else {
        rootDocumentKey = DocumentKey.ofRootDocumentPath(rootDocumentPathOrNew);
        lockHolder = getOrLoadDocument(rootDocumentKey);
        isNewRootDocument = false;
    }
    try (@SuppressWarnings("unused") final IAutoCloseable writeLock = lockHolder.lockForWriting()) {
        final Document rootDocument;
        if (isNewRootDocument) {
            rootDocument = lockHolder;
        } else {
            rootDocument = getOrLoadDocument(rootDocumentKey).copy(CopyMode.CheckOutWritable, changesCollector).refreshFromRepositoryIfStaled();
            DocumentPermissionsHelper.assertCanEdit(rootDocument);
        }
        // 
        // Execute the actual processor
        final R result = rootDocumentProcessor.apply(rootDocument);
        // 
        // Commit or remove it from cache if deleted
        if (rootDocument.isDeleted()) {
            rootDocuments.invalidate(rootDocumentKey);
            changesCollector.collectDeleted(rootDocument.getDocumentPath());
        } else {
            commitRootDocument(rootDocument);
        }
        // Return the result
        return result;
    }
}


@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public boolean isWindowIdSupported(WindowId windowId){
    return documentDescriptorFactory.isWindowIdSupported(windowId);
}


public TableRecordReference getTableRecordReference(DocumentPath documentPath){
    return documentDescriptorFactory.getTableRecordReference(documentPath);
}


public void delete(DocumentPath documentPath,IDocumentChangesCollector changesCollector){
    if (documentPath.isRootDocument()) {
        final DocumentEntityDescriptor entityDescriptor = documentDescriptorFactory.getDocumentEntityDescriptor(documentPath);
        assertDeleteDocumentAllowed(entityDescriptor);
    }
    final DocumentPath rootDocumentPath = documentPath.getRootDocumentPath();
    if (rootDocumentPath.isNewDocument()) {
        throw new InvalidDocumentPathException(rootDocumentPath);
    }
    forRootDocumentWritable(rootDocumentPath, changesCollector, rootDocument -> {
        if (documentPath.isRootDocument()) {
            if (!rootDocument.isNew()) {
                rootDocument.deleteFromRepository();
            }
            rootDocument.markAsDeleted();
        } else if (documentPath.hasIncludedDocuments()) {
            rootDocument.deleteIncludedDocuments(documentPath.getDetailId(), documentPath.getRowIds());
        } else {
            throw new InvalidDocumentPathException(documentPath);
        }
        // nothing to return
        return null;
    });
}


public Document getDocumentReadonly(DocumentPath documentPath){
    return forDocumentReadonly(documentPath, Function.identity());
}


public R forDocumentWritable(DocumentPath documentPath,IDocumentChangesCollector changesCollector,Function<Document,R> documentProcessor){
    final DocumentPath rootDocumentPath = documentPath.getRootDocumentPath();
    return forRootDocumentWritable(rootDocumentPath, changesCollector, rootDocument -> {
        final Document document;
        if (documentPath.isRootDocument()) {
            document = rootDocument;
        } else if (documentPath.isSingleNewIncludedDocument()) {
            document = rootDocument.createIncludedDocument(documentPath.getDetailId());
        } else {
            document = rootDocument.getIncludedDocument(documentPath.getDetailId(), documentPath.getSingleRowId());
            DocumentPermissionsHelper.assertCanEdit(rootDocument);
        }
        return documentProcessor.apply(document);
    });
}


public String cacheReset(boolean forgetNotSavedDocuments){
    final String result;
    if (forgetNotSavedDocuments) {
        final long count = rootDocuments.size();
        rootDocuments.invalidateAll();
        result = "invalidate all " + count + " documents";
    } else {
        long countDocumentsWithChanges = 0;
        final List<DocumentKey> documentKeysToInvalidate = new ArrayList<>();
        for (final Map.Entry<DocumentKey, Document> entry : rootDocuments.asMap().entrySet()) {
            final Document document = entry.getValue();
            if (document.hasChangesRecursivelly()) {
                countDocumentsWithChanges++;
            } else {
                documentKeysToInvalidate.add(entry.getKey());
            }
        }
        rootDocuments.invalidateAll(documentKeysToInvalidate);
        result = "invalidate " + documentKeysToInvalidate.size() + " documents with no changes;" + " skipped " + countDocumentsWithChanges + " documents with changes";
    }
    rootDocuments.cleanUp();
    logger.info("cacheReset: {}", result);
    return result;
}


public Set<WindowId> getCachedWindowIdsForTableName(String tableName){
    final Set<WindowId> windowIds = tableName2windowIds.get(tableName);
    return windowIds != null && !windowIds.isEmpty() ? ImmutableSet.copyOf(windowIds) : ImmutableSet.of();
}


public Document getOrLoadDocument(DocumentKey documentKey){
    try {
        return rootDocuments.get(documentKey, () -> {
            final Document rootDocument = retrieveRootDocumentFromRepository(documentKey).copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance);
            addToTableName2WindowIdsCache(rootDocument.getEntityDescriptor());
            return rootDocument;
        });
    } catch (final ExecutionException e) {
        throw AdempiereException.wrapIfNeeded(e);
    }
}


public DocumentEntityDescriptor getDocumentEntityDescriptor(WindowId windowId){
    final DocumentDescriptor descriptor = documentDescriptorFactory.getDocumentDescriptor(windowId);
    return descriptor.getEntityDescriptor();
}


@Override
public Object getFieldValue(String fieldName){
    return document.getFieldView(fieldName).getValue();
}


@Override
public int hashCode(){
    if (_hashcode == null) {
        _hashcode = Objects.hash(documentType, documentTypeId, documentId);
    }
    return _hashcode;
}


public R forDocumentReadonly(DocumentPath documentPath,Function<Document,R> documentProcessor){
    final DocumentPath rootDocumentPath = documentPath.getRootDocumentPath();
    return forRootDocumentReadonly(rootDocumentPath, rootDocument -> {
        if (documentPath.isRootDocument()) {
            return documentProcessor.apply(rootDocument);
        } else if (documentPath.isSingleIncludedDocument()) {
            final Document includedDocument = rootDocument.getIncludedDocument(documentPath.getDetailId(), documentPath.getSingleRowId());
            DocumentPermissionsHelper.assertCanView(includedDocument, UserSession.getCurrentPermissions());
            return documentProcessor.apply(includedDocument);
        } else {
            throw new InvalidDocumentPathException(documentPath);
        }
    });
}


public DocumentKey of(WindowId windowId,DocumentId documentId){
    return new DocumentKey(DocumentType.Window, windowId.toDocumentId(), documentId);
}


public DocumentKey ofRootDocumentPath(DocumentPath documentPath){
    if (!documentPath.isRootDocument()) {
        throw new InvalidDocumentPathException(documentPath, "shall be a root document path");
    }
    if (documentPath.isNewDocument()) {
        throw new InvalidDocumentPathException(documentPath, "document path for creating new documents is not allowed");
    }
    return new DocumentKey(documentPath.getDocumentType(), documentPath.getDocumentTypeId(), documentPath.getDocumentId());
}


public void invalidateRootDocument(DocumentPath documentPath){
    final DocumentKey documentKey = DocumentKey.ofRootDocumentPath(documentPath);
    // 
    // Invalidate the root documents
    rootDocuments.invalidate(documentKey);
    // 
    // Notify frontend
    websocketPublisher.staleRootDocument(documentKey.getWindowId(), documentKey.getDocumentId());
}


public DocumentPath duplicateDocumentInTrx(DocumentPath fromDocumentPath){
    // NOTE: assume it's already running in transaction
    final TableRecordReference fromRecordRef = getTableRecordReference(fromDocumentPath);
    final Object fromModel = fromRecordRef.getModel(PlainContextAware.newWithThreadInheritedTrx());
    final String tableName = InterfaceWrapperHelper.getModelTableName(fromModel);
    final PO fromPO = InterfaceWrapperHelper.getPO(fromModel);
    if (!CopyRecordFactory.isEnabledForTableName(tableName)) {
        throw new AdempiereException(MSG_CLONING_NOT_ALLOWED_FOR_CURRENT_WINDOW);
    }
    final PO toPO = TableModelLoader.instance.newPO(Env.getCtx(), tableName, ITrx.TRXNAME_ThreadInherited);
    // set "getValueToCopy" advisor
    toPO.setDynAttribute(PO.DYNATTR_CopyRecordSupport, CopyRecordFactory.getCopyRecordSupport(tableName));
    PO.copyValues(fromPO, toPO, true);
    InterfaceWrapperHelper.save(toPO);
    final CopyRecordSupport childCRS = CopyRecordFactory.getCopyRecordSupport(tableName);
    childCRS.setAdWindowId(fromDocumentPath.getAdWindowIdOrNull());
    childCRS.setParentPO(toPO);
    childCRS.setBase(true);
    childCRS.copyRecord(fromPO, ITrx.TRXNAME_ThreadInherited);
    return DocumentPath.rootDocumentPath(fromDocumentPath.getWindowId(), DocumentId.of(toPO.get_ID()));
}


public void addToTableName2WindowIdsCache(DocumentEntityDescriptor entityDescriptor){
    final String tableName = entityDescriptor.getTableNameOrNull();
    if (tableName == null) {
        return;
    }
    final Set<WindowId> windowIds = tableName2windowIds.computeIfAbsent(tableName, k -> Collections.newSetFromMap(new ConcurrentHashMap<>()));
    windowIds.add(entityDescriptor.getWindowId());
}


public DocumentDescriptor getDocumentDescriptor(WindowId windowId){
    return documentDescriptorFactory.getDocumentDescriptor(windowId);
}


public R forRootDocumentReadonly(DocumentPath documentPath,Function<Document,R> rootDocumentProcessor){
    final DocumentKey rootDocumentKey = DocumentKey.ofRootDocumentPath(documentPath.getRootDocumentPath());
    try (@SuppressWarnings("unused") final IAutoCloseable readLock = getOrLoadDocument(rootDocumentKey).lockForReading()) {
        final Document rootDocument = getOrLoadDocument(rootDocumentKey).copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance);
        DocumentPermissionsHelper.assertCanView(rootDocument, UserSession.getCurrentPermissions());
        return rootDocumentProcessor.apply(rootDocument);
    }
}


public DocumentId getDocumentId(){
    return documentId;
}


@Override
public boolean hasFieldValue(String fieldName){
    return document.hasField(fieldName);
}


@Override
public int getFieldValueAsInt(String fieldName,int defaultValue){
    if (!document.hasField(fieldName)) {
        return defaultValue;
    }
    return document.getFieldView(fieldName).getValueAsInt(defaultValue);
}


public void assertNewDocumentAllowed(DocumentEntityDescriptor entityDescriptor){
    final ILogicExpression allowExpr = entityDescriptor.getAllowCreateNewLogic();
    final LogicExpressionResult allow = allowExpr.evaluateToResult(userSession.toEvaluatee(), OnVariableNotFound.ReturnNoResult);
    if (allow.isFalse()) {
        throw new AdempiereException("Create not allowed");
    }
}


public void commitRootDocument(Document rootDocument){
    Preconditions.checkState(rootDocument.isRootDocument(), "{} is not a root document", rootDocument);
    final boolean wasNew = rootDocument.isNew();
    // 
    // Try saving it if possible
    rootDocument.saveIfValidAndHasChanges();
    // 
    // Make sure all included detail (tab) statuses are up2date.
    // IMPORTANT: we have to do this after saving because some of the logics depends on if they are any new included documents or not
    rootDocument.updateIncludedDetailsStatus();
    // 
    // Add the saved and changed document back to index
    final DocumentKey rootDocumentKey = DocumentKey.of(rootDocument);
    rootDocuments.put(rootDocumentKey, rootDocument.copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance));
    addToTableName2WindowIdsCache(rootDocument.getEntityDescriptor());
    // 
    // Make sure all events were collected for the case when we just created the new document
    // FIXME: this is a workaround and in case we find out all events were collected, we just need to remove this.
    if (wasNew) {
        logger.debug("Checking if we collected all events for the new document");
        final Set<String> collectedFieldNames = rootDocument.getChangesCollector().collectFrom(rootDocument, () -> "new document, initially missed");
        if (!collectedFieldNames.isEmpty()) {
            logger.warn("We would expect all events to be auto-magically collected but it seems that not all of them were collected!" + // 
            "\n Missed (but collected now) field names were: {}" + "\n Document path: {}", collectedFieldNames, rootDocument.getDocumentPath());
        }
    }
}


public void deleteAll(List<DocumentPath> documentPaths,IDocumentChangesCollector changesCollector){
    // FIXME: i think we shall refactor this method and make sure that "deleteAll" is atomic
    for (final DocumentPath documentPath : documentPaths) {
        delete(documentPath, changesCollector);
    }
}


public Document createRootDocument(DocumentPath documentPath,IDocumentChangesCollector changesCollector){
    if (!documentPath.isNewDocument()) {
        throw new InvalidDocumentPathException(documentPath, "new document ID was expected");
    }
    final WindowId windowId = documentPath.getWindowId();
    final DocumentEntityDescriptor entityDescriptor = getDocumentEntityDescriptor(windowId);
    assertNewDocumentAllowed(entityDescriptor);
    final DocumentsRepository documentsRepository = entityDescriptor.getDataBinding().getDocumentsRepository();
    @SuppressWarnings("UnnecessaryLocalVariable")
    final Document document = documentsRepository.createNewDocument(entityDescriptor, Document.NULL, changesCollector);
    // NOTE: we assume document is writable
    // NOTE: we are not adding it to index. That shall be done on "commit".
    return document;
}


public DocumentPrint createDocumentPrint(DocumentPath documentPath){
    final Document document = getDocumentReadonly(documentPath);
    final int windowNo = document.getWindowNo();
    final DocumentEntityDescriptor entityDescriptor = document.getEntityDescriptor();
    final AdProcessId printProcessId = entityDescriptor.getPrintProcessId();
    final TableRecordReference recordRef = getTableRecordReference(documentPath);
    final ProcessExecutionResult processExecutionResult = ProcessInfo.builder().setCtx(Env.getCtx()).setAD_Process_ID(printProcessId).setWindowNo(// important: required for ProcessInfo.findReportingLanguage
    windowNo).setRecord(recordRef).setPrintPreview(true).setJRDesiredOutputType(OutputType.PDF).buildAndPrepareExecution().onErrorThrowException().switchContextWhenRunning().executeSync().getResult();
    return DocumentPrint.builder().filename(processExecutionResult.getReportFilename()).reportContentType(processExecutionResult.getReportContentType()).reportData(processExecutionResult.getReportData()).build();
}


public void invalidate(DocumentToInvalidate documentToInvalidate){
    final ImmutableList<DocumentEntityDescriptor> entityDescriptors = getCachedWindowIdsForTableName(documentToInvalidate.getTableName()).stream().map(this::getDocumentEntityDescriptor).collect(ImmutableList.toImmutableList());
    if (entityDescriptors.isEmpty()) {
        return;
    }
    final DocumentId rootDocumentId = documentToInvalidate.getDocumentId();
    for (final DocumentEntityDescriptor entityDescriptor : entityDescriptors) {
        final WindowId windowId = entityDescriptor.getWindowId();
        final DocumentKey rootDocumentKey = DocumentKey.of(windowId, rootDocumentId);
        final Document rootDocument = rootDocuments.getIfPresent(rootDocumentKey);
        if (rootDocument != null) {
            try (@SuppressWarnings("unused") final IAutoCloseable lock = rootDocument.lockForWriting()) {
                for (final IncludedDocumentToInvalidate includedDocumentToInvalidate : documentToInvalidate.getIncludedDocuments()) {
                    final DocumentIdsSelection includedRowIds = includedDocumentToInvalidate.toDocumentIdsSelection();
                    if (includedRowIds.isEmpty()) {
                        continue;
                    }
                    for (final DocumentEntityDescriptor includedEntityDescriptor : entityDescriptor.getIncludedEntitiesByTableName(includedDocumentToInvalidate.getTableName())) {
                        final DetailId detailId = includedEntityDescriptor.getDetailId();
                        rootDocument.getIncludedDocumentsCollection(Check.assumeNotNull(detailId, "Expected detailId not null")).markStale(includedRowIds);
                        websocketPublisher.staleIncludedDocuments(windowId, rootDocumentId, detailId, includedRowIds);
                    }
                }
            }
        }
        // 
        // Invalidate the root document
        if (documentToInvalidate.isInvalidateDocument()) {
            rootDocuments.invalidate(rootDocumentKey);
        }
        // 
        // Notify frontend, even if the root document does not exist (or it was not cached).
        websocketPublisher.staleRootDocument(windowId, rootDocumentId);
    }
}


public Document duplicateDocument(DocumentPath fromDocumentPath){
    // NOTE: assume running out of transaction
    // Clone the document in transaction.
    // One of the reasons of doing this is because for some documents there are events which are triggered on each change (but on trx commit).
    // If we would run out of transaction, those events would be triggered 10k times.
    // e.g. copying the AD_Role. Each time a record like AD_Window_Access is created, the UserRolePermissionsEventBus.fireCacheResetEvent() is called.
    final ITrxManager trxManager = Services.get(ITrxManager.class);
    final DocumentPath toDocumentPath = trxManager.call(ITrx.TRXNAME_ThreadInherited, () -> duplicateDocumentInTrx(fromDocumentPath));
    return getDocumentReadonly(toDocumentPath);
}


public void invalidateDocumentByRecordId(String tableName,int recordId){
    // 
    // Create possible documentKeys for given tableName/recordId
    final DocumentId documentId = DocumentId.of(recordId);
    final Set<DocumentKey> documentKeys = getCachedWindowIdsForTableName(tableName).stream().map(windowId -> DocumentKey.of(windowId, documentId)).collect(ImmutableSet.toImmutableSet());
    // stop here if no document keys found
    if (documentKeys.isEmpty()) {
        return;
    }
    // 
    // Invalidate the root documents
    rootDocuments.invalidateAll(documentKeys);
    // 
    // Notify frontend
    documentKeys.forEach(documentKey -> websocketPublisher.staleRootDocument(documentKey.getWindowId(), documentKey.getDocumentId()));
}


public DocumentPath getDocumentPath(){
    return DocumentPath.rootDocumentPath(documentType, documentTypeId, documentId);
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (!(obj instanceof DocumentKey)) {
        return false;
    }
    final DocumentKey other = (DocumentKey) obj;
    return Objects.equals(documentType, other.documentType) && Objects.equals(documentTypeId, other.documentTypeId) && Objects.equals(documentId, other.documentId);
}


public void assertDeleteDocumentAllowed(DocumentEntityDescriptor entityDescriptor){
    final Evaluatee evalCtx = Evaluatees.mapBuilder().put(WindowConstants.FIELDNAME_Processed, false).build().andComposeWith(userSession.toEvaluatee());
    final ILogicExpression allowExpr = entityDescriptor.getAllowDeleteLogic();
    final LogicExpressionResult allow = allowExpr.evaluateToResult(evalCtx, OnVariableNotFound.ReturnNoResult);
    if (allow.isFalse()) {
        throw new AdempiereException("Delete not allowed");
    }
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("type", documentType).add("typeId", documentTypeId).add("documentId", documentId).toString();
}


public WindowId getWindowId(){
    Check.assume(documentType == DocumentType.Window, "documentType shall be {} but it was {}", DocumentType.Window, documentType);
    return WindowId.of(documentTypeId);
}


}