package Interface;
public interface UserRepository {

   public Page<User> findByOrgiAndDatastatus(String orgi,boolean b,Pageable pageRequest);
   public List<User> findAll(Specification<User> spec);
   public List<User> findByOrgiAndAgentAndDatastatus(String orgi,boolean agent,boolean status);
   public List<User> findByOrganInAndAgentAndDatastatus(List<String> organIdList,boolean b,boolean status);
   public Page<User> findByOrgidAndAgentAndDatastatusAndUsertype(String orgid,boolean agent,boolean datastatus,String type,Pageable pageRequest);
   public Object save(Object Object);
   public Object getOne(Object Object);
   public User findByUsernameAndDatastatus(String username,boolean datastatus);
   public User findByEmailAndDatastatus(String email,boolean datastatus);
   public User findByMobileAndDatastatus(String mobile,boolean datastatus);
}