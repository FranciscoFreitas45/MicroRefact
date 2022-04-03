package de.metas.ui.web.picking.pickingslot;
 import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Util.ArrayKey;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.picking.PickingConstants;
import de.metas.ui.web.picking.pickingslot.PickingSlotRepoQuery.PickingSlotRepoQueryBuilder;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_HUEditor_Launcher;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_M_Picking_Candidate_Process;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_M_Picking_Candidate_Unprocess;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_M_Source_HU_Delete;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_PickQtyToExistingHU;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_PickQtyToNewHU;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_RemoveHUFromPickingSlot;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_ReturnQtyToSourceHU;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_TU_Label;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = PickingConstants.WINDOWID_PickingSlotView_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class PickingSlotViewFactory implements IViewFactory{

@Autowired
 private  PickingSlotViewRepository pickingSlotRepo;

 private  CCache<ArrayKey,ViewLayout> viewLayoutCache;

 private  CCache<Integer,DocumentFilterDescriptorsProvider> filterDescriptorsProviderCache;


public List<RelatedProcessDescriptor> createAdditionalRelatedProcessDescriptors(){
    return ImmutableList.of(// allow to open the HU-editor for various picking related purposes
    createProcessDescriptorForPickingSlotView(WEBUI_Picking_HUEditor_Launcher.class), // fine-picking related processes
    createProcessDescriptorForPickingSlotView(WEBUI_Picking_PickQtyToNewHU.class), createProcessDescriptorForPickingSlotView(WEBUI_Picking_PickQtyToExistingHU.class), createProcessDescriptorForPickingSlotView(WEBUI_Picking_ReturnQtyToSourceHU.class), // note that WEBUI_Picking_M_Source_HU_Create is called from the HU-editor
    createProcessDescriptorForPickingSlotView(WEBUI_Picking_M_Source_HU_Delete.class), // complete-HU-picking related processes; note that the add to-slot-process is called from the HU-editor
    createProcessDescriptorForPickingSlotView(WEBUI_Picking_RemoveHUFromPickingSlot.class), // "picking-lifecycle" processes
    createProcessDescriptorForPickingSlotView(WEBUI_Picking_M_Picking_Candidate_Process.class), createProcessDescriptorForPickingSlotView(WEBUI_Picking_M_Picking_Candidate_Unprocess.class), // label
    createProcessDescriptorForPickingSlotView(WEBUI_Picking_TU_Label.class));
}


public PickingSlotRepoQuery createPickingSlotRowsQuery(DocumentFilterList filters,ShipmentScheduleId currentShipmentScheduleId,Set<ShipmentScheduleId> allShipmentScheduleIds){
    // 
    // setup the picking slot query and the rowsSupplier which uses the query to retrieve the PickingSlotView's rows.
    final PickingSlotRepoQueryBuilder queryBuilder = PickingSlotRepoQuery.builder().onlyNotClosedOrNotRackSystem(true).currentShipmentScheduleId(currentShipmentScheduleId);
    if (allShipmentScheduleIds == null || allShipmentScheduleIds.isEmpty()) {
        queryBuilder.shipmentScheduleId(currentShipmentScheduleId);
    } else {
        Check.errorUnless(allShipmentScheduleIds.contains(currentShipmentScheduleId), "The given allShipmentScheduleIds has to include the given request's shipmentScheduleId; shipmentScheduleId={}; allShipmentScheduleIds={}; filters={}", currentShipmentScheduleId, allShipmentScheduleIds, filters);
        queryBuilder.shipmentScheduleIds(allShipmentScheduleIds);
    }
    final String barcode = PickingSlotViewFilters.getPickingSlotBarcode(filters);
    if (!Check.isEmpty(barcode, true)) {
        queryBuilder.pickingSlotBarcode(barcode);
    }
    return queryBuilder.build();
}


public RelatedProcessDescriptor createProcessDescriptorForPickingSlotView(Class<?> processClass){
    final IADProcessDAO adProcessDAO = Services.get(IADProcessDAO.class);
    final AdProcessId processId = adProcessDAO.retrieveProcessIdByClassIfUnique(processClass);
    Preconditions.checkArgument(processId != null, "No AD_Process_ID found for %s", processClass);
    return RelatedProcessDescriptor.builder().processId(processId).displayPlace(DisplayPlace.ViewQuickActions).build();
}


public ViewLayout createViewLayout(WindowId windowId,JSONViewDataType viewDataType){
    if (!PickingConstants.WINDOWID_PickingSlotView.equals(windowId)) {
        throw new AdempiereException("windowId shall be " + PickingConstants.WINDOWID_PickingSlotView);
    }
    return ViewLayout.builder().setWindowId(windowId).setCaption("Picking slots").addElementsFromViewRowClass(PickingSlotRow.class, viewDataType).setHasTreeSupport(true).setTreeCollapsible(true).setTreeExpandedDepth(ViewLayout.TreeExpandedDepth_ExpandedFirstLevel).setFilters(getFilterDescriptorsProvider().getAll()).build();
}


public PickingSlotView createView(CreateViewRequest request,Set<ShipmentScheduleId> allShipmentScheduleIds){
    final DocumentFilterList filters = request.getFiltersUnwrapped(getFilterDescriptorsProvider());
    final ViewId pickingViewId = request.getParentViewId();
    final DocumentId pickingRowId = request.getParentRowId();
    final ViewId pickingSlotViewId = PickingSlotViewsIndexStorage.createViewId(pickingViewId, pickingRowId);
    final ShipmentScheduleId currentShipmentScheduleId = extractCurrentShipmentScheduleId(request);
    final PickingSlotRepoQuery query = createPickingSlotRowsQuery(filters, currentShipmentScheduleId, allShipmentScheduleIds);
    final Supplier<List<PickingSlotRow>> rowsSupplier = () -> pickingSlotRepo.retrieveRows(query);
    return PickingSlotView.builder().viewId(pickingSlotViewId).parentViewId(pickingViewId).parentRowId(pickingRowId).currentShipmentScheduleId(currentShipmentScheduleId).rowsSupplier(rowsSupplier).additionalRelatedProcessDescriptors(createAdditionalRelatedProcessDescriptors()).filters(filters).build();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId_NOTUSED){
    final ArrayKey cacheKey = ArrayKey.of(windowId, viewDataType);
    return viewLayoutCache.getOrLoad(cacheKey, () -> createViewLayout(windowId, viewDataType));
}


public ShipmentScheduleId extractCurrentShipmentScheduleId(CreateViewRequest request){
    final DocumentId pickingRowId = request.getParentRowId();
    // TODO make it more obvious/explicit
    return ShipmentScheduleId.ofRepoId(pickingRowId.toInt());
}


public DocumentFilterDescriptorsProvider getFilterDescriptorsProvider(){
    return filterDescriptorsProviderCache.getOrLoad(0, () -> PickingSlotViewFilters.createFilterDescriptorsProvider());
}


}