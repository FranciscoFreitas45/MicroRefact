package cn.com.cnc.fcc.Interface;
public interface RbacUserRightRelationRepository {

   public Optional<RbacUserRightRelation> findByUserId(Integer userId);
}