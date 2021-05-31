import java.util.List;
import org.activiti.engine.identity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.department.entity.DeptDTO;
import com.hmm.finance.roomOrder.domain.RoomOrder;
import com.hmm.finance.roomOrder.domain.RoomOrderDTO;
public interface IRoomOrderService {


public List<RoomOrderDTO> findByRoomNo(String roomNo)


public void save2(String bookRoomNo,User user)


public void save(String[] dataArray,User user)


public Page<RoomOrderDTO> findAll(Specification<RoomOrder> whereClause,Pageable pageable)


}