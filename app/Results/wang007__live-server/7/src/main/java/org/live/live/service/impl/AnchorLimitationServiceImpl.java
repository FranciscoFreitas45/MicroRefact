package org.live.live.service.impl;
 import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.live.entity.AnchorLimitation;
import org.live.live.repository.AnchorLimitationRepository;
import org.live.live.service.AnchorLimitationService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service("anchorLimitationService")
public class AnchorLimitationServiceImpl extends BaseServiceImpl<AnchorLimitation, String>implements AnchorLimitationService{

@Resource
 private  AnchorLimitationRepository repository;


@Override
public List<AnchorLimitation> findLimitationsForLiveRoom(String liveRoomId){
    return repository.findLimitationsForLiveRoom(liveRoomId);
}


@Override
public List<Integer> findLimitations(String userId,String liveRoomId){
    return repository.findAnchorLimitations(userId, liveRoomId);
}


@Override
public BaseRepository<AnchorLimitation,String> getRepository(){
    return this.repository;
}


}