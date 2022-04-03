package de.metas.ui.web.order.sales.hu.reservation.process;
 import java.math.BigDecimal;
import org.adempiere.exceptions.AdempiereException;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.handlingunits.reservation.ReserveHUsRequest;
import de.metas.handlingunits.reservation.RetrieveHUsQtyRequest;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.product.ProductId;
import de.metas.purchasecandidate.SalesOrderLine;
import de.metas.purchasecandidate.SalesOrderLineRepository;
import de.metas.quantity.Quantity;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.uom.IUOMConversionBL;
import de.metas.uom.UOMConversionContext;
import de.metas.util.Services;
import lombok.NonNull;
public class WEBUI_C_OrderLineSO_Make_HUReservation extends HUEditorProcessTemplateimplements IProcessDefaultParametersProvider,IProcessPrecondition{

@Autowired
 private  HUReservationService huReservationService;

@Autowired
 private  SalesOrderLineRepository salesOrderLineRepository;

 private  IUOMConversionBL uomConversionBL;

 private  String PARAMNAME_QTY_TO_RESERVE;

@Param(mandatory = true, parameterName = PARAMNAME_QTY_TO_RESERVE)
 private  BigDecimal qtyToReserveBD;


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    if (PARAMNAME_QTY_TO_RESERVE.equals(parameter.getColumnName())) {
        final SalesOrderLine salesOrderLine = WEBUI_C_OrderLineSO_Util.retrieveSalesOrderLine(getView(), salesOrderLineRepository).get();
        final ProductId productId = salesOrderLine.getProductId();
        final Quantity orderedQty = salesOrderLine.getOrderedQty();
        final Quantity reservedQty = huReservationService.retrieveReservedQty(salesOrderLine.getId().getOrderLineId()).orElse(orderedQty.toZero());
        final Quantity reservedQtyInSalesOrderUOM = uomConversionBL.convertQuantityTo(reservedQty, UOMConversionContext.of(productId), orderedQty.getUomId());
        final Quantity requiredQty = orderedQty.subtract(salesOrderLine.getDeliveredQty()).subtract(reservedQtyInSalesOrderUOM);
        final Quantity reservableQty = retrieveReservableQuantity(productId);
        final Quantity reservableQtyInSalesOrderUOM = uomConversionBL.convertQuantityTo(reservableQty, UOMConversionContext.of(productId), orderedQty.getUomId());
        return requiredQty.min(reservableQtyInSalesOrderUOM).toBigDecimal();
    }
    return null;
}


public Quantity retrieveReservableQuantity(ProductId productId){
    final RetrieveHUsQtyRequest request = WEBUI_C_OrderLineSO_Util.createHuQuantityRequest(streamSelectedHUIds(Select.ALL), productId);
    final Quantity reservableQty = huReservationService.retrieveReservableQty(request);
    return reservableQty;
}


@Override
public String doIt(){
    final SalesOrderLine salesOrderLine = WEBUI_C_OrderLineSO_Util.retrieveSalesOrderLine(getView(), salesOrderLineRepository).get();
    final ImmutableList<HuId> selectedHuIds = streamSelectedHUIds(Select.ALL).collect(ImmutableList.toImmutableList());
    if (selectedHuIds.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    final Quantity qtyToReserve = Quantity.of(qtyToReserveBD, salesOrderLine.getOrderedQty().getUOM());
    final ReserveHUsRequest reservationRequest = ReserveHUsRequest.builder().huIds(selectedHuIds).productId(salesOrderLine.getProductId()).qtyToReserve(qtyToReserve).salesOrderLineId(salesOrderLine.getId().getOrderLineId()).build();
    huReservationService.makeReservation(reservationRequest);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final SalesOrderLine salesOrderLine = WEBUI_C_OrderLineSO_Util.retrieveSalesOrderLine(getView(), salesOrderLineRepository).orElse(null);
    if (salesOrderLine == null) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("No sales order was set");
    }
    final ProductId productId = salesOrderLine.getProductId();
    final Quantity reservableQty = retrieveReservableQuantity(productId);
    if (reservableQty.signum() <= 0) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("No reservable quantity for productId=" + productId);
    }
    return ProcessPreconditionsResolution.accept();
}


}