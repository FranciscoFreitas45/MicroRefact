package de.metas.ui.web.shipment_candidates_editor;
 import java.math.BigDecimal;
import javax.annotation.Nullable;
import de.metas.ui.web.window.datatypes.LookupValue;
import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class ShipmentCandidateRowUserChangeRequest {

@Nullable
 private BigDecimal qtyToDeliverUserEntered;

@Nullable
 private BigDecimal qtyToDeliverCatchOverride;

@Nullable
 private LookupValue asi;


}