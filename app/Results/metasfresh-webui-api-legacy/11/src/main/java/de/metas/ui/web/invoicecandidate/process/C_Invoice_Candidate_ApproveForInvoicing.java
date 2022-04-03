package de.metas.ui.web.invoicecandidate.process;
 import java.util.Set;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.IQueryUpdater;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.IQuery;
import org.springframework.context.annotation.Profile;
import de.metas.Profiles;
import de.metas.invoicecandidate.model.I_C_Invoice_Candidate;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Services;
@Profile(Profiles.PROFILE_Webui)
public class C_Invoice_Candidate_ApproveForInvoicing extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  int countUpdated;


@Override
@RunOutOfTrx
public String doIt(){
    final IQuery<I_C_Invoice_Candidate> query = retrieveInvoiceCandidatesToApproveQuery();
    // 
    // Fail if there is nothing to update
    final int countToUpdate = query.count();
    if (countToUpdate <= 0) {
        throw new AdempiereException("@NoSelection@");
    }
    // 
    // Update selected invoice candidates
    countUpdated = query.update(ic -> {
        ic.setApprovalForInvoicing(true);
        return IQueryUpdater.MODEL_UPDATED;
    });
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


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    // 
    // Notify frontend that the view shall be refreshed because we changed some candidates
    if (countUpdated > 0) {
        invalidateView();
    }
}


public IQuery<I_C_Invoice_Candidate> retrieveInvoiceCandidatesToApproveQuery(){
    final IQueryBuilder<I_C_Invoice_Candidate> queryBuilder = Services.get(IQueryBL.class).createQueryBuilder(I_C_Invoice_Candidate.class).filter(getProcessInfo().getQueryFilterOrElseFalse()).addOnlyActiveRecordsFilter().addNotEqualsFilter(I_C_Invoice_Candidate.COLUMN_Processed, // not processed
    true).addNotEqualsFilter(I_C_Invoice_Candidate.COLUMN_ApprovalForInvoicing, // not already approved
    true);
    // Only selected rows
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (!selectedRowIds.isAll()) {
        final Set<Integer> invoiceCandidateIds = selectedRowIds.toIntSet();
        if (invoiceCandidateIds.isEmpty()) {
            // shall not happen
            throw new AdempiereException("@NoSelection@");
        }
        queryBuilder.addInArrayFilter(I_C_Invoice_Candidate.COLUMN_C_Invoice_Candidate_ID, invoiceCandidateIds);
    }
    return queryBuilder.create();
}


}