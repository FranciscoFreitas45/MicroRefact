package de.metas.ui.web.window.controller;
 import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.IMsgBL;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.cache.ETagResponseEntityBuilder;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.menu.MenuTree;
import de.metas.ui.web.menu.MenuTreeRepository;
import de.metas.ui.web.process.DocumentPreconditionsAsContext;
import de.metas.ui.web.process.ProcessRestController;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.process.json.JSONDocumentActionsList;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangeLog;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayout;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions.JSONDocumentLayoutOptionsBuilder;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions.JSONDocumentOptionsBuilder;
import de.metas.ui.web.window.datatypes.json.JSONDocumentPath;
import de.metas.ui.web.window.datatypes.json.JSONDocumentReference;
import de.metas.ui.web.window.datatypes.json.JSONDocumentReferencesGroup;
import de.metas.ui.web.window.datatypes.json.JSONDocumentReferencesGroupList;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.datatypes.json.JSONOptions.JSONOptionsBuilder;
import de.metas.ui.web.window.datatypes.json.JSONZoomInto;
import de.metas.ui.web.window.descriptor.ButtonFieldActionDescriptor;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.factory.NewRecordDescriptorsProvider;
import de.metas.ui.web.window.events.DocumentWebsocketPublisher;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentChangeLogService;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.DocumentCollection.DocumentPrint;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.DocumentReference;
import de.metas.ui.web.window.model.DocumentReferencesService;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.ui.web.window.model.lookup.DocumentZoomIntoInfo;
import de.metas.util.Services;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
@Api
@RestController
@RequestMapping(value = WindowRestController.ENDPOINT)
public class WindowRestController {

 public  String ENDPOINT;

 private  String PARAM_Advanced;

 private  String PARAM_Advanced_DefaultValue;

 private  String PARAM_FieldsList;

