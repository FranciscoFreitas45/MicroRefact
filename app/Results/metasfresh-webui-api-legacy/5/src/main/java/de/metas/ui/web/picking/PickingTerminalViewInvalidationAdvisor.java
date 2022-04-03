package de.metas.ui.web.picking;
 import java.util.HashSet;
import java.util.Set;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.model.I_M_Picking_Candidate;
import de.metas.handlingunits.picking.PickingCandidateId;
import de.metas.handlingunits.picking.PickingCandidateRepository;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.inoutcandidate.model.I_M_ShipmentSchedule;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewInvalidationAdvisor;
import de.metas.ui.web.view.SqlViewRowIdsOrderedSelectionFactory;
import de.metas.ui.web.view.descriptor.SqlViewKeyColumnNamesMap;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowId;
@Component
public class PickingTerminalViewInvalidationAdvisor implements IViewInvalidationAdvisor{

@Autowired
 private  PickingCandidateRepository pickingCandidateRepository;


public Set<ShipmentScheduleId> extractShipmentScheduleIds(TableRecordReferenceSet recordRefs){
    if (recordRefs.isEmpty()) {
        return ImmutableSet.of();
    }
    final Set<ShipmentScheduleId> shipmentScheduleIds = new HashSet<>();
    final Set<PickingCandidateId> pickingCandidateIds = new HashSet<>();
    for (TableRecordReference recordRef : recordRefs) {
        final String tableName = recordRef.getTableName();
        if (I_M_ShipmentSchedule.Table_Name.equals(tableName)) {
            shipmentScheduleIds.add(ShipmentScheduleId.ofRepoId(recordRef.getRecord_ID()));
        } else if (I_M_Picking_Candidate.Table_Name.equals(tableName)) {
            pickingCandidateIds.add(PickingCandidateId.ofRepoId(recordRef.getRecord_ID()));
        }
    }
    if (!pickingCandidateIds.isEmpty()) {
        shipmentScheduleIds.addAll(pickingCandidateRepository.getShipmentScheduleIdsByPickingCandidateIds(pickingCandidateIds));
    }
    return shipmentScheduleIds;
}


@Override
public Set<DocumentId> findAffectedRowIds(TableRecordReferenceSet recordRefs,IView view){
    final Set<ShipmentScheduleId> shipmentScheduleIds = extractShipmentScheduleIds(recordRefs);
    if (shipmentScheduleIds.isEmpty()) {
        return ImmutableSet.of();
    }
    final SqlViewKeyColumnNamesMap keyColumnNamesMap = SqlViewKeyColumnNamesMap.ofIntKeyField(I_M_Packageable_V.COLUMNNAME_M_ShipmentSchedule_ID);
    return SqlViewRowIdsOrderedSelectionFactory.retrieveRowIdsForLineIds(keyColumnNamesMap, view.getViewId(), ShipmentScheduleId.toIntSet(shipmentScheduleIds));
}


@Override
public WindowId getWindowId(){
    return PickingConstants.WINDOWID_PackageableView;
}


}