package Interface;
public interface BlackListRepository {

   public BlackEntity findByUseridAndOrgi(String userid,String orgi);
   public Object save(Object Object);
   public Page<BlackEntity> findByEndtimeLessThan(Date endtime,Pageable page);
   public Object delete(Object Object);
}