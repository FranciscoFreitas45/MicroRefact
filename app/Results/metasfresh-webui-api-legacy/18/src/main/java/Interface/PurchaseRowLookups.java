package Interface;
public interface PurchaseRowLookups {

   public LookupValue createProductLookupValue(ProductId productId,String productValue,String productName);
   public LookupValue createASILookupValue(AttributeSetInstanceId attributeSetInstanceId);
   public LookupValue createBPartnerLookupValue(BPartnerId bpartnerId);
   public String createUOMLookupValue(I_C_UOM uom);
   public PurchaseRowLookups newInstance();
}