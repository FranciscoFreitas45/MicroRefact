package de.metas.ui.web.handlingunits.process;
 import java.util.List;
import org.springframework.context.annotation.Profile;
import com.google.common.collect.ImmutableList;
import de.metas.Profiles;
import de.metas.handlingunits.report.HUReportExecutor;
import de.metas.handlingunits.report.HUReportService;
import de.metas.handlingunits.report.HUToReport;
import de.metas.process.AdProcessId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
@Profile(Profiles.PROFILE_Webui)
public class WEBUI_M_HU_PrintReceiptLabel extends HUEditorProcessTemplateimplements IProcessPrecondition{

@Param(mandatory = true, parameterName = "Copies")
 private  int p_copies;


@Override
@RunOutOfTrx
public String doIt(){
    final HUReportService huReportService = HUReportService.get();
    final AdProcessId adProcessId = huReportService.retrievePrintReceiptLabelProcessIdOrNull();
    final HUToReport hu = getSingleSelectedRow().getAsHUToReport();
    final List<HUToReport> husToProcess = huReportService.getHUsToProcess(hu, adProcessId).stream().filter(// issue https://github.com/metasfresh/metasfresh/issues/3851
    HUToReport::isTopLevel).collect(ImmutableList.toImmutableList());
    HUReportExecutor.newInstance(getCtx()).windowNo(getProcessInfo().getWindowNo()).numberOfCopies(p_copies).executeHUReportAfterCommit(adProcessId, husToProcess);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isHUEditorView()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
    }
    final HUReportService huReportService = HUReportService.get();
    final AdProcessId adProcessId = huReportService.retrievePrintReceiptLabelProcessIdOrNull();
    if (adProcessId == null) {
        return ProcessPreconditionsResolution.reject("Receipt label process not configured via sysconfig " + HUReportService.SYSCONFIG_RECEIPT_LABEL_PROCESS_ID);
    }
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.reject("No (single) row selected");
    }
    final HUToReport hu = getSingleSelectedRow().getAsHUToReportOrNull();
    if (hu == null) {
        return ProcessPreconditionsResolution.reject("No (single) HU selected");
    }
    final List<HUToReport> husToProcess = huReportService.getHUsToProcess(hu, adProcessId);
    if (husToProcess.isEmpty()) {
        return ProcessPreconditionsResolution.reject("current HU's type does not match the receipt label process");
    }
    return ProcessPreconditionsResolution.accept();
}


}