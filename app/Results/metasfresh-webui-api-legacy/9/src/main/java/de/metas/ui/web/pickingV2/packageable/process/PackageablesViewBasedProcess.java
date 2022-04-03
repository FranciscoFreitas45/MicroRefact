package de.metas.ui.web.pickingV2.packageable.process;
 import java.util.stream.Stream;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockRequest;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockType;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pickingV2.packageable.PackageableRow;
import de.metas.ui.web.pickingV2.packageable.PackageableView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
public class PackageablesViewBasedProcess extends ViewBasedProcessTemplateimplements IProcessPrecondition{


@Override
public Stream<PackageableRow> streamSelectedRows(){
    return super.streamSelectedRows().map(PackageableRow::cast);
}


public ShipmentScheduleLockRequest createLockRequest(PackageableRow row){
    return ShipmentScheduleLockRequest.builder().shipmentScheduleIds(row.getShipmentScheduleIds()).lockType(ShipmentScheduleLockType.PICKING).lockedBy(getLoggedUserId()).build();
}


@Override
public PackageableRow getSingleSelectedRow(){
    return PackageableRow.cast(super.getSingleSelectedRow());
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable()


@Override
public PackageableView getView(){
    return PackageableView.cast(super.getView());
}


public ProcessPreconditionsResolution checkPreconditionsApplicable_SingleSelectedRow(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    } else if (!selectedRowIds.isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


}