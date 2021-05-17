import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.guest.dto.GuestDto;
import com.hmm.guest.entity.Guest;
import com.hmm.guest.util.GuestState;
import com.hmm.logistics.roomClean.entity.RoomClean;
@Repository
public interface GuestRepository extends JpaSpecificationExecutor<Guest> {


@Query("from Guest g where g.idCard = ?1 ")
public Guest findGuestByIdCard(String idCard)


@Query("select new com.hmm.guest.dto.GuestDto(g.realName,g.idCard,g.phone,g.gender,g.state)" + " from Guest g where g.state in(?1,?2)")
public Page<GuestDto> findVipGuestInfo(GuestState guestState,GuestState guestState1,Pageable pageable)


@Query("select new com.hmm.guest.dto.GuestDto(g.realName,g.idCard,g.phone,g.gender,g.state)" + " from Guest g where g.room is not null")
public Page<GuestDto> findAllCheckInGuest(Pageable pageable)


@Query("from Guest g where g.room.roomNo = ?1 ")
public List<Guest> findGuestByRoomNo(String roomNo)


@Query("select new com.hmm.guest.dto.GuestDto(g.realName,g.idCard,g.phone,g.gender,g.state)" + " from Guest g where g.room is null")
public Page<GuestDto> findGuestInfo(Pageable pageable)


}