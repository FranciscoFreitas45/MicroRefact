package de.metas.ui.web.pickingV2.packageable.process;
 import org.springframework.beans.factory.annotation.Autowired;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockRepository;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockType;
import de.metas.inoutcandidate.lock.ShipmentScheduleUnLockRequest;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pickingV2.packageable.PackageableRow;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
public class PackageablesView_UnlockFromLoggedUser extends PackageablesViewBasedProcess{

@Autowired
 private  ShipmentScheduleLockRepository locksRepo;


public ProcessPreconditionsResolution acceptIfSingleSelectedRow(){
    final DocumentIdsSelection rowIds = getSelectedRowIds();
    if (!rowIds.isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select a single row");
    } else {
        return ProcessPreconditionsResolution.accept();
    }
}


@Override
public String doIt(){
    final PackageableRow row = getSingleSelectedRow();
    locksRepo.unlock(ShipmentScheduleUnLockRequest.builder().shipmentScheduleIds(row.getShipmentScheduleIds()).lockType(ShipmentScheduleLockType.PICKING).lockedBy(getLoggedUserId()).build());
    return MSG_OK;
}


public ProcessPreconditionsResolution acceptIfLockedByCurrentUser(){
    final PackageableRow row = getSingleSelectedRow();
    if (!row.isLockedBy(getLoggedUserId())) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not locked by current user");
    } else {
        return ProcessPreconditionsResolution.accept();
    }
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    return acceptIfSingleSelectedRow().and(this::acceptIfLockedByCurrentUser);
}


}