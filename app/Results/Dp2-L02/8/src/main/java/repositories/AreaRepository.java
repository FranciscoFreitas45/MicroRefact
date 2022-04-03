package repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Area;
import domain.Brotherhood;
@Repository
public interface AreaRepository extends JpaRepository<Area, Integer>{


@Query("select a from Brotherhood a where a.area.id = ?1")
public List<Brotherhood> brotherhoodsOfAnArea(int areaId)
;

}