 private  ReasonSupplier REASON_Value_DirectSetFromCommitAPI;

@Autowired
 private  UserSession userSession;

@Autowired
 private  DocumentCollection documentCollection;

@Autowired
 private  DocumentChangeLogService documentChangeLogService;

@Autowired
 private  NewRecordDescriptorsProvider newRecordDescriptorsProvider;

@Autowired
 private  ProcessRestController processRestController;

@Autowired
 private  DocumentReferencesService documentReferencesService;

@Autowired
 private  MenuTreeRepository menuTreeRepository;

@Autowired
 private  DocumentWebsocketPublisher websocketPublisher;


public JSONOptionsBuilder newJSONOptions(){
    return JSONOptions.prepareFrom(userSession);
}


public JSONDocumentChangeLog getDocumentChangeLog(DocumentPath documentPath){
    final TableRecordReference recordRef = documentCollection.getTableRecordReference(documentPath);
    final JSONDocumentChangeLog json = documentChangeLogService.getJSONDocumentChangeLog(recordRef, userSession.getAD_Language());
    json.setPath(JSONDocumentPath.ofWindowDocumentPath(documentPath));
    return json;
}


public JSONLookupValuesList toJSONLookupValuesList(LookupValuesList lookupValuesList){
    return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
}


@GetMapping("/{windowId}/{documentId}/{tabId}/topActions")
public JSONDocumentActionsList getIncludedTabTopActions(String windowIdStr,String documentIdStr,String tabIdStr){
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentPath rootDocumentPath = DocumentPath.rootDocumentPath(windowId, documentIdStr);
    final DetailId selectedTabId = DetailId.fromJson(tabIdStr);
    final Set<TableRecordReference> selectedIncludedRecords = ImmutableSet.of();
    boolean returnDisabled = false;
    return getDocumentActions(rootDocumentPath, selectedTabId, selectedIncludedRecords, returnDisabled, DisplayPlace.IncludedTabTopActionsMenu);
}


@DeleteMapping("/{windowId}/{documentId}")
public List<JSONDocument> deleteRootDocument(String windowIdStr,String documentId){
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentPath documentPath = DocumentPath.rootDocumentPath(windowId, documentId);
    return deleteDocuments(ImmutableList.of(documentPath));
}


public JSONDocumentActionsList getDocumentActions(DocumentPath documentPath,DetailId selectedTabId,Set<TableRecordReference> selectedIncludedRecords,boolean returnDisabled,DisplayPlace displayPlace){
    userSession.assertLoggedIn();
    final Predicate<WebuiRelatedProcessDescriptor> isEnabled = returnDisabled ? WebuiRelatedProcessDescriptor::isEnabledOrNotSilent : WebuiRelatedProcessDescriptor::isEnabled;
    return documentCollection.forDocumentReadonly(documentPath, document -> {
        final DocumentPreconditionsAsContext preconditionsContext = DocumentPreconditionsAsContext.builder().document(document).selectedTabId(selectedTabId).selectedIncludedRecords(selectedIncludedRecords).displayPlace(displayPlace).build();
        return processRestController.streamDocumentRelatedProcesses(preconditionsContext).filter(descriptor -> descriptor.isDisplayedOn(displayPlace)).filter(isEnabled).collect(JSONDocumentActionsList.collect(newJSONOptions().build()));
    });
}


public JSONDocumentOptionsBuilder newJSONDocumentOptions(){
    return JSONDocumentOptions.builder().userSession(userSession);
}


@DeleteMapping("/{windowId}/{documentId}/{tabId}/{rowId}")
public List<JSONDocument> deleteIncludedDocument(String windowIdStr,String documentId,String tabId,String rowId){
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentPath documentPath = DocumentPath.includedDocumentPath(windowId, documentId, tabId, rowId);
    return deleteDocuments(ImmutableList.of(documentPath));
}


public List<JSONDocument> deleteDocuments(List<DocumentPath> documentPaths){
    userSession.assertLoggedIn();
    final JSONDocumentOptions jsonOpts = newJSONDocumentOptions().showAdvancedFields(false).build();
    return Execution.callInNewExecution("window.delete", () -> {
        final IDocumentChangesCollector changesCollector = Execution.getCurrentDocumentChangesCollectorOrNull();
        documentCollection.deleteAll(documentPaths, changesCollector);
        return JSONDocument.ofEvents(changesCollector, jsonOpts);
    });
}


public JSONLookupValuesList getDocumentFieldDropdown(DocumentPath documentPath,String fieldName){
    userSession.assertLoggedIn();
    return documentCollection.forDocumentReadonly(documentPath, document -> document.getFieldLookupValues(fieldName)).transform(this::toJSONLookupValuesList);
}


@GetMapping("/{windowId}/{documentId}/print/{filename:.*}")
public ResponseEntity<byte[]> getDocumentPrint(String windowIdStr,String documentIdStr,String filename){
    userSession.assertLoggedIn();
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentPath documentPath = DocumentPath.rootDocumentPath(windowId, documentIdStr);
    final DocumentPrint documentPrint = documentCollection.createDocumentPrint(documentPath);
    final byte[] reportData = documentPrint.getReportData();
    final String reportContentType = documentPrint.getReportContentType();
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(reportContentType));
    headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"");
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    final ResponseEntity<byte[]> response = new ResponseEntity<>(reportData, headers, HttpStatus.OK);
    return response;
}


@GetMapping("/{windowId}/{tabId}/layout")
public ResponseEntity<JSONDocumentLayout> getLayout(String windowIdStr,String tabIdStr,boolean advanced,WebRequest request){
    userSession.assertLoggedIn();
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DetailId detailId = DetailId.fromJson(tabIdStr);
    final DocumentDescriptor descriptor = documentCollection.getDocumentDescriptorFactory().getDocumentDescriptor(windowId);
    DocumentPermissionsHelper.checkWindowAccess(descriptor.getEntityDescriptor(), userSession.getUserRolePermissions());
    return ETagResponseEntityBuilder.ofETagAware(request, descriptor).includeLanguageInETag().cacheMaxAge(userSession.getHttpCacheMaxAge()).map(desc -> desc.getLayout().getDetail(detailId)).jsonLayoutOptions(() -> newJSONLayoutOptions().showAdvancedFields(advanced).build()).toLayoutJson(JSONDocumentLayout::ofDetailTab);
}


public JSONDocumentLayoutOptionsBuilder newJSONLayoutOptions(){
    return JSONDocumentLayoutOptions.prepareFrom(userSession).newRecordDescriptorsProvider(newRecordDescriptorsProvider);
}


