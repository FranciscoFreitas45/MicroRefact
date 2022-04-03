package run.halo.app.Interface;
public interface OptionService {

   public String getBlogBaseUrl();
   public T getByPropertyOrDefault(PropertyEnum property,Class<T> propertyType);
}