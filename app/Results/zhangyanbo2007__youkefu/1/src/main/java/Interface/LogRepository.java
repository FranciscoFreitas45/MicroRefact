package Interface;
public interface LogRepository {

   public Page<Log> findByOrgi(String orgi,Pageable page);
   public Page<Log> findByOrgiAndLevels(String orgi,String levels,Pageable page);
   public Object findOne(Object Object);
}