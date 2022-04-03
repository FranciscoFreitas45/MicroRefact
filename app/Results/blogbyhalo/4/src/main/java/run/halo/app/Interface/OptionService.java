package run.halo.app.Interface;
public interface OptionService {

   public String getBlogBaseUrl();
   public Boolean isEnabledAbsolutePath();
   public T getEnumByPropertyOrDefault(PropertyEnum property,Class<T> valueType,T defaultValue);
}