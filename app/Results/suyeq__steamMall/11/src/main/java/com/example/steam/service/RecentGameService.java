package com.example.steam.service;
 import com.example.steam.dao.RecentGameDao;
import com.example.steam.entity.RecentGame;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.RecentGameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import com.example.steam.Interface.RedisService;
@Service
public class RecentGameService {

 private  int RECENT_GAME_SIZE;

@Autowired
 private RecentGameDao recentGameDao;

@Autowired
 private RedisService redisService;


public List<RecentGame> findRecentGameListByEmail(String email){
    return recentGameDao.findRecentGameListByEmail(email);
}


public List<RecentGameVo> findThreeRecentGameVoListByEmail(String email){
    List<RecentGame> recentGameList = recentGameDao.findRecentGameListByEmail(email);
    List<String> gameIdList = new LinkedList<>();
    List<RecentGameVo> recentGameVoList = new LinkedList<>();
    int length = recentGameList.size() > RECENT_GAME_SIZE ? RECENT_GAME_SIZE : recentGameList.size();
    for (int i = 0; i < recentGameList.size(); i++) {
        gameIdList.add(recentGameList.get(i).getGameId() + "");
    }
    List<GameDetail> gameDetailList = redisService.getPipelineBatch(GameKey.GAME_ID, gameIdList, GameDetail.class);
    for (int i = 0; i < recentGameList.size(); i++) {
        RecentGameVo recentGameVo = new RecentGameVo();
        recentGameVo.setId(recentGameList.get(i).getId());
        recentGameVo.setEmail(recentGameList.get(i).getEmail());
        recentGameVo.setGameId(recentGameList.get(i).getGameId());
        recentGameVo.setLastPlay(recentGameList.get(i).getLastPlay());
        recentGameVo.setPlayTime(recentGameList.get(i).getPlayTime());
        recentGameVo.setGameName(gameDetailList.get(i).getGameName());
        recentGameVo.setPosterImage(gameDetailList.get(i).getPosterImage());
        recentGameVoList.add(recentGameVo);
    }
    return recentGameVoList;
}


}