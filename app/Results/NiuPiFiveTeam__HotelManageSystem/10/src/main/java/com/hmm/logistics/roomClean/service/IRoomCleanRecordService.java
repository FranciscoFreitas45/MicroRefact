package com.hmm.logistics.roomClean.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.logistics.roomClean.dto.FloorVoRoomVoRoomCleanDTO;
import com.hmm.logistics.roomClean.entity.RoomCleanRecord;
public interface IRoomCleanRecordService {


public RoomCleanRecord findById(Long id)
;

public RoomCleanRecord save(RoomCleanRecord entity)
;

public long count()
;

public void deleteById(Long id)
;

public List<RoomCleanRecord> findByRoomId(Long roomId)
;

public Page<RoomCleanRecord> findAll(Specification<RoomCleanRecord> spec,Pageable pageable)
;

}