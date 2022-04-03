package org.live.live.service;
 import org.live.common.base.BaseService;
import org.live.live.entity.AnchorLimitation;
import java.util.List;
public interface AnchorLimitationService extends BaseService<AnchorLimitation, String>{


public List<AnchorLimitation> findLimitationsForLiveRoom(String liveRoomId)
;

public List<Integer> findLimitations(String userId,String liveRoomId)
;

}