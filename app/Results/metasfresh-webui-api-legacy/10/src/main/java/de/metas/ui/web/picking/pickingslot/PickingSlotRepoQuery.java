package de.metas.ui.web.picking.pickingslot;
 import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
public class PickingSlotRepoQuery {

 private ShipmentScheduleId currentShipmentScheduleId;

 private ImmutableSet<ShipmentScheduleId> shipmentScheduleIds;

 private boolean onlyNotClosedOrNotRackSystem;

 private String pickingSlotBarcode;


@VisibleForTesting
public PickingSlotRepoQuery of(ShipmentScheduleId shipmentScheduleId){
    return builder().currentShipmentScheduleId(shipmentScheduleId).shipmentScheduleId(shipmentScheduleId).build();
}


}