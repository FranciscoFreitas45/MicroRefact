package de.metas.ui.web.order.sales.hu.reservation.process;
 import java.util.Optional;
import java.util.stream.Stream;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.reservation.RetrieveHUsQtyRequest;
import de.metas.order.OrderLineId;
import de.metas.product.ProductId;
import de.metas.purchasecandidate.SalesOrderLine;
import de.metas.purchasecandidate.SalesOrderLineRepository;
import de.metas.ui.web.handlingunits.HUEditorView;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class WEBUI_C_OrderLineSO_Util {


public Optional<SalesOrderLine> retrieveSalesOrderLine(HUEditorView huEditorView,SalesOrderLineRepository salesOrderLineRepository){
    final Optional<OrderLineId> orderLineId = huEditorView.getParameterAsId(WEBUI_C_OrderLineSO_Launch_HUEditor.VIEW_PARAM_PARENT_SALES_ORDER_LINE_ID);
    return orderLineId.map(salesOrderLineRepository::getById);
}


public RetrieveHUsQtyRequest createHuQuantityRequest(Stream<HuId> selectedHUIdStream,ProductId productId){
    final ImmutableList<HuId> selectedHuIds = selectedHUIdStream.collect(ImmutableList.toImmutableList());
    final RetrieveHUsQtyRequest request = RetrieveHUsQtyRequest.builder().huIds(selectedHuIds).productId(productId).build();
    return request;
}


}