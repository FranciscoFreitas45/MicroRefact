package de.metas.ui.web.pporder;
 import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.handlingunits.DefaultHUEditorViewFactory;
import de.metas.ui.web.pattribute.ASIRepository;
import de.metas.ui.web.view.ASIViewRowAttributesProvider;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.IncludedViewLayout;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.factory.standard.LayoutFactory;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = PPOrderConstants.AD_WINDOW_ID_IssueReceipt_String, viewTypes = {})
public class PPOrderLinesViewFactory implements IViewFactory{

@Autowired
 private  ASIRepository asiRepository;

@Autowired
 private  DefaultHUEditorViewFactory huEditorViewFactory;

@Autowired
 private  HUReservationService huReservationService;

 private  CCache<WindowId,ViewLayout> layouts;


@Override
public IView filterView(IView view,JSONFilterViewRequest filterViewRequest,Supplier<IViewsRepository> viewsRepo){
    throw new AdempiereException("View does not support filtering").setParameter("view", view).setParameter("filterViewRequest", filterViewRequest);
}


public List<RelatedProcessDescriptor> createAdditionalRelatedProcessDescriptors(){
    return ImmutableList.of(createProcessDescriptorForIssueReceiptWindow(de.metas.ui.web.pporder.process.WEBUI_PP_Order_Receipt.class), createProcessDescriptorForIssueReceiptWindow(de.metas.ui.web.pporder.process.WEBUI_PP_Order_ReverseCandidate.class), createProcessDescriptorForIssueReceiptWindow(de.metas.ui.web.pporder.process.WEBUI_PP_Order_ChangePlanningStatus_Planning.class), createProcessDescriptorForIssueReceiptWindow(de.metas.ui.web.pporder.process.WEBUI_PP_Order_ChangePlanningStatus_Review.class), createProcessDescriptorForIssueReceiptWindow(de.metas.ui.web.pporder.process.WEBUI_PP_Order_ChangePlanningStatus_Complete.class), createProcessDescriptorForIssueReceiptWindow(de.metas.ui.web.pporder.process.WEBUI_PP_Order_HUEditor_Launcher.class), createProcessDescriptorForIssueReceiptWindow(de.metas.ui.web.pporder.process.WEBUI_PP_Order_M_Source_HU_Delete.class), createProcessDescriptorForIssueReceiptWindow(de.metas.ui.web.pporder.process.WEBUI_PP_Order_M_Source_HU_IssueTuQty.class), createProcessDescriptorForIssueReceiptWindow(de.metas.ui.web.pporder.process.WEBUI_PP_Order_M_Source_HU_IssueCUQty.class));
}


@Override
public IView deleteStickyFilter(IView view,String filterId){
    throw new AdempiereException("View does not allow removing sticky/static filter").setParameter("view", view).setParameter("filterId", filterId);
}


public RelatedProcessDescriptor createProcessDescriptorForIssueReceiptWindow(Class<?> processClass){
    final IADProcessDAO adProcessDAO = Services.get(IADProcessDAO.class);
    final AdProcessId processId = adProcessDAO.retrieveProcessIdByClass(processClass);
    return RelatedProcessDescriptor.builder().processId(processId).windowId(PPOrderConstants.AD_WINDOW_ID_IssueReceipt.toAdWindowIdOrNull()).anyTable().displayPlace(DisplayPlace.ViewQuickActions).build();
}


@Override
public PPOrderLinesView createView(CreateViewRequest request){
    final ViewId viewId = request.getViewId();
    final PPOrderId ppOrderId = PPOrderId.ofRepoId(request.getSingleFilterOnlyId());
    final PPOrderLinesViewDataSupplier dataSupplier = PPOrderLinesViewDataSupplier.builder().viewWindowId(viewId.getWindowId()).ppOrderId(ppOrderId).asiAttributesProvider(ASIViewRowAttributesProvider.newInstance(asiRepository)).huSQLViewBinding(huEditorViewFactory.getSqlViewBinding()).huReservationService(huReservationService).build();
    return PPOrderLinesView.builder().parentViewId(request.getParentViewId()).parentRowId(request.getParentRowId()).viewId(viewId).viewType(request.getViewType()).referencingDocumentPaths(request.getReferencingDocumentPaths()).ppOrderId(ppOrderId).dataSupplier(dataSupplier).additionalRelatedProcessDescriptors(createAdditionalRelatedProcessDescriptors()).build();
}


public ViewLayout createViewLayout(WindowId windowId){
    return ViewLayout.builder().setWindowId(windowId).setCaption("PP Order Issue/Receipt").setEmptyResultText(LayoutFactory.HARDCODED_TAB_EMPTY_RESULT_TEXT).setEmptyResultHint(LayoutFactory.HARDCODED_TAB_EMPTY_RESULT_HINT).setHasAttributesSupport(true).setHasTreeSupport(true).setIncludedViewLayout(IncludedViewLayout.DEFAULT).addElementsFromViewRowClass(PPOrderLineRow.class, JSONViewDataType.grid).build();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType_NOTUSED,ViewProfileId profileId_NOTUSED){
    return layouts.getOrLoad(windowId, () -> createViewLayout(windowId));
}


}