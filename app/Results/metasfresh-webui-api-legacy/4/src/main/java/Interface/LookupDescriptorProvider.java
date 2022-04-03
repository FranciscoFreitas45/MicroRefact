package Interface;
public interface LookupDescriptorProvider {

   public Optional<LookupDescriptor> provide();
   public Optional<LookupDescriptor> provideForFilter();
   public Optional<String> getTableName();
   public boolean isNumericKey();
   public Object orElse(Object Object);
}