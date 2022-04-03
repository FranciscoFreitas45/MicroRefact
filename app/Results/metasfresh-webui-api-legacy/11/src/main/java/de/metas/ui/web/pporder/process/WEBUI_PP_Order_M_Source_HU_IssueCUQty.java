package de.metas.ui.web.pporder.process;
 import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.adempiere.exceptions.AdempiereException;
import org.eevolution.model.I_PP_Order_BOMLine;
import org.eevolution.model.X_PP_Order_BOMLine;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.IMutableHUContext;
import de.metas.handlingunits.allocation.transfer.HUTransformService;
import de.metas.handlingunits.allocation.transfer.HUTransformService.HUsToNewCUsRequest;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_Source_HU;
import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.handlingunits.storage.EmptyHUListener;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.logging.LogManager;
import de.metas.material.planning.pporder.IPPOrderBOMBL;
import de.metas.material.planning.pporder.IPPOrderBOMDAO;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.quantity.Quantity;
import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.pporder.util.WEBUI_PP_Order_ProcessHelper;
import de.metas.util.Services;
public class WEBUI_PP_Order_M_Source_HU_IssueCUQty extends WEBUI_PP_Order_Templateimplements IProcessDefaultParametersProvider,IProcessPrecondition{

 private  Logger logger;

 private  IPPOrderBOMBL ppOrderBomBL;

 private  String PARAM_QtyCU;

 private  String PARAM_IsShowAllParams;

@Param(parameterName = PARAM_QtyCU, mandatory = false)
 private  BigDecimal qtyCU;


public BigDecimal computeQtyToIssue(PPOrderLineRow row){
    final I_PP_Order_BOMLine bomLine = Services.get(IPPOrderBOMDAO.class).getOrderBOMLineById(row.getOrderBOMLineId());
    final IMutableHUContext huContext = Services.get(IHandlingUnitsBL.class).createMutableHUContext(getCtx());
    final List<I_M_Source_HU> activeSourceHus = WEBUI_PP_Order_ProcessHelper.retrieveActiveSourceHus(row);
    final I_M_HU hu = activeSourceHus.stream().sorted(Comparator.comparing(I_M_Source_HU::getM_HU_ID)).map(I_M_Source_HU::getM_HU).findFirst().orElseThrow(() -> new AdempiereException("@NoSelection@"));
    final List<IHUProductStorage> productStorages = huContext.getHUStorageFactory().getStorage(hu).getProductStorages();
    final String issueMethod = row.getIssueMethod();
    if (X_PP_Order_BOMLine.ISSUEMETHOD_IssueOnlyForReceived.equals(issueMethod)) {
        final BigDecimal qtyLeftToIssue = row.getQtyPlan().subtract(row.getQty());
        if (qtyLeftToIssue.signum() <= 0) {
            return BigDecimal.ZERO;
        }
        if (row.isProcessed()) {
            final Quantity quantityToIssueForWhatWasReceived = ppOrderBomBL.computeQtyToIssueBasedOnFinishedGoodReceipt(bomLine, row.getUom());
            return qtyLeftToIssue.min(quantityToIssueForWhatWasReceived.toBigDecimal());
        } else {
            return qtyLeftToIssue;
        }
    } else {
        final BigDecimal sourceHuStorageQty = productStorages.get(0).getQty().toBigDecimal();
        return sourceHuStorageQty;
    }
}


public void issue(PPOrderLineRow row){
    final List<I_M_Source_HU> sourceHus = WEBUI_PP_Order_ProcessHelper.retrieveActiveSourceHus(row);
    if (sourceHus.isEmpty()) {
        new AdempiereException("@NoSelection@" + row).throwIfDeveloperModeOrLogWarningElse(logger);
        return;
    }
    final Map<Integer, I_M_Source_HU> huId2SourceHu = new HashMap<>();
    final ImmutableList<I_M_HU> husThatAreFlaggedAsSource = sourceHus.stream().peek(sourceHu -> huId2SourceHu.put(sourceHu.getM_HU_ID(), sourceHu)).sorted(Comparator.comparing(I_M_Source_HU::getM_HU_ID)).map(I_M_Source_HU::getM_HU).collect(ImmutableList.toImmutableList());
    final BigDecimal qty = isSingleSelectedRow() ? qtyCU : computeQtyToIssue(row);
    final HUsToNewCUsRequest request = HUsToNewCUsRequest.builder().sourceHUs(husThatAreFlaggedAsSource).productId(row.getProductId()).qtyCU(Quantity.of(qty, row.getUom())).build();
    final EmptyHUListener emptyHUListener = EmptyHUListener.doBeforeDestroyed(hu -> {
        if (huId2SourceHu.containsKey(hu.getM_HU_ID())) {
            SourceHUsService.get().snapshotSourceHU(huId2SourceHu.get(hu.getM_HU_ID()));
        }
    }, "Create snapshot of source-HU before it is destroyed");
    final List<I_M_HU> extractedCUs = HUTransformService.builder().emptyHUListener(emptyHUListener).build().husToNewCUs(request);
    final PPOrderLinesView ppOrderView = getView();
    final PPOrderId ppOrderId = ppOrderView.getPpOrderId();
    Services.get(IHUPPOrderBL.class).createIssueProducer(ppOrderId).considerIssueMethodForQtyToIssueCalculation(// issue exactly the CUs we split
    false).createIssues(extractedCUs);
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    if (PARAM_QtyCU.equals(parameter.getColumnName()) && isSingleSelectedRow()) {
        return computeQtyToIssue(getSingleSelectedRow());
    } else if (PARAM_IsShowAllParams.equals(parameter.getColumnName())) {
        return isSingleSelectedRow();
    } else {
        return DEFAULT_VALUE_NOTAVAILABLE;
    }
}


@Override
public String doIt(){
    streamPPOrderLineRows().forEach(row -> issue(row));
    getView().invalidateAll();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (isSingleSelectedRow()) {
        final PPOrderLineRow singleSelectedRow = getSingleSelectedRow();
        return WEBUI_PP_Order_ProcessHelper.checkIssueSourceDefaultPreconditionsApplicable(singleSelectedRow);
    } else {
        final boolean allMatch = streamPPOrderLineRows().allMatch(row -> WEBUI_PP_Order_ProcessHelper.checkIssueSourceDefaultPreconditionsApplicable(row).isAccepted());
        if (allMatch) {
            return ProcessPreconditionsResolution.accept();
        }
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
}


public boolean isSingleSelectedRow(){
    return getSelectedRowIds().isSingleDocumentId();
}


}