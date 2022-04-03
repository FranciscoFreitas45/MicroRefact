package de.metas.ui.web.pickingV2.packageable.process;
 import org.adempiere.exceptions.AdempiereException;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockRepository;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockRequest;
import de.metas.inoutcandidate.lock.ShipmentScheduleUnLockRequest;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pickingV2.packageable.PackageableRow;
import de.metas.ui.web.pickingV2.productsToPick.ProductsToPickView;
import de.metas.ui.web.pickingV2.productsToPick.ProductsToPickViewFactory;
public class PackageablesView_OpenProductsToPick extends PackageablesViewBasedProcess{

@Autowired
 private  ProductsToPickViewFactory productsToPickViewFactory;

@Autowired
 private  ShipmentScheduleLockRepository locksRepo;


public ProcessPreconditionsResolution acceptIfRowNotLockedByOtherUser(){
    final PackageableRow row = getSingleSelectedRow();
    if (row.isNotLocked() || row.isLockedBy(getLoggedUserId())) {
        return ProcessPreconditionsResolution.accept();
    } else {
        return ProcessPreconditionsResolution.rejectWithInternalReason("row is locked by other users");
    }
}


@Override
public String doIt(){
    final PackageableRow row = getSingleSelectedRow();
    final ShipmentScheduleLockRequest lockRequest = createLockRequest(row);
    locksRepo.lock(lockRequest);
    try {
        final ProductsToPickView productsToPickView = productsToPickViewFactory.createView(row);
        getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().target(ViewOpenTarget.ModalOverlay).viewId(productsToPickView.getViewId().toJson()).build());
        return MSG_OK;
    } catch (final Exception ex) {
        locksRepo.unlockNoFail(ShipmentScheduleUnLockRequest.of(lockRequest));
        throw AdempiereException.wrapIfNeeded(ex);
    }
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    return checkPreconditionsApplicable_SingleSelectedRow().and(this::acceptIfRowNotLockedByOtherUser);
}


}