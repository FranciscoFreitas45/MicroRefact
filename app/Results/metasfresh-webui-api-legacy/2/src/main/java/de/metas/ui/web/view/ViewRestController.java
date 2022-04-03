package de.metas.ui.web.view;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;
import org.compiere.util.MimeType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import com.google.common.collect.ImmutableList;
import de.metas.impexp.excel.ExcelFormat;
import de.metas.impexp.excel.ExcelFormats;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.cache.ETagResponseEntityBuilder;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.process.ProcessRestController;
import de.metas.ui.web.process.ViewAsPreconditionsContext;
import de.metas.ui.web.process.WebuiPreconditionsContext;
import de.metas.ui.web.process.json.JSONDocumentActionsList;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONCreateViewRequest;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.view.json.JSONViewLayout;
import de.metas.ui.web.view.json.JSONViewProfilesList;
import de.metas.ui.web.view.json.JSONViewResult;
import de.metas.ui.web.view.json.JSONViewRow;
import de.metas.ui.web.window.controller.WindowRestController;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.datatypes.json.JSONZoomInto;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.NonNull;
@Api
@RestController
@RequestMapping(value = ViewRestController.ENDPOINT)
public class ViewRestController {

 public  String PARAM_WindowId;

 public  String ENDPOINT;

 private  String PARAM_ViewId;

 private  String PARAM_ViewDataType;

 private  String PARAM_OrderBy;

 private  String PARAM_OrderBy_Description;

 private  String PARAM_FirstRow;

 private  String PARAM_FirstRow_Description;

 private  String PARAM_PageLength;

 private  String PARAM_FilterId;

 private  UserSession userSession;

 private  IViewsRepository viewsRepo;

 private  ProcessRestController processRestController;

 private  WindowRestController windowRestController;


public JSONOptions newJSONOptions(){
    return JSONOptions.of(userSession);
}


@GetMapping("/{viewId}/filter/{filterId}/field/{parameterName}/typeahead")
public JSONLookupValuesList getFilterParameterTypeahead(String windowId,String viewIdStr,String filterId,String parameterName,String query){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowId, viewIdStr);
    final IView view = viewsRepo.getView(viewId);
    final Evaluatee ctx = createFilterParameterLookupContext(view);
    return view.getFilterParameterTypeahead(filterId, parameterName, query, ctx).transform(this::toJSONLookupValuesList);
}


@DeleteMapping("/{viewId}")
public void closeView(String windowId,String viewIdStr,String closeActionStr){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowId, viewIdStr);
    final ViewCloseAction closeAction = ViewCloseAction.fromJsonOr(closeActionStr, ViewCloseAction.DONE);
    viewsRepo.closeView(viewId, closeAction);
}


@GetMapping("/{viewId}/filter/{filterId}/field/{parameterName}/dropdown")
public JSONLookupValuesList getFilterParameterDropdown(String windowId,String viewIdStr,String filterId,String parameterName){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowId, viewIdStr);
    final IView view = viewsRepo.getView(viewId);
    final Evaluatee ctx = createFilterParameterLookupContext(view);
    return view.getFilterParameterDropdown(filterId, parameterName, ctx).transform(this::toJSONLookupValuesList);
}


public JSONLookupValuesList toJSONLookupValuesList(LookupValuesList lookupValuesList){
    return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
}


@GetMapping("/{viewId}/actions")
public JSONDocumentActionsList getDocumentActions(String windowId,String viewIdStr,String selectedIdsListStr,String parentViewIdStr,String parentViewSelectedIdsListStr,String childViewIdStr,String childViewSelectedIdsListStr,boolean all){
    userSession.assertLoggedIn();
    final WebuiPreconditionsContext preconditionsContext = newPreconditionsContextBuilder().windowId(windowId).viewIdString(viewIdStr).selectedIdsList(selectedIdsListStr).parentViewId(parentViewIdStr).parentViewSelectedIdsList(parentViewSelectedIdsListStr).childViewId(childViewIdStr).childViewSelectedIdsList(childViewSelectedIdsListStr).displayPlace(DisplayPlace.ViewActionsMenu).build();
    return processRestController.streamDocumentRelatedProcesses(preconditionsContext).filter(// shall be already filtered out, but just to make sure
    descriptor -> descriptor.isDisplayedOn(preconditionsContext.getDisplayPlace())).filter(// only those which are enabled and not internally rejected
    descriptor -> all || descriptor.isEnabled()).collect(JSONDocumentActionsList.collect(newJSONOptions()));
}


@GetMapping("/layout")
public ResponseEntity<JSONViewLayout> getViewLayout(String windowIdStr,JSONViewDataType viewDataType,String profileIdStr,WebRequest request){
    userSession.assertLoggedIn();
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final ViewLayout viewLayout = viewsRepo.getViewLayout(windowId, viewDataType, ViewProfileId.fromJson(profileIdStr));
    return ETagResponseEntityBuilder.ofETagAware(request, viewLayout).includeLanguageInETag().cacheMaxAge(userSession.getHttpCacheMaxAge()).jsonLayoutOptions(() -> newJSONLayoutOptions()).toLayoutJson(JSONViewLayout::of);
}


