package de.metas.ui.web.quickinput.orderline;
 import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceBL;
import org.adempiere.mm.attributes.api.ImmutableAttributeSet;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_M_AttributeSetInstance;
import org.eevolution.api.BOMUse;
import org.eevolution.api.IProductBOMBL;
import org.eevolution.api.IProductBOMDAO;
import org.eevolution.api.ProductBOMLineId;
import org.eevolution.model.I_PP_Product_BOM;
import org.eevolution.model.I_PP_Product_BOMLine;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.adempiere.callout.OrderFastInput;
import de.metas.adempiere.gui.search.HUPackingAwareCopy.ASICopyMode;
import de.metas.adempiere.gui.search.IHUPackingAwareBL;
import de.metas.adempiere.gui.search.impl.OrderLineHUPackingAware;
import de.metas.adempiere.gui.search.impl.PlainHUPackingAware;
import de.metas.adempiere.model.I_C_Order;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.ShipmentAllocationBestBeforePolicy;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.lang.SOTrx;
import de.metas.logging.LogManager;
import de.metas.order.IOrderLineInputValidator;
import de.metas.order.OrderId;
import de.metas.order.OrderLineInputValidatorResults;
import de.metas.order.compensationGroup.GroupCreateRequest;
import de.metas.order.compensationGroup.GroupId;
import de.metas.order.compensationGroup.OrderGroupRepository;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.ui.web.quickinput.IQuickInputProcessor;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor.ProductAndAttributes;
import de.metas.uom.UomId;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public class OrderLineQuickInputProcessor implements IQuickInputProcessor{

 private  Logger logger;

 private  IHUPackingAwareBL huPackingAwareBL;

 private  IProductBL productBL;

 private  IAttributeSetInstanceBL asiBL;

 private  IProductBOMDAO bomsRepo;

 private  IProductBOMBL bomsService;

 private  OrderGroupRepository orderGroupsRepo;

 private  Collection<IOrderLineInputValidator> validators;

@NonNull
 private OrderId orderId;

@NonNull
 private ProductId productId;

@NonNull
 private ImmutableAttributeSet attributes;

@NonNull
 private UomId uomId;

@Nullable
 private HUPIItemProductId piItemProductId;

@NonNull
 private BigDecimal qty;

@Nullable
 private ShipmentAllocationBestBeforePolicy bestBeforePolicy;

@Nullable
 private BPartnerId bpartnerId;

@NonNull
 private SOTrx soTrx;

@Nullable
 private GroupId compensationGroupId;

@Nullable
 private ProductBOMLineId explodedFromBOMLineId;


@Override
public Set<DocumentId> process(QuickInput quickInput){
    final OrderLineCandidate initialCandidate = toOrderLineCandidate(quickInput);
    validateInput(initialCandidate);
    final List<OrderLineCandidate> candidates = explodePhantomBOM(initialCandidate);
    final I_C_Order order = quickInput.getRootDocumentAs(I_C_Order.class);
    final Properties ctx = InterfaceWrapperHelper.getCtx(order);
    final ImmutableSet.Builder<DocumentId> documentIds = ImmutableSet.builder();
    for (final OrderLineCandidate candidate : candidates) {
        final I_C_OrderLine newOrderLine = OrderFastInput.addOrderLine(ctx, order, orderLine -> updateOrderLine(orderLine, candidate));
        final int newOrderLineId = newOrderLine.getC_OrderLine_ID();
        final DocumentId documentId = DocumentId.of(newOrderLineId);
        documentIds.add(documentId);
    }
    return documentIds.build();
}


public AttributeSetInstanceId createASI(ProductId productId,ImmutableAttributeSet attributes){
    if (attributes.isEmpty()) {
        return null;
    }
    final I_M_AttributeSetInstance asi = asiBL.createASIWithASFromProductAndInsertAttributeSet(productId, attributes);
    return AttributeSetInstanceId.ofRepoId(asi.getM_AttributeSetInstance_ID());
}


public void validateInput(OrderLineCandidate candidate){
    final BPartnerId bpartnerId = candidate.getBpartnerId();
    final ProductId productId = candidate.getProductId();
    final SOTrx soTrx = candidate.getSoTrx();
    final ArrayList<ITranslatableString> validationErrorMessages = new ArrayList<>();
    for (final IOrderLineInputValidator validator : validators) {
        final OrderLineInputValidatorResults validationResults = validator.validate(bpartnerId, productId, soTrx);
        if (validationResults != null && !validationResults.isValid() && validationResults.getErrorMessage() != null) {
            validationErrorMessages.add(validationResults.getErrorMessage());
        }
    }
    if (!validationErrorMessages.isEmpty()) {
        throw new AdempiereException(TranslatableStrings.joinList("\n", validationErrorMessages));
    }
}


public List<OrderLineCandidate> explodePhantomBOM(OrderLineCandidate initialCandidate){
    final ProductId bomProductId = initialCandidate.getProductId();
    final I_PP_Product_BOM bom = bomsRepo.getDefaultBOMByProductId(bomProductId).orElse(null);
    if (bom == null) {
        return ImmutableList.of(initialCandidate);
    }
    final BOMUse bomUse = BOMUse.ofNullableCode(bom.getBOMUse());
    if (!BOMUse.Phantom.equals(bomUse)) {
        return ImmutableList.of(initialCandidate);
    }
    GroupId compensationGroupId = null;
    final ArrayList<OrderLineCandidate> result = new ArrayList<>();
    final List<I_PP_Product_BOMLine> bomLines = bomsRepo.retrieveLines(bom);
    for (final I_PP_Product_BOMLine bomLine : bomLines) {
        final ProductBOMLineId bomLineId = ProductBOMLineId.ofRepoId(bomLine.getPP_Product_BOMLine_ID());
        final ProductId bomLineProductId = ProductId.ofRepoId(bomLine.getM_Product_ID());
        final BigDecimal bomLineQty = bomsService.computeQtyRequired(bomLine, bomProductId, initialCandidate.getQty());
        final AttributeSetInstanceId bomLineAsiId = AttributeSetInstanceId.ofRepoIdOrNone(bomLine.getM_AttributeSetInstance_ID());
        final ImmutableAttributeSet attributes = asiBL.getImmutableAttributeSetById(bomLineAsiId);
        if (compensationGroupId == null) {
            compensationGroupId = orderGroupsRepo.createNewGroupId(GroupCreateRequest.builder().orderId(initialCandidate.getOrderId()).name(productBL.getProductName(bomProductId)).build());
        }
        result.add(initialCandidate.toBuilder().productId(bomLineProductId).attributes(attributes).qty(bomLineQty).compensationGroupId(compensationGroupId).explodedFromBOMLineId(bomLineId).build());
    }
    return result;
}


public void updateOrderLine(I_C_OrderLine to,OrderLineCandidate candidate){
    final PlainHUPackingAware fromPackingInfo = createQuickInputPackingAware(candidate);
    huPackingAwareBL.prepareCopyFrom(fromPackingInfo).overridePartner(false).asiCopyMode(// because we just created the ASI
    ASICopyMode.CopyID).copyTo(OrderLineHUPackingAware.of(to));
    if (candidate.getBestBeforePolicy() != null) {
        to.setShipmentAllocation_BestBefore_Policy(candidate.getBestBeforePolicy().getCode());
    }
    if (candidate.getCompensationGroupId() != null) {
        to.setC_Order_CompensationGroup_ID(candidate.getCompensationGroupId().getOrderCompensationGroupId());
    }
    if (candidate.getExplodedFromBOMLineId() != null) {
        to.setExplodedFrom_BOMLine_ID(candidate.getExplodedFromBOMLineId().getRepoId());
    }
}


public PlainHUPackingAware createQuickInputPackingAware(OrderLineCandidate candidate){
    final PlainHUPackingAware huPackingAware = new PlainHUPackingAware();
    huPackingAware.setBpartnerId(candidate.getBpartnerId());
    huPackingAware.setInDispute(false);
    huPackingAware.setProductId(candidate.getProductId());
    huPackingAware.setUomId(candidate.getUomId());
    huPackingAware.setAsiId(createASI(candidate.getProductId(), candidate.getAttributes()));
    huPackingAware.setPiItemProductId(candidate.getPiItemProductId());
    // 
    huPackingAwareBL.computeAndSetQtysForNewHuPackingAware(huPackingAware, candidate.getQty());
    // 
    // Validate:
    if (huPackingAware.getQty() == null || huPackingAware.getQty().signum() <= 0) {
        logger.warn("Invalid Qty={} for {}", huPackingAware.getQty(), huPackingAware);
        // TODO trl
        throw new AdempiereException("Qty shall be greather than zero");
    }
    if (huPackingAware.getQtyTU() == null || huPackingAware.getQtyTU().signum() <= 0) {
        logger.warn("Invalid QtyTU={} for {}", huPackingAware.getQtyTU(), huPackingAware);
        // TODO trl
        throw new AdempiereException("QtyTU shall be greather than zero");
    }
    return huPackingAware;
}


public OrderLineCandidate toOrderLineCandidate(QuickInput quickInput){
    final IOrderLineQuickInput orderLineQuickInput = quickInput.getQuickInputDocumentAs(IOrderLineQuickInput.class);
    // Validate quick input:
    final BigDecimal quickInputQty = orderLineQuickInput.getQty();
    if (quickInputQty == null || quickInputQty.signum() <= 0) {
        logger.warn("Invalid Qty={} for {}", quickInputQty, orderLineQuickInput);
        // TODO trl
        throw new AdempiereException("Qty shall be greather than zero");
    }
    final I_C_Order order = quickInput.getRootDocumentAs(I_C_Order.class);
    final OrderId orderId = OrderId.ofRepoId(order.getC_Order_ID());
    final BPartnerId bpartnerId = BPartnerId.ofRepoIdOrNull(order.getC_BPartner_ID());
    final ProductAndAttributes productAndAttributes = ProductLookupDescriptor.toProductAndAttributes(orderLineQuickInput.getM_Product_ID());
    final UomId uomId = productBL.getStockUOMId(productAndAttributes.getProductId());
    return OrderLineCandidate.builder().orderId(orderId).productId(productAndAttributes.getProductId()).attributes(productAndAttributes.getAttributes()).uomId(uomId).piItemProductId(HUPIItemProductId.ofRepoIdOrNull(orderLineQuickInput.getM_HU_PI_Item_Product_ID())).qty(quickInputQty).bestBeforePolicy(ShipmentAllocationBestBeforePolicy.ofNullableCode(orderLineQuickInput.getShipmentAllocation_BestBefore_Policy())).bpartnerId(bpartnerId).soTrx(SOTrx.ofBoolean(order.isSOTrx())).build();
}


}