package de.metas.ui.web.order.pricingconditions.view;
 import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import java.util.Objects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSetMultimap;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.BPartnerType;
import de.metas.bpartner.service.IBPartnerBL;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.inout.IInOutDAO;
import de.metas.lang.SOTrx;
import de.metas.logging.LogManager;
import de.metas.money.Money;
import de.metas.order.OrderLineId;
import de.metas.payment.paymentterm.PaymentTermId;
import de.metas.pricing.conditions.PriceSpecification;
import de.metas.pricing.conditions.PricingConditions;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.pricing.conditions.PricingConditionsBreakId;
import de.metas.pricing.conditions.PricingConditionsBreakMatchCriteria;
import de.metas.pricing.conditions.PricingConditionsId;
import de.metas.pricing.conditions.service.IPricingConditionsRepository;
import de.metas.product.ProductCategoryId;
import de.metas.product.ProductId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.util.Services;
import de.metas.util.lang.Percent;
import lombok.Builder;
import lombok.NonNull;
public class PricingConditionsRowsLoader {

 private  Logger logger;

 private  IBPartnerDAO bpartnersRepo;

 private  IBPartnerBL bpartnerBL;

 private  IPricingConditionsRepository pricingConditionsRepo;

 private  IInOutDAO inoutsRepo;

 private  Comparator<PricingConditionsRow> ROWS_SORTING;

 private  PricingConditionsRowLookups lookups;

 private  PricingConditionsBreaksExtractor pricingConditionsBreaksExtractor;

 private  BasePricingSystemPriceCalculator basePricingSystemPriceCalculator;

 private  DocumentFilterList filters;

 private  SourceDocumentLine sourceDocumentLine;

 private  ImmutableSetMultimap<PricingConditionsId,PricingConditionsInfo> pricingConditionsInfoById;