@GetMapping("/availableProfiles")
public JSONViewProfilesList getAvailableViewProfiles(String windowIdStr,JSONViewDataType viewDataType){
    final WindowId windowId = WindowId.fromJson(windowIdStr);
    final List<ViewProfile> availableProfiles = viewsRepo.getAvailableProfiles(windowId, viewDataType);
    return JSONViewProfilesList.of(availableProfiles, userSession.getAD_Language());
}


@GetMapping("/{viewId}/export/excel")
public ResponseEntity<Resource> exportToExcel(String windowIdStr,String viewIdStr,String selectedIdsListStr){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.ofViewIdString(viewIdStr, WindowId.fromJson(windowIdStr));
    final ExcelFormat excelFormat = ExcelFormats.getDefaultFormat();
    final File tmpFile = File.createTempFile("exportToExcel", "." + excelFormat.getFileExtension());
    try (final FileOutputStream out = new FileOutputStream(tmpFile)) {
        ViewExcelExporter.builder().excelFormat(excelFormat).view(viewsRepo.getView(viewId)).rowIds(DocumentIdsSelection.ofCommaSeparatedString(selectedIdsListStr)).layout(viewsRepo.getViewLayout(viewId.getWindowId(), JSONViewDataType.grid, ViewProfileId.NULL)).language(userSession.getLanguage()).zoneId(userSession.getTimeZone()).build().export(out);
    }
    // TODO: use a better name
    final String filename = "report." + excelFormat.getFileExtension();
    final String contentType = MimeType.getMimeType(filename);
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(contentType));
    headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"");
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    final ResponseEntity<Resource> response = new ResponseEntity<>(new InputStreamResource(new FileInputStream(tmpFile)), headers, HttpStatus.OK);
    return response;
}


@GetMapping("/{viewId}/byIds")
public List<JSONViewRow> getByIds(String windowId,String viewIdStr,String idsListStr){
    userSession.assertLoggedIn();
    final DocumentIdsSelection rowIds = DocumentIdsSelection.ofCommaSeparatedString(idsListStr);
    if (rowIds.isAll()) {
        throw new AdempiereException("retrieving ALL rows is not allowed here");
    }
    final ViewId viewId = ViewId.of(windowId, viewIdStr);
    final IView view = viewsRepo.getView(viewId);
    final List<? extends IViewRow> result = view.streamByIds(rowIds).collect(ImmutableList.toImmutableList());
    final IViewRowOverrides rowOverrides = ViewRowOverridesHelper.getViewRowOverrides(view);
    final JSONOptions jsonOpts = newJSONOptions();
    return JSONViewRow.ofViewRows(result, rowOverrides, jsonOpts);
}


@GetMapping("/{viewId}/quickActions")
public JSONDocumentActionsList getDocumentQuickActions(String windowId,String viewIdStr,String selectedIdsListStr,String parentViewIdStr,String parentViewSelectedIdsListStr,String childViewIdStr,String childViewSelectedIdsListStr,String viewProfileIdStr,boolean all){
    userSession.assertLoggedIn();
    final WebuiPreconditionsContext preconditionsContext = newPreconditionsContextBuilder().windowId(windowId).viewIdString(viewIdStr).viewProfileIdStr(viewProfileIdStr).selectedIdsList(selectedIdsListStr).parentViewId(parentViewIdStr).parentViewSelectedIdsList(parentViewSelectedIdsListStr).childViewId(childViewIdStr).childViewSelectedIdsList(childViewSelectedIdsListStr).displayPlace(DisplayPlace.ViewQuickActions).build();
    return processRestController.streamDocumentRelatedProcesses(preconditionsContext).filter(// shall be already filtered out, but just to make sure
    descriptor -> descriptor.isDisplayedOn(preconditionsContext.getDisplayPlace())).filter(// only those which are enabled or not silent
    descriptor -> all || descriptor.isEnabledOrNotSilent()).collect(JSONDocumentActionsList.collect(newJSONOptions()));
}


@PostMapping("/{viewId}/filter")
public JSONViewResult filterView(String windowIdStr,String viewIdStr,JSONFilterViewRequest jsonRequest){
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final IView newView = viewsRepo.filterView(viewId, jsonRequest);
    final JSONOptions jsonOpts = newJSONOptions();
    return JSONViewResult.of(ViewResult.ofView(newView), ViewRowOverridesHelper.getViewRowOverrides(newView), jsonOpts);
}


