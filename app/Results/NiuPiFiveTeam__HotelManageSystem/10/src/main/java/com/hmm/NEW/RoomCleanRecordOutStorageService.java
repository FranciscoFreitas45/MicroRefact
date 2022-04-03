package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.logistics.roomClean.repository.RoomCleanRecordRepository;
import com.hmm.logistics.roomClean.entity.RoomCleanRecord;
@Service
public class RoomCleanRecordOutStorageService {

@Autowired
 private RoomCleanRecordRepository roomcleanrecordrepository;


public void setRoomCleanRecord(Long idOU5W,RoomCleanRecord roomCleanRecord){
roomcleanrecordrepository.setRoomCleanRecord(idOU5W,roomCleanRecord);
}


public RoomCleanRecord getRoomCleanRecord(Long idOU5W){
return roomcleanrecordrepository.getRoomCleanRecord(idOU5W);
}


}