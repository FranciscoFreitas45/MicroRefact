package de.metas.ui.web.pporder.process;
 import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.allocation.transfer.HUTransformService;
import de.metas.handlingunits.allocation.transfer.HUTransformService.HUsToNewTUsRequest;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_Source_HU;
import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.handlingunits.storage.EmptyHUListener;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.pporder.util.WEBUI_PP_Order_ProcessHelper;
import de.metas.util.Services;
import de.metas.util.StringUtils;
public class WEBUI_PP_Order_M_Source_HU_IssueTuQty extends WEBUI_PP_Order_Templateimplements IProcessPrecondition{

@Param(parameterName = "QtyTU", mandatory = true)
 private  BigDecimal qtyTU;


@Override
public String doIt(){
    final PPOrderLineRow row = getSingleSelectedRow();
    final List<I_M_Source_HU> sourceHus = WEBUI_PP_Order_ProcessHelper.retrieveActiveSourceHus(row);
    if (sourceHus.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    final Map<Integer, I_M_Source_HU> huId2SourceHu = new HashMap<>();
    final ImmutableList<I_M_HU> husThatAreFlaggedAsSource = sourceHus.stream().peek(sourceHu -> huId2SourceHu.put(sourceHu.getM_HU_ID(), sourceHu)).map(I_M_Source_HU::getM_HU).collect(ImmutableList.toImmutableList());
    final HUsToNewTUsRequest request = HUsToNewTUsRequest.builder().sourceHUs(husThatAreFlaggedAsSource).qtyTU(qtyTU.intValue()).build();
    EmptyHUListener emptyHUListener = EmptyHUListener.doBeforeDestroyed(hu -> {
        if (huId2SourceHu.containsKey(hu.getM_HU_ID())) {
            SourceHUsService.get().snapshotSourceHU(huId2SourceHu.get(hu.getM_HU_ID()));
        }
    }, "Create snapshot of source-HU before it is destroyed");
    final List<I_M_HU> extractedTUs = HUTransformService.builder().emptyHUListener(emptyHUListener).build().husToNewTUs(request);
    final PPOrderLinesView ppOrderView = getView();
    final PPOrderId ppOrderId = ppOrderView.getPpOrderId();
    Services.get(IHUPPOrderBL.class).createIssueProducer(ppOrderId).createIssues(extractedTUs);
    getView().invalidateAll();
    ppOrderView.invalidateAll();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final PPOrderLineRow singleSelectedRow = getSingleSelectedRow();
    if (singleSelectedRow.isProcessed()) {
        final String internalReason = StringUtils.formatMessage("The selected row is already processed; row={}", singleSelectedRow);
        return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);
    }
    return WEBUI_PP_Order_ProcessHelper.checkIssueSourceDefaultPreconditionsApplicable(singleSelectedRow);
}


}