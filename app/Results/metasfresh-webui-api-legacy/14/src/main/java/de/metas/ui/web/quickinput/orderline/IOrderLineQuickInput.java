package de.metas.ui.web.quickinput.orderline;
 import java.math.BigDecimal;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
public interface IOrderLineQuickInput {

 private String COLUMNNAME_M_Product_ID;

 private String COLUMNNAME_M_HU_PI_Item_Product_ID;

 private String COLUMNNAME_Qty;

 private String COLUMNNAME_ShipmentAllocation_BestBefore_Policy;


public IntegerLookupValue getM_Product_ID()
;

public String getShipmentAllocation_BestBefore_Policy()
;

public void setM_HU_PI_Item_Product(I_M_HU_PI_Item_Product huPIItemProduct)
;

public BigDecimal getQty()
;

public void setShipmentAllocation_BestBefore_Policy(String bestBeforePolicy)
;

public int getM_HU_PI_Item_Product_ID()
;

}