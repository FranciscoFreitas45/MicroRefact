package de.metas.ui.web.handlingunits.process;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.allocation.transfer.HUTransformService;
import de.metas.handlingunits.allocation.transfer.HUTransformService.HUsToNewTUsRequest;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.DocumentCollection;
public class WEBUI_M_HU_MoveTUsToDirectWarehouse extends HUEditorProcessTemplateimplements IProcessPrecondition{

@Autowired
 private  DocumentCollection documentsCollection;

@Param(parameterName = "QtyTU")
 private  int p_QtyTU;


@Override
public String doIt(){
    if (p_QtyTU <= 0) {
        throw new FillMandatoryException("QtyTU");
    }
    final I_M_HU topLevelHU = getRecord(I_M_HU.class);
    final HUsToNewTUsRequest request = HUsToNewTUsRequest.forSourceHuAndQty(topLevelHU, p_QtyTU);
    final List<I_M_HU> tus = HUTransformService.newInstance().husToNewTUs(request);
    if (tus.size() != p_QtyTU) {
        throw new AdempiereException(WEBUI_HU_Constants.MSG_NotEnoughTUsFound, new Object[] { p_QtyTU, tus.size() });
    }
    HUMoveToDirectWarehouseService.newInstance().setDocumentsCollection(documentsCollection).setHUView(getView()).setFailOnFirstError(true).setLoggable(this).move(tus.iterator());
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
    if (!huRow.isHUStatusActive()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("HUStatus is not Active");
    }
    return ProcessPreconditionsResolution.accept();
}


}