package Interface;
public interface FormFilterRepository {

   public FormFilter findByIdAndOrgi(String id,String orgi);
   public Object save(Object Object);
}