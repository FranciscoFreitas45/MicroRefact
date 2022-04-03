package Interface;
public interface PricingConditionsViewFilters {

   public Collection<DocumentFilterDescriptor> getFilterDescriptors();
   public DocumentFilterDescriptorsProvider getFilterDescriptorsProvider();
   public DocumentFilterList extractFilters(CreateViewRequest request);
}