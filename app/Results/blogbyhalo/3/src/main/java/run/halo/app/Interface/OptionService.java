package run.halo.app.Interface;
public interface OptionService {

   public Object getByPropertyOfNonNull(PropertyEnum property);
   public Object toString(Object Object);
   public T getByPropertyOrDefault(PropertyEnum property,Class<T> propertyType);
}