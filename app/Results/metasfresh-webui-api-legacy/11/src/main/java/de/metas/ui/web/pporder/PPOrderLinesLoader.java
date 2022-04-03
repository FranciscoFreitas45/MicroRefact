package de.metas.ui.web.pporder;
 import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.warehouse.WarehouseId;
import org.compiere.model.I_C_DocType;
import org.compiere.model.I_C_UOM;
import org.eevolution.api.BOMComponentType;
import org.eevolution.api.IPPOrderDAO;
import org.eevolution.api.PPOrderPlanningStatus;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import de.metas.document.DocTypeId;
import de.metas.document.IDocTypeDAO;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_M_HU_LUTU_Configuration;
import de.metas.handlingunits.model.I_PP_Order;
import de.metas.handlingunits.model.I_PP_Order_BOMLine;
import de.metas.handlingunits.model.I_PP_Order_Qty;
import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.handlingunits.pporder.api.IHUPPOrderQtyBL;
import de.metas.handlingunits.pporder.api.IHUPPOrderQtyDAO;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.handlingunits.sourcehu.SourceHUsService.MatchingSourceHusQuery;
import de.metas.i18n.IModelTranslationMap;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.material.planning.pporder.IPPOrderBOMBL;
import de.metas.material.planning.pporder.IPPOrderBOMDAO;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorRowAttributesProvider;
import de.metas.ui.web.handlingunits.HUEditorViewRepository;
import de.metas.ui.web.handlingunits.SqlHUEditorViewRepository;
import de.metas.ui.web.handlingunits.util.HUPackingInfoFormatter;
import de.metas.ui.web.handlingunits.util.HUPackingInfos;
import de.metas.ui.web.handlingunits.util.IHUPackingInfo;
import de.metas.ui.web.view.ASIViewRowAttributesProvider;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.lang.CoalesceUtil;
import lombok.Builder;
import lombok.NonNull;
public class PPOrderLinesLoader {

 private  IPPOrderBOMDAO ppOrderBOMDAO;

 private  IPPOrderBOMBL ppOrderBOMBL;

 private  IHUPPOrderQtyDAO ppOrderQtyDAO;

 private  HUEditorViewRepository huEditorRepo;

