package Interface;
public interface HUEditorRow {

   public boolean isHUPlanningReceiptOwnerPM();
   public I_M_HU getM_HU();
   public I_C_UOM getC_UOM();
   public boolean isCU();
   public boolean isTU();
   public boolean isLU();
   public ProductId getProductId();
}