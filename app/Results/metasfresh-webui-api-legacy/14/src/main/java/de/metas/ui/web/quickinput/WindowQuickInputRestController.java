package de.metas.ui.web.quickinput;
 import java.util.List;
import java.util.function.Function;
import org.adempiere.util.lang.IAutoCloseable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de.metas.cache.CCache;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.controller.DocumentPermissionsHelper;
import de.metas.ui.web.window.controller.Execution;
import de.metas.ui.web.window.controller.WindowRestController;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONQuickInputLayoutDescriptor;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.factory.NewRecordDescriptorsProvider;
import de.metas.ui.web.window.events.DocumentWebsocketPublisher;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import io.swagger.annotations.Api;
import lombok.NonNull;
@Api
@RestController
@RequestMapping(WindowQuickInputRestController.ENDPOINT)
public class WindowQuickInputRestController {

 public  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private  DocumentCollection documentsCollection;

@Autowired
 private  QuickInputDescriptorFactoryService quickInputDescriptors;

@Autowired
 private  NewRecordDescriptorsProvider newRecordDescriptorsProvider;

@Autowired
 private  DocumentWebsocketPublisher websocketPublisher;

 private  CCache<DocumentId,QuickInput> _quickInputDocuments;


public R forQuickInputWritable(QuickInputPath quickInputPath,IDocumentChangesCollector changesCollector,Function<QuickInput,R> quickInputProcessor){
    return documentsCollection.forRootDocumentWritable(quickInputPath.getRootDocumentPath(), changesCollector, rootDocument -> {
        try (final IAutoCloseable c = getQuickInputNoLock(quickInputPath).lockForWriting()) {
            final QuickInput quickInput = getQuickInputNoLock(quickInputPath).copy(CopyMode.CheckOutWritable, changesCollector).bindRootDocument(rootDocument).assertTargetWritable();
            final R result = quickInputProcessor.apply(quickInput);
            commit(quickInput);
            return result;
        }
    });
}


@GetMapping("/{quickInputId}/field/{fieldName}/typeahead")
public JSONLookupValuesList getFieldTypeaheadValues(String windowIdStr,String documentIdStr,String tabIdStr,String quickInputIdStr,String fieldName,String query){
    userSession.assertLoggedIn();
    final String adLanguage = userSession.getAD_Language();
    final QuickInputPath quickInputPath = QuickInputPath.of(windowIdStr, documentIdStr, tabIdStr, quickInputIdStr);
    return forQuickInputReadonly(quickInputPath, quickInput -> quickInput.getFieldTypeaheadValues(fieldName, query, adLanguage));
}


public R forQuickInputReadonly(QuickInputPath quickInputPath,Function<QuickInput,R> quickInputProcessor){
    return documentsCollection.forDocumentReadonly(quickInputPath.getRootDocumentPath(), rootDocument -> {
        try (final IAutoCloseable c = getQuickInputNoLock(quickInputPath).lockForReading()) {
            final QuickInput quickInput = getQuickInputNoLock(quickInputPath).copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance).bindRootDocument(rootDocument).assertTargetWritable();
            return quickInputProcessor.apply(quickInput);
        }
    });
}


public List<JSONDocument> completeQuickInput(QuickInput quickInput){
    final List<Document> documentLines = quickInput.complete();
    return JSONDocument.ofDocumentsList(documentLines, newJSONDocumentOptions());
}


public void commit(QuickInput quickInput){
    if (quickInput.isCompleted()) {
        _quickInputDocuments.remove(quickInput.getId());
    } else {
        _quickInputDocuments.put(quickInput.getId(), quickInput.copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance));
    }
}


public QuickInput getQuickInputNoLock(QuickInputPath quickInputPath){
    return _quickInputDocuments.getOrElseThrow(quickInputPath.getQuickInputId(), () -> new EntityNotFoundException("No quick input document found for " + quickInputPath));
}


public QuickInput createQuickInput(Document rootDocument,DetailId detailId){
    // Make sure we can edit our root document. Fail fast.
    DocumentPermissionsHelper.assertCanEdit(rootDocument, userSession.getUserRolePermissions());
    final DocumentEntityDescriptor includedDocumentDescriptor = rootDocument.getEntityDescriptor().getIncludedEntityByDetailId(detailId);
    final QuickInputDescriptor quickInputDescriptor = quickInputDescriptors.getQuickInputEntityDescriptor(includedDocumentDescriptor);
    try {
        return QuickInput.builder().setQuickInputDescriptor(quickInputDescriptor).setRootDocumentPath(rootDocument.getDocumentPath()).build().bindRootDocument(rootDocument).assertTargetWritable();
    } catch (Exception ex) {
        // Avoid showing "weird" exception to use, so we return HTTP 404 which is interpreted by frontend
        // see https://github.com/metasfresh/metasfresh-webui-frontend/issues/487
        throw EntityNotFoundException.wrapIfNeeded(ex);
    }
}


