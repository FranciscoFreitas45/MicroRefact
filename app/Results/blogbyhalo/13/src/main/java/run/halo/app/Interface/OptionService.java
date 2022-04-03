package run.halo.app.Interface;
public interface OptionService {

   public T getByPropertyOrDefault(PropertyEnum property,Class<T> propertyType);
   public Object getByPropertyOfNonNull(PropertyEnum property);
   public Object toString(Object Object);
}