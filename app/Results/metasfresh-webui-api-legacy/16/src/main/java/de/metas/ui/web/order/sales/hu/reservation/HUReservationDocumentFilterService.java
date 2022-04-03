package de.metas.ui.web.order.sales.hu.reservation;
 import org.springframework.stereotype.Service;
import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.inoutcandidate.api.Packageable;
import de.metas.order.OrderLine;
import de.metas.order.OrderLineId;
import de.metas.order.OrderLineRepository;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.handlingunits.HUIdsFilterHelper;
import lombok.NonNull;
@Service
public class HUReservationDocumentFilterService {

 private  OrderLineRepository orderLineRepository;

 private  HUReservationService huReservationService;


public IHUQueryBuilder createHUQuery(OrderLine salesOrderLine){
    return huReservationService.prepareHUQuery().warehouseId(salesOrderLine.getWarehouseId()).productId(salesOrderLine.getProductId()).asiId(salesOrderLine.getAsiId()).reservedToSalesOrderLineIdOrNotReservedAtAll(salesOrderLine.getId()).build();
}


public DocumentFilter createDocumentFilterIgnoreAttributes(Packageable packageable){
    final IHUQueryBuilder huQuery = createHUQueryIgnoreAttributes(packageable);
    return HUIdsFilterHelper.createFilter(huQuery);
}


public IHUQueryBuilder createHUQueryIgnoreAttributes(Packageable packageable){
    return huReservationService.prepareHUQuery().warehouseId(packageable.getWarehouseId()).productId(packageable.getProductId()).asiId(// ignore attributes
    null).reservedToSalesOrderLineIdOrNotReservedAtAll(packageable.getSalesOrderLineIdOrNull()).build();
}


public DocumentFilter createOrderLineDocumentFilter(OrderLine salesOrderLine){
    final IHUQueryBuilder huQuery = createHUQuery(salesOrderLine);
    return HUIdsFilterHelper.createFilter(huQuery);
}


}