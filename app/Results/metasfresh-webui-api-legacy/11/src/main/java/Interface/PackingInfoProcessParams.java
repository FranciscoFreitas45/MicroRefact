package Interface;
public interface PackingInfoProcessParams {

   public Object builder(Object Object);
   public Object defaultLUTUConfigManager(Object Object);
   public void setTU_HU_PI_Item_Product_ID(int tu_HU_PI_Item_Product_ID);
   public void setLuPiItemId(int lu_PI_Item_ID);
   public void setQtyLU(BigDecimal qtyLU);
   public void setQtyTU(BigDecimal qtyTU);
   public void setQtyCU(BigDecimal qtyCU);
}