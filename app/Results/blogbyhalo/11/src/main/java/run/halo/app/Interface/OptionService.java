package run.halo.app.Interface;
public interface OptionService {

   public T getByPropertyOrDefault(PropertyEnum property,Class<T> propertyType);
   public Optional<T> getByProperty(PropertyEnum property,Class<T> propertyType);
}