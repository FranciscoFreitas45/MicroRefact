import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.logistics.roomClean.entity.FloorVoRoomVoRoomClean;
@Repository
public interface FloorVoRoomVoRoomCleanRepository extends PagingAndSortingRepository<FloorVoRoomVoRoomClean, Long> {


}