package de.metas.ui.web.order.pricingconditions.view.OrderLinePricingConditionsViewFactory;
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
public class OrderLineBasePricingSystemPriceCalculator implements BasePricingSystemPriceCalculator{

 private  IOrderLineBL orderLineBL;

 private  I_C_OrderLine orderLine;

 private  ConcurrentHashMap<PricingConditionsBreak,Money> basePricesCache;


public Money calculate(PricingConditionsBreak pricingConditionsBreak){
    final IPricingResult pricingResult = orderLineBL.computePrices(OrderLinePriceUpdateRequest.builder().orderLine(orderLine).pricingConditionsBreakOverride(pricingConditionsBreak).resultUOM(ResultUOM.PRICE_UOM_IF_ORDERLINE_IS_NEW).build());
    return Money.of(pricingResult.getPriceStd(), pricingResult.getCurrencyId());
}


}