package de.metas.ui.web.pickingV2.packageable.process;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.TrxRunnable2;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.PickingCandidateStatus;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockRepository;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockRequest;
import de.metas.inoutcandidate.lock.ShipmentScheduleUnLockRequest;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.process.AdProcessId;
import de.metas.process.IADPInstanceDAO;
import de.metas.process.PInstanceId;
import de.metas.process.PInstanceRequest;
import de.metas.process.ProcessInfo;
import de.metas.process.ProcessInfoParameter;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.report.client.ReportsClient;
import de.metas.report.server.OutputType;
import de.metas.report.server.ReportResult;
import de.metas.ui.web.pickingV2.packageable.PackageableRow;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRowsService;
import de.metas.util.Services;
import lombok.NonNull;
public class PackageablesView_PrintPicklist extends PackageablesViewBasedProcess{

 final  AdProcessId PickListPdf_AD_Process_ID;

@Autowired
 private  ProductsToPickRowsService productsToPickRowsService;

@Autowired
 private  PickingCandidateService pickingCandidateService;

@Autowired
 private  ShipmentScheduleLockRepository locksRepo;

 final  IADPInstanceDAO adPInstanceDAO;


public ProcessPreconditionsResolution acceptIfPickingCandidatesAreDraft(){
    final PackageableRow row = getSingleSelectedRow();
    // allow draft picking candidates
    final List<PickingCandidate> pickingCandidates = pickingCandidateService.getByShipmentScheduleIdsAndStatus(row.getShipmentScheduleIds(), PickingCandidateStatus.Draft);
    if (pickingCandidates.size() > 0) {
        return ProcessPreconditionsResolution.accept();
    }
    // allow if there is no picking candidate yet, this process will generate before printing
    final boolean existsPickingCandidates = pickingCandidateService.existsPickingCandidates(row.getShipmentScheduleIds());
    if (!existsPickingCandidates) {
        return ProcessPreconditionsResolution.accept();
    }
    return ProcessPreconditionsResolution.rejectWithInternalReason("no picking product to print");
}


public void createPickingCandidatesIfNeeded(PackageableRow row){
    final boolean existsPickingCandidates = pickingCandidateService.existsPickingCandidates(row.getShipmentScheduleIds());
    if (!existsPickingCandidates) {
        // run in a different transaction so that the report can access it
        trxManager.runInNewTrx(new TrxRunnable2() {

            @Override
            public void run(final String localTrxName) throws Exception {
                productsToPickRowsService.createPickingCandidates(row);
            }

            // Throw an explicit error in order to make sure that the user sees that something went wrong
            // mainly we might got some line that should not be in picking terminal
            @Override
            public boolean doCatch(final Throwable e) throws Throwable {
                throw AdempiereException.wrapIfNeeded(e).markUserNotified();
            }

            @Override
            public void doFinally() {
            // nothing
            }
        });
    }
}


@Override
public void doFinally(){
// nothing
}


public ReportResult printPicklist(PackageableRow row){
    final PInstanceRequest pinstanceRequest = createPInstanceRequest(row);
    final PInstanceId pinstanceId = adPInstanceDAO.createADPinstanceAndADPInstancePara(pinstanceRequest);
    final ProcessInfo jasperProcessInfo = ProcessInfo.builder().setCtx(getCtx()).setAD_Process_ID(PickListPdf_AD_Process_ID).setAD_PInstance(adPInstanceDAO.getById(pinstanceId)).setReportLanguage(getProcessInfo().getReportLanguage()).setJRDesiredOutputType(OutputType.PDF).build();
    final ReportsClient reportsClient = ReportsClient.get();
    return reportsClient.report(jasperProcessInfo);
}


public PInstanceRequest createPInstanceRequest(PackageableRow row){
    final String orderNo = row.getOrderDocumentNo();
    final List<ProcessInfoParameter> piParams = ImmutableList.of(ProcessInfoParameter.of(I_M_Packageable_V.COLUMNNAME_OrderDocumentNo, orderNo));
    final PInstanceRequest pinstanceRequest = PInstanceRequest.builder().processId(PickListPdf_AD_Process_ID).processParams(piParams).build();
    return pinstanceRequest;
}


@Override
public boolean doCatch(Throwable e){
    throw AdempiereException.wrapIfNeeded(e).markUserNotified();
}


@Override
public String doIt(){
    final PackageableRow row = getSingleSelectedRow();
    final ShipmentScheduleLockRequest lockRequest = createLockRequest(row);
    // the line needs to remain locked until the user explicitly unlocks it
    locksRepo.lock(lockRequest);
    try {
        createPickingCandidatesIfNeeded(row);
        // print
        final ReportResult pickList = printPicklist(row);
        // preview
        getResult().setReportData(pickList.getReportContent(), buildFilename(row), OutputType.PDF.getContentType());
        return MSG_OK;
    } catch (final Exception ex) {
        // if no exception is caught, then the records remain locked until unlocked by the used who performs the picking
        locksRepo.unlockNoFail(ShipmentScheduleUnLockRequest.of(lockRequest));
        throw AdempiereException.wrapIfNeeded(ex);
    }
}


public String buildFilename(PackageableRow row){
    final String customer = row.getCustomer().getDisplayName();
    final String docuemntNo = row.getOrderDocumentNo();
    return Joiner.on("_").skipNulls().join(docuemntNo, customer) + ".pdf";
}


@Override
public void run(String localTrxName){
    productsToPickRowsService.createPickingCandidates(row);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    return checkPreconditionsApplicable_SingleSelectedRow().and(this::acceptIfPickingCandidatesAreDraft);
}


}