package de.metas.ui.web.handlingunits.process;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.context.annotation.Profile;
import de.metas.Profiles;
import de.metas.handlingunits.inout.ReceiptCorrectHUsProcessor;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.inoutcandidate.api.IReceiptScheduleBL;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.util.Services;
@Profile(Profiles.PROFILE_Webui)
public class WEBUI_M_ReceiptSchedule_SelectHUsToReverse extends ReceiptScheduleBasedProcess{


@Override
public String doIt(){
    final I_M_ReceiptSchedule receiptSchedule = getRecord(I_M_ReceiptSchedule.class);
    final List<I_M_HU> hus = ReceiptCorrectHUsProcessor.builder().setM_ReceiptSchedule(receiptSchedule).build().getAvailableHUsToReverse();
    if (hus.isEmpty()) {
        throw new AdempiereException("@NotFound@ @M_HU_ID@");
    }
    getResult().setRecordsToOpen(TableRecordReference.ofCollection(hus));
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    if (!context.isSingleSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    // Receipt schedule shall not be already closed
    final IReceiptScheduleBL receiptScheduleBL = Services.get(IReceiptScheduleBL.class);
    final I_M_ReceiptSchedule receiptSchedule = context.getSelectedModel(I_M_ReceiptSchedule.class);
    if (receiptScheduleBL.isClosed(receiptSchedule)) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("already closed");
    }
    // Receipt schedule shall not be about packing materials
    if (receiptSchedule.isPackagingMaterial()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not applying for packing materials");
    }
    if (receiptSchedule.getQtyMoved().signum() <= 0) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no receipts to be reversed");
    }
    return ProcessPreconditionsResolution.accept();
}


}