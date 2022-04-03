package de.metas.ui.web.picking.husToPick.process;
 import de.metas.ui.web.handlingunits.WEBUI_HU_Constants.MSG_WEBUI_SELECT_ACTIVE_UNSELECTED_HU;
import java.math.BigDecimal;
import java.util.List;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.I_C_UOM;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUContext;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.allocation.IAllocationRequest;
import de.metas.handlingunits.allocation.impl.AllocationUtils;
import de.metas.handlingunits.allocation.impl.HUProducerDestination;
import de.metas.handlingunits.allocation.transfer.impl.HUSplitBuilderCoreEngine;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.picking.api.PickingConfigRepository;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessParametersCallout;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.picking.packageable.PackageableRow;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.collections.CollectionUtils;
import de.metas.util.time.SystemTime;
public class WEBUI_HUsToPick_PickCU extends HUsToPickViewBasedProcessimplements IProcessDefaultParametersProvider,IProcessPrecondition,IProcessParametersCallout{

@Autowired
 private  PickingCandidateService pickingCandidateService;

@Autowired
 private  PickingConfigRepository pickingConfigRepo;

 private  IHandlingUnitsDAO handlingUnitsDAO;

 private  IProductBL productBL;

 private  AdMessageKey MSG_InvalidProduct;

 private  String PARAM_M_Product_ID;

@Param(parameterName = PARAM_M_Product_ID, mandatory = false)
 private  int scannedProductId;

 private  String PARAM_QtyCU;

@Param(parameterName = PARAM_QtyCU, mandatory = true)
 private  BigDecimal qtyCU;

 private  ProductId _productIdToPack;

 private  boolean isAutoProcess;


public HuId performPickCU(){
    final HuId huIdToSplit = retrieveHUIdToSplit();
    final List<I_M_HU> splitHUs = HUSplitBuilderCoreEngine.builder().huToSplit(handlingUnitsDAO.getById(huIdToSplit)).requestProvider(this::createSplitAllocationRequest).destination(HUProducerDestination.ofVirtualPI()).build().withPropagateHUValues().withAllowPartialUnloads(// we allow partial loads and unloads so if a user enters a very large number, then that will just account to "all of it" and there will be no error
    true).performSplit();
    final I_M_HU splitCU = CollectionUtils.singleElement(splitHUs);
    final HuId splitCUId = HuId.ofRepoId(splitCU.getM_HU_ID());
    addHUIdToCurrentPickingSlot(splitCUId);
    return splitCUId;
}


public HuId retrieveHUIdToSplit(){
    return retrieveEligibleHUEditorRows().map(HUEditorRow::getHuId).collect(GuavaCollectors.singleElementOrThrow(() -> new AdempiereException("Only one HU shall be selected")));
}


@Override
public void onParameterChanged(String parameterName){
    if (PARAM_M_Product_ID.equals(parameterName)) {
        // Make sure user scanned the right product
        getProductId();
    }
}


public void autoProcessPicking(HuId splitCUId){
    final ShipmentScheduleId shipmentScheduleId = null;
    pickingCandidateService.processForHUIds(ImmutableSet.of(splitCUId), shipmentScheduleId);
}


public void pickCUs(){
    final HuId splitCUId = Services.get(ITrxManager.class).callInNewTrx(this::performPickCU);
    if (isAutoProcess) {
        autoProcessPicking(splitCUId);
    }
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    } else if (selectedRowIds.isMoreThanOneDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final HUEditorRow huRow = getSingleSelectedRow();
    if (!isEligible(huRow)) {
        final ITranslatableString reason = Services.get(IMsgBL.class).getTranslatableMsgText(MSG_WEBUI_SELECT_ACTIVE_UNSELECTED_HU);
        return ProcessPreconditionsResolution.reject(reason);
    }
    return ProcessPreconditionsResolution.accept();
}


public ProductId getProductIdToPack(){
    if (_productIdToPack == null) {
        final PackageableRow packageableRow = getSingleSelectedPackageableRow();
        _productIdToPack = packageableRow.getProductId();
    }
    return _productIdToPack;
}


public ProductId getProductId(){
    final ProductId productIdToPack = getProductIdToPack();
    // 
    // Assert scanned product is matching the shipment schedule product.
    // NOTE: scannedProductId might be not set, in case we deactivate the process parameter.
    if (scannedProductId > 0 && scannedProductId != productIdToPack.getRepoId()) {
        final IProductBL productBL = Services.get(IProductBL.class);
        final String scannedProductStr = productBL.getProductName(ProductId.ofRepoId(scannedProductId));
        final String expectedProductStr = productBL.getProductName(productIdToPack);
        throw new AdempiereException(MSG_InvalidProduct, new Object[] { scannedProductStr, expectedProductStr });
    }
    return productIdToPack;
}


public IAllocationRequest createSplitAllocationRequest(IHUContext huContext){
    final ProductId productId = getProductId();
    final I_C_UOM uom = productBL.getStockUOM(productId);
    return AllocationUtils.createAllocationRequestBuilder().setHUContext(huContext).setProduct(productId).setQuantity(getQtyCU(), uom).setDate(SystemTime.asZonedDateTime()).setFromReferencedModel(// N/A
    null).setForceQtyAllocation(false).create();
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    if (PARAM_M_Product_ID.equals(parameter.getColumnName())) {
        // For now, according to https://github.com/metasfresh/metasfresh-webui-api/issues/876,
        // we are setting the "scanned product" field to the right value.
        final PackageableRow packageableRow = getSingleSelectedPackageableRow();
        return packageableRow.getProductId();
    } else if (PARAM_QtyCU.equals(parameter.getColumnName())) {
        final PackageableRow packageableRow = getSingleSelectedPackageableRow();
        final BigDecimal qtyToDeliver = packageableRow.getQtyOrderedWithoutPicked().toBigDecimal();
        final HUEditorRow huRow = getSingleSelectedRow();
        final BigDecimal huQty = huRow.getQtyCU();
        return qtyToDeliver.min(huQty);
    } else {
        return DEFAULT_VALUE_NOTAVAILABLE;
    }
}


@Override
@RunOutOfTrx
public String doIt(){
    // #3778
    // Process the picking automatically in case it is configured this way.
    isAutoProcess = pickingConfigRepo.getPickingConfig().isAutoProcess();
    pickCUs();
    return MSG_OK;
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    if (!isAutoProcess) {
        invalidateAndGoBackToPickingSlotsView();
    } else {
        invalidatePickingSlotsView();
        invalidatePackablesView();
    }
}


public BigDecimal getQtyCU(){
    if (qtyCU == null || qtyCU.signum() <= 0) {
        throw new FillMandatoryException(PARAM_QtyCU);
    }
    return qtyCU;
}


}