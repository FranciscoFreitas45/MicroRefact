import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.logistics.roomClean.dto.FloorVoRoomVoRoomCleanDTO;
import com.hmm.logistics.roomClean.entity.RoomCleanRecord;
import com.hmm.logistics.roomClean.repository.RoomCleanRecordRepository;
@Service
@Transactional
public class RoomCleanRecordService implements IRoomCleanRecordService,com.hmm.logistics.roomClean.service.IRoomCleanRecordService{

@Autowired
 private  RoomCleanRecordRepository roomCleanRecordRepository;


@Override
public RoomCleanRecord findById(Long id){
    // TODO Auto-generated method stub
    return roomCleanRecordRepository.findById(id).get();
}


@Override
public RoomCleanRecord save(RoomCleanRecord entity){
    // TODO Auto-generated method stub
    return roomCleanRecordRepository.save(entity);
}


@Override
public long count(){
    // TODO Auto-generated method stub
    return roomCleanRecordRepository.count();
}


@Override
public void deleteById(Long id){
    // TODO Auto-generated method stub
    roomCleanRecordRepository.deleteById(id);
}


@Override
public List<RoomCleanRecord> findByRoomId(Long roomId){
    // TODO Auto-generated method stub
    return roomCleanRecordRepository.findByRoomId(roomId);
}


@Override
public Page<RoomCleanRecord> findAll(Specification<RoomCleanRecord> spec,Pageable pageable){
    // TODO Auto-generated method stub
    return roomCleanRecordRepository.findAll(spec, pageable);
}


}