import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.logistics.roomClean.entity.RoomClean;
import com.hmm.logistics.roomClean.entity.RoomCleanRecord;
// 扫描持久层
@Repository
public interface RoomCleanRecordRepository extends PagingAndSortingRepository<RoomCleanRecord, Long> {


@Query("FROM RoomCleanRecord rcr WHERE rcr.room.roomId=?1")
public List<RoomCleanRecord> findByRoomId(Long roomId)


}