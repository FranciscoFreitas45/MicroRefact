package de.metas.ui.web.order.pricingconditions.view;
 import de.metas.util.Check.assumeNotNull;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.model.InterfaceWrapperHelper;
import com.google.common.collect.ImmutableList;
import de.metas.currency.CurrencyPrecision;
import de.metas.i18n.TranslatableStrings;
import de.metas.interfaces.I_C_OrderLine;
import de.metas.money.CurrencyId;
import de.metas.money.Money;
import de.metas.order.IOrderDAO;
import de.metas.order.IOrderLineBL;
import de.metas.order.OrderLineId;
import de.metas.order.OrderLinePriceUpdateRequest;
import de.metas.order.PriceAndDiscount;
import de.metas.payment.paymentterm.PaymentTermId;
import de.metas.pricing.conditions.PriceSpecification;
import de.metas.pricing.conditions.PriceSpecificationType;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.pricing.conditions.PricingConditionsBreakId;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IEditableView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.lang.Percent;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
public class PricingConditionsView extends AbstractCustomView<PricingConditionsRow>implements IEditableView{

 private  PricingConditionsRowData rowsData;

 private  List<RelatedProcessDescriptor> relatedProcessDescriptors;


public BigDecimal limitPrice(BigDecimal price,I_C_OrderLine orderLineRecord){
    if (!orderLineRecord.isEnforcePriceLimit()) {
        return price;
    }
    final BigDecimal priceLimit = orderLineRecord.getPriceLimit();
    if (priceLimit.signum() == 0) {
        return price;
    }
    return price.max(priceLimit);
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return relatedProcessDescriptors;
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    // TODO Auto-generated method stub
    return null;
}


@Override
public LookupValuesList getFieldDropdown(RowEditingContext ctx,String fieldName){
    return getById(ctx.getRowId()).getFieldDropdown(fieldName);
}


public void updateOrderLineRecord(PricingConditionsBreak pricingConditionsBreak,Money basePrice){
    final IOrderDAO ordersRepo = Services.get(IOrderDAO.class);
    final IOrderLineBL orderLineBL = Services.get(IOrderLineBL.class);
    final I_C_OrderLine orderLineRecord = ordersRepo.getOrderLineById(getOrderLineId());
    orderLineRecord.setIsTempPricingConditions(pricingConditionsBreak.isTemporaryPricingConditionsBreak());
    if (pricingConditionsBreak.isTemporaryPricingConditionsBreak()) {
        orderLineRecord.setM_DiscountSchema_ID(-1);
        orderLineRecord.setM_DiscountSchemaBreak_ID(-1);
        final PriceSpecification price = pricingConditionsBreak.getPriceSpecification();
        final PriceSpecificationType type = price.getType();
        if (type == PriceSpecificationType.NONE) {
        // 
        } else if (type == PriceSpecificationType.BASE_PRICING_SYSTEM) {
            assumeNotNull(basePrice, "If type={}, then the given basePrice may not be null; pricingConditionsBreak={}", type, pricingConditionsBreak);
            final BigDecimal priceEntered = limitPrice(basePrice.toBigDecimal(), orderLineRecord);
            orderLineRecord.setIsManualPrice(true);
            orderLineRecord.setPriceEntered(priceEntered);
            orderLineRecord.setC_Currency_ID(basePrice.getCurrencyId().getRepoId());
            orderLineRecord.setBase_PricingSystem_ID(price.getBasePricingSystemId().getRepoId());
        } else if (type == PriceSpecificationType.FIXED_PRICE) {
            final Money fixedPrice = price.getFixedPrice();
            Check.assumeNotNull(fixedPrice, "fixedPrice shall not be null for {}", price);
            final BigDecimal priceEntered = limitPrice(fixedPrice.toBigDecimal(), orderLineRecord);
            orderLineRecord.setIsManualPrice(true);
            orderLineRecord.setPriceEntered(priceEntered);
            orderLineRecord.setC_Currency_ID(fixedPrice.getCurrencyId().getRepoId());
            orderLineRecord.setBase_PricingSystem_ID(-1);
        }
        orderLineRecord.setIsManualDiscount(true);
        orderLineRecord.setDiscount(pricingConditionsBreak.getDiscount().toBigDecimal());
        // make sure it's not overwritten by whatever the system comes up with when we save the orderLine.
        orderLineRecord.setIsManualPaymentTerm(true);
        orderLineRecord.setC_PaymentTerm_Override_ID(PaymentTermId.toRepoId(pricingConditionsBreak.getDerivedPaymentTermIdOrNull()));
        orderLineRecord.setPaymentDiscount(Percent.toBigDecimalOrNull(pricingConditionsBreak.getPaymentDiscountOverrideOrNull()));
        // 
        // PriceActual & Discount
        Percent discountEffective = pricingConditionsBreak.getDiscount();
        final BigDecimal priceActual = discountEffective.subtractFromBase(orderLineRecord.getPriceEntered(), CurrencyPrecision.TWO.toInt());
        final BigDecimal priceActualEffective = limitPrice(priceActual, orderLineRecord);
        if (priceActualEffective.compareTo(priceActual) != 0) {
            discountEffective = PriceAndDiscount.calculateDiscountFromPrices(orderLineRecord.getPriceEntered(), priceActualEffective, CurrencyPrecision.TWO);
        }
        // 
        orderLineRecord.setIsManualDiscount(true);
        orderLineRecord.setDiscount(discountEffective.toBigDecimal());
        orderLineRecord.setPriceActual(priceActualEffective);
    } else {
        final PricingConditionsBreakId pricingConditionsBreakId = pricingConditionsBreak.getId();
        orderLineRecord.setM_DiscountSchema_ID(pricingConditionsBreakId.getDiscountSchemaId());
        orderLineRecord.setM_DiscountSchemaBreak_ID(pricingConditionsBreakId.getDiscountSchemaBreakId());
        orderLineRecord.setIsManualDiscount(false);
        orderLineRecord.setIsManualPrice(false);
        orderLineRecord.setIsManualPaymentTerm(false);
        final OrderLinePriceUpdateRequest orderLinePriceUpdateRequest = OrderLinePriceUpdateRequest.prepare(orderLineRecord).pricingConditionsBreakOverride(pricingConditionsBreak).build();
        orderLineBL.updatePrices(orderLinePriceUpdateRequest);
    }
    orderLineBL.updateLineNetAmtFromQtyEntered(orderLineRecord);
    orderLineBL.setTaxAmtInfo(orderLineRecord);
    InterfaceWrapperHelper.save(orderLineRecord);
}


public boolean hasEditableRow(){
    return rowsData.hasEditableRow();
}


@Override
public LookupValuesList getFieldTypeahead(RowEditingContext ctx,String fieldName,String query){
    return getById(ctx.getRowId()).getFieldTypeahead(fieldName, query);
}


public PricingConditionsView filter(DocumentFilterList filters){
    return new PricingConditionsView(this, rowsData.filter(filters));
}


public PricingConditionsView cast(Object viewObj){
    return (PricingConditionsView) viewObj;
}


public PricingConditionsRow getEditableRow(){
    return rowsData.getEditableRow();
}


public void updateSalesOrderLineIfPossible(){
    if (!hasEditableRow()) {
        return;
    }
    final PricingConditionsRow editableRow = getEditableRow();
    final BigDecimal basePriceAmt = editableRow.getBasePriceAmt();
    final CurrencyId currencyId = editableRow.getCurrencyId();
    final Money basePriceFromRow = Money.ofOrNull(basePriceAmt, currencyId);
    final PricingConditionsBreak pricingConditionsBreak = editableRow.getPricingConditionsBreak();
    updateOrderLineRecord(pricingConditionsBreak, basePriceFromRow);
}


@Override
public DocumentFilterList getFilters(){
    return rowsData.getFilters();
}


public OrderLineId getOrderLineId(){
    return rowsData.getOrderLineId();
}


public void patchEditableRow(PricingConditionsRowChangeRequest request){
    rowsData.patchEditableRow(request);
}


}