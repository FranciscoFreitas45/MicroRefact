package de.metas.ui.web.pickingV2.packageable.process;
 import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockRepository;
import de.metas.inoutcandidate.lock.ShipmentScheduleLockType;
import de.metas.inoutcandidate.lock.ShipmentScheduleUnLockRequest;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pickingV2.packageable.PackageableRow;
public class PackageablesView_UnlockAll extends PackageablesViewBasedProcess{

@Autowired
 private  ShipmentScheduleLockRepository locksRepo;


@Override
public String doIt(){
    locksRepo.unlock(ShipmentScheduleUnLockRequest.builder().shipmentScheduleIds(streamLockedShipmentScheduleIds().collect(ImmutableSet.toImmutableSet())).lockType(ShipmentScheduleLockType.PICKING).lockedByAnyUser().build());
    return MSG_OK;
}


public boolean hasLockedShipmentScheduleIds(){
    return streamLockedShipmentScheduleIds().anyMatch(Predicates.alwaysTrue());
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!hasLockedShipmentScheduleIds()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no locked rows selected");
    }
    return ProcessPreconditionsResolution.accept();
}


public Stream<ShipmentScheduleId> streamLockedShipmentScheduleIds(){
    return streamSelectedRows().filter(PackageableRow::isLocked).flatMap(row -> row.getShipmentScheduleIds().stream()).distinct();
}


}