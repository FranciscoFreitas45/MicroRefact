public interface RbacUserRightRelationRepository {

   public List<RbacUserRightRelation> findByRoleId(Integer id);
}