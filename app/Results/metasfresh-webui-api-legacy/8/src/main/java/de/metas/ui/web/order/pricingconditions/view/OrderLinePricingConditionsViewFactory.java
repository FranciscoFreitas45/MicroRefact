package de.metas.ui.web.order.pricingconditions.view;
 import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.mm.attributes.api.IAttributeDAO;
import org.adempiere.mm.attributes.api.ImmutableAttributeSet;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_OrderLine;
import de.metas.bpartner.BPartnerId;
import de.metas.lang.SOTrx;
import de.metas.money.CurrencyId;
import de.metas.money.Money;
import de.metas.order.IOrderDAO;
import de.metas.order.IOrderLineBL;
import de.metas.order.OrderLineId;
import de.metas.order.OrderLinePriceUpdateRequest;
import de.metas.order.OrderLinePriceUpdateRequest.ResultUOM;
import de.metas.payment.paymentterm.PaymentTermId;
import de.metas.pricing.IPricingResult;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.pricing.conditions.PricingConditionsBreakId;
import de.metas.pricing.conditions.PricingConditionsBreakQuery;
import de.metas.product.IProductDAO;
import de.metas.product.ProductAndCategoryAndManufacturerId;
import de.metas.product.ProductCategoryId;
import de.metas.product.ProductId;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowsLoader.PricingConditionsBreaksExtractor;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowsLoader.SourceDocumentLine;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.collections.CollectionUtils;
import de.metas.util.lang.Percent;
import lombok.NonNull;
@ViewFactory(windowId = OrderLinePricingConditionsViewFactory.WINDOW_ID_STRING)
public class OrderLinePricingConditionsViewFactory extends PricingConditionsViewFactoryTemplate{

 public  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;

 private  IOrderLineBL orderLineBL;

 private  I_C_OrderLine orderLine;

 private  ConcurrentHashMap<PricingConditionsBreak,Money> basePricesCache;


@Override
public PricingConditionsRowData createPricingConditionsRowData(CreateViewRequest request){
    final IOrderDAO ordersRepo = Services.get(IOrderDAO.class);
    final int orderLineId = CollectionUtils.singleElement(request.getFilterOnlyIds());
    Check.assumeGreaterThanZero(orderLineId, "salesOrderLineId");
    final I_C_OrderLine orderLine = ordersRepo.getOrderLineById(orderLineId);
    final I_C_Order order = orderLine.getC_Order();
    final SOTrx soTrx = SOTrx.ofBoolean(order.isSOTrx());
    final PricingConditionsRowData rowsData = preparePricingConditionsRowData().pricingConditionsBreaksExtractor(createPricingConditionsBreaksExtractor(orderLine)).basePricingSystemPriceCalculator(new OrderLineBasePricingSystemPriceCalculator(orderLine)).filters(extractFilters(request)).sourceDocumentLine(createSourceDocumentLine(orderLine, soTrx)).load();
    return rowsData;
}


public SourceDocumentLine createSourceDocumentLine(I_C_OrderLine orderLineRecord,SOTrx soTrx){
    final IProductDAO productsRepo = Services.get(IProductDAO.class);
    final ProductId productId = ProductId.ofRepoId(orderLineRecord.getM_Product_ID());
    final ProductCategoryId productCategoryId = productsRepo.retrieveProductCategoryByProductId(productId);
    final Money priceEntered = Money.of(orderLineRecord.getPriceEntered(), CurrencyId.ofRepoId(orderLineRecord.getC_Currency_ID()));
    return SourceDocumentLine.builder().orderLineId(OrderLineId.ofRepoIdOrNull(orderLineRecord.getC_OrderLine_ID())).soTrx(soTrx).bpartnerId(BPartnerId.ofRepoId(orderLineRecord.getC_BPartner_ID())).productId(productId).productCategoryId(productCategoryId).priceEntered(priceEntered).discount(Percent.of(orderLineRecord.getDiscount())).paymentTermId(PaymentTermId.ofRepoIdOrNull(orderLineRecord.getC_PaymentTerm_Override_ID())).pricingConditionsBreakId(PricingConditionsBreakId.ofOrNull(orderLineRecord.getM_DiscountSchema_ID(), orderLineRecord.getM_DiscountSchemaBreak_ID())).build();
}


public PricingConditionsBreaksExtractor createPricingConditionsBreaksExtractor(I_C_OrderLine salesOrderLine){
    final PricingConditionsBreakQuery pricingConditionsBreakQuery = createPricingConditionsBreakQuery(salesOrderLine);
    return pricingConditions -> {
        final PricingConditionsBreak matchingBreak = pricingConditions.pickApplyingBreak(pricingConditionsBreakQuery);
        return matchingBreak != null ? Stream.of(matchingBreak) : Stream.empty();
    };
}


public Money calculate(PricingConditionsBreak pricingConditionsBreak){
    final IPricingResult pricingResult = orderLineBL.computePrices(OrderLinePriceUpdateRequest.builder().orderLine(orderLine).pricingConditionsBreakOverride(pricingConditionsBreak).resultUOM(ResultUOM.PRICE_UOM_IF_ORDERLINE_IS_NEW).build());
    return Money.of(pricingResult.getPriceStd(), pricingResult.getCurrencyId());
}


@Override
public void onViewClosedByUser(PricingConditionsView view){
    view.updateSalesOrderLineIfPossible();
}


public PricingConditionsBreakQuery createPricingConditionsBreakQuery(I_C_OrderLine salesOrderLine){
    final IProductDAO productsRepo = Services.get(IProductDAO.class);
    final IAttributeDAO attributesRepo = Services.get(IAttributeDAO.class);
    final ProductId productId = ProductId.ofRepoId(salesOrderLine.getM_Product_ID());
    final ProductAndCategoryAndManufacturerId product = productsRepo.retrieveProductAndCategoryAndManufacturerByProductId(productId);
    final AttributeSetInstanceId asiId = AttributeSetInstanceId.ofRepoIdOrNone(salesOrderLine.getM_AttributeSetInstance_ID());
    final ImmutableAttributeSet attributes = attributesRepo.getImmutableAttributeSetById(asiId);
    final BigDecimal qty = salesOrderLine.getQtyOrdered();
    final BigDecimal price = salesOrderLine.getPriceActual();
    return PricingConditionsBreakQuery.builder().product(product).attributes(attributes).qty(qty).price(price).build();
}


}