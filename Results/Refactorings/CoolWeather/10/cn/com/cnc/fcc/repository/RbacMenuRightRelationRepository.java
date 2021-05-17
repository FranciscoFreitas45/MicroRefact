import cn.com.cnc.fcc.domain.RbacMenuRightRelation;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@SuppressWarnings("unused")
@Repository
public interface RbacMenuRightRelationRepository extends JpaRepository<RbacMenuRightRelation, Long> {


@Query(value = "update RbacMenuRightRelation set delFlag = 1  where rightId= ?1")
@Modifying
public int updateByRightId(Integer rightId)


@Query(value = "delete from RbacMenuRightRelation where rightId= ?1")
@Modifying
public int deleteByRightId(Integer rightId)


}