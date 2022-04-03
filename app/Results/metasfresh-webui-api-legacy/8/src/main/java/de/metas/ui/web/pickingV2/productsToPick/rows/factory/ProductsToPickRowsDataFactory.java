package de.metas.ui.web.pickingV2.productsToPick.rows.factory;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.service.IDeveloperModeBL;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.api.AttributeConstants;
import org.adempiere.mm.attributes.api.ImmutableAttributeSet;
import org.adempiere.mm.attributes.api.impl.LotNumberDateAttributeDAO;
import org.eevolution.api.IPPOrderBL;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.ShipmentAllocationBestBeforePolicy;
import de.metas.bpartner.service.IBPartnerBL;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.attribute.storage.IAttributeStorage;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactory;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactoryService;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.picking.PickFrom;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.handlingunits.picking.PickingCandidateIssueToBOMLine;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.PickingCandidateStatus;
import de.metas.handlingunits.reservation.HUReservation;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.inoutcandidate.api.Packageable;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.material.planning.pporder.PPOrderBOMLineId;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.material.planning.pporder.impl.QtyCalculationsBOM;
import de.metas.material.planning.pporder.impl.QtyCalculationsBOMLine;
import de.metas.order.OrderLineId;
import de.metas.product.IProductDAO;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.shipping.ShipperId;
import de.metas.ui.web.pickingV2.packageable.PackageableRow;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductInfo;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRow;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowId;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowType;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowsData;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.lookup.LookupValueByIdSupplier;
import de.metas.uom.IUOMDAO;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class ProductsToPickRowsDataFactory {

 private  IProductDAO productsRepo;

 private  IUOMDAO uomsRepo;

 private  IDeveloperModeBL developerModeBL;

 private  IPPOrderBL ppOrdersBL;

 private  IBPartnerBL bpartnersService;

 private  HUReservationService huReservationService;

 private  PickingCandidateService pickingCandidateService;

 private  ProductInfoSupplier productInfos;

 private  LookupValueByIdSupplier locatorLookup;

 private  IAttributeStorageFactory attributesFactory;

 private  ProductsToPickSourceStorage storages;

 private  Map<HuId,ImmutableAttributeSet> huAttributesCache;

 private  boolean considerAttributes;

 public  String ATTR_LotNumber;

 public  String ATTR_BestBeforeDate;

 public  String ATTR_RepackNumber;

 private  ImmutableSet<String> ATTRIBUTES;


public ImmutableAttributeSet retrieveHUAttributes(HuId huId){
    final I_M_HU hu = storages.getHU(huId);
    final IAttributeStorage attributes = attributesFactory.getAttributeStorage(hu);
    return ImmutableAttributeSet.createSubSet(attributes, a -> ATTRIBUTES.contains(a.getValue()));
}


public ProductsToPickRowBuilder prepareRow_PickFromPickingOrder(AllocablePackageable finishedGoodPackageable){
    return prepareRow().rowType(ProductsToPickRowType.PICK_FROM_PICKING_ORDER).productId(finishedGoodPackageable.getProductId()).shipmentScheduleId(finishedGoodPackageable.getShipmentScheduleId()).salesOrderLineId(finishedGoodPackageable.getSalesOrderLineIdOrNull()).shipperId(finishedGoodPackageable.getShipperId());
}


public AllocablePackageable toBOMLineAllocablePackageable(PickingCandidateIssueToBOMLine issueToBOMLine,AllocablePackageable finishedGoodPackageable){
    return finishedGoodPackageable.toBuilder().productId(issueToBOMLine.getProductId()).qtyToAllocateTarget(issueToBOMLine.getQtyToIssue()).issueToOrderBOMLineId(issueToBOMLine.getIssueToOrderBOMLineId()).build();
}


public Optional<QtyCalculationsBOM> getPickingOrderBOM(AllocablePackageable packageable){
    final PPOrderId pickFromOrderId = packageable.getPickFromOrderId();
    return pickFromOrderId != null ? ppOrdersBL.getOpenPickingOrderBOM(pickFromOrderId) : Optional.empty();
}


@Nullable
public ProductsToPickRow allocateRowFromHU(ProductsToPickRow row,AllocablePackageable packageable){
    if (packageable.isAllocated()) {
        return null;
    }
    final HuId pickFromHUId = row.getPickFromHUId();
    if (pickFromHUId == null) {
        throw new AdempiereException("No pickFromHUId set for " + row);
    }
    final ProductId productId = packageable.getProductId();
    final ReservableStorage storage = storages.getStorage(pickFromHUId, productId);
    final Quantity qty = storage.reserve(packageable);
    if (qty.isZero()) {
        return null;
    }
    return row.withQty(qty);
}


public ProductsToPickRow createQtyNotAvailableRowForRemainingQtyToAllocate(AllocablePackageable packageable){
    final Quantity qty = packageable.getQtyToAllocate();
    packageable.allocateQty(qty);
    return prepareRow().rowType(ProductsToPickRowType.UNALLOCABLE).productId(packageable.getProductId()).shipmentScheduleId(packageable.getShipmentScheduleId()).salesOrderLineId(packageable.getSalesOrderLineIdOrNull()).shipperId(packageable.getShipperId()).issueToOrderBOMLineId(packageable.getIssueToOrderBOMLineId()).qty(qty).build();
}


@Builder(builderMethodName = "prepareRow", builderClassName = "ProductsToPickRowBuilder")
public ProductsToPickRow createRow(ProductsToPickRowType rowType,ProductId productId,ShipmentScheduleId shipmentScheduleId,OrderLineId salesOrderLineId,ShipperId shipperId,PPOrderBOMLineId issueToOrderBOMLineId,Quantity qty,HuId pickFromHUId,PPOrderId pickFromPickingOrderId,PickingCandidate existingPickingCandidate,List<ProductsToPickRow> includedRows){
    final ProductInfo productInfo = productInfos.getByProductId(productId);
    final boolean huReservedForThisRow = pickFromHUId != null && salesOrderLineId != null && huReservationService.isVhuIdReservedToSalesOrderLineId(pickFromHUId, salesOrderLineId);
    final LookupValue locator = pickFromHUId != null ? getLocatorLookupValueByHuId(pickFromHUId) : null;
    final ImmutableAttributeSet attributes = pickFromHUId != null ? getHUAttributes(pickFromHUId) : ImmutableAttributeSet.EMPTY;
    final ProductsToPickRowId rowId = ProductsToPickRowId.builder().productId(productInfo.getProductId()).shipmentScheduleId(shipmentScheduleId).pickFromHUId(pickFromHUId).pickFromPickingOrderId(pickFromPickingOrderId).issueToOrderBOMLineId(issueToOrderBOMLineId).build();
    return ProductsToPickRow.builder().rowId(rowId).rowType(rowType).productInfo(productInfo).huReservedForThisRow(huReservedForThisRow).locator(locator).lotNumber(attributes.getValueAsStringIfExists(ATTR_LotNumber).orElseGet(() -> buildLotNumberFromHuId(pickFromHUId))).expiringDate(attributes.getValueAsLocalDateIfExists(ATTR_BestBeforeDate).orElse(null)).repackNumber(attributes.getValueAsStringIfExists(ATTR_RepackNumber).orElse(null)).qty(qty).shipperId(shipperId).includedRows(includedRows).build().withUpdatesFromPickingCandidateIfNotNull(existingPickingCandidate);
}


public ImmutableAttributeSet getHUAttributes(HuId huId){
    return huAttributesCache.computeIfAbsent(huId, this::retrieveHUAttributes);
}


public ProductsToPickRowsData create(PackageableRow packageableRow){
    final ImmutableList<ProductsToPickRow> rows = packageableRow.getPackageables().stream().map(this::toAllocablePackageable).flatMap(this::createRowsAndStream).collect(ImmutableList.toImmutableList());
    return ProductsToPickRowsData.builder().pickingCandidateService(pickingCandidateService).rows(rows).orderBy(DocumentQueryOrderBy.byFieldName(ProductsToPickRow.FIELD_Locator)).build();
}


public ProductsToPickRowBuilder prepareRow_IssueComponentsToPickingOrder(AllocablePackageable packageable){
    return prepareRow().rowType(ProductsToPickRowType.ISSUE_COMPONENTS_TO_PICKING_ORDER).productId(packageable.getProductId()).shipmentScheduleId(packageable.getShipmentScheduleId()).salesOrderLineId(packageable.getSalesOrderLineIdOrNull()).shipperId(packageable.getShipperId()).issueToOrderBOMLineId(packageable.getIssueToOrderBOMLineId());
}


public List<ProductsToPickRow> createRowsFromPickingOrderBOMLine(QtyCalculationsBOMLine bomLine,AllocablePackageable finishedGoodPackageable){
    final AllocablePackageable bomLinePackageable = toBOMLineAllocablePackageable(bomLine, finishedGoodPackageable);
    final ArrayList<ProductsToPickRow> rows = new ArrayList<>();
    rows.addAll(createRowsFromHUs(bomLinePackageable));
    if (!bomLinePackageable.isAllocated()) {
        rows.add(createQtyNotAvailableRowForRemainingQtyToAllocate(bomLinePackageable));
    }
    return rows;
}


public Stream<ProductsToPickRow> createRowsAndStream(AllocablePackageable packageable){
    final ArrayList<ProductsToPickRow> rows = new ArrayList<>();
    rows.addAll(createRowsFromExistingPickingCandidates(packageable));
    rows.addAll(createRowsFromHUs(packageable));
    if (!packageable.isAllocated()) {
        final QtyCalculationsBOM pickingOrderBOM = getPickingOrderBOM(packageable).orElse(null);
        if (pickingOrderBOM != null) {
            rows.add(createRowsFromPickingOrder(pickingOrderBOM, packageable));
        }
    }
    if (!packageable.isAllocated()) {
        rows.add(createQtyNotAvailableRowForRemainingQtyToAllocate(packageable));
    }
    return rows.stream();
}


public ProductsToPickRow createZeroQtyRowFromHU(AllocablePackageable packageable,HuId pickFromHUId){
    final boolean isPickFromHU = packageable.getIssueToOrderBOMLineId() == null;
    if (isPickFromHU) {
        return prepareRow_PickFromHU(packageable).pickFromHUId(pickFromHUId).qty(packageable.getQtyToAllocate().toZero()).build();
    } else {
        return prepareRow_IssueComponentsToPickingOrder(packageable).pickFromHUId(pickFromHUId).qty(packageable.getQtyToAllocate().toZero()).build();
    }
}


public LookupValue getLocatorLookupValueByHuId(HuId huId){
    final I_M_HU hu = storages.getHU(huId);
    final int locatorId = hu.getM_Locator_ID();
    if (locatorId <= 0) {
        return null;
    }
    return locatorLookup.findById(locatorId);
}


public ShipmentAllocationBestBeforePolicy getBestBeforePolicy(AllocablePackageable packageable){
    final Optional<ShipmentAllocationBestBeforePolicy> bestBeforePolicy = packageable.getBestBeforePolicy();
    if (bestBeforePolicy.isPresent()) {
        return bestBeforePolicy.get();
    }
    final BPartnerId bpartnerId = packageable.getCustomerId();
    return bpartnersService.getBestBeforePolicy(bpartnerId);
}


public Set<HuId> getHuIdsReservedForSalesOrderLine(AllocablePackageable packageable){
    final OrderLineId salesOrderLineId = packageable.getSalesOrderLineIdOrNull();
    if (salesOrderLineId == null) {
        return ImmutableSet.of();
    }
    final HUReservation huReservation = huReservationService.getBySalesOrderLineId(salesOrderLineId).orElse(null);
    if (huReservation == null) {
        return ImmutableSet.of();
    }
    return huReservation.getVhuIds();
}


public AllocablePackageable toAllocablePackageable(Packageable packageable){
    final Quantity qtyToAllocateTarget = packageable.getQtyToDeliver().subtract(packageable.getQtyPickedNotDelivered()).toZeroIfNegative();
    return AllocablePackageable.builder().customerId(packageable.getCustomerId()).productId(packageable.getProductId()).asiId(packageable.getAsiId()).shipmentScheduleId(packageable.getShipmentScheduleId()).bestBeforePolicy(packageable.getBestBeforePolicy()).warehouseId(packageable.getWarehouseId()).salesOrderLineIdOrNull(packageable.getSalesOrderLineIdOrNull()).shipperId(packageable.getShipperId()).pickFromOrderId(packageable.getPickFromOrderId()).qtyToAllocateTarget(qtyToAllocateTarget).build();
}


public String buildLotNumberFromHuId(HuId huId){
    if (huId == null) {
        return null;
    }
    if (!developerModeBL.isEnabled()) {
        return null;
    }
    return "<" + huId.getRepoId() + ">";
}


public Set<HuId> getHuIdsAvailableToAllocate(AllocablePackageable packageable){
    final OrderLineId salesOrderLine = packageable.getSalesOrderLineIdOrNull();
    final Set<HuId> huIds = huReservationService.prepareHUQuery().warehouseId(packageable.getWarehouseId()).productId(packageable.getProductId()).asiId(considerAttributes ? packageable.getAsiId() : null).reservedToSalesOrderLineIdOrNotReservedAtAll(salesOrderLine).build().listIds();
    warmUpCacheForHuIds(huIds);
    return huIds;
}


public List<ProductsToPickRow> createRowsFromHUs(AllocablePackageable packageable){
    if (packageable.isAllocated()) {
        return ImmutableList.of();
    }
    final ImmutableSet<HuId> huIdsAvailableToPick = ImmutableSet.<HuId>builder().addAll(// reserved HUs first
    getHuIdsReservedForSalesOrderLine(packageable)).addAll(getHuIdsAvailableToAllocate(packageable)).build();
    final List<ProductsToPickRow> rowsWithZeroQty = huIdsAvailableToPick.stream().map(pickFromHUId -> createZeroQtyRowFromHU(packageable, pickFromHUId)).collect(ImmutableList.toImmutableList());
    final ShipmentAllocationBestBeforePolicy bestBeforePolicy = getBestBeforePolicy(packageable);
    return rowsWithZeroQty.stream().sorted(Comparator.<ProductsToPickRow>comparingInt(// consider reserved HU first
    row -> row.isHuReservedForThisRow() ? 0 : 1).thenComparing(// then first/last expiring HU
    bestBeforePolicy.comparator(ProductsToPickRow::getExpiringDate))).map(row -> allocateRowFromHU(row, packageable)).filter(Objects::nonNull).collect(ImmutableList.toImmutableList());
}


public List<ProductsToPickRow> createRowsFromExistingPickingCandidates(AllocablePackageable packageable){
    final List<PickingCandidate> pickingCandidates = pickingCandidateService.getByShipmentScheduleIdAndStatus(packageable.getShipmentScheduleId(), PickingCandidateStatus.Draft);
    return pickingCandidates.stream().map(pickingCandidate -> createRowFromExistingPickingCandidate(packageable, pickingCandidate)).filter(Objects::nonNull).collect(ImmutableList.toImmutableList());
}


public ProductsToPickRow createRowFromExistingPickingCandidate(AllocablePackageable packageable,PickingCandidate existingPickingCandidate){
    final PickFrom pickFrom = existingPickingCandidate.getPickFrom();
    if (pickFrom.isPickFromHU()) {
        final Quantity qty;
        final HuId pickFromHUId = pickFrom.getHuId();
        if (pickFromHUId != null) {
            final ProductId productId = packageable.getProductId();
            final ReservableStorage storage = storages.getStorage(pickFromHUId, productId);
            qty = storage.reserve(packageable, existingPickingCandidate.getQtyPicked());
        } else {
            qty = existingPickingCandidate.getQtyPicked();
        }
        return prepareRow_PickFromHU(packageable).pickFromHUId(pickFromHUId).qty(qty).existingPickingCandidate(existingPickingCandidate).build();
    } else if (pickFrom.isPickFromPickingOrder()) {
        final ImmutableList<ProductsToPickRow> includedRows = existingPickingCandidate.getIssuesToPickingOrder().stream().map(issueToBOMLine -> prepareRow_IssueComponentsToPickingOrder(toBOMLineAllocablePackageable(issueToBOMLine, packageable)).pickFromHUId(issueToBOMLine.getIssueFromHUId()).qty(issueToBOMLine.getQtyToIssue()).build()).collect(ImmutableList.toImmutableList());
        return prepareRow_PickFromPickingOrder(packageable).pickFromPickingOrderId(pickFrom.getPickingOrderId()).qty(existingPickingCandidate.getQtyPicked()).existingPickingCandidate(existingPickingCandidate).includedRows(includedRows).build();
    } else {
        throw new AdempiereException("Unknown " + pickFrom);
    }
}


public ProductsToPickRow createRowsFromPickingOrder(QtyCalculationsBOM pickingOrderBOM,AllocablePackageable finishedGoodPackageable){
    final List<ProductsToPickRow> bomLineRows = new ArrayList<>();
    for (final QtyCalculationsBOMLine bomLine : pickingOrderBOM.getLines()) {
        bomLineRows.addAll(createRowsFromPickingOrderBOMLine(bomLine, finishedGoodPackageable));
    }
    final Quantity qtyOfFinishedGoods = finishedGoodPackageable.getQtyToAllocate();
    finishedGoodPackageable.allocateQty(qtyOfFinishedGoods);
    return prepareRow_PickFromPickingOrder(finishedGoodPackageable).pickFromPickingOrderId(pickingOrderBOM.getOrderId()).qty(qtyOfFinishedGoods).includedRows(bomLineRows).build();
}


public void warmUpCacheForHuIds(Collection<HuId> huIds){
    // pre-load all HUs
    storages.warmUpCacheForHuIds(huIds);
    huReservationService.warmup(huIds);
}


public ProductsToPickRowBuilder prepareRow_PickFromHU(AllocablePackageable packageable){
    return prepareRow().rowType(ProductsToPickRowType.PICK_FROM_HU).productId(packageable.getProductId()).shipmentScheduleId(packageable.getShipmentScheduleId()).salesOrderLineId(packageable.getSalesOrderLineIdOrNull()).shipperId(packageable.getShipperId());
}


}