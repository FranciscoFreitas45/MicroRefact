package org.live.live.schedule;
 import org.live.live.repository.LiveRoomRepository;
import org.live.live.service.LiveRoomService;
import org.live.websocket.chat.ChatHallManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
@Component
public class LiveRoomSchedule {

 private  Logger LOGGER;

@Resource
 private  LiveRoomRepository liveRoomRepository;


@Scheduled(fixedDelay = 5000)
public void flushOnlineCountToLiveRoomTask(){
    try {
        String liveRoomNum;
        int onlineCount;
        Map<String, Integer> listChatroomOnlineCount = ChatHallManager.listChatroomOnlineCount();
        Set<Map.Entry<String, Integer>> entrySet = listChatroomOnlineCount.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            liveRoomNum = entry.getKey();
            onlineCount = entry.getValue().intValue();
            liveRoomRepository.setOnlineCountByLiveRoomNum(liveRoomNum, onlineCount);
        }
    } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
    }
}


}