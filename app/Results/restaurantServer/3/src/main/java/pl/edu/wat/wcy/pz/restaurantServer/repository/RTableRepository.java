package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.RTable;
import javax.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Transactional
@Repository
public interface RTableRepository extends JpaRepository<RTable, Long>{


public List<RTable> findAllBySize(int size)
;

public boolean existsBySize(int size)
;

public boolean existsByNumber(int number)
;

@Modifying
@Query(value = "update rtable r set r.status = ?2 where r.rtable_id = ?1", nativeQuery = true)
public void setStatus(Long id,String status);

}