@GetMapping(value = "/{windowId}/{documentId}/references")
public JSONDocumentReferencesGroupList getDocumentReferences(String windowIdStr,String documentId){
    userSession.assertLoggedIn();
    // Get document references
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentPath documentPath = DocumentPath.rootDocumentPath(windowId, documentId);
    final List<DocumentReference> documentReferences = documentReferencesService.getDocumentReferences(documentPath);
    if (documentReferences.isEmpty()) {
        return JSONDocumentReferencesGroupList.EMPTY;
    }
    // Organize document references in groups (by top level menu) and return them as JSON
    final JSONOptions jsonOpts = newJSONOptions().build();
    final MenuTree menuTree = menuTreeRepository.getMenuTree(userSession.getUserRolePermissionsKey(), jsonOpts.getAdLanguage());
    final String othersMenuCaption = Services.get(IMsgBL.class).translatable("DocumentReferences.group.Others").translate(jsonOpts.getAdLanguage());
    return JSONDocumentReferencesGroupList.of(documentReferences, menuTree, othersMenuCaption, jsonOpts);
}


@DeleteMapping("/{windowId}/{documentId}/{tabId}")
public List<JSONDocument> deleteIncludedDocumentsList(String windowIdStr,String documentId,String tabId,String rowIdsListStr){
    final DocumentPath documentPath = DocumentPath.builder().setDocumentType(WindowId.fromJson(windowIdStr)).setDocumentId(documentId).setDetailId(tabId).setRowIdsList(rowIdsListStr).build();
    if (documentPath.getRowIds().isEmpty()) {
        throw new IllegalArgumentException("No rowId(s) specified");
    }
    return deleteDocuments(ImmutableList.of(documentPath));
}


@GetMapping("/{windowId}/{documentId}/processNewRecord")
public int processRecord(String windowIdStr,String documentIdStr){
    userSession.assertLoggedIn();
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentPath documentPath = DocumentPath.rootDocumentPath(windowId, documentIdStr);
    final IDocumentChangesCollector changesCollector = NullDocumentChangesCollector.instance;
    return Execution.callInNewExecution("window.processTemplate", () -> documentCollection.forDocumentWritable(documentPath, changesCollector, document -> {
        document.saveIfValidAndHasChanges();
        if (document.hasChangesRecursivelly()) {
            throw new AdempiereException("Not saved");
        }
        final int newRecordId = newRecordDescriptorsProvider.getNewRecordDescriptor(document.getEntityDescriptor()).getProcessor().processNewRecordDocument(document);
        return newRecordId;
    }));
}


public List<JSONDocument> getData(DocumentPath documentPath,DocumentQueryOrderByList orderBys,JSONDocumentOptions jsonOpts){
    userSession.assertLoggedIn();
    return documentCollection.forRootDocumentReadonly(documentPath, rootDocument -> {
        List<Document> documents;
        if (documentPath.isRootDocument()) {
            documents = ImmutableList.of(rootDocument);
        } else if (documentPath.isAnyIncludedDocument()) {
            documents = rootDocument.getIncludedDocuments(documentPath.getDetailId(), orderBys).toList();
        } else if (documentPath.isSingleIncludedDocument()) {
            documents = ImmutableList.of(rootDocument.getIncludedDocument(documentPath.getDetailId(), documentPath.getSingleRowId()));
        } else {
            documents = rootDocument.getIncludedDocuments(documentPath.getDetailId(), documentPath.getRowIds()).toList();
        }
        return JSONDocument.ofDocumentsList(documents, jsonOpts);
    });
}


@GetMapping("/{windowId}/{documentId}")
public List<JSONDocument> getRootDocuments(String windowIdStr,String documentIdStr,String fieldsListStr,boolean advanced,boolean noTabs){
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentPath documentPath = DocumentPath.rootDocumentPath(windowId, documentIdStr);
    final JSONDocumentOptions jsonOpts = newJSONDocumentOptions().showOnlyFieldsListStr(fieldsListStr).showAdvancedFields(advanced).doNotFetchIncludedTabs(noTabs).build();
    return getData(documentPath, DocumentQueryOrderByList.EMPTY, jsonOpts);
}


@PostMapping("/{windowId}/{documentId}/{tabId}/{rowId}/discardChanges")
public void discardChanges(String windowIdStr,String documentIdStr,String tabIdStr_NOTUSED,String rowIdStr_NOTUSED){
    // For now it's OK if we invalidate the whole root document
    discardChanges(windowIdStr, documentIdStr);
}