@PatchMapping("/{quickInputId}")
public List<JSONDocument> processChanges(String windowIdStr,String documentIdStr,String tabIdStr,String quickInputIdStr,List<JSONDocumentChangedEvent> events){
    userSession.assertLoggedIn();
    final QuickInputPath quickInputPath = QuickInputPath.of(windowIdStr, documentIdStr, tabIdStr, quickInputIdStr);
    return Execution.callInNewExecution("quickInput-writable-" + quickInputPath, () -> {
        final IDocumentChangesCollector changesCollector = Execution.getCurrentDocumentChangesCollectorOrNull();
        forQuickInputWritable(quickInputPath, changesCollector, quickInput -> {
            quickInput.processValueChanges(events);
            changesCollector.setPrimaryChange(quickInput.getDocumentPath());
            // void
            return null;
        });
        // Extract and send websocket events
        final List<JSONDocument> jsonDocumentEvents = JSONDocument.ofEvents(changesCollector, newJSONDocumentOptions());
        websocketPublisher.convertAndPublish(jsonDocumentEvents);
        return jsonDocumentEvents;
    });
}


public JSONDocumentOptions newJSONDocumentOptions(){
    return JSONDocumentOptions.builder().userSession(userSession).build();
}


@GetMapping("/layout")
public JSONQuickInputLayoutDescriptor getLayout(String windowIdStr,String documentIdStr_NOTUSED,String tabIdStr){
    userSession.assertLoggedIn();
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentEntityDescriptor includedDocumentDescriptor = documentsCollection.getDocumentEntityDescriptor(windowId).getIncludedEntityByDetailId(DetailId.fromJson(tabIdStr));
    final QuickInputDescriptor quickInputDescriptor = quickInputDescriptors.getQuickInputEntityDescriptor(includedDocumentDescriptor);
    if (quickInputDescriptor == null) {
        return null;
    }
    final QuickInputLayoutDescriptor layout = quickInputDescriptor.getLayout();
    return JSONQuickInputLayoutDescriptor.fromNullable(layout, newJSONLayoutOptions());
}


@GetMapping("/{quickInputId}/field/{fieldName}/dropdown")
public JSONLookupValuesList getFieldDropdownValues(String windowIdStr,String documentIdStr,String tabIdStr,String quickInputIdStr,String fieldName){
    userSession.assertLoggedIn();
    final String adLanguage = userSession.getAD_Language();
    final QuickInputPath quickInputPath = QuickInputPath.of(windowIdStr, documentIdStr, tabIdStr, quickInputIdStr);
    return forQuickInputReadonly(quickInputPath, quickInput -> quickInput.getFieldDropdownValues(fieldName, adLanguage));
}


@GetMapping("/{quickInputId}")
public JSONDocument getById(String windowIdStr,String documentIdStr,String tabIdStr,String quickInputIdStr){
    userSession.assertLoggedIn();
    final QuickInputPath quickInputPath = QuickInputPath.of(windowIdStr, documentIdStr, tabIdStr, quickInputIdStr);
    return forQuickInputReadonly(quickInputPath, quickInput -> JSONDocument.ofDocument(quickInput.getQuickInputDocument(), newJSONDocumentOptions()));
}


public JSONDocumentLayoutOptions newJSONLayoutOptions(){
    return JSONDocumentLayoutOptions.prepareFrom(userSession).newRecordDescriptorsProvider(newRecordDescriptorsProvider).build();
}


@RequestMapping(method = RequestMethod.HEAD)
public ResponseEntity<Object> checkSupported(String windowIdStr,String documentIdStr_NOTUSED,String tabIdStr){
    userSession.assertLoggedIn();
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final DocumentEntityDescriptor includedDocumentDescriptor = documentsCollection.getDocumentEntityDescriptor(windowId).getIncludedEntityByDetailId(DetailId.fromJson(tabIdStr));
    if (quickInputDescriptors.hasQuickInputEntityDescriptor(includedDocumentDescriptor)) {
        return new ResponseEntity<>(HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


@PostMapping("{quickInputId}/complete")
public List<JSONDocument> complete(String windowIdStr,String documentIdStr,String tabIdStr,String quickInputIdStr){
    userSession.assertLoggedIn();
    final QuickInputPath quickInputPath = QuickInputPath.of(windowIdStr, documentIdStr, tabIdStr, quickInputIdStr);
    return Execution.callInNewExecution("quickInput-complete-" + quickInputPath, () -> forQuickInputWritable(quickInputPath, NullDocumentChangesCollector.instance, this::completeQuickInput));
}


}