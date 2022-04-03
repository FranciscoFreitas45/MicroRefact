package Interface;
public interface LookupDescriptor {

   public boolean isNumericKey();
   public T castOrNull(Class<T> lookupDescriptorClass);
}