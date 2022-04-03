package de.metas.ui.web.handlingunits.process;
 import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.Adempiere;
import org.compiere.model.I_M_InOut;
import de.metas.handlingunits.empties.IHUEmptiesService;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.inoutcandidate.api.IReceiptScheduleBL;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessExecutionResult.RecordsToOpen.OpenTarget;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.time.SystemTime;
public class WEBUI_M_ReceiptSchedule_CreateEmptiesReturns_Base extends ReceiptScheduleBasedProcess{

 private  IHUEmptiesService huEmptiesService;

 private  IReceiptScheduleBL receiptScheduleBL;

 private  DocumentCollection documentsRepo;

 private  String _returnMovementType;

 private  int _targetWindowId;


public String getReturnMovementType(){
    return _returnMovementType;
}


public int createDraftEmptiesDocument(){
    final DocumentPath documentPath = DocumentPath.builder().setDocumentType(WindowId.of(getTargetWindowId())).setDocumentId(DocumentId.NEW_ID_STRING).allowNewDocumentId().build();
    final DocumentId documentId = documentsRepo.forDocumentWritable(documentPath, NullDocumentChangesCollector.instance, document -> {
        huEmptiesService.newReturnsInOutProducer(getCtx()).setMovementType(getReturnMovementType()).setMovementDate(SystemTime.asDayTimestamp()).fillReturnsInOutHeader(InterfaceWrapperHelper.create(document, I_M_InOut.class));
        return document.getDocumentId();
    });
    return documentId.toInt();
}


public int getTargetWindowId(){
    return _targetWindowId;
}


public int createDraftEmptiesInOutFromReceiptSchedule(I_M_ReceiptSchedule receiptSchedule){
    // 
    // Create a draft "empties inout" without any line;
    // Lines will be created manually by the user.
    final I_M_InOut emptiesInOut = huEmptiesService.newReturnsInOutProducer(getCtx()).setMovementType(getReturnMovementType()).setMovementDate(SystemTime.asDayTimestamp()).setC_BPartner(receiptScheduleBL.getC_BPartner_Effective(receiptSchedule)).setC_BPartner_Location(receiptScheduleBL.getC_BPartner_Location_Effective(receiptSchedule)).setM_Warehouse(receiptScheduleBL.getM_Warehouse_Effective(receiptSchedule)).setC_Order(receiptSchedule.getC_Order()).dontComplete().create();
    return emptiesInOut == null ? -1 : emptiesInOut.getM_InOut_ID();
}


@Override
public String doIt(){
    final I_M_ReceiptSchedule receiptSchedule = getProcessInfo().getRecordIfApplies(I_M_ReceiptSchedule.class, ITrx.TRXNAME_ThreadInherited).orElse(null);
    final int emptiesInOutId;
    if (receiptSchedule == null) {
        emptiesInOutId = createDraftEmptiesDocument();
    } else {
        emptiesInOutId = createDraftEmptiesInOutFromReceiptSchedule(receiptSchedule);
    }
    // 
    // Notify frontend that the empties document shall be opened in single document layout (not grid)
    if (emptiesInOutId > 0) {
        getResult().setRecordToOpen(TableRecordReference.of(I_M_InOut.Table_Name, emptiesInOutId), getTargetWindowId(), OpenTarget.SingleDocument);
    }
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    if (context.isNoSelection()) {
        return ProcessPreconditionsResolution.accept();
    }
    if (!context.isSingleSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


}