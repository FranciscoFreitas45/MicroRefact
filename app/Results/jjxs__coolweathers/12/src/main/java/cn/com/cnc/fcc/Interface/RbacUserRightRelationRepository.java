package cn.com.cnc.fcc.Interface;
public interface RbacUserRightRelationRepository {

   public List<RbacUserRightRelation> findByRoleId(Integer id);
}