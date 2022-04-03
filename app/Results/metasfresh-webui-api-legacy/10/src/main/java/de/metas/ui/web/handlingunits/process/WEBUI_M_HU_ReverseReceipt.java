package de.metas.ui.web.handlingunits.process;
 import java.util.List;
import org.adempiere.ad.dao.ConstantQueryFilter;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.model.I_M_InOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import com.google.common.collect.ImmutableList;
import de.metas.Profiles;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.inout.ReceiptCorrectHUsProcessor;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.lang.RepoIdAwares;
@Profile(value = Profiles.PROFILE_Webui)
public class WEBUI_M_HU_ReverseReceipt extends WEBUI_M_HU_Receipt_Baseimplements IProcessPrecondition{

@Autowired
 private  IViewsRepository viewsRepo;

@Autowired
 private  DocumentCollection documentsCollection;


public List<HuId> retrieveHUsToReverse(){
    // gh #1955: prevent an OutOfMemoryError
    final IQueryFilter<I_M_HU> processFilter = getProcessInfo().getQueryFilterOrElse(ConstantQueryFilter.of(false));
    return Services.get(IQueryBL.class).createQueryBuilder(I_M_HU.class, this).filter(processFilter).addOnlyActiveRecordsFilter().create().listIds().stream().map(HuId::ofRepoId).collect(ImmutableList.toImmutableList());
}


@Override
@RunOutOfTrx
public String doIt(){
    final List<I_M_ReceiptSchedule> receiptSchedules = getM_ReceiptSchedules();
    final List<HuId> huIdsToReverse = retrieveHUsToReverse();
    boolean hasChanges = false;
    try {
        for (final I_M_ReceiptSchedule receiptSchedule : receiptSchedules) {
            final ReceiptCorrectHUsProcessor processor = ReceiptCorrectHUsProcessor.builder().setM_ReceiptSchedule(receiptSchedule).tolerateNoHUsFound().build();
            if (processor.isNoHUsFound()) {
                continue;
            }
            final List<Integer> asRepoIds = RepoIdAwares.asRepoIds(huIdsToReverse);
            final List<I_M_InOut> receiptsToReverse = processor.getReceiptsToReverseFromHUIds(asRepoIds);
            if (receiptsToReverse.isEmpty()) {
                continue;
            }
            processor.reverseReceipts(receiptsToReverse);
            hasChanges = true;
        }
    } finally {
        if (hasChanges) {
            // Reset the view's affected HUs
            getView().removeHUIdsAndInvalidate(huIdsToReverse);
            // Notify all active views that given receipt schedules were changed
            viewsRepo.notifyRecordsChanged(TableRecordReferenceSet.of(TableRecordReference.ofSet(receiptSchedules)));
        }
    }
    if (!hasChanges) {
        throw new AdempiereException("@NotFound@ @M_InOut_ID@");
    }
    return MSG_OK;
}


@Override
public HUEditorView getView(){
    return getView(HUEditorView.class);
}


@Override
public ProcessPreconditionsResolution rejectResolutionOrNull(HUEditorRow document){
    if (!document.isHUStatusActive()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("Only active HUs can be reversed");
    }
    final List<I_M_ReceiptSchedule> receiptSchedules = getM_ReceiptSchedules();
    if (receiptSchedules.isEmpty()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("Thre are no receipt schedules");
    }
    return null;
}


public List<I_M_ReceiptSchedule> getM_ReceiptSchedules(){
    return getView().getReferencingDocumentPaths().stream().map(referencingDocumentPath -> documentsCollection.getTableRecordReference(referencingDocumentPath).getModel(this, I_M_ReceiptSchedule.class)).collect(GuavaCollectors.toImmutableList());
}


}