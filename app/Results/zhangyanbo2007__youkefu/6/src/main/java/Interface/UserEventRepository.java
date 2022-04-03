package Interface;
public interface UserEventRepository {

   public List<Object> findByOrgiAndCreatetimeRange(String orgi,Date start,Date end);
}