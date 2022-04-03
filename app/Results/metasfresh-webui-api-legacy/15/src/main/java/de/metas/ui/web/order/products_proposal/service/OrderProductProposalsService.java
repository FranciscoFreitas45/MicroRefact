package de.metas.ui.web.order.products_proposal.service;
 import java.time.ZonedDateTime;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_M_PriceList;
import org.compiere.util.TimeUtil;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.service.IBPartnerBL;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.handlingunits.IHUPIItemProductBL;
import de.metas.handlingunits.model.I_C_OrderLine;
import de.metas.lang.SOTrx;
import de.metas.location.CountryId;
import de.metas.money.CurrencyId;
import de.metas.order.IOrderDAO;
import de.metas.order.OrderId;
import de.metas.order.OrderLineId;
import de.metas.pricing.PriceListId;
import de.metas.pricing.PriceListVersionId;
import de.metas.pricing.service.IPriceListDAO;
import de.metas.product.ProductId;
import de.metas.util.Services;
import lombok.NonNull;
@Service
public class OrderProductProposalsService {

 private  IOrderDAO ordersRepo;

 private  IPriceListDAO priceListsRepo;

 private  IHUPIItemProductBL packingMaterialsService;

 private  IBPartnerBL bpartnersService;


public Order getOrderById(OrderId orderId){
    final I_C_Order orderRecord = ordersRepo.getById(orderId);
    final ZonedDateTime datePromised = TimeUtil.asZonedDateTime(orderRecord.getDatePromised());
    final PriceListId priceListId = PriceListId.ofRepoId(orderRecord.getM_PriceList_ID());
    final I_M_PriceList priceList = priceListsRepo.getById(priceListId);
    final PriceListVersionId priceListVersionId = priceListsRepo.retrievePriceListVersionId(priceListId, datePromised);
    final BPartnerId bpartnerId = BPartnerId.ofRepoId(orderRecord.getC_BPartner_ID());
    final String bpartnerName = bpartnersService.getBPartnerName(bpartnerId);
    return Order.builder().orderId(orderId).soTrx(SOTrx.ofBoolean(orderRecord.isSOTrx())).datePromised(datePromised).bpartnerId(bpartnerId).bpartnerName(bpartnerName).pricingSystemId(priceListsRepo.getPricingSystemId(priceListId)).priceListId(priceListId).priceListVersionId(priceListVersionId).countryId(CountryId.ofRepoIdOrNull(priceList.getC_Country_ID())).currencyId(CurrencyId.ofRepoId(priceList.getC_Currency_ID())).lines(ordersRepo.retrieveOrderLines(orderId, I_C_OrderLine.class).stream().map(this::toOrderLine).collect(ImmutableList.toImmutableList())).build();
}


public OrderLine toOrderLine(I_C_OrderLine record){
    final HUPIItemProductId packingMaterialId = HUPIItemProductId.ofRepoIdOrNone(record.getM_HU_PI_Item_Product_ID());
    return OrderLine.builder().orderLineId(OrderLineId.ofRepoId(record.getC_OrderLine_ID())).productId(ProductId.ofRepoId(record.getM_Product_ID())).packingMaterialId(packingMaterialId).packingMaterialWithInfiniteCapacity(packingMaterialsService.isInfiniteCapacity(packingMaterialId)).priceActual(record.getPriceActual()).qtyEnteredCU(record.getQtyEntered()).qtyEnteredTU(record.getQtyEnteredTU().intValue()).description(record.getDescription()).build();
}


}