@Builder(builderMethodName = "newPreconditionsContextBuilder")
public ViewAsPreconditionsContext createPreconditionsContext(String windowId,String viewIdString,String viewProfileIdStr,String selectedIdsList,String parentViewId,String parentViewSelectedIdsList,String childViewId,String childViewSelectedIdsList,DisplayPlace displayPlace){
    final ViewId viewId = ViewId.of(windowId, viewIdString);
    final IView view = viewsRepo.getView(viewId);
    ViewRowIdsSelection viewRowIdsSelection = ViewRowIdsSelection.of(viewId, DocumentIdsSelection.ofCommaSeparatedString(selectedIdsList));
    ViewRowIdsSelection parentViewRowIdsSelection = ViewRowIdsSelection.ofNullableStrings(parentViewId, parentViewSelectedIdsList);
    ViewRowIdsSelection childViewRowIdsSelection = ViewRowIdsSelection.ofNullableStrings(childViewId, childViewSelectedIdsList);
    return ViewAsPreconditionsContext.builder().view(view).viewProfileId(ViewProfileId.fromJson(viewProfileIdStr)).viewRowIdsSelection(viewRowIdsSelection).parentViewRowIdsSelection(parentViewRowIdsSelection).childViewRowIdsSelection(childViewRowIdsSelection).displayPlace(displayPlace).build();
}


@DeleteMapping("/{viewId}/staticFilter/{filterId}")
public JSONViewResult deleteStickyFilter(String windowIdStr,String viewIdStr,String filterId){
    final ViewId viewId = ViewId.of(windowIdStr, viewIdStr);
    final IView newView = viewsRepo.deleteStickyFilter(viewId, filterId);
    final JSONOptions jsonOpts = newJSONOptions();
    return JSONViewResult.of(ViewResult.ofView(newView), ViewRowOverridesHelper.getViewRowOverrides(newView), jsonOpts);
}


public JSONDocumentLayoutOptions newJSONLayoutOptions(){
    return JSONDocumentLayoutOptions.of(userSession);
}


public Evaluatee createFilterParameterLookupContext(IView view){
    return Evaluatees.mapBuilder().put(LookupDataSourceContext.PARAM_ViewId, view.getViewId()).put(LookupDataSourceContext.PARAM_ViewSize, view.size()).build().andComposeWith(userSession.toEvaluatee());
}


@PostMapping
public JSONViewResult createView(String windowIdStr,JSONCreateViewRequest jsonRequest){
    userSession.assertLoggedIn();
    final WindowId windowId = extractWindowId(windowIdStr, jsonRequest.getWindowId());
    final CreateViewRequest request = CreateViewRequest.builder(windowId, jsonRequest.getViewType()).setProfileId(jsonRequest.getProfileId()).setReferencingDocumentPaths(jsonRequest.getReferencingDocumentPaths()).setFiltersFromJSON(jsonRequest.getFilters()).setFilterOnlyIds(jsonRequest.getFilterOnlyIds()).setUseAutoFilters(true).build();
    final IView view = viewsRepo.createView(request);
    // 
    // Fetch result if requested
    final ViewResult result;
    if (jsonRequest.getQueryPageLength() > 0) {
        final JSONOptions jsonOpts = newJSONOptions();
        result = view.getPage(jsonRequest.getQueryFirstRow(), jsonRequest.getQueryPageLength(), ViewRowsOrderBy.empty(jsonOpts));
    } else {
        result = ViewResult.ofView(view);
    }
    final IViewRowOverrides rowOverrides = ViewRowOverridesHelper.getViewRowOverrides(view);
    final JSONOptions jsonOpts = newJSONOptions();
    return JSONViewResult.of(result, rowOverrides, jsonOpts);
}


@GetMapping("/{viewId}/{rowId}/field/{fieldName}/zoomInto")
public JSONZoomInto getRowFieldZoomInto(String windowIdStr,String viewIdStr,String rowId,String fieldName){
    // userSession.assertLoggedIn(); // NOTE: not needed because we are forwarding to windowRestController
    // just validate the windowId and viewId
    ViewId.ofViewIdString(viewIdStr, WindowId.fromJson(windowIdStr));
    // TODO: atm we are forwarding all calls to windowRestController hoping the document existing and has the same ID as view's row ID.
    return windowRestController.getDocumentFieldZoomInto(windowIdStr, rowId, fieldName);
}


public WindowId extractWindowId(String pathWindowIdStr,WindowId requestWindowId){
    final WindowId pathWindowId = WindowId.fromNullableJson(pathWindowIdStr);
    WindowId windowIdEffective = requestWindowId;
    if (windowIdEffective == null) {
        windowIdEffective = WindowId.fromJson(pathWindowIdStr);
    } else if (!Objects.equals(pathWindowId, windowIdEffective)) {
        throw new IllegalArgumentException("Request's windowId is not matching the one from path");
    }
    return windowIdEffective;
}


@GetMapping("/{viewId}")
public JSONViewResult getViewData(String windowId,String viewIdStr,int firstRow,int pageLength,String orderBysListStr){
    userSession.assertLoggedIn();
    final ViewId viewId = ViewId.of(windowId, viewIdStr);
    final IView view = viewsRepo.getView(viewId);
    final JSONOptions jsonOpts = newJSONOptions();
    final ViewResult result = view.getPage(firstRow, pageLength, ViewRowsOrderBy.parseString(orderBysListStr, jsonOpts));
    final IViewRowOverrides rowOverrides = ViewRowOverridesHelper.getViewRowOverrides(view);
    return JSONViewResult.of(result, rowOverrides, jsonOpts);
}


}