@PatchMapping("/{windowId}/{documentId}/{tabId}/{rowId}")
public List<JSONDocument> patchIncludedDocument(String windowIdStr,String documentIdStr,String detailIdStr,String rowIdStr,boolean advanced,List<JSONDocumentChangedEvent> events){
    final DocumentPath documentPath = DocumentPath.builder().setDocumentType(WindowId.fromJson(windowIdStr)).setDocumentId(documentIdStr).setDetailId(detailIdStr).setRowId(rowIdStr).allowNewRowId().build();
    return patchDocument(documentPath, advanced, events);
}


@PatchMapping("/{windowId}/{documentId}")
public List<JSONDocument> patchRootDocument(String windowIdStr,String documentIdStr,boolean advanced,List<JSONDocumentChangedEvent> events){
    final DocumentPath documentPath = DocumentPath.builder().setDocumentType(WindowId.fromJson(windowIdStr)).setDocumentId(documentIdStr).allowNewDocumentId().build();
    return patchDocument(documentPath, advanced, events);
}


public DocumentZoomIntoInfo getDocumentFieldZoomInto(Document document,String fieldName){
    final DocumentEntityDescriptor entityDescriptor = document.getEntityDescriptor();
    final DocumentFieldDescriptor singleKeyFieldDescriptor = entityDescriptor.getSingleIdFieldOrNull();
    final IDocumentFieldView field = document.getFieldView(fieldName);
    // Generic ZoomInto button
    if (field.getDescriptor().getWidgetType() == DocumentFieldWidgetType.ZoomIntoButton) {
        final ButtonFieldActionDescriptor buttonActionDescriptor = field.getDescriptor().getButtonActionDescriptor();
        final String zoomIntoTableIdFieldName = buttonActionDescriptor.getZoomIntoTableIdFieldName();
        final Integer adTableId = document.getFieldView(zoomIntoTableIdFieldName).getValueAs(Integer.class);
        if (adTableId == null || adTableId <= 0) {
            throw new EntityNotFoundException("Cannot fetch ZoomInto infos from a null value. No AD_Table_ID.").setParameter("documentPath", document.getDocumentPath()).setParameter("fieldName", fieldName).setParameter("zoomIntoTableIdFieldName", zoomIntoTableIdFieldName);
        }
        final Integer recordId = field.getValueAs(Integer.class);
        if (recordId == null) {
            throw new EntityNotFoundException("Cannot fetch ZoomInto infos from a null value. No Record_ID.").setParameter("documentPath", document.getDocumentPath()).setParameter("fieldName", fieldName).setParameter("zoomIntoTableIdFieldName", zoomIntoTableIdFieldName);
        }
        final String tableName = Services.get(IADTableDAO.class).retrieveTableName(adTableId);
        return DocumentZoomIntoInfo.of(tableName, recordId);
    } else // Key Field
    if (singleKeyFieldDescriptor != null && singleKeyFieldDescriptor.getFieldName().equals(fieldName)) {
        // Allow zooming into key column. It shall open precisely this record in a new window.
        // (see https://github.com/metasfresh/metasfresh/issues/1687 to understand the use-case)
        final String tableName = entityDescriptor.getTableName();
        final int recordId = document.getDocumentIdAsInt();
        return DocumentZoomIntoInfo.of(tableName, recordId);
    } else // Regular lookup value
    {
        return field.getZoomIntoInfo();
    }
}


@PostMapping("/{windowId}/{documentId}/duplicate")
public JSONDocument duplicate(String windowIdStr,String documentIdStr,boolean advanced){
    userSession.assertLoggedIn();
    final DocumentPath fromDocumentPath = DocumentPath.rootDocumentPath(WindowId.fromJson(windowIdStr), DocumentId.of(documentIdStr));
    final Document documentCopy = documentCollection.duplicateDocument(fromDocumentPath);
    final JSONDocumentOptions jsonOpts = newJSONDocumentOptions().showAdvancedFields(advanced).build();
    return JSONDocument.ofDocument(documentCopy, jsonOpts);
}


public List<JSONDocument> patchDocument(DocumentPath documentPath,boolean advanced,List<JSONDocumentChangedEvent> events){
    userSession.assertLoggedIn();
    final JSONDocumentOptions jsonOpts = newJSONDocumentOptions().showAdvancedFields(advanced).build();
    return Execution.callInNewExecution("window.commit", () -> patchDocument0(documentPath, events, jsonOpts));
}


