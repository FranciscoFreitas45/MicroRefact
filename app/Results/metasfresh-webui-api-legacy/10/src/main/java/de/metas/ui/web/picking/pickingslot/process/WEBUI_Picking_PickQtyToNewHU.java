package de.metas.ui.web.picking.pickingslot.process;
 import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_MISSING_SOURCE_HU;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_PICKING_SLOT;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Properties;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.warehouse.LocatorId;
import org.adempiere.warehouse.WarehouseId;
import org.adempiere.warehouse.api.IWarehouseBL;
import org.compiere.model.I_C_UOM;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUPIItemProductBL;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.hutransaction.IHUTrxBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.handlingunits.model.I_M_ShipmentSchedule;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.requests.AddQtyToHURequest;
import de.metas.handlingunits.report.HUReportService;
import de.metas.handlingunits.report.HUToReportWrapper;
import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.api.IShipmentScheduleEffectiveBL;
import de.metas.picking.api.PickingConfigRepository;
import de.metas.picking.api.PickingSlotId;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.handlingunits.util.WEBUI_ProcessHelper;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotViewFactory;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.util.Services;
import lombok.NonNull;
public class WEBUI_Picking_PickQtyToNewHU extends WEBUI_Picking_With_M_Source_HU_Baseimplements IProcessDefaultParametersProvider,IProcessPrecondition{

@Autowired
 private  PickingCandidateService pickingCandidateService;

@Autowired
 private  PickingConfigRepository pickingConfigRepo;

 private  String PARAM_M_HU_PI_Item_Product_ID;

@Param(parameterName = PARAM_M_HU_PI_Item_Product_ID, mandatory = true)
 private  I_M_HU_PI_Item_Product huPIItemProduct;

 private  String PARAM_QTY_CU;

@Param(parameterName = PARAM_QTY_CU, mandatory = true)
 private  BigDecimal qtyCU;


public Quantity getQtyToPack(){
    final I_C_UOM uom = getCurrentShipmentScheuduleUOM();
    return Quantity.of(qtyCU, uom);
}


public HuId createNewHuId(){
    final PickingSlotRow pickingSlotRow = getSingleSelectedRow();
    final LocatorId pickingSlotLocatorId = getPickingSlotLocatorId(pickingSlotRow);
    final I_M_HU hu = createTU(huPIItemProduct, pickingSlotLocatorId);
    return HuId.ofRepoId(hu.getM_HU_ID());
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    if (Objects.equals(PARAM_QTY_CU, parameter.getColumnName())) {
        return retrieveQtyToPick().toBigDecimal();
    } else if (Objects.equals(PARAM_M_HU_PI_Item_Product_ID, parameter.getColumnName())) {
        final I_M_ShipmentSchedule shipmentSchedule = getCurrentShipmentSchedule();
        final HUPIItemProductId piItemProductId = HUPIItemProductId.ofRepoIdOrNull(shipmentSchedule.getM_HU_PI_Item_Product_ID());
        if (piItemProductId == null) {
            return IProcessDefaultParametersProvider.DEFAULT_VALUE_NOTAVAILABLE;
        } else {
            final IHUPIItemProductBL hupiItemProductBL = Services.get(IHUPIItemProductBL.class);
            final ITranslatableString displayName = hupiItemProductBL.getDisplayName(piItemProductId);
            return IntegerLookupValue.of(piItemProductId, displayName);
        }
    } else {
        return DEFAULT_VALUE_NOTAVAILABLE;
    }
}


@Override
public String doIt(){
    final Quantity qtyToPack = getQtyToPack();
    if (qtyToPack.signum() <= 0) {
        throw new AdempiereException("@QtyCU@ > 0");
    }
    final HuId packToHuId = createNewHuId();
    pickFromSourceHUsAndPackTo(qtyToPack, packToHuId);
    printPickingLabel(packToHuId);
    // left side view
    invalidatePackablesView();
    // right side view
    invalidatePickingSlotsView();
    return MSG_OK;
}


public void printPickingLabel(HuId huId){
    final HUReportService huReportService = HUReportService.get();
    final IHandlingUnitsDAO handlingUnitsRepo = Services.get(IHandlingUnitsDAO.class);
    final I_M_HU hu = handlingUnitsRepo.getById(huId);
    final HUToReportWrapper huToReport = HUToReportWrapper.of(hu);
    final boolean onlyIfAutoPrintIsEnabled = true;
    huReportService.printPickingLabel(huToReport, onlyIfAutoPrintIsEnabled);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final PickingSlotRow pickingSlotRow = getSingleSelectedRow();
    if (!pickingSlotRow.isPickingSlotRow()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_SELECT_PICKING_SLOT));
    }
    if (!checkSourceHuPrecondition()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_MISSING_SOURCE_HU));
    }
    return ProcessPreconditionsResolution.accept();
}


