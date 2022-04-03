package DTO;
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
public class JSONDocument extends JSONDocumentBase{

 private  DocumentValidStatus validStatus;

 private  DocumentSaveStatus saveStatus;

 private  Map<String,JSONIncludedTabInfo> includedTabsInfo;

 private  Set<DocumentStandardAction> standardActions;

 private  String websocketEndpoint;

 private  String timestamp;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public DocumentValidStatus getValidStatus(){
    return validStatus;
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


public List<JSONDocument> ofDocumentsList(Collection<Document> documents,JSONDocumentOptions options){
    return documents.stream().map(document -> ofDocument(document, options)).collect(Collectors.toList());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofDocumentsList"))

.queryParam("documents",documents);
.queryParam("options",options);
List<JSONDocument> aux = restTemplate.getForObject(builder.toUriString(),List<JSONDocument>.class);
return aux;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofEvents"))

.queryParam("documentChangesCollector",documentChangesCollector);
.queryParam("options",options);
List<JSONDocument> aux = restTemplate.getForObject(builder.toUriString(),List<JSONDocument>.class);
return aux;
}


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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofDocument"))

.queryParam("document",document);
.queryParam("options",options);
JSONDocument aux = restTemplate.getForObject(builder.toUriString(),JSONDocument.class);
return aux;
}


}