 private  LoadingCache<LastInOutDateRequest,Optional<LocalDate>> lastInOutDates;

@lombok.NonNull
 private PricingConditionsId pricingConditionsId;

@lombok.NonNull
 private LookupValue bpartner;

@lombok.NonNull
 private BPartnerType bpartnerType;

@Nullable
 private OrderLineId orderLineId;

@NonNull
 private SOTrx soTrx;

@NonNull
 private BPartnerId bpartnerId;

@NonNull
 private ProductId productId;

@NonNull
 private ProductCategoryId productCategoryId;

@NonNull
 private Money priceEntered;

@lombok.Builder.Default
@NonNull
 private Percent discount;

@Nullable
 private PaymentTermId paymentTermId;

@Nullable
 private PricingConditionsBreakId pricingConditionsBreakId;

@NonNull
 private BPartnerId bpartnerId;

@NonNull
 private ProductId productId;

@NonNull
 private SOTrx soTrx;


public PricingConditionsId getPricingConditionsIdByBPartner(LookupValue bpartner,Map<BPartnerId,Integer> discountSchemaIdsByBPartnerId){
    final BPartnerId bpartnerId = BPartnerId.ofRepoId(bpartner.getIdAsInt());
    final Integer discountSchemaId = discountSchemaIdsByBPartnerId.get(bpartnerId);
    if (discountSchemaId == null) {
        return null;
    }
    return PricingConditionsId.ofRepoId(discountSchemaId);
}


public boolean isCurrentConditions(PricingConditionsRow row){
    if (sourceDocumentLine == null) {
        return false;
    } else if (sourceDocumentLine.getPricingConditionsBreakId() != null) {
        return sourceDocumentLine.getPricingConditionsBreakId().equals(row.getPricingConditionsBreak().getId());
    } else {
        return Objects.equals(row.getBpartnerId(), sourceDocumentLine.getBpartnerId()) && (sourceDocumentLine.getSoTrx().isSales() ? row.isCustomer() : row.isVendor());
    }
}


public PricingConditionsRow createPricingConditionsRow(PricingConditionsBreak pricingConditionsBreak,PricingConditionsInfo pricingConditionsInfo){
    final SOTrx soTrx = pricingConditionsInfo.getBpartnerType().getSOTrx();
    return PricingConditionsRow.builder().lookups(lookups).editable(false).bpartner(pricingConditionsInfo.getBpartner()).customer(soTrx.isSales()).pricingConditionsId(pricingConditionsInfo.getPricingConditionsId()).pricingConditionsBreak(pricingConditionsBreak).dateLastInOut(getLastInOutDate(pricingConditionsInfo.getBPartnerId(), soTrx, pricingConditionsBreak)).basePricingSystemPriceCalculator(basePricingSystemPriceCalculator).build();
}


public Stream<PricingConditionsRow> createPricingConditionsRows(PricingConditionsBreak pricingConditionsBreak){
    return getPricingConditionsInfos(pricingConditionsBreak.getPricingConditionsId()).stream().map(pricingConditionsInfo -> createPricingConditionsRow(pricingConditionsBreak, pricingConditionsInfo));
}


public PricingConditionsRowData load(){
    return build().load();
}


public Set<PricingConditionsInfo> getPricingConditionsInfos(PricingConditionsId pricingConditionId){
    return getPricingConditionsInfosIndexedById().get(pricingConditionId);
}


public ImmutableSetMultimap<PricingConditionsId,PricingConditionsInfo> getPricingConditionsInfosIndexedById(){
    if (pricingConditionsInfoById == null) {
        final Stream<PricingConditionsInfo> vendorPricingConditions = streamPricingConditionsInfos(BPartnerType.VENDOR);
        final Stream<PricingConditionsInfo> customerPricingConditions = streamPricingConditionsInfos(BPartnerType.CUSTOMER);
        pricingConditionsInfoById = Stream.concat(vendorPricingConditions, customerPricingConditions).collect(ImmutableSetMultimap.toImmutableSetMultimap(PricingConditionsInfo::getPricingConditionsId, Function.identity()));
    }
    return pricingConditionsInfoById;
}


public PricingConditionsRow createEditablePricingConditionsRowOrNull(){
    if (sourceDocumentLine == null) {
        return null;
    }
    final int discountSchemaId = bpartnerBL.getDiscountSchemaId(sourceDocumentLine.getBpartnerId(), sourceDocumentLine.getSoTrx());
    final PricingConditionsId pricingConditionsId = PricingConditionsId.ofRepoIdOrNull(discountSchemaId);
    final Money priceEntered = sourceDocumentLine.getPriceEntered();
    final PricingConditionsBreak pricingConditionsBreak = PricingConditionsBreak.builder().id(// N/A
    null).matchCriteria(PricingConditionsBreakMatchCriteria.builder().breakValue(BigDecimal.ZERO).productId(sourceDocumentLine.getProductId()).productCategoryId(sourceDocumentLine.getProductCategoryId()).build()).priceSpecification(PriceSpecification.fixedPrice(priceEntered)).paymentTermIdOrNull(sourceDocumentLine.getPaymentTermId()).discount(sourceDocumentLine.getDiscount()).dateCreated(// N/A; the PricingConditionsBreak hasn't been created (i.e. persisted on DB) yet
    null).createdById(null).build();
    return PricingConditionsRow.builder().lookups(lookups).editable(true).bpartner(lookups.lookupBPartner(sourceDocumentLine.getBpartnerId())).customer(sourceDocumentLine.getSoTrx().isSales()).pricingConditionsId(pricingConditionsId).pricingConditionsBreak(pricingConditionsBreak).basePricingSystemPriceCalculator(basePricingSystemPriceCalculator).dateLastInOut(getLastInOutDate(sourceDocumentLine.getBpartnerId(), sourceDocumentLine.getSoTrx(), pricingConditionsBreak)).build();
}


public Stream<PricingConditionsInfo> streamPricingConditionsInfos(BPartnerType bpartnerType){
    final Map<BPartnerId, Integer> discountSchemaIdsByBPartnerId = bpartnersRepo.retrieveAllDiscountSchemaIdsIndexedByBPartnerId(bpartnerType);
    return discountSchemaIdsByBPartnerId.keySet().stream().map(lookups::lookupBPartner).filter(Objects::nonNull).map(bpartner -> PricingConditionsInfo.builder().bpartner(bpartner).pricingConditionsId(getPricingConditionsIdByBPartner(bpartner, discountSchemaIdsByBPartnerId)).bpartnerType(bpartnerType).build());
}


public Stream<PricingConditionsBreak> streamPricingConditionsBreaks(PricingConditions pricingConditions)


public Set<PricingConditionsId> getAllPricingConditionsId(){
    return getPricingConditionsInfosIndexedById().keySet();
}


public LocalDate getLastInOutDate(BPartnerId bpartnerId,SOTrx soTrx,PricingConditionsBreak pricingConditionsBreak){
    final ProductId productId = pricingConditionsBreak.getMatchCriteria().getProductId();
    if (productId == null) {
        return null;
    }
    final LastInOutDateRequest request = LastInOutDateRequest.builder().bpartnerId(bpartnerId).productId(productId).soTrx(soTrx).build();
    try {
        return lastInOutDates.get(request).orElse(null);
    } catch (ExecutionException ex) {
        logger.warn("Failed fetching last InOut date for {}. Returning null.", request, ex);
        return null;
    }
}


public BPartnerId getBPartnerId(){
    return BPartnerId.ofRepoId(bpartner.getIdAsInt());
}


}