package run.halo.app.Interface;
public interface OptionRepository {

   public Optional<Option> findByKey(String key);
   public Object save(Object Object);
}