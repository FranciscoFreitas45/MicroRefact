package de.metas.ui.web.quickinput.forecastline;
 import java.math.BigDecimal;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
public interface IForecastLineQuickInput {

 private String COLUMNNAME_M_Product_ID;

 private String COLUMNNAME_M_HU_PI_Item_Product_ID;

 private String COLUMNNAME_Qty;


public IntegerLookupValue getM_Product_ID()
;

public I_M_HU_PI_Item_Product getM_HU_PI_Item_Product()
;

public void setM_HU_PI_Item_Product(I_M_HU_PI_Item_Product huPIItemProduct)
;

public BigDecimal getQty()
;

public void setM_HU_PI_Item_Product_ID(int M_HU_PI_Item_Product_ID)
;

public int getM_HU_PI_Item_Product_ID()
;

}