 private  ASIViewRowAttributesProvider asiAttributesProvider;


public Quantity computeQuantityForHuPPOrderLineRow(I_PP_Order_Qty ppOrderQty,HUEditorRow huEditorRow,HUEditorRow parentHUEditorRow){
    final Quantity quantity;
    if (huEditorRow.isHUStatusDestroyed()) {
        // Top level HU which was already destroyed (i.e. it was already issued & processed)
        // => get the Qty/UOM from PP_Order_Qty because on HU level, for sure it will be ZERO.
        if (parentHUEditorRow == null) {
            quantity = Quantity.of(ppOrderQty.getQty(), IHUPPOrderQtyBL.extractUOM(ppOrderQty));
        } else // Included HU which was already destroyed
        // => we don't know the Qty
        {
            quantity = Quantity.zero(huEditorRow.getC_UOM());
        }
    } else {
        if (huEditorRow.getQtyCU() == null && huEditorRow.getC_UOM() == null) {
            final I_C_UOM uom = IHUPPOrderQtyBL.extractUOM(ppOrderQty);
            quantity = Quantity.zero(uom);
        } else {
            quantity = Quantity.of(huEditorRow.getQtyCU(), huEditorRow.getC_UOM());
        }
    }
    return quantity;
}


public String extractPackingInfoString(I_M_HU_LUTU_Configuration lutuConfig){
    if (lutuConfig == null) {
        return null;
    }
    final IHUPackingInfo packingInfo = HUPackingInfos.of(lutuConfig);
    return HUPackingInfoFormatter.newInstance().setShowLU(false).format(packingInfo);
}


public String computePackingInfo(I_PP_Order_BOMLine ppOrderBOMLine){
    final IHUPPOrderBL huPPOrderBL = Services.get(IHUPPOrderBL.class);
    final I_M_HU_LUTU_Configuration lutuConfig = huPPOrderBL.createReceiptLUTUConfigurationManager(ppOrderBOMLine).getCreateLUTUConfiguration();
    return extractPackingInfoString(lutuConfig);
}


public PPOrderLineRow createForPPOrderQty(I_PP_Order_Qty ppOrderQty,boolean readonly){
    final HUEditorRow huEditorRow = huEditorRepo.retrieveForHUId(HuId.ofRepoId(ppOrderQty.getM_HU_ID()));
    final HUEditorRow parentHUViewRecord = null;
    return createForHUViewRecordRecursively(ppOrderQty, huEditorRow, parentHUViewRecord, readonly);
}


public boolean isReadOnly(I_PP_Order ppOrder){
    final PPOrderPlanningStatus ppOrder_planningStatus = PPOrderPlanningStatus.ofCode(ppOrder.getPlanningStatus());
    final boolean readonly = PPOrderPlanningStatus.COMPLETE.equals(ppOrder_planningStatus);
    return readonly;
}


public List<PPOrderLineRow> createRowsForBomLines(I_PP_Order ppOrder,ListMultimap<Integer,I_PP_Order_Qty> ppOrderQtysByBOMLineId){
    final // 
    Comparator<PPOrderLineRow> ppOrderBomLineRowSorter = // receipt lines first
    Comparator.<PPOrderLineRow>comparingInt(row -> row.isReceipt() ? 0 : 1).thenComparing(// BOM lines order
    row -> row.getOrderBOMLineId());
    final // 
    Function<? super I_PP_Order_BOMLine, ? extends PPOrderLineRow> ppOrderBomLineRowCreator = ppOrderBOMLine -> createRowForBOMLine(ppOrderBOMLine, isReadOnly(ppOrder), ppOrderQtysByBOMLineId.get(ppOrderBOMLine.getPP_Order_BOMLine_ID()));
    final PPOrderId ppOrderId = PPOrderId.ofRepoId(ppOrder.getPP_Order_ID());
    final ImmutableList<PPOrderLineRow> bomLineRows = ppOrderBOMDAO.retrieveOrderBOMLines(ppOrderId, I_PP_Order_BOMLine.class).stream().map(ppOrderBomLineRowCreator).sorted(ppOrderBomLineRowSorter).collect(ImmutableList.toImmutableList());
    return bomLineRows;
}


public ITranslatableString extractDescription(I_PP_Order ppOrder){
    return TranslatableStrings.join(" ", extractDocTypeName(ppOrder), ppOrder.getDocumentNo());
}


public ImmutableList<PPOrderLineRow> createIncludedRowsForPPOrderQtys(List<I_PP_Order_Qty> ppOrderQtys,boolean readOnly){
    final ImmutableList<PPOrderLineRow> includedRows = ppOrderQtys.stream().map(ppOrderQty -> createForPPOrderQty(ppOrderQty, readOnly)).collect(ImmutableList.toImmutableList());
    return includedRows;
}


public PPOrderLinesViewData retrieveData(PPOrderId ppOrderId){
    final I_PP_Order ppOrder = Services.get(IPPOrderDAO.class).getById(ppOrderId, I_PP_Order.class);
    final int mainProductBOMLineId = 0;
    final ListMultimap<Integer, I_PP_Order_Qty> ppOrderQtysByBOMLineId = ppOrderQtyDAO.streamOrderQtys(ppOrderId).collect(GuavaCollectors.toImmutableListMultimap(ppOrderQty -> CoalesceUtil.firstGreaterThanZero(ppOrderQty.getPP_Order_BOMLine_ID(), mainProductBOMLineId)));
    final ImmutableList.Builder<PPOrderLineRow> records = ImmutableList.builder();
    // Main product
    final PPOrderLineRow rowForMainProduct = createRowForMainProduct(ppOrder, ppOrderQtysByBOMLineId.get(mainProductBOMLineId));
    records.add(rowForMainProduct);
    // BOM lines
    final List<PPOrderLineRow> bomLineRows = createRowsForBomLines(ppOrder, ppOrderQtysByBOMLineId);
    records.addAll(bomLineRows);
    // Source HUs
    final WarehouseId warehouseId = WarehouseId.ofRepoId(ppOrder.getM_Warehouse_ID());
    final List<PPOrderLineRow> sourceHuRowsForIssueProducts = createRowsForIssueProductSourceHUs(warehouseId, bomLineRows);
    records.addAll(sourceHuRowsForIssueProducts);
    final PPOrderPlanningStatus planningStatus = PPOrderPlanningStatus.ofCode(ppOrder.getPlanningStatus());
    return new PPOrderLinesViewData(extractDescription(ppOrder), planningStatus, records.build());
}


public List<PPOrderLineRow> createRowsForIssueProductSourceHUs(WarehouseId warehouseId,List<PPOrderLineRow> bomLineRows){
    final ImmutableSet<ProductId> issueProductIds = bomLineRows.stream().filter(PPOrderLineRow::isIssue).map(PPOrderLineRow::getProductId).collect(ImmutableSet.toImmutableSet());
    final ImmutableList.Builder<PPOrderLineRow> result = ImmutableList.builder();
    final MatchingSourceHusQuery sourceHusQuery = MatchingSourceHusQuery.builder().productIds(issueProductIds).warehouseId(warehouseId).build();
    for (final HuId sourceHUId : SourceHUsService.get().retrieveMatchingSourceHUIds(sourceHusQuery)) {
        final HUEditorRow huEditorRow = huEditorRepo.retrieveForHUId(sourceHUId);
        result.add(createRowForSourceHU(huEditorRow));
    }
    return result.build();
}


public PPOrderLineRow createRowForSourceHU(HUEditorRow huEditorRow){
    final PPOrderLineRowId rowId = PPOrderLineRowId.ofSourceHU(huEditorRow.getId(), huEditorRow.getHuId());
    return PPOrderLineRow.builderForSourceHU().rowId(rowId).type(PPOrderLineType.ofHUEditorRowType(huEditorRow.getType())).huId(huEditorRow.getHuId()).attributesSupplier(huEditorRow.getAttributesSupplier().map(supplier -> supplier.changeRowId(rowId.toDocumentId())).orElse(null)).code(huEditorRow.getValue()).product(huEditorRow.getProduct()).packingInfo(huEditorRow.getPackingInfo()).uom(huEditorRow.getUOM()).qty(huEditorRow.getQtyCU()).huStatus(huEditorRow.getHUStatusDisplay()).topLevelHU(huEditorRow.isTopLevel()).build();
}


public PPOrderLinesLoaderBuilder builder(WindowId viewWindowId){
    return new PPOrderLinesLoaderBuilder().viewWindowId(viewWindowId);
}


public PPOrderLineRow createRowForBOMLine(I_PP_Order_BOMLine ppOrderBOMLine,boolean readOnly,List<I_PP_Order_Qty> ppOrderQtys){
    final PPOrderLineType lineType;
    final String packingInfo;
    final BigDecimal qtyPlan;
    final BOMComponentType componentType = BOMComponentType.ofCode(ppOrderBOMLine.getComponentType());
    if (componentType.isByOrCoProduct()) {
        lineType = PPOrderLineType.BOMLine_ByCoProduct;
        packingInfo = computePackingInfo(ppOrderBOMLine);
        qtyPlan = ppOrderBOMBL.adjustCoProductQty(ppOrderBOMLine.getQtyRequiered());
    } else {
        lineType = PPOrderLineType.BOMLine_Component;
        // we don't know the packing info for what will be issued.
        packingInfo = null;
        qtyPlan = ppOrderBOMLine.getQtyRequiered();
    }
    final ImmutableList<PPOrderLineRow> includedRows = createIncludedRowsForPPOrderQtys(ppOrderQtys, readOnly);
    return PPOrderLineRow.builderForPPOrderBomLine().ppOrderBomLine(ppOrderBOMLine).type(lineType).packingInfoOrNull(packingInfo).qtyPlan(qtyPlan).attributesProvider(asiAttributesProvider).processed(readOnly).includedRows(includedRows).build();
}


public ITranslatableString extractDocTypeName(I_PP_Order ppOrder){
    final DocTypeId docTypeId = DocTypeId.ofRepoIdOrNull(ppOrder.getC_DocType_ID());
    final I_C_DocType docType = docTypeId != null ? Services.get(IDocTypeDAO.class).getById(docTypeId) : null;
    if (docType != null) {
        final IModelTranslationMap docTypeTrlMap = InterfaceWrapperHelper.getModelTranslationMap(docType);
        return docTypeTrlMap.getColumnTrl(I_C_DocType.COLUMNNAME_Name, docType.getName());
    } else {
        return TranslatableStrings.empty();
    }
}


public PPOrderLineRow createForHUViewRecordRecursively(I_PP_Order_Qty ppOrderQty,HUEditorRow huEditorRow,HUEditorRow parentHUEditorRow,boolean readonly){
    final Quantity quantity = computeQuantityForHuPPOrderLineRow(ppOrderQty, huEditorRow, parentHUEditorRow);
    final ImmutableList<PPOrderLineRow> includedRows = huEditorRow.getIncludedRows().stream().map(includedHUEditorRow -> createForHUViewRecordRecursively(ppOrderQty, includedHUEditorRow, huEditorRow, readonly)).collect(ImmutableList.toImmutableList());
    final PPOrderLineRowId rowId = PPOrderLineRowId.ofIssuedOrReceivedHU(parentHUEditorRow != null ? parentHUEditorRow.getId() : null, huEditorRow.getHuId());
    return PPOrderLineRow.builderForIssuedOrReceivedHU().rowId(rowId).type(PPOrderLineType.ofHUEditorRowType(huEditorRow.getType())).ppOrderQty(ppOrderQty).processed(readonly || ppOrderQty.isProcessed()).attributesSupplier(huEditorRow.getAttributesSupplier().map(supplier -> supplier.changeRowId(rowId.toDocumentId())).orElse(null)).code(huEditorRow.getValue()).product(huEditorRow.getProduct()).packingInfo(huEditorRow.getPackingInfo()).topLevelHU(huEditorRow.isTopLevel()).huStatus(huEditorRow.getHUStatusDisplay()).quantity(quantity).includedRows(includedRows).build();
}


public PPOrderLineRow createRowForMainProduct(I_PP_Order ppOrder,List<I_PP_Order_Qty> ppOrderQtysforMainProduct){
    final boolean readOnly = isReadOnly(ppOrder);
    final String packingInfoOrNull = computePackingInfo(ppOrder);
    final ImmutableList<PPOrderLineRow> includedRows = createIncludedRowsForPPOrderQtys(ppOrderQtysforMainProduct, readOnly);
    return PPOrderLineRow.builderForPPOrder().ppOrder(ppOrder).packingInfoOrNull(packingInfoOrNull).processed(readOnly).attributesProvider(asiAttributesProvider).includedRows(includedRows).build();
}


}