package de.metas.ui.web.order.sales.purchasePlanning.view;
 import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.mm.attributes.api.AttributesKeys;
import org.compiere.model.I_C_UOM;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import de.metas.logging.LogManager;
import de.metas.material.commons.attributes.AttributesKeyPatterns;
import de.metas.material.dispo.commons.repository.atp.AvailableToPromiseQuery;
import de.metas.material.dispo.commons.repository.atp.AvailableToPromiseRepository;
import de.metas.material.event.commons.AttributesKey;
import de.metas.money.CurrencyId;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.purchasecandidate.PurchaseCandidate;
import de.metas.purchasecandidate.PurchaseCandidatesGroup;
import de.metas.purchasecandidate.PurchaseDemand;
import de.metas.purchasecandidate.availability.AvailabilityResult;
import de.metas.purchasecandidate.grossprofit.PurchaseProfitInfo;
import de.metas.purchasecandidate.grossprofit.PurchaseProfitInfoService;
import de.metas.quantity.Quantity;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
@Service
public class PurchaseRowFactory {

 private  Logger logger;

 private  AvailableToPromiseRepository availableToPromiseRepository;

 private  PurchaseProfitInfoService purchaseProfitInfoService;

 private  PurchaseRowLookups lookups;

 private  IProductBL productsBL;


public Quantity getQtyAvailableToPromise(PurchaseDemand demand){
    final ProductId productId = demand.getProductId();
    final AttributesKey attributesKey = AttributesKeys.createAttributesKeyFromASIStorageAttributes(demand.getAttributeSetInstanceId()).orElse(AttributesKey.ALL);
    final AvailableToPromiseQuery query = AvailableToPromiseQuery.builder().productId(productId.getRepoId()).date(demand.getSalesPreparationDate()).storageAttributesKeyPattern(AttributesKeyPatterns.ofAttributeKey(attributesKey)).build();
    final BigDecimal qtyAvailableToPromise = availableToPromiseRepository.retrieveAvailableStockQtySum(query);
    final I_C_UOM uom = productsBL.getStockUOM(productId);
    return Quantity.of(qtyAvailableToPromise, uom);
}


@Builder(builderMethodName = "lineRowBuilder", builderClassName = "LineRowBuilder")
public PurchaseRow createLineRow(PurchaseCandidatesGroup purchaseCandidatesGroup,CurrencyId convertAmountsToCurrencyId){
    final PurchaseProfitInfo profitInfo = convertToCurrencyIfPossible(purchaseCandidatesGroup.getProfitInfoOrNull(), convertAmountsToCurrencyId);
    return PurchaseRow.lineRowBuilder().purchaseProfitInfoService(purchaseProfitInfoService).lookups(lookups).purchaseCandidatesGroup(purchaseCandidatesGroup.withProfitInfo(profitInfo)).build();
}


public PurchaseRow createGroupRow(PurchaseDemand demand,List<PurchaseRow> rows){
    return PurchaseRow.groupRowBuilder().lookups(lookups).demand(demand).qtyAvailableToPromise(getQtyAvailableToPromise(demand)).includedRows(rows).build();
}


@Builder(builderMethodName = "availabilityDetailSuccessBuilder", builderClassName = "AvailabilityDetailSuccessBuilder")
public PurchaseRow buildRowFromFromAvailabilityResult(PurchaseRow lineRow,AvailabilityResult availabilityResult){
    return PurchaseRow.availabilityDetailSuccessBuilder().lineRow(lineRow).availabilityResult(availabilityResult).build();
}


public PurchaseProfitInfo convertToCurrencyIfPossible(PurchaseProfitInfo profitInfo,CurrencyId currencyIdTo){
    if (profitInfo == null) {
        return null;
    }
    if (currencyIdTo == null) {
        return profitInfo;
    }
    try {
        return purchaseProfitInfoService.convertToCurrency(profitInfo, currencyIdTo);
    } catch (final Exception ex) {
        logger.warn("Failed converting {} to {}. Returning profitInfo as is.", profitInfo, currencyIdTo, ex);
        return profitInfo;
    }
}


@Builder(builderMethodName = "availabilityDetailErrorBuilder", builderClassName = "AvailabilityDetailErrorBuilder")
public PurchaseRow buildRowFromFromThrowable(PurchaseRow lineRow,Throwable throwable){
    return PurchaseRow.availabilityDetailErrorBuilder().lineRow(lineRow).throwable(throwable).build();
}


}