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
import de.metas.ui.web.view.IEditableView.RowEditingContext;
import de.metas.ui.web.view.json.JSONViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.model.DocumentCollection;
@RestController
@RequestMapping(ViewRowEditRestController.ENDPOINT)
public class ViewRowEditRestController {

 private  String PARAM_WindowId;

 private  String PARAM_ViewId;

 private  String PARAM_RowId;

 private  String PARAM_FieldName;

 static  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private  IViewsRepository viewsRepo;

@Autowired
 private  DocumentCollection documentsCollection;


public JSONOptions newJSONOptions(){
    return JSONOptions.of(userSession);
}


@GetMapping("/{fieldName}/typeahead")
public JSONLookupValuesList getFieldTypeahead(String windowIdStr,String viewIdStr,String rowIdStr,String fieldName,String query){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final DocumentId rowId = DocumentId.of(rowIdStr);
    final IEditableView view = getEditableView(viewId);
    final RowEditingContext editingCtx = createRowEditingContext(rowId);
    return view.getFieldTypeahead(editingCtx, fieldName, query).transform(this::toJSONLookupValuesList);
}


public JSONLookupValuesList toJSONLookupValuesList(LookupValuesList lookupValuesList){
    return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
}


public RowEditingContext createRowEditingContext(DocumentId rowId){
    return RowEditingContext.builder().rowId(rowId).documentsCollection(documentsCollection).build();
}


public IEditableView getEditableView(ViewId viewId){
    final IView view = viewsRepo.getView(viewId);
    return IEditableView.asEditableView(view);
}


@PatchMapping
public JSONViewRow patchRow(String windowIdStr,String viewIdStr,String rowIdStr,List<JSONDocumentChangedEvent> fieldChangeRequests){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final DocumentId rowId = DocumentId.of(rowIdStr);
    final IEditableView view = getEditableView(viewId);
    final RowEditingContext editingCtx = createRowEditingContext(rowId);
    view.patchViewRow(editingCtx, fieldChangeRequests);
    final IViewRow row = view.getById(rowId);
    final IViewRowOverrides rowOverrides = ViewRowOverridesHelper.getViewRowOverrides(view);
    final JSONOptions jsonOpts = newJSONOptions();
    return JSONViewRow.ofRow(row, rowOverrides, jsonOpts);
}


@GetMapping("/{fieldName}/dropdown")
public JSONLookupValuesList getFieldDropdown(String windowIdStr,String viewIdStr,String rowIdStr,String fieldName){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final DocumentId rowId = DocumentId.of(rowIdStr);
    final IEditableView view = getEditableView(viewId);
    final RowEditingContext editingCtx = createRowEditingContext(rowId);
    return view.getFieldDropdown(editingCtx, fieldName).transform(this::toJSONLookupValuesList);
}


}