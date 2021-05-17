public interface RbacUserRightRelationRepository {

   public Optional<RbacUserRightRelation> findByUserId(Integer userId);
}