import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.room.entity.Floor;
@Repository
public interface FloorRepository extends PagingAndSortingRepository<Floor, String> {


@Query("from Floor f where f.floorId = ?1")
public List<Floor> findChildNodes(Long parentId)


@Query("from Floor f order by f.floorId")
public List<Floor> findFloorNodes()


}