package de.metas.ui.web.handlingunits.process;
 import org.springframework.context.annotation.Profile;
import de.metas.Profiles;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.report.HUReceiptScheduleReportExecutor;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
@Profile(Profiles.PROFILE_Webui)
public class WEBUI_M_ReceiptSchedule_RunMaterialReceiptJasper extends ReceiptScheduleBasedProcess{


@Override
public String doIt(){
    final I_M_ReceiptSchedule receiptSchedule = getRecord(I_M_ReceiptSchedule.class);
    HUReceiptScheduleReportExecutor.get(receiptSchedule).executeHUReport();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    if (context.isNoSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!context.isSingleSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final I_M_ReceiptSchedule receiptSchedule = context.getSelectedModel(I_M_ReceiptSchedule.class);
    // guard against null (might happen if the selected ID is not valid)
    if (receiptSchedule == null) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


}