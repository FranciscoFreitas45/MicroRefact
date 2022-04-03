package Interface;
public interface PricingConditionsRowLookups {

   public PricingConditionsRowLookups newInstance();
   public String getTemporaryPriceConditionsColor();
   public LookupValue lookupProduct(ProductId productId);
   public LookupValue lookupPaymentTerm(PaymentTermId paymentTermId);
   public LookupValue lookupPriceType(PriceSpecificationType priceType);
   public LookupValue lookupUser(UserId userId);
   public LookupValuesList getFieldTypeahead(String fieldName,String query);
   public LookupValuesList getFieldDropdown(String fieldName);
   public LookupValue lookupBPartner(BPartnerId bpartnerId);
}