package Interface;
public interface UserRepository {

   public List<User> findAll(Specification<User> spec);
   public List<User> findByOrgi(String orgi);
   public Page<User> findByDatastatusAndOrgi(boolean datastatus,String orgi,Pageable paramPageable);
   public Page<User> findByDatastatusAndOrgiAndUsernameLike(boolean datastatus,String orgi,String username,Pageable paramPageable);
   public Page<User> findByIdAndOrgi(String id,String orgi,Pageable paramPageable);
   public Object save(Object Object);
   public Object delete(Object Object);
   public List<User> findByOrgiAndAgentAndDatastatus(String orgi,boolean agent,boolean status);
   public List<User> findByOrganInAndAgentAndDatastatus(List<String> organIdList,boolean b,boolean status);
}