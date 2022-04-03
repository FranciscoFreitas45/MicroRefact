package de.metas.ui.web.handlingunits.process;
 import java.sql.Timestamp;
import java.util.List;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.util.Env;
import com.google.common.collect.ImmutableList;
import de.metas.edi.model.I_M_InOutLine;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUAssignmentBL;
import de.metas.handlingunits.IHUAssignmentDAO;
import de.metas.handlingunits.allocation.transfer.HUTransformService;
import de.metas.handlingunits.allocation.transfer.HUTransformService.HUsToNewTUsRequest;
import de.metas.handlingunits.inout.IHUInOutBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Services;
import de.metas.util.collections.CollectionUtils;
public class WEBUI_M_HU_ReturnTUsToVendor extends HUEditorProcessTemplateimplements IProcessPrecondition{

 private  IHUInOutBL huInOutBL;

 private  IHUAssignmentDAO huAssignmentDAO;

 private  IHUAssignmentBL huAssignmentBL;

@Param(parameterName = "QtyTU", mandatory = true)
 private  int p_QtyTU;

 private  I_M_HU topLevelHU;

 private  List<I_M_HU> tusToReturn;


@Override
public String doIt(){
    if (p_QtyTU <= 0) {
        throw new FillMandatoryException("QtyTU");
    }
    topLevelHU = getRecord(I_M_HU.class);
    // 
    // Get the original receipt line
    final List<I_M_InOutLine> receiptLines = huAssignmentDAO.retrieveModelsForHU(topLevelHU, I_M_InOutLine.class).stream().filter(// material receipt
    inoutLine -> !inoutLine.getM_InOut().isSOTrx()).collect(ImmutableList.toImmutableList());
    final I_M_InOutLine receiptLine = CollectionUtils.singleElement(receiptLines);
    // 
    // Split out the TUs we need to return
    final HUsToNewTUsRequest request = HUsToNewTUsRequest.forSourceHuAndQty(topLevelHU, p_QtyTU);
    tusToReturn = HUTransformService.newInstance().husToNewTUs(request);
    if (tusToReturn.size() != p_QtyTU) {
        throw new AdempiereException(WEBUI_HU_Constants.MSG_NotEnoughTUsFound, new Object[] { p_QtyTU, tusToReturn.size() });
    }
    // 
    // Assign the split TUs to the receipt line
    // FIXME: this is a workaround until https://github.com/metasfresh/metasfresh/issues/2392 is implemented
    tusToReturn.forEach(tu -> huAssignmentBL.createHUAssignmentBuilder().initializeAssignment(getCtx(), ITrx.TRXNAME_ThreadInherited).setM_LU_HU(null).setM_TU_HU(tu).setTopLevelHU(tu).setModel(receiptLine).build());
    // 
    // Actually create the vendor return
    final Timestamp movementDate = Env.getDate(getCtx());
    huInOutBL.createVendorReturnInOutForHUs(tusToReturn, movementDate);
    // we split something off the original HUs and therefore need to refresh our view
    invalidateView();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isHUEditorView()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
    }
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (!selectedRowIds.isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final DocumentId rowId = selectedRowIds.getSingleDocumentId();
    final HUEditorRow huRow = getView().getById(rowId);
    if (huRow.isLU()) {
        if (!huRow.hasIncludedTUs()) {
            return ProcessPreconditionsResolution.rejectWithInternalReason("no TUs");
        }
    } else if (huRow.isTU()) {
    // OK
    } else {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not a LU or TU");
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    // If something failed, don't bother
    if (!success) {
        return;
    }
    // Remove the TUs from our view (in case of any top level TUs)
    if (tusToReturn != null && !tusToReturn.isEmpty()) {
        final ImmutableList<HuId> collect = tusToReturn.stream().map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableList.toImmutableList());
        getView().removeHUIds(collect);
    }
    // Invalidate the view because the top level LU changed too
    getView().invalidateAll();
}


}