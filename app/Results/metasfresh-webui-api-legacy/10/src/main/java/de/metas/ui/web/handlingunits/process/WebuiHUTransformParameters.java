package de.metas.ui.web.handlingunits.process;
 import java.math.BigDecimal;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.ui.web.handlingunits.process.WebuiHUTransformCommand.ActionType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class WebuiHUTransformParameters {

@NonNull
 private  ActionType actionType;

 private  I_M_HU_PI_Item_Product huPIItemProduct;

 private  I_M_HU_PI_Item huPIItem;

 private  I_M_HU tuHU;

 private  I_M_HU luHU;

 private  BigDecimal qtyCU;

 private  BigDecimal qtyTU;

 private  boolean huPlanningReceiptOwnerPM_LU;

 private  boolean huPlanningReceiptOwnerPM_TU;


}