public List<JSONDocument> patchDocument0(DocumentPath documentPath,List<JSONDocumentChangedEvent> events,JSONDocumentOptions jsonOpts){
    final IDocumentChangesCollector changesCollector = Execution.getCurrentDocumentChangesCollectorOrNull();
    documentCollection.forDocumentWritable(documentPath, changesCollector, document -> {
        document.processValueChanges(events, REASON_Value_DirectSetFromCommitAPI);
        changesCollector.setPrimaryChange(document.getDocumentPath());
        // void
        return null;
    });
    // Extract and send websocket events
    final List<JSONDocument> jsonDocumentEvents = JSONDocument.ofEvents(changesCollector, jsonOpts);
    websocketPublisher.convertAndPublish(jsonDocumentEvents);
    return jsonDocumentEvents;
}


public JSONLookupValuesList getDocumentFieldTypeahead(DocumentPath documentPath,String fieldName,String query){
    userSession.assertLoggedIn();
    return documentCollection.forDocumentReadonly(documentPath, document -> document.getFieldLookupValuesForQuery(fieldName, query)).transform(this::toJSONLookupValuesList);
}


@GetMapping("/{windowId}/{documentId}/{tabId}")
public List<JSONDocument> getIncludedTabRows(String windowIdStr,String documentIdStr,String tabIdStr,String rowIdsListStr,String fieldsListStr,boolean advanced,String orderBysListStr){
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentId documentId = DocumentId.of(documentIdStr);
    final DetailId tabId = DetailId.fromJson(tabIdStr);
    final DocumentIdsSelection onlyRowIds = DocumentIdsSelection.ofCommaSeparatedString(rowIdsListStr);
    final DocumentPath documentPath;
    if (onlyRowIds.isEmpty() || onlyRowIds.isAll()) {
        documentPath = DocumentPath.includedDocumentPath(windowId, documentId, tabId);
    } else {
        documentPath = DocumentPath.includedDocumentPath(windowId, documentId, tabId, onlyRowIds);
    }
    final DocumentQueryOrderByList orderBys = DocumentQueryOrderByList.parse(orderBysListStr);
    final JSONDocumentOptions jsonOpts = newJSONDocumentOptions().showOnlyFieldsListStr(fieldsListStr).showAdvancedFields(advanced).build();
    return getData(documentPath, orderBys, jsonOpts);
}


@GetMapping("/{windowId}/{documentId}/{tabId}/{rowId}")
public List<JSONDocument> getIncludedTabRow(String windowIdStr,String documentIdStr,String tabIdStr,String rowIdStr,String fieldsListStr,boolean advanced){
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentPath documentPath = DocumentPath.includedDocumentPath(windowId, documentIdStr, tabIdStr, rowIdStr);
    final JSONDocumentOptions jsonOpts = newJSONDocumentOptions().showOnlyFieldsListStr(fieldsListStr).showAdvancedFields(advanced).build();
    return getData(documentPath, DocumentQueryOrderByList.EMPTY, jsonOpts);
}


@DeleteMapping("/{windowId}")
public List<JSONDocument> deleteRootDocumentsList(String windowIdStr,String idsListStr){
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final List<DocumentPath> documentPaths = DocumentPath.rootDocumentPathsList(windowId, idsListStr);
    if (documentPaths.isEmpty()) {
        throw new IllegalArgumentException("No ids provided");
    }
    return deleteDocuments(documentPaths);
}


@GetMapping("/{windowId}/{documentId}/{tabId}/{rowId}/actions")
public JSONDocumentActionsList getIncludedDocumentActions(String windowIdStr,String documentIdStr,String tabIdStr,String rowIdStr,boolean returnDisabled){
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DetailId selectedTabId = DetailId.fromJson(tabIdStr);
    final DocumentPath includedDocumentPath = DocumentPath.includedDocumentPath(windowId, documentIdStr, selectedTabId.toJson(), rowIdStr);
    final Set<TableRecordReference> selectedIncludedRecords = ImmutableSet.of();
    return getDocumentActions(includedDocumentPath, selectedTabId, selectedIncludedRecords, returnDisabled, DisplayPlace.SingleDocumentActionsMenu);
}


}