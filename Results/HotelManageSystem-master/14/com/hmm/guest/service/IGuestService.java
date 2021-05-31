import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.guest.dto.GuestDto;
import com.hmm.guest.dto.GuestInfoDto;
import com.hmm.guest.entity.Guest;
import com.hmm.logistics.roomClean.entity.RoomClean;
public interface IGuestService {


public Guest findGuestByIdCard(String idCard)


public Page<GuestDto> findAllVipGuest(Pageable pageable)


public Iterable<Guest> findAllGuest()


public void save(Guest guest)


public Page<GuestDto> findAll(Pageable pageable)


public Page<GuestDto> findAllCheckInGuest(Pageable pageable)


public List<Guest> findGuestByRoomNo(String roomNo)


}