public LocatorId getPickingSlotLocatorId(PickingSlotRow pickingSlotRow){
    if (pickingSlotRow.getPickingSlotLocatorId() != null) {
        return pickingSlotRow.getPickingSlotLocatorId();
    }
    final WarehouseId pickingSlotWarehouseId = pickingSlotRow.getPickingSlotWarehouseId();
    if (pickingSlotWarehouseId == null) {
        throw new AdempiereException("Picking slot with M_PickingSlot_ID=" + pickingSlotRow.getPickingSlotId() + " has no warehouse configured");
    }
    return Services.get(IWarehouseBL.class).getDefaultLocatorId(pickingSlotWarehouseId);
}


public I_M_HU createTU(I_M_HU_PI_Item_Product itemProduct,LocatorId locatorId){
    final IHandlingUnitsDAO handlingUnitsDAO = Services.get(IHandlingUnitsDAO.class);
    final IHUTrxBL huTrxBL = Services.get(IHUTrxBL.class);
    final I_M_HU_PI huPI = itemProduct.getM_HU_PI_Item().getM_HU_PI_Version().getM_HU_PI();
    return huTrxBL.createHUContextProcessorExecutor().call(huContext -> handlingUnitsDAO.createHUBuilder(huContext).setM_HU_Item_Parent(// no parent
    null).setM_HU_PI_Item_Product(itemProduct).setLocatorId(locatorId).setHUStatus(X_M_HU.HUSTATUS_Active).create(huPI));
}


public void pickFromSourceHUsAndPackTo(Quantity qtyToPack,HuId packToHuId){
    final boolean allowOverDelivery = pickingConfigRepo.getPickingConfig().isAllowOverDelivery();
    final PickingSlotRow pickingSlotRow = getSingleSelectedRow();
    final PickingSlotId pickingSlotId = pickingSlotRow.getPickingSlotId();
    pickingCandidateService.addQtyToHU(AddQtyToHURequest.builder().qtyToPack(qtyToPack).packToHuId(packToHuId).pickingSlotId(pickingSlotId).shipmentScheduleId(getCurrentShipmentScheduleId()).allowOverDelivery(allowOverDelivery).build());
}


@ProcessParamLookupValuesProvider(parameterName = PARAM_M_HU_PI_Item_Product_ID, dependsOn = {}, numericKey = true, lookupTableName = I_M_HU_PI_Item_Product.Table_Name)
public LookupValuesList getM_HU_PI_Item_Products(){
    final Properties ctx = getCtx();
    final I_M_ShipmentSchedule shipmentSchedule = getCurrentShipmentSchedule();
    final ProductId productId = ProductId.ofRepoId(shipmentSchedule.getM_Product_ID());
    return WEBUI_ProcessHelper.retrieveHUPIItemProducts(ctx, productId, Services.get(IShipmentScheduleEffectiveBL.class).getBPartnerId(shipmentSchedule), // includeVirtualItem = true..similar case as with production
    true);
}


}