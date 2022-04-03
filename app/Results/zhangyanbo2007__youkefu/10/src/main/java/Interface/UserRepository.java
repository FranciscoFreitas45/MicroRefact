package Interface;
public interface UserRepository {

   public Page<User> findByDatastatusAndOrgiAndOrgid(boolean b,String orgi,String orgid,Pageable pageRequest);
}