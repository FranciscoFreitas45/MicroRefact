package DTO;
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
public class DocumentCollection {

 private  String SYSCONFIG_CACHE_SIZE;

 private  int DEFAULT_CACHE_SIZE;

 private  Logger logger;

 public  AdMessageKey MSG_CLONING_NOT_ALLOWED_FOR_CURRENT_WINDOW;

 private  DocumentDescriptorFactory documentDescriptorFactory;

 private  UserSession userSession;

 private  DocumentWebsocketPublisher websocketPublisher;

 private  Cache<DocumentKey,Document> rootDocuments;

 private  ConcurrentHashMap<String,Set<WindowId>> tableName2windowIds;

 private  Document document;

 private String filename;

 private String reportContentType;

 private byte[] reportData;

 private  DocumentType documentType;

 private  DocumentId documentTypeId;

 private  DocumentId documentId;

 private  Integer _hashcode;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public DocumentDescriptorFactory getDocumentDescriptorFactory(){
    return documentDescriptorFactory;
}


public DocumentWebsocketPublisher getWebsocketPublisher(){
    return websocketPublisher;
}


public TableRecordReference getTableRecordReference(DocumentPath documentPath){
    return documentDescriptorFactory.getTableRecordReference(documentPath);
}


public Document getDocumentReadonly(DocumentPath documentPath){
    return forDocumentReadonly(documentPath, Function.identity());
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


public DocumentDescriptor getDocumentDescriptor(WindowId windowId){
    return documentDescriptorFactory.getDocumentDescriptor(windowId);
}


public DocumentId getDocumentId(){
    return documentId;
}


@Override
public int getFieldValueAsInt(String fieldName,int defaultValue){
    if (!document.hasField(fieldName)) {
        return defaultValue;
    }
    return document.getFieldView(fieldName).getValueAsInt(defaultValue);
}


public DocumentPath getDocumentPath(){
    return DocumentPath.rootDocumentPath(documentType, documentTypeId, documentId);
}


public WindowId getWindowId(){
    Check.assume(documentType == DocumentType.Window, "documentType shall be {} but it was {}", DocumentType.Window, documentType);
    return WindowId.of(documentTypeId);
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/forDocumentReadonly"))

.queryParam("documentPath",documentPath);
.queryParam("documentProcessor",documentProcessor);
R aux = restTemplate.getForObject(builder.toUriString(),R.class);
return aux;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/forDocumentWritable"))

.queryParam("documentPath",documentPath);
.queryParam("changesCollector",changesCollector);
.queryParam("documentProcessor",documentProcessor);
R aux = restTemplate.getForObject(builder.toUriString(),R.class);
return aux;
}


public void invalidateRootDocument(DocumentPath documentPath){
    final DocumentKey documentKey = DocumentKey.ofRootDocumentPath(documentPath);
    // 
    // Invalidate the root documents
    rootDocuments.invalidate(documentKey);
    // 
    // Notify frontend
    websocketPublisher.staleRootDocument(documentKey.getWindowId(), documentKey.getDocumentId());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/invalidateRootDocument"))

.queryParam("documentPath",documentPath);
restTemplate.put(builder.toUriString(),null);
}


}