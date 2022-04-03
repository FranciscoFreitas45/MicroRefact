package de.metas.ui.web.window.datatypes.json;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentChanges;
import de.metas.ui.web.window.model.DocumentSaveStatus;
import de.metas.ui.web.window.model.DocumentStandardAction;
import de.metas.ui.web.window.model.DocumentValidStatus;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IIncludedDocumentsCollection;
import lombok.NonNull;
import lombok.ToString;
@ToString(callSuper = true)
public class JSONDocument extends JSONDocumentBase{

@JsonProperty("validStatus")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  DocumentValidStatus validStatus;

@JsonProperty("saveStatus")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  DocumentSaveStatus saveStatus;

@JsonProperty("includedTabsInfo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Map<String,JSONIncludedTabInfo> includedTabsInfo;

@JsonProperty("standardActions")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Set<DocumentStandardAction> standardActions;

@JsonProperty("websocketEndpoint")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String websocketEndpoint;

@JsonProperty("timestamp")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String timestamp;


public JSONDocument ofDocument(Document document,JSONDocumentOptions options){
    final JSONDocument jsonDocument = new JSONDocument(document.getDocumentPath());
    // fieldsBy
    // Fields
    {
        final List<JSONDocumentField> jsonFields = new ArrayList<>();
        // Add pseudo "ID" field first
        jsonFields.add(0, JSONDocumentField.idField(document.getDocumentIdAsJson()));
        // Append the other fields
        document.getFieldViews().stream().filter(options.documentFieldFilter()).map(field -> JSONDocumentField.ofDocumentField(field, options.getJsonOpts())).peek(// apply permissions
        jsonField -> options.getDocumentPermissions().apply(document, jsonField)).forEach(jsonFields::add);
        jsonDocument.setFields(jsonFields);
    }
    // 
    // Valid Status
    final DocumentValidStatus documentValidStatus = document.getValidStatus();
    if (documentValidStatus != null) {
        jsonDocument.setValidStatus(documentValidStatus);
    }
    // 
    // Save Status
    final DocumentSaveStatus documentSaveStatus = document.getSaveStatus();
    if (documentSaveStatus != null) {
        jsonDocument.setSaveStatus(documentSaveStatus);
    }
    // 
    // Included tabs info
    if (!options.isDoNotFetchIncludedTabs()) {
        document.getIncludedDocumentsCollections().stream().map(JSONDocument::createIncludedTabInfo).peek(jsonIncludedTabInfo -> options.getDocumentPermissions().apply(document, jsonIncludedTabInfo)).forEach(jsonDocument::addIncludedTabInfo);
    }
    // 
    // Available standard actions
    jsonDocument.setStandardActions(document.getStandardActions());
    // 
    // Set debugging info
    if (WindowConstants.isProtocolDebugging()) {
        jsonDocument.putDebugProperty("tablename", document.getEntityDescriptor().getTableNameOrNull());
        jsonDocument.putDebugProperty(JSONOptions.DEBUG_ATTRNAME, options.toString());
        jsonDocument.putDebugProperty("fields-count", jsonDocument.getFieldsCount());
    }
    return jsonDocument;
}


public DocumentValidStatus getValidStatus(){
    return validStatus;
}


public JSONDocument ofEventOrNull(DocumentChanges documentChangedEvents,JSONDocumentOptions options){
    if (documentChangedEvents.isEmpty()) {
        return null;
    }
    final DocumentPath documentPath = documentChangedEvents.getDocumentPath();
    final JSONDocument jsonDocument = new JSONDocument(documentPath);
    // If the document was deleted, we just need to export that flag. All the other changes are not relevant.
    if (documentChangedEvents.isDeleted()) {
        jsonDocument.setDeleted();
        return jsonDocument;
    }
    // 
    // Fields
    {
        final List<JSONDocumentField> jsonFields = new ArrayList<>();
        documentChangedEvents.getFieldChangesList().stream().filter(options.documentFieldChangeFilter()).forEach((field) -> {
            // Add the pseudo-field "ID" first
            if (field.isKey()) {
                jsonFields.add(0, JSONDocumentField.idField(field.getValueAsJsonObject(options.getJsonOpts())));
            }
            // Append the other fields
            final JSONDocumentField jsonField = JSONDocumentField.ofDocumentFieldChangedEvent(field, options.getJsonOpts());
            // apply permissions
            options.getDocumentPermissions().apply(documentPath, jsonField);
            jsonFields.add(jsonField);
        });
        jsonDocument.setFields(jsonFields);
    }
    // 
    // Valid status
    final DocumentValidStatus documentValidStatus = documentChangedEvents.getDocumentValidStatus();
    if (documentValidStatus != null) {
        jsonDocument.setValidStatus(documentValidStatus);
    }
    // 
    // Save status
    final DocumentSaveStatus documentSaveStatus = documentChangedEvents.getDocumentSaveStatus();
    if (documentSaveStatus != null) {
        jsonDocument.setSaveStatus(documentSaveStatus);
    }
    // 
    // Included tabs info
    documentChangedEvents.getIncludedDetailInfos().stream().map(JSONDocument::createIncludedTabInfo).peek(jsonIncludedTabInfo -> options.getDocumentPermissions().apply(documentPath, jsonIncludedTabInfo)).forEach(jsonDocument::addIncludedTabInfo);
    return jsonDocument;
}


public String buildWebsocketEndpointOrNull(WindowId windowId,DocumentId documentId){
    if (windowId != null && documentId != null) {
        return WebSocketConfig.buildDocumentTopicName(windowId, documentId);
    } else {
        return null;
    }
}


public void setStandardActions(Set<DocumentStandardAction> standardActions){
    this.standardActions = standardActions;
}


public void setSaveStatus(DocumentSaveStatus documentSaveStatus){
    saveStatus = documentSaveStatus;
}


public List<JSONDocument> ofDocumentsList(Collection<Document> documents,JSONDocumentOptions options){
    return documents.stream().map(document -> ofDocument(document, options)).collect(Collectors.toList());
}


public JSONIncludedTabInfo createIncludedTabInfo(DocumentChanges.IncludedDetailInfo includedDetailInfo){
    final JSONIncludedTabInfo tabInfo = JSONIncludedTabInfo.newInstance(includedDetailInfo.getDetailId());
    if (includedDetailInfo.isStale()) {
        tabInfo.markAllRowsStaled();
    }
    final LogicExpressionResult allowCreateNew = includedDetailInfo.getAllowNew();
    if (allowCreateNew != null) {
        tabInfo.setAllowCreateNew(allowCreateNew.booleanValue(), allowCreateNew.getName());
    }
    final LogicExpressionResult allowDelete = includedDetailInfo.getAllowDelete();
    if (allowDelete != null) {
        tabInfo.setAllowDelete(allowDelete.booleanValue(), allowDelete.getName());
    }
    return tabInfo;
}


public List<JSONDocument> ofEvents(IDocumentChangesCollector documentChangesCollector,JSONDocumentOptions options){
    final int MAX_SIZE = 100;
    final List<JSONDocument> jsonChanges = documentChangesCollector.streamOrderedDocumentChanges().map(documentChanges -> ofEventOrNull(documentChanges, options)).filter(jsonDocument -> jsonDocument != null).limit(MAX_SIZE + 1).collect(ImmutableList.toImmutableList());
    // 
    // Prevent sending more then MAX_SIZE events because that will freeze the frontend application.
    if (jsonChanges.size() > MAX_SIZE) {
        throw new AdempiereException("Events count exceeded").setParameter("maxSize", MAX_SIZE).setParameter("documentChangesCollector", documentChangesCollector).setParameter("first events", jsonChanges);
    }
    return jsonChanges;
}


public void setValidStatus(DocumentValidStatus validStatus){
    this.validStatus = validStatus;
}


public DocumentSaveStatus getSaveStatus(){
    return saveStatus;
}


@JsonIgnore
public Collection<JSONIncludedTabInfo> getIncludedTabsInfos(){
    if (includedTabsInfo == null || includedTabsInfo.isEmpty()) {
        return ImmutableSet.of();
    }
    return includedTabsInfo.values();
}


public void addIncludedTabInfo(JSONIncludedTabInfo tabInfo){
    if (includedTabsInfo == null) {
        includedTabsInfo = new HashMap<>();
    }
    includedTabsInfo.put(tabInfo.getTabId().toJson(), tabInfo);
}


}