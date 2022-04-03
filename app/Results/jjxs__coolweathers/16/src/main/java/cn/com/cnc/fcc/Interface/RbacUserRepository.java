package cn.com.cnc.fcc.Interface;
public interface RbacUserRepository {

   public List<RbacUser> findAllNonExistDefault();
}