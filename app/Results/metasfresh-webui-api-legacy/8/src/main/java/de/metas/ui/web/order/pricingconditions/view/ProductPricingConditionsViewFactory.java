package de.metas.ui.web.order.pricingconditions.view;
 import java.math.BigDecimal;
import java.util.Set;
import de.metas.lang.SOTrx;
import de.metas.money.Money;
import de.metas.pricing.IEditablePricingContext;
import de.metas.pricing.IPricingContext;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.pricing.conditions.service.CalculatePricingConditionsRequest;
import de.metas.pricing.conditions.service.IPricingConditionsService;
import de.metas.pricing.service.IPricingBL;
import de.metas.product.IProductDAO;
import de.metas.product.ProductAndCategoryAndManufacturerId;
import de.metas.product.ProductId;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import de.metas.util.Services;
@ViewFactory(windowId = ProductPricingConditionsViewFactory.WINDOW_ID_STRING)
public class ProductPricingConditionsViewFactory extends PricingConditionsViewFactoryTemplate{

 public  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;


@Override
public PricingConditionsRowData createPricingConditionsRowData(CreateViewRequest request){
    final Set<ProductId> productIds = ProductId.ofRepoIds(request.getFilterOnlyIds());
    Check.assumeNotEmpty(productIds, "productIds is not empty");
    final IProductDAO productsRepo = Services.get(IProductDAO.class);
    final Set<ProductAndCategoryAndManufacturerId> products = productsRepo.retrieveProductAndCategoryAndManufacturersByProductIds(productIds);
    return preparePricingConditionsRowData().pricingConditionsBreaksExtractor(pricingConditions -> pricingConditions.streamBreaksMatchingAnyOfProducts(products)).basePricingSystemPriceCalculator(this::calculateBasePricingSystemPrice).load();
}


public IPricingContext createPricingContext(BasePricingSystemPriceCalculatorRequest request){
    final IPricingBL pricingBL = Services.get(IPricingBL.class);
    final PricingConditionsBreak pricingConditionsBreak = request.getPricingConditionsBreak();
    final IEditablePricingContext pricingCtx = pricingBL.createPricingContext();
    final ProductId productId = pricingConditionsBreak.getMatchCriteria().getProductId();
    pricingCtx.setProductId(productId);
    pricingCtx.setQty(BigDecimal.ONE);
    pricingCtx.setBPartnerId(request.getBpartnerId());
    pricingCtx.setSOTrx(SOTrx.ofBoolean(request.isSOTrx()));
    return pricingCtx;
}


public Money calculateBasePricingSystemPrice(BasePricingSystemPriceCalculatorRequest request){
    final IPricingConditionsService pricingConditionsService = Services.get(IPricingConditionsService.class);
    return pricingConditionsService.calculatePricingConditions(CalculatePricingConditionsRequest.builder().forcePricingConditionsBreak(request.getPricingConditionsBreak()).pricingCtx(createPricingContext(request)).build()).map(result -> Money.of(result.getPriceStdOverride(), result.getCurrencyId())).orElse(null);
}


}