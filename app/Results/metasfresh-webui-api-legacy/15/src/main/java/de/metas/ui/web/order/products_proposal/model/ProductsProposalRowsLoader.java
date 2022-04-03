package de.metas.ui.web.order.products_proposal.model;
 import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_M_PriceList;
import org.compiere.model.I_M_Product;
import org.slf4j.Logger;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.product.stats.BPartnerProductStats;
import de.metas.bpartner.product.stats.BPartnerProductStatsService;
import de.metas.currency.Amount;
import de.metas.currency.CurrencyCode;
import de.metas.currency.ICurrencyDAO;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.handlingunits.IHUPIItemProductBL;
import de.metas.handlingunits.model.I_M_ProductPrice;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.lang.SOTrx;
import de.metas.logging.LogManager;
import de.metas.money.CurrencyId;
import de.metas.pricing.PriceListVersionId;
import de.metas.pricing.ProductPriceId;
import de.metas.pricing.service.IPriceListDAO;
import de.metas.product.ProductId;
import de.metas.ui.web.order.products_proposal.campaign_price.CampaignPriceProvider;
import de.metas.ui.web.order.products_proposal.campaign_price.CampaignPriceProviders;
import de.metas.ui.web.order.products_proposal.service.Order;
import de.metas.ui.web.view.ViewHeaderProperties;
import de.metas.ui.web.view.ViewHeaderProperties.ViewHeaderPropertiesBuilder;
import de.metas.ui.web.view.ViewHeaderProperty;
import de.metas.ui.web.window.datatypes.DocumentIdIntSequence;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.time.SystemTime;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
public class ProductsProposalRowsLoader {

 private  Logger logger;

 private  IPriceListDAO priceListsRepo;

 private  IAttributeSetInstanceBL attributeSetInstanceBL;

 private  ICurrencyDAO currenciesRepo;

 private  BPartnerProductStatsService bpartnerProductStatsService;

 private  IHUPIItemProductBL packingMaterialsService;

 private  IMsgBL msgBL;

 private  CampaignPriceProvider campaignPriceProvider;

 private  LookupDataSource productLookup;

 private  DocumentIdIntSequence nextRowIdSequence;

 private  ImmutableSet<PriceListVersionId> priceListVersionIds;

 private  Order order;

 private  BPartnerId bpartnerId;

 private  SOTrx soTrx;

 private  ImmutableSet<ProductId> productIdsToExclude;

