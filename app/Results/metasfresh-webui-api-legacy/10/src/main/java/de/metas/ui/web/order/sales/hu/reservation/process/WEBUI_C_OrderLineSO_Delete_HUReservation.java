package de.metas.ui.web.order.sales.hu.reservation.process;
 import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.handlingunits.reservation.RetrieveHUsQtyRequest;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.product.ProductId;
import de.metas.purchasecandidate.SalesOrderLine;
import de.metas.purchasecandidate.SalesOrderLineRepository;
import de.metas.quantity.Quantity;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
public class WEBUI_C_OrderLineSO_Delete_HUReservation extends HUEditorProcessTemplateimplements IProcessPrecondition{

@Autowired
 private  HUReservationService huReservationService;

@Autowired
 private  SalesOrderLineRepository salesOrderLineRepository;


public Quantity retrieveUnreservableQuantity(ProductId productId){
    final RetrieveHUsQtyRequest request = WEBUI_C_OrderLineSO_Util.createHuQuantityRequest(streamSelectedHUIds(Select.ALL), productId);
    final Quantity reservableQty = huReservationService.retrieveUnreservableQty(request);
    return reservableQty;
}


@Override
// the service we invoke creates its own transaction
@RunOutOfTrx
public String doIt(){
    final ImmutableList<HuId> selectedReservedHUs = streamSelectedHUIds(HUEditorRowFilter.ALL).collect(ImmutableList.toImmutableList());
    huReservationService.deleteReservations(selectedReservedHUs);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final SalesOrderLine salesOrderLine = WEBUI_C_OrderLineSO_Util.retrieveSalesOrderLine(getView(), salesOrderLineRepository).orElse(null);
    if (salesOrderLine == null) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("No sales order was set");
    }
    final ProductId productId = salesOrderLine.getProductId();
    final Quantity unreservableQty = retrieveUnreservableQuantity(productId);
    if (unreservableQty.signum() <= 0) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("No unreservableQty quantity for productId=" + productId);
    }
    return ProcessPreconditionsResolution.accept();
}


}