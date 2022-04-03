package de.metas.ui.web.process.adprocess;
 import java.util.stream.Stream;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import org.compiere.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.process.ClientOnlyProcess;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.process.ProcessExecutionResult;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.ViewAsPreconditionsContext;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.ViewRowIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import lombok.NonNull;
@ClientOnlyProcess
public class ViewBasedProcessTemplate extends JavaProcess{

@Autowired
 private  IViewsRepository viewsRepo;

 static  String PARAM_ViewId;

@Param(parameterName = PARAM_ViewId, mandatory = true)
 private  String p_WebuiViewId;

 static  String PARAM_ViewSelectedIds;

@Param(parameterName = PARAM_ViewSelectedIds, mandatory = true)
 private  String p_WebuiViewSelectedIdsStr;

 static  String PARAM_ParentViewId;

@Param(parameterName = PARAM_ParentViewId, mandatory = false)
 private  String p_WebuiParentViewId;

 public  String PARAM_ParentViewSelectedIds;

@Param(parameterName = PARAM_ParentViewSelectedIds, mandatory = false)
 private  String p_WebuiParentViewSelectedIdsStr;

 public  String PARAM_ChildViewId;

@Param(parameterName = PARAM_ChildViewId, mandatory = false)
 private  String p_WebuiChildViewId;

 public  String PARAM_ChildViewSelectedIds;

@Param(parameterName = PARAM_ChildViewSelectedIds, mandatory = false)
 private  String p_WebuiChildViewSelectedIdsStr;

 private  IView _view;

 private  ViewProfileId _viewProfileId;

 private  ViewRowIdsSelection _viewRowIdsSelection;

 private  ViewRowIdsSelection _parentViewRowIdsSelection;

 private  ViewRowIdsSelection _childViewRowIdsSelection;

 private  JSONOptions _jsonOptions;


@Override
public void init(IProcessPreconditionsContext context){
    super.init(context);
    // Fetch and set view and view selected IDs from autowired process parameters
    setViewInfos(ViewAsPreconditionsContext.cast(context));
}


public boolean isViewClass(Class<T> expectedViewClass){
    final IView view = _view;
    if (view == null) {
        return false;
    }
    return expectedViewClass.isAssignableFrom(view.getClass());
}


@OverridingMethodsMustInvokeSuper
public Stream<? extends IViewRow> streamSelectedRows(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    return getView().streamByIds(selectedRowIds);
}


public ViewRowIdsSelection getParentViewRowIdsSelection(){
    return _parentViewRowIdsSelection;
}


public DocumentIdsSelection getChildViewSelectedRowIds(){
    final ViewRowIdsSelection childViewRowIdsSelection = getChildViewRowIdsSelection();
    return childViewRowIdsSelection != null ? childViewRowIdsSelection.getRowIds() : DocumentIdsSelection.EMPTY;
}


@OverridingMethodsMustInvokeSuper
public IViewRow getSingleSelectedRow(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    final DocumentId documentId = selectedRowIds.getSingleDocumentId();
    return getView().getById(documentId);
}


public DocumentIdsSelection getSelectedRowIds(){
    Check.assumeNotNull(_viewRowIdsSelection, "View loaded");
    return _viewRowIdsSelection.getRowIds();
}


public T getChildView(Class<T> viewType){
    final ViewRowIdsSelection childViewRowIdsSelection = getChildViewRowIdsSelection();
    Check.assumeNotNull(childViewRowIdsSelection, "child view is set");
    final IView childView = viewsRepo.getView(childViewRowIdsSelection.getViewId());
    return viewType.cast(childView);
}


public IViewRow getChildViewSingleSelectedRow(){
    final DocumentIdsSelection selectedRowIds = getChildViewSelectedRowIds();
    final DocumentId rowId = selectedRowIds.getSingleDocumentId();
    return getChildView(IView.class).getById(rowId);
}


public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    return ProcessPreconditionsResolution.accept();
}


public ViewProfileId getViewProfileId(){
    return _viewProfileId;
}


public ViewRowIdsSelection getChildViewRowIdsSelection(){
    return _childViewRowIdsSelection;
}


public JSONOptions getJSONOptions(){
    JSONOptions jsonOptions = this._jsonOptions;
    if (jsonOptions == null) {
        jsonOptions = this._jsonOptions = JSONOptions.newInstance();
    }
    return jsonOptions;
}


@Override
public void loadParametersFromContext(boolean failIfNotValid){
    super.loadParametersFromContext(failIfNotValid);
    // shall not happen
    Check.assumeNotEmpty(p_WebuiViewId, "Process parameter {} is set", PARAM_ViewId);
    final IView view = viewsRepo.getView(p_WebuiViewId);
    final ViewRowIdsSelection viewRowIdsSelection = ViewRowIdsSelection.of(view.getViewId(), DocumentIdsSelection.ofCommaSeparatedString(p_WebuiViewSelectedIdsStr));
    final ViewRowIdsSelection parentViewRowIdsSelection = ViewRowIdsSelection.ofNullableStrings(p_WebuiParentViewId, p_WebuiParentViewSelectedIdsStr);
    final ViewRowIdsSelection childViewRowIdsSelection = ViewRowIdsSelection.ofNullableStrings(p_WebuiChildViewId, p_WebuiChildViewSelectedIdsStr);
    setViewInfos(ViewAsPreconditionsContext.builder().view(view).viewRowIdsSelection(viewRowIdsSelection).parentViewRowIdsSelection(parentViewRowIdsSelection).childViewRowIdsSelection(childViewRowIdsSelection).build());
}


public void invalidateParentView(){
    final IView view = getView();
    final ViewId parentViewId = view.getParentViewId();
    if (parentViewId != null) {
        invalidateView(parentViewId);
    }
}


public void invalidateView(){
    final IView view = getView();
    invalidateView(view.getViewId());
}


public void setViewInfos(ViewAsPreconditionsContext viewContext){
    _view = viewContext.getView();
    _viewProfileId = viewContext.getViewProfileId();
    _viewRowIdsSelection = viewContext.getViewRowIdsSelection();
    _parentViewRowIdsSelection = viewContext.getParentViewRowIdsSelection();
    _childViewRowIdsSelection = viewContext.getChildViewRowIdsSelection();
    // Update result from view
    // Do this only when view is not null to avoid reseting previous set info (shall not happen)
    final ProcessExecutionResult result = getResultOrNull();
    if (// might be null when preconditions are checked (for example)
    result != null && _view != null) {
        result.setWebuiViewId(_view.getViewId().getViewId());
    }
}


@OverridingMethodsMustInvokeSuper
public IView getView(){
    Check.assumeNotNull(_view, "View loaded");
    return _view;
}


public IViewsRepository getViewsRepo(){
    return viewsRepo;
}


public WindowId getWindowId(){
    return getView().getViewId().getWindowId();
}


}