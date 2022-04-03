package run.halo.app.Interface;
public interface OptionService {

   public T getByPropertyOrDefault(PropertyEnum property,Class<T> propertyType);
   public String getSeoKeywords();
   public String getSeoDescription();
}