package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.repository.LiveRoomRepository;
import org.live.live.entity.LiveRoom;
@Service
public class LiveRoomReportService {

@Autowired
 private LiveRoomRepository liveroomrepository;


public LiveRoom getLiveRoom(String idWHGX){
return liveroomrepository.getLiveRoom(idWHGX);
}


public void setLiveRoom(String idWHGX,LiveRoom liveRoom){
liveroomrepository.setLiveRoom(idWHGX,liveRoom);
}


}