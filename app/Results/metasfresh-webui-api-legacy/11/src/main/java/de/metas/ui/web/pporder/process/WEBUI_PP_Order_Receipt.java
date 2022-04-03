package de.metas.ui.web.pporder.process;
 import java.math.BigDecimal;
import org.adempiere.exceptions.AdempiereException;
import org.eevolution.api.IPPOrderDAO;
import org.eevolution.model.I_PP_Order_BOMLine;
import de.metas.handlingunits.impl.IDocumentLUTUConfigurationManager;
import de.metas.handlingunits.model.I_M_HU_LUTU_Configuration;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.handlingunits.model.I_PP_Order;
import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.handlingunits.pporder.api.IPPOrderReceiptHUProducer;
import de.metas.material.planning.pporder.IPPOrderBOMDAO;
import de.metas.material.planning.pporder.PPOrderBOMLineId;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.ui.web.pporder.PPOrderLineType;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.util.Services;
import lombok.NonNull;
public class WEBUI_PP_Order_Receipt extends WEBUI_PP_Order_Templateimplements IProcessDefaultParametersProvider,IProcessPrecondition{

 private  IHUPPOrderBL huPPOrderBL;

@Param(parameterName = PackingInfoProcessParams.PARAM_M_HU_PI_Item_Product_ID, mandatory = true)
 private  I_M_HU_PI_Item_Product p_M_HU_PI_Item_Product;

@Param(parameterName = PackingInfoProcessParams.PARAM_M_HU_PI_Item_ID, mandatory = false)
 private  I_M_HU_PI_Item p_M_HU_PI_Item;

@Param(parameterName = PackingInfoProcessParams.PARAM_QtyCU, mandatory = true)
 private  BigDecimal p_QtyCU;

@Param(parameterName = PackingInfoProcessParams.PARAM_QtyTU, mandatory = true)
 private  BigDecimal p_QtyTU;

@Param(parameterName = PackingInfoProcessParams.PARAM_QtyLU, mandatory = false)
 private  BigDecimal p_QtyLU;

 private  PackingInfoProcessParams _packingInfoParams;


public IPPOrderReceiptHUProducer newReceiptCandidatesProducer(){
    final PPOrderLineRow row = getSingleSelectedRow();
    final PPOrderLineType type = row.getType();
    if (type == PPOrderLineType.MainProduct) {
        final PPOrderId ppOrderId = row.getOrderId();
        return huPPOrderBL.receivingMainProduct(ppOrderId);
    } else if (type == PPOrderLineType.BOMLine_ByCoProduct) {
        final PPOrderBOMLineId ppOrderBOMLineId = row.getOrderBOMLineId();
        return huPPOrderBL.receivingByOrCoProduct(ppOrderBOMLineId);
    } else {
        throw new AdempiereException("Receiving is not allowed");
    }
}


@ProcessParamLookupValuesProvider(parameterName = PackingInfoProcessParams.PARAM_M_HU_PI_Item_ID, dependsOn = PackingInfoProcessParams.PARAM_M_HU_PI_Item_Product_ID, numericKey = true, lookupTableName = I_M_HU_PI_Item.Table_Name)
public LookupValuesList getM_HU_PI_Item_IDs(){
    return getPackingInfoParams().getM_HU_PI_Item_IDs(p_M_HU_PI_Item_Product);
}


public PackingInfoProcessParams getPackingInfoParams(){
    if (_packingInfoParams == null) {
        _packingInfoParams = PackingInfoProcessParams.builder().defaultLUTUConfigManager(createDefaultLUTUConfigManager(getSingleSelectedRow())).enforcePhysicalTU(// allow to to produce just the CU, without a TU etc..maybe later we'll add a sysconfig for this
        false).build();
    }
    // Update it from the user-selected process parameters
    if (p_M_HU_PI_Item_Product != null) {
        _packingInfoParams.setTU_HU_PI_Item_Product_ID(p_M_HU_PI_Item_Product.getM_HU_PI_Item_Product_ID());
    }
    if (// this parameter is not mandatory
    p_M_HU_PI_Item != null) {
        _packingInfoParams.setLuPiItemId(p_M_HU_PI_Item.getM_HU_PI_Item_ID());
    } else {
        _packingInfoParams.setLuPiItemId(0);
    }
    _packingInfoParams.setQtyLU(p_QtyLU);
    _packingInfoParams.setQtyTU(p_QtyTU);
    _packingInfoParams.setQtyCU(p_QtyCU);
    return _packingInfoParams;
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    return getPackingInfoParams().getParameterDefaultValue(parameter.getColumnName());
}


@Override
@RunOutOfTrx
public String doIt(){
    // Calculate and set the LU/TU config from packing info params and defaults
    final I_M_HU_LUTU_Configuration lutuConfig = getPackingInfoParams().createAndSaveNewLUTUConfig();
    newReceiptCandidatesProducer().packUsingLUTUConfiguration(lutuConfig).createDraftReceiptCandidatesAndPlanningHUs();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final PPOrderLinesView ppOrder = getView();
    if (!(ppOrder.isStatusPlanning() || ppOrder.isStatusReview())) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not planning or in review");
    }
    final PPOrderLineRow ppOrderLineRow = getSingleSelectedRow();
    if (!ppOrderLineRow.isReceipt()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("ppOrderLineRow is not a receipt line; ppOrderLineRow=" + ppOrderLineRow);
    }
    if (ppOrderLineRow.isProcessed()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("ppOrderLineRow is already processed; ppOrderLineRow=" + ppOrderLineRow);
    }
    // 
    // OK, Override caption with current packing info, if any
    final String packingInfo = getSingleSelectedRow().getPackingInfo();
    if (!Check.isEmpty(packingInfo, true)) {
        return ProcessPreconditionsResolution.accept().deriveWithCaptionOverride(packingInfo);
    }
    // 
    // OK
    return ProcessPreconditionsResolution.accept();
}


public IDocumentLUTUConfigurationManager createDefaultLUTUConfigManager(PPOrderLineRow row){
    final PPOrderLineType type = row.getType();
    if (type == PPOrderLineType.MainProduct) {
        final PPOrderId ppOrderId = row.getOrderId();
        final I_PP_Order ppOrder = Services.get(IPPOrderDAO.class).getById(ppOrderId, I_PP_Order.class);
        return huPPOrderBL.createReceiptLUTUConfigurationManager(ppOrder);
    } else if (type == PPOrderLineType.BOMLine_ByCoProduct) {
        final PPOrderBOMLineId ppOrderBOMLineId = row.getOrderBOMLineId();
        final I_PP_Order_BOMLine ppOrderBOMLine = Services.get(IPPOrderBOMDAO.class).getOrderBOMLineById(ppOrderBOMLineId);
        return huPPOrderBL.createReceiptLUTUConfigurationManager(ppOrderBOMLine);
    } else {
        throw new AdempiereException("Receiving is not allowed");
    }
}


@Override
public void postProcess(boolean success){
    // Invalidate the view because for sure we have changes
    final PPOrderLinesView ppOrderLinesView = getView();
    ppOrderLinesView.invalidateAll();
    getViewsRepo().notifyRecordChanged(I_PP_Order.Table_Name, ppOrderLinesView.getPpOrderId().getRepoId());
}


@ProcessParamLookupValuesProvider(parameterName = PackingInfoProcessParams.PARAM_M_HU_PI_Item_Product_ID, dependsOn = {}, numericKey = true, lookupTableName = I_M_HU_PI_Item_Product.Table_Name)
public LookupValuesList getM_HU_PI_Item_Products(){
    return getPackingInfoParams().getM_HU_PI_Item_Products();
}


}