import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cn.com.cnc.fcc.domain.RbacUserRightRelation;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface RbacUserRightRelationRepository extends JpaRepository<RbacUserRightRelation, Long> {


public Optional<RbacUserRightRelation> findByUserId(Integer userId)


public List<RbacUserRightRelation> findByRoleId(Integer id)


@Query(value = "delete from RbacUserRightRelation where userId= ?1")
@Modifying
public int deleteByUserId(Integer userId)


}