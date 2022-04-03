package Interface;
public interface UserEventRepository {

   public List<Object> findByOrgiAndCreatetimeRange(String orgi,Date start,Date end);
   public Page<UserHistory> findBySessionidAndOrgi(String sessionid,String orgi,Pageable page);
}