package de.metas.ui.web.order.sales.hu.reservation.process;
 import java.util.Set;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.Adempiere;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_OrderLine;
import de.metas.document.engine.DocStatus;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.order.OrderLineId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.order.sales.hu.reservation.HUReservationDocumentFilterService;
import de.metas.ui.web.order.sales.hu.reservation.HUsReservationViewFactory;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.util.collections.CollectionUtils;
import lombok.NonNull;
public class WEBUI_C_OrderLineSO_Launch_HUEditor extends JavaProcessimplements IProcessPrecondition{

 public  String VIEW_PARAM_PARENT_SALES_ORDER_LINE_ID;

 private  HUReservationService huReservationService;

 private  HUReservationDocumentFilterService huReservationDocumentFilterService;

 private  IViewsRepository viewsRepo;


@Override
public String doIt(){
    final ViewId viewId = createHUEditorView();
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(viewId.getViewId()).target(ViewOpenTarget.ModalOverlay).build());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    final I_C_Order salesOrder = context.getSelectedModel(I_C_Order.class);
    if (salesOrder == null) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("No C_Order selected");
    }
    if (!salesOrder.isSOTrx()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("only sales orders are allowed");
    }
    final DocStatus docStatus = DocStatus.ofNullableCodeOrUnknown(salesOrder.getDocStatus());
    if (!huReservationService.isReservationAllowedForDocStatus(docStatus)) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("C_Order.DocStatus=" + docStatus);
    }
    final Set<TableRecordReference> salesOrderLines = context.getSelectedIncludedRecords();
    if (salesOrderLines.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (salesOrderLines.size() != 1) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


public ViewId createHUEditorView(){
    final Integer singleElement = CollectionUtils.singleElement(getSelectedIncludedRecordIds(I_C_OrderLine.class));
    final OrderLineId orderLineId = OrderLineId.ofRepoId(singleElement);
    final DocumentFilter stickyFilters = huReservationDocumentFilterService.createOrderLineDocumentFilter(orderLineId);
    final IView view = viewsRepo.createView(CreateViewRequest.builder(HUsReservationViewFactory.WINDOW_ID).addStickyFilters(stickyFilters).setParameter(VIEW_PARAM_PARENT_SALES_ORDER_LINE_ID, orderLineId).build());
    return view.getViewId();
}


}