package de.metas.ui.web.picking.process;
 import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_DIVERGING_LOCATIONS;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_TOO_MANY_PACKAGEABLES_1P;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.util.Env;
import com.google.common.collect.ImmutableList;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.PickingConstants;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
public class WEBUI_Picking_Launcher extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  int MAX_ROWS_ALLOWED;


public long getSelectionSize(DocumentIdsSelection rowIds){
    if (rowIds.isAll()) {
        return getView().size();
    } else {
        return rowIds.size();
    }
}


@Override
public String doIt(){
    // repeat the verification from checkPreconditionsApplicable() just to be sure.
    // We had cases where the selected rows of checkPreconditionsApplicable() were not the selected rows of doIt()
    final ProcessPreconditionsResolution verifySelectedDocuments = verifySelectedDocuments();
    if (verifySelectedDocuments.isRejected()) {
        throw new AdempiereException(verifySelectedDocuments().getRejectReason().translate(Env.getAD_Language(getCtx())));
    }
    final DocumentIdsSelection selectedRowIds = getSelectedRootDocumentIds();
    final List<TableRecordReference> records = getView().streamByIds(selectedRowIds).flatMap(selectedRow -> selectedRow.getIncludedRows().stream()).map(IViewRow::getId).map(DocumentId::removeDocumentPrefixAndConvertToInt).map(recordId -> TableRecordReference.of(I_M_Packageable_V.Table_Name, recordId)).collect(ImmutableList.toImmutableList());
    if (records.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    getResult().setRecordsToOpen(records, PickingConstants.WINDOWID_PickingView.toInt());
    return MSG_OK;
}


public int getBPartnerLocationId(IViewRow row){
    return row.getFieldValueAsInt(I_M_Packageable_V.COLUMNNAME_C_BPartner_Location_ID, -1);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    return verifySelectedDocuments();
}


public ProcessPreconditionsResolution verifySelectedDocuments(){
    final DocumentIdsSelection selectedRowIds = getSelectedRootDocumentIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    final long selectionSize = getSelectionSize(selectedRowIds);
    if (selectionSize > MAX_ROWS_ALLOWED) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_TOO_MANY_PACKAGEABLES_1P, MAX_ROWS_ALLOWED));
    }
    // Make sure that they all have the same C_BPartner and location.
    if (selectionSize > 1) {
        final Set<Integer> bpartnerLocationIds = getView().streamByIds(selectedRowIds).flatMap(selectedRow -> selectedRow.getIncludedRows().stream()).map(this::getBPartnerLocationId).collect(Collectors.toSet());
        if (bpartnerLocationIds.size() > 1) {
            return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_DIVERGING_LOCATIONS));
        }
    }
    return ProcessPreconditionsResolution.accept();
}


public DocumentIdsSelection getSelectedRootDocumentIds(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isAll()) {
        return selectedRowIds;
    } else if (selectedRowIds.isEmpty()) {
        return selectedRowIds;
    } else {
        return selectedRowIds.stream().filter(DocumentId::isInt).collect(DocumentIdsSelection.toDocumentIdsSelection());
    }
}


}