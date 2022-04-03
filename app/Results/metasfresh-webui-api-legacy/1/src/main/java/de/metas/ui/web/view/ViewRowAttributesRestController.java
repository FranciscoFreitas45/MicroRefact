package de.metas.ui.web.view;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.view.json.JSONViewRowAttributes;
import de.metas.ui.web.view.json.JSONViewRowAttributesLayout;
import de.metas.ui.web.window.controller.Execution;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
@RestController
@RequestMapping(value = ViewRowAttributesRestController.ENDPOINT)
public class ViewRowAttributesRestController {

 private  String PARAM_WindowId;

 private  String PARAM_ViewId;

 private  String PARAM_RowId;

 static  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private  IViewsRepository viewsRepo;


public JSONOptions newJSONOptions(){
    return JSONOptions.of(userSession);
}


public JSONDocumentOptions newJSONDocumentOptions(){
    return JSONDocumentOptions.of(userSession);
}


@GetMapping("/attribute/{attributeName}/typeahead")
public JSONLookupValuesList getAttributeTypeahead(String windowIdStr,String viewIdStr,String rowIdStr,String attributeName,String query){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final DocumentId rowId = DocumentId.of(rowIdStr);
    return viewsRepo.getView(viewId).getById(rowId).getAttributes().getAttributeTypeahead(attributeName, query).transform(this::toJSONLookupValuesList);
}


public JSONLookupValuesList toJSONLookupValuesList(LookupValuesList lookupValuesList){
    return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
}


@GetMapping("/layout")
public JSONViewRowAttributesLayout getAttributesLayout(String windowIdStr,String viewIdStr,String rowIdStr){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final ViewRowAttributesLayout layout = viewsRepo.getView(viewId).getById(DocumentId.of(rowIdStr)).getAttributes().getLayout();
    return JSONViewRowAttributesLayout.of(layout, newJSONLayoutOptions());
}


@GetMapping("/attribute/{attributeName}/dropdown")
public JSONLookupValuesList getAttributeDropdown(String windowIdStr,String viewIdStr,String rowIdStr,String attributeName){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final DocumentId rowId = DocumentId.of(rowIdStr);
    return viewsRepo.getView(viewId).getById(rowId).getAttributes().getAttributeDropdown(attributeName).transform(this::toJSONLookupValuesList);
}


public JSONDocumentLayoutOptions newJSONLayoutOptions(){
    return JSONDocumentLayoutOptions.of(userSession);
}


@GetMapping
public JSONViewRowAttributes getData(String windowIdStr,String viewIdStr,String rowIdStr){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final DocumentId rowId = DocumentId.of(rowIdStr);
    return viewsRepo.getView(viewId).getById(rowId).getAttributes().toJson(newJSONOptions());
}


@PatchMapping
public List<JSONDocument> processChanges(String windowIdStr,String viewIdStr,String rowIdStr,List<JSONDocumentChangedEvent> events){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final DocumentId rowId = DocumentId.of(rowIdStr);
    return Execution.callInNewExecution("processChanges", () -> {
        viewsRepo.getView(viewId).getById(rowId).getAttributes().processChanges(events);
        return JSONDocument.ofEvents(Execution.getCurrentDocumentChangesCollectorOrNull(), newJSONDocumentOptions());
    });
}


}