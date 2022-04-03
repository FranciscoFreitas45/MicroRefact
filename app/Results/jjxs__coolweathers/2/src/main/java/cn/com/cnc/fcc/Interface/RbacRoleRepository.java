package cn.com.cnc.fcc.Interface;
public interface RbacRoleRepository {

   public List<RbacRole> findByRoleCode(String s);
}