 private  Map<PriceListVersionId,CurrencyCode> currencyCodesByPriceListVersionId;


public CurrencyCode getCurrencyCode(PriceListVersionId priceListVersionId){
    return currencyCodesByPriceListVersionId.computeIfAbsent(priceListVersionId, this::retrieveCurrencyCode);
}


public CurrencyCode retrieveCurrencyCode(PriceListVersionId priceListVersionId){
    final I_M_PriceList priceList = priceListsRepo.getPriceListByPriceListVersionId(priceListVersionId);
    final CurrencyId currencyId = CurrencyId.ofRepoId(priceList.getC_Currency_ID());
    return currenciesRepo.getCurrencyCodeById(currencyId);
}


public ProductsProposalRowsData load(){
    List<ProductsProposalRow> rows = loadRows();
    logger.debug("loaded {} productsProposalRows for priceListVersionIds={}", rows.size(), priceListVersionIds);
    rows = updateLastShipmentDays(rows);
    final PriceListVersionId singlePriceListVersionId = priceListVersionIds.size() == 1 ? priceListVersionIds.iterator().next() : null;
    final PriceListVersionId basePriceListVersionId;
    if (singlePriceListVersionId != null) {
        final ZonedDateTime datePromised = order == null ? SystemTime.asZonedDateTime() : order.getDatePromised();
        basePriceListVersionId = priceListsRepo.getBasePriceListVersionIdForPricingCalculationOrNull(singlePriceListVersionId, datePromised);
        logger.debug("singlePriceListVersionId={} and datePromised={}; -> basePriceListVersionId={}", PriceListVersionId.toRepoId(singlePriceListVersionId), datePromised, PriceListVersionId.toRepoId(basePriceListVersionId));
    } else {
        basePriceListVersionId = null;
    }
    // 
    final ViewHeaderPropertiesBuilder headerProperties = ViewHeaderProperties.builder();
    if (order != null) {
        logger.debug("order!=null; -> add bpartnerName={} to headerProperties", order.getBpartnerName());
        headerProperties.entry(ViewHeaderProperty.builder().caption(msgBL.translatable("C_BPartner_ID")).value(order.getBpartnerName()).build());
    }
    return ProductsProposalRowsData.builder().nextRowIdSequence(nextRowIdSequence).campaignPriceProvider(campaignPriceProvider).singlePriceListVersionId(singlePriceListVersionId).basePriceListVersionId(basePriceListVersionId).order(order).bpartnerId(bpartnerId).soTrx(soTrx).headerProperties(headerProperties.build()).rows(rows).build();
}


public ProductASIDescription extractProductASIDescription(I_M_ProductPrice record){
    final AttributeSetInstanceId asiId = AttributeSetInstanceId.ofRepoIdOrNone(record.getM_AttributeSetInstance_ID());
    return ProductASIDescription.ofString(attributeSetInstanceBL.getASIDescriptionById(asiId));
}


public ProductProposalPrice extractProductProposalPrice(I_M_ProductPrice record){
    final PriceListVersionId priceListVersionId = PriceListVersionId.ofRepoId(record.getM_PriceList_Version_ID());
    final Amount priceListPrice = Amount.of(record.getPriceStd(), getCurrencyCode(priceListVersionId));
    final ProductId productId = ProductId.ofRepoId(record.getM_Product_ID());
    final ProductProposalCampaignPrice campaignPrice = campaignPriceProvider.getCampaignPrice(productId).orElse(null);
    return ProductProposalPrice.builder().priceListPrice(priceListPrice).campaignPrice(campaignPrice).build();
}


public Integer calculateLastShipmentOrReceiptInDays(BPartnerProductStats stats){
    if (stats == null) {
        return null;
    } else if (soTrx.isSales()) {
        return stats.getLastShipmentInDays();
    } else {
        return stats.getLastReceiptInDays();
    }
}


public ProductsProposalRow updateRowFromStats(ProductsProposalRow row,BPartnerProductStats stats){
    final Integer lastShipmentOrReceiptInDays = calculateLastShipmentOrReceiptInDays(stats);
    return row.withLastShipmentDays(lastShipmentOrReceiptInDays);
}


public Stream<ProductsProposalRow> loadAndStreamRowsForPriceListVersionId(PriceListVersionId priceListVersionId){
    return priceListsRepo.retrieveProductPrices(priceListVersionId, productIdsToExclude).map(productPriceRecord -> InterfaceWrapperHelper.create(productPriceRecord, I_M_ProductPrice.class)).map(productPriceRecord -> toProductsProposalRowOrNull(productPriceRecord)).filter(Objects::nonNull);
}


public ProductsProposalRow toProductsProposalRowOrNull(I_M_ProductPrice record){
    final ProductId productId = ProductId.ofRepoId(record.getM_Product_ID());
    final LookupValue product = productLookup.findById(productId);
    if (!product.isActive()) {
        return null;
    }
    final HUPIItemProductId packingMaterialId = HUPIItemProductId.ofRepoIdOrNull(record.getM_HU_PI_Item_Product_ID());
    final ITranslatableString packingDescription = packingMaterialId != null ? packingMaterialsService.getDisplayName(packingMaterialId) : TranslatableStrings.empty();
    return ProductsProposalRow.builder().id(nextRowIdSequence.nextDocumentId()).product(product).packingMaterialId(packingMaterialId).packingDescription(packingDescription).asiDescription(extractProductASIDescription(record)).price(extractProductProposalPrice(record)).qty(null).lastShipmentDays(// will be populated later
    null).seqNo(record.getSeqNo()).productPriceId(ProductPriceId.ofRepoId(record.getM_ProductPrice_ID())).build().withExistingOrderLine(order);
}


public List<ProductsProposalRow> loadRows(){
    return priceListVersionIds.stream().flatMap(this::loadAndStreamRowsForPriceListVersionId).sorted(Comparator.comparing(ProductsProposalRow::getSeqNo).thenComparing(ProductsProposalRow::getProductName)).collect(ImmutableList.toImmutableList());
}


public List<ProductsProposalRow> updateLastShipmentDays(List<ProductsProposalRow> rows){
    final Set<ProductId> productIds = rows.stream().map(ProductsProposalRow::getProductId).collect(ImmutableSet.toImmutableSet());
    if (productIds.isEmpty()) {
        return rows;
    }
    final Map<ProductId, BPartnerProductStats> statsByProductId = bpartnerProductStatsService.getByPartnerAndProducts(bpartnerId, productIds);
    return rows.stream().map(row -> updateRowFromStats(row, statsByProductId.get(row.getProductId()))).collect(ImmutableList.toImmutableList());
}


}