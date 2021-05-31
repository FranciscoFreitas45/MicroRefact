import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.hmm.guest.dto.GuestDto;
import com.hmm.guest.dto.GuestInfoDto;
import com.hmm.guest.entity.Guest;
import com.hmm.guest.repository.GuestRepository;
import com.hmm.guest.util.GuestState;
import com.hmm.logistics.roomClean.entity.RoomClean;
@Service
public class GuestService implements IGuestService,com.hmm.guest.service.IGuestService{

@Autowired
 private  GuestRepository guestRepository;


@Override
public Guest findGuestByIdCard(String idCard){
    return guestRepository.findGuestByIdCard(idCard);
}


@Override
public Page<GuestDto> findAllVipGuest(Pageable pageable){
    // TODO Auto-generated method stub
    return guestRepository.findVipGuestInfo(GuestState.MEMBER, GuestState.STARMEMBER, pageable);
}


@Override
public Iterable<Guest> findAllGuest(){
    Iterable<Guest> allGuest = guestRepository.findAll();
    if (allGuest == null) {
        return null;
    }
    return allGuest;
}


@Override
public void save(Guest guest){
    guestRepository.save(guest);
}


@Override
public Page<GuestDto> findAll(Pageable pageable){
    return guestRepository.findGuestInfo(pageable);
}


@Override
public Page<GuestDto> findAllCheckInGuest(Pageable pageable){
    // TODO Auto-generated method stub
    return guestRepository.findAllCheckInGuest(pageable);
}


@Override
public List<Guest> findGuestByRoomNo(String roomNo){
    // TODO Auto-generated method stub
    return guestRepository.findGuestByRoomNo(roomNo);
}


}