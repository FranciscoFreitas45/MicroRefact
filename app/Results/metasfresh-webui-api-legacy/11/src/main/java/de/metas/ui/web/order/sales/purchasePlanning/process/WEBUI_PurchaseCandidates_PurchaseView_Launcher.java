package de.metas.ui.web.order.sales.purchasePlanning.process;
 import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import com.google.common.collect.ImmutableSet;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.purchasecandidate.model.I_C_PurchaseCandidate;
import de.metas.ui.web.order.sales.purchasePlanning.view.PurchaseCandidates2PurchaseViewFactory;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
public class WEBUI_PurchaseCandidates_PurchaseView_Launcher extends ViewBasedProcessTemplateimplements IProcessPrecondition{


@Override
public String doIt(){
    final Set<TableRecordReference> purchaseCandidateRefs = getSelectedRowIds().stream().map(DocumentId::toInt).map(recordId -> TableRecordReference.of(I_C_PurchaseCandidate.Table_Name, recordId)).collect(ImmutableSet.toImmutableSet());
    if (purchaseCandidateRefs.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    getResult().setRecordsToOpen(purchaseCandidateRefs, PurchaseCandidates2PurchaseViewFactory.WINDOW_ID_STRING);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


}