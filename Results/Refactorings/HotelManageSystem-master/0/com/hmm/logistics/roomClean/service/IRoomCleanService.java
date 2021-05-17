import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.logistics.roomClean.entity.FloorVoRoomVoRoomClean;
import com.hmm.logistics.roomClean.entity.RoomClean;
public interface IRoomCleanService {


public Page<FloorVoRoomVoRoomClean> findAllFloorVoRoomVoRoomCleanDTO(Specification<FloorVoRoomVoRoomClean> spec,ExtjsPageRequest pageRequest)


public void dailyNecessary(String roomNo,String dailyTagData)


public void saveAllFloorVoRoomVoRoomCleanDTO()


public void set()


public void changeRoomState(String roomNo,String selectValue,String remark)


public RoomClean findById(Long id)


public RoomClean save(RoomClean entity)


public long count()


public void deleteById(Long id)


public RoomClean findByRoomId(Long roomId)


public Page<RoomClean> findAll(Specification<RoomClean> spec,Pageable pageable)


public void aloadRoomCleantoDTO(RoomClean roomClean)


}