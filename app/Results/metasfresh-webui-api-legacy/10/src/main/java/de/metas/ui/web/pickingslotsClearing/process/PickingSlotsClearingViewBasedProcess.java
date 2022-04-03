package de.metas.ui.web.pickingslotsClearing.process;
 import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.warehouse.LocatorId;
import org.adempiere.warehouse.api.IWarehouseDAO;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.IHUContext;
import de.metas.handlingunits.IHUContextFactory;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.allocation.IAllocationRequestBuilder;
import de.metas.handlingunits.allocation.impl.AllocationUtils;
import de.metas.handlingunits.allocation.transfer.impl.LUTUProducerDestination;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.model.I_M_Locator;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.handlingunits.model.X_M_HU_PI_Version;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.handlingunits.storage.IHUStorage;
import de.metas.product.ProductId;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotRowId;
import de.metas.ui.web.pickingslotsClearing.PickingSlotsClearingView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class PickingSlotsClearingViewBasedProcess extends ViewBasedProcessTemplate{

 protected  IHUContextFactory huContextFactory;


public HUEditorView getPackingHUsView(){
    return getChildView(HUEditorView.class);
}


public void invalidatePickingSlotsClearingView(){
    invalidateView(getPickingSlotsClearingView().getViewId());
}


public I_M_HU getSingleSelectedPickingSlotTopLevelHU(){
    final PickingSlotRow huRow = getSingleSelectedPickingSlotRow();
    Check.assume(huRow.isTopLevelHU(), "row {} shall be a top level HU", huRow);
    final I_M_HU fromHU = InterfaceWrapperHelper.load(huRow.getHuId(), I_M_HU.class);
    return fromHU;
}


public List<I_M_HU> getSelectedPickingSlotTopLevelHUs(){
    return getSelectedPickingSlotRows().stream().peek(huRow -> Check.assume(huRow.isTopLevelHU(), "row {} shall be a top level HU", huRow)).map(huRow -> huRow.getHuId()).distinct().map(huId -> InterfaceWrapperHelper.load(huId, I_M_HU.class)).collect(ImmutableList.toImmutableList());
}


public PickingSlotRow getSingleSelectedPickingSlotRow(){
    return PickingSlotRow.cast(super.getSingleSelectedRow());
}


public LUTUProducerDestination createNewHUProducer(PickingSlotRow pickingRow,I_M_HU_PI targetHUPI){
    final BPartnerId bpartnerId = BPartnerId.ofRepoIdOrNull(pickingRow.getBPartnerId());
    final int bpartnerLocationId = pickingRow.getBPartnerLocationId();
    final LocatorId locatorId = pickingRow.getPickingSlotLocatorId();
    final I_M_Locator locator = Services.get(IWarehouseDAO.class).getLocatorById(locatorId, I_M_Locator.class);
    if (!locator.isAfterPickingLocator()) {
        throw new AdempiereException("Picking slot's locator is not an after picking locator: " + locator.getValue());
    }
    final LUTUProducerDestination lutuProducer = new LUTUProducerDestination();
    lutuProducer.setBPartnerId(bpartnerId).setC_BPartner_Location_ID(bpartnerLocationId).setLocatorId(locatorId).setHUStatus(X_M_HU.HUSTATUS_Picked);
    final String targetHuType = Services.get(IHandlingUnitsBL.class).getHU_UnitType(targetHUPI);
    if (X_M_HU_PI_Version.HU_UNITTYPE_LoadLogistiqueUnit.equals(targetHuType)) {
        lutuProducer.setLUPI(targetHUPI);
    } else if (X_M_HU_PI_Version.HU_UNITTYPE_TransportUnit.equals(targetHuType)) {
        lutuProducer.setNoLU();
        lutuProducer.setTUPI(targetHUPI);
    }
    return lutuProducer;
}


public DocumentIdsSelection getSelectedPackingHUsRowIds(){
    return getChildViewSelectedRowIds();
}


public HUEditorRow getSingleSelectedPackingHUsRow(){
    final DocumentIdsSelection selectedRowIds = getSelectedPackingHUsRowIds();
    final DocumentId rowId = selectedRowIds.getSingleDocumentId();
    return getPackingHUsView().getById(rowId);
}


public boolean isSingleSelectedPackingHUsRow(){
    final DocumentIdsSelection selectedRowIds = getSelectedPackingHUsRowIds();
    return selectedRowIds.isSingleDocumentId();
}


public PickingSlotRow getRootRowForSelectedPickingSlotRows(){
    final Set<PickingSlotRowId> rootRowIds = getRootRowIdsForSelectedPickingSlotRows();
    Check.assumeNotEmpty(rootRowIds, "rootRowIds is not empty");
    if (rootRowIds.size() > 1) {
        throw new AdempiereException("Select rows from one picking slot");
    }
    final PickingSlotRowId rootRowId = rootRowIds.iterator().next();
    final PickingSlotsClearingView pickingSlotsClearingView = getPickingSlotsClearingView();
    return pickingSlotsClearingView.getById(rootRowId);
}


public Set<PickingSlotRowId> getRootRowIdsForSelectedPickingSlotRows(){
    final PickingSlotsClearingView pickingSlotsClearingView = getPickingSlotsClearingView();
    return getSelectedPickingSlotRows().stream().map(row -> pickingSlotsClearingView.getRootRowIdWhichIncludesRowId(row.getPickingSlotRowId())).collect(ImmutableSet.toImmutableSet());
}


public IAllocationRequestBuilder prepareUnloadRequest(I_M_HU fromHU,BigDecimal qtyCU){
    Check.assume(qtyCU.signum() > 0, "qtyCU > 0");
    final IHUContext huContext = huContextFactory.createMutableHUContext();
    final IHUStorage fromHUStorage = huContext.getHUStorageFactory().getStorage(fromHU);
    final ProductId productId = fromHUStorage.getSingleProductIdOrNull();
    if (productId == null) {
        throw new AdempiereException("Cannot determine the product to transfer from " + fromHU);
    }
    final IHUProductStorage productStorage = fromHUStorage.getProductStorage(productId);
    return AllocationUtils.createAllocationRequestBuilder().setHUContext(huContext).setProduct(productId).setQuantity(qtyCU, productStorage.getC_UOM()).setDateAsToday().setFromReferencedModel(null).setForceQtyAllocation(false);
}


public BigDecimal retrieveQtyCU(I_M_HU hu){
    final IHUContext huContext = huContextFactory.createMutableHUContext();
    final IHUStorage fromHUStorage = huContext.getHUStorageFactory().getStorage(hu);
    final ProductId productId = fromHUStorage.getSingleProductIdOrNull();
    if (productId == null) {
        return BigDecimal.ZERO;
    }
    final IHUProductStorage productStorage = fromHUStorage.getProductStorage(productId);
    return productStorage.getQty().toBigDecimal();
}


public PickingSlotsClearingView getPickingSlotsClearingView(){
    return getView(PickingSlotsClearingView.class);
}


public boolean isSingleSelectedPickingSlotRow(){
    return getSelectedRowIds().isSingleDocumentId();
}


public List<PickingSlotRow> getSelectedPickingSlotRows(){
    return streamSelectedRows().map(PickingSlotRow::cast).collect(ImmutableList.toImmutableList());
}


}