package de.metas.ui.web.picking.pickingslot;
 import java.util.List;
import java.util.stream.Collectors;
import org.adempiere.warehouse.api.IWarehouseDAO;
import org.compiere.util.DisplayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import de.metas.handlingunits.picking.PickingCandidatesQuery;
import de.metas.inoutcandidate.api.IShipmentScheduleEffectiveBL;
import de.metas.inoutcandidate.api.IShipmentSchedulePA;
import de.metas.inoutcandidate.model.I_M_ShipmentSchedule;
import de.metas.picking.api.IPickingSlotDAO;
import de.metas.picking.api.PickingSlotId;
import de.metas.picking.api.PickingSlotQuery;
import de.metas.picking.model.I_M_PickingSlot;
import de.metas.printing.esb.base.util.Check;
import de.metas.product.ProductId;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.picking.pickingslot.PickingHURowsRepository.PickedHUEditorRow;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class PickingSlotViewRepository {

 private  IWarehouseDAO warehousesRepo;

 private  PickingHURowsRepository pickingHUsRepo;

 private  Supplier<LookupDataSource> warehouseLookup;

 private  Supplier<LookupDataSource> bpartnerLookup;

 private  Supplier<LookupDataSource> bpartnerLocationLookup;


public PickingSlotRow createPickingSlotRowWithIncludedRows(I_M_PickingSlot pickingSlot,List<PickingSlotRow> pickedHuRows){
    return PickingSlotRow.fromPickingSlotBuilder().pickingSlotId(PickingSlotId.ofRepoId(pickingSlot.getM_PickingSlot_ID())).pickingSlotName(pickingSlot.getPickingSlot()).pickingSlotWarehouse(warehouseLookup.get().findById(pickingSlot.getM_Warehouse_ID())).pickingSlotLocatorId(warehousesRepo.getLocatorIdByRepoIdOrNull(pickingSlot.getM_Locator_ID())).pickingSlotBPartner(bpartnerLookup.get().findById(pickingSlot.getC_BPartner_ID())).pickingSlotBPLocation(bpartnerLocationLookup.get().findById(pickingSlot.getC_BPartner_Location_ID())).includedHURows(pickedHuRows).build();
}


public Supplier<LookupDataSource> createBPartnerLookup(){
    return Suppliers.memoize(() -> LookupDataSourceFactory.instance.getLookupDataSource(SqlLookupDescriptor.builder().setCtxTableName(null).setCtxColumnName(I_M_PickingSlot.COLUMNNAME_C_BPartner_ID).setDisplayType(DisplayType.Search).setWidgetType(DocumentFieldWidgetType.Lookup).buildForDefaultScope()));
}


public PickingSlotRow createPickedHURow(PickedHUEditorRow from,PickingSlotId pickingSlotId){
    final HUEditorRow huEditorRow = from.getHuEditorRow();
    final List<PickingSlotRow> includedHURows = huEditorRow.getIncludedRows().stream().map(includedHUEditorRow -> createPickedHURow(// create PickingSlotRows for the included HU rows which shall inherit the parent's processed flag
    new PickedHUEditorRow(includedHUEditorRow, from.isProcessed()), pickingSlotId)).collect(ImmutableList.toImmutableList());
    return PickingSlotRow.fromPickedHUBuilder().pickingSlotId(pickingSlotId).huId(huEditorRow.getHURowId().getHuId()).huStorageProductId(ProductId.toRepoId(huEditorRow.getHURowId().getStorageProductId())).huEditorRowType(huEditorRow.getType()).processed(from.isProcessed()).huCode(huEditorRow.getValue()).product(huEditorRow.getProduct()).packingInfo(huEditorRow.getPackingInfo()).qtyCU(huEditorRow.getQtyCU()).topLevelHU(huEditorRow.isTopLevel()).includedHURows(includedHURows).build();
}


public Supplier<LookupDataSource> createWarehouseLookup(){
    return Suppliers.memoize(() -> LookupDataSourceFactory.instance.getLookupDataSource(SqlLookupDescriptor.builder().setCtxTableName(null).setCtxColumnName(I_M_PickingSlot.COLUMNNAME_M_Warehouse_ID).setDisplayType(DisplayType.Search).setWidgetType(DocumentFieldWidgetType.Lookup).buildForDefaultScope()));
}


public List<PickingSlotRow> retrieveRows(PickingSlotRepoQuery query){
    Check.errorIf(query.getShipmentScheduleIds().isEmpty(), "Given query has no shipmentScheduleIds; query={}", query);
    // get M_HU_Source records that reference active HUs with their locator in this WH and not on the picking location
    final List<HUEditorRow> sourceHUEditorRows = pickingHUsRepo.retrieveSourceHUs(query);
    final List<PickingSlotRow> sourceHUPickingSlotRows = sourceHUEditorRows.stream().map(sourceHuEditorRow -> createSourceHURow(sourceHuEditorRow)).collect(Collectors.toList());
    // get the picking slot rows, including the rows the represent picked HUs
    final ImmutableList<PickingSlotRow> pickingSlotRows = retrievePickingSlotRows(query);
    return ImmutableList.copyOf(Iterables.concat(pickingSlotRows, sourceHUPickingSlotRows));
}


@VisibleForTesting
public ImmutableList<PickingSlotRow> retrievePickingSlotRows(PickingSlotRepoQuery query){
    final List<I_M_PickingSlot> pickingSlots = retrievePickingSlotsForShipmentSchedule(query);
    // retrieve picked HU rows (if any) to be displayed below there respective picking slots
    final ListMultimap<PickingSlotId, PickedHUEditorRow> huEditorRowsByPickingSlotId = pickingHUsRepo.retrievePickedHUsIndexedByPickingSlotId(toPickingCandidatesQuery(query));
    final ImmutableList<PickingSlotRow> result = // get stream of I_M_PickingSlot
    pickingSlots.stream().map(// create the actual PickingSlotRows
    pickingSlot -> createPickingSlotRow(pickingSlot, huEditorRowsByPickingSlotId)).collect(ImmutableList.toImmutableList());
    return result;
}


public Supplier<LookupDataSource> createBPartnerLocationLookup(){
    return Suppliers.memoize(() -> LookupDataSourceFactory.instance.getLookupDataSource(SqlLookupDescriptor.builder().setCtxTableName(null).setCtxColumnName(I_M_PickingSlot.COLUMNNAME_C_BPartner_Location_ID).setDisplayType(DisplayType.Search).setWidgetType(DocumentFieldWidgetType.Lookup).buildForDefaultScope()));
}


public List<I_M_PickingSlot> retrievePickingSlotsForShipmentSchedule(PickingSlotRepoQuery repoQuery){
    final I_M_ShipmentSchedule shipmentSchedule = Services.get(IShipmentSchedulePA.class).getById(repoQuery.getCurrentShipmentScheduleId());
    final IShipmentScheduleEffectiveBL shipmentScheduleEffectiveBL = Services.get(IShipmentScheduleEffectiveBL.class);
    final PickingSlotQuery pickingSlotQuery = PickingSlotQuery.builder().availableForBPartnerId(shipmentScheduleEffectiveBL.getBPartnerId(shipmentSchedule)).availableForBPartnerLocationId(shipmentScheduleEffectiveBL.getBPartnerLocationId(shipmentSchedule)).warehouseId(shipmentScheduleEffectiveBL.getWarehouseId(shipmentSchedule)).barcode(repoQuery.getPickingSlotBarcode()).build();
    final IPickingSlotDAO pickingSlotDAO = Services.get(IPickingSlotDAO.class);
    final List<I_M_PickingSlot> pickingSlots = pickingSlotDAO.retrievePickingSlots(pickingSlotQuery);
    return pickingSlots;
}


public PickingSlotRow createPickingSlotRow(I_M_PickingSlot pickingSlot,ListMultimap<PickingSlotId,PickedHUEditorRow> huEditorRowsByPickingSlotId){
    final List<PickingSlotRow> pickedHuRows = retrieveHuRowsToIncludeInPickingSlotRow(pickingSlot, huEditorRowsByPickingSlotId);
    return createPickingSlotRowWithIncludedRows(pickingSlot, pickedHuRows);
}


public PickingCandidatesQuery toPickingCandidatesQuery(PickingSlotRepoQuery query){
    return PickingCandidatesQuery.builder().shipmentScheduleIds(query.getShipmentScheduleIds()).onlyNotClosedOrNotRackSystem(query.isOnlyNotClosedOrNotRackSystem()).pickingSlotBarcode(query.getPickingSlotBarcode()).includeShippedHUs(false).build();
}


@VisibleForTesting
public PickingSlotRow createSourceHURow(HUEditorRow sourceHuEditorRow){
    final PickingSlotRow pickingSourceHuRow = PickingSlotRow.fromSourceHUBuilder().huId(sourceHuEditorRow.getHuId()).huEditorRowType(sourceHuEditorRow.getType()).huCode(sourceHuEditorRow.getValue()).product(sourceHuEditorRow.getProduct()).packingInfo(sourceHuEditorRow.getPackingInfo()).qtyCU(sourceHuEditorRow.getQtyCU()).topLevelHU(sourceHuEditorRow.isTopLevel()).build();
    return pickingSourceHuRow;
}


public List<PickingSlotRow> retrievePickingSlotsRows(PickingSlotQuery query){
    final List<I_M_PickingSlot> pickingSlots = Services.get(IPickingSlotDAO.class).retrievePickingSlots(query);
    final ListMultimap<PickingSlotId, PickedHUEditorRow> huEditorRowsByPickingSlotId = pickingHUsRepo.retrieveAllPickedHUsIndexedByPickingSlotId(pickingSlots);
    return // get stream of I_M_PickingSlot
    pickingSlots.stream().map(// create the actual PickingSlotRows
    pickingSlot -> createPickingSlotRow(pickingSlot, huEditorRowsByPickingSlotId)).collect(ImmutableList.toImmutableList());
}


public List<PickingSlotRow> retrieveHuRowsToIncludeInPickingSlotRow(I_M_PickingSlot pickingSlot,ListMultimap<PickingSlotId,PickedHUEditorRow> huEditorRowsByPickingSlotId){
    final PickingSlotId pickingSlotId = PickingSlotId.ofRepoId(pickingSlot.getM_PickingSlot_ID());
    // create picking slot rows for included/picked HUs
    final List<PickingSlotRow> pickedHuRows = huEditorRowsByPickingSlotId.get(pickingSlotId).stream().map(pickingSlotHuEditorRow -> createPickedHURow(pickingSlotHuEditorRow, pickingSlotId)).collect(ImmutableList.toImmutableList());
    return pickedHuRows;
}


}