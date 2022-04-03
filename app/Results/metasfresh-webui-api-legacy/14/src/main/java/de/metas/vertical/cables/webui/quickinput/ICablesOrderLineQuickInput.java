package de.metas.vertical.cables.webui.quickinput;
 import java.math.BigDecimal;
public interface ICablesOrderLineQuickInput {

 private String COLUMNNAME_Plug1_Product_ID;

 private String COLUMNNAME_Plug2_Product_ID;

 private String COLUMNNAME_Cable_Product_ID;

 private String COLUMNNAME_CableLength;

 private String COLUMNNAME_Qty;


public int getPlug1_Product_ID()
;

public int getPlug2_Product_ID()
;

public int getCable_Product_ID()
;

public BigDecimal getCableLength()
;

public BigDecimal getQty()
;

}