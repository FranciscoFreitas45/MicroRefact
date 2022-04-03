package com.example.steam.service;
 import com.example.steam.dao.UserGameDao;
import com.example.steam.entity.RecentGame;
import com.example.steam.entity.UserGame;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.redis.key.UserKey;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.RecentGameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.example.steam.Interface.RedisService;
@Service
public class UserGameService {

 private  int RECENT_GAME_SIZE;

@Autowired
 private UserGameDao userGameDao;

@Autowired
 private RedisService redisService;

@Autowired
 private ApplicationContext applicationContext;


public List<Long> findGamesIdByEmail(String email){
    List<Long> containsGames = redisService.getList(UserKey.CONTAINS_GAMES, UserKey.CONTAINS_KEY + email, Long.class);
    if (containsGames != null) {
        return containsGames;
    }
    containsGames = new LinkedList<>();
    List<UserGame> userGameList = userGameDao.findGamesByEmail(email);
    for (UserGame userGame : userGameList) {
        containsGames.add(userGame.getGameId());
    }
    redisService.set(UserKey.CONTAINS_GAMES, UserKey.CONTAINS_KEY + email, containsGames);
    return containsGames;
}


public List<UserGame> findUserGameListByEmail(String email){
    return userGameDao.findGamesByEmail(email);
}


public int addGameToUser(UserGame userGame){
    List<Long> containsGames = redisService.getList(UserKey.CONTAINS_GAMES, UserKey.CONTAINS_KEY + userGame.getEmail(), Long.class);
    containsGames.add(userGame.getGameId());
    redisService.set(UserKey.CONTAINS_GAMES, UserKey.CONTAINS_KEY + userGame.getEmail(), containsGames);
    return userGameDao.addGameToUser(userGame);
}


public UserGame findOneUserGameByEmailAndGameId(String email,long gameId){
    return userGameDao.findGameByEmailAndGameId(email, gameId);
}


public boolean isContains(String email,long gameId){
    List<Long> gameList = ((UserGameService) applicationContext.getBean("userGameService")).findGamesIdByEmail(email);
    return gameList.contains(gameId);
}


public List<RecentGameVo> findThreeRecentGameVoListByEmail(String email){
    List<UserGame> userGameList = userGameDao.findGamesByEmailOrderByLastPlayDesc(email);
    List<String> gameIdList = new LinkedList<>();
    List<RecentGameVo> recentGameVoList = new LinkedList<>();
    Map<Long, GameDetail> map = new HashMap<>();
    int length = userGameList.size() > RECENT_GAME_SIZE ? RECENT_GAME_SIZE : userGameList.size();
    for (int i = 0; i < userGameList.size(); i++) {
        gameIdList.add(userGameList.get(i).getGameId() + "");
    }
    List<GameDetail> gameDetailList = redisService.getPipelineBatch(GameKey.GAME_ID, gameIdList, GameDetail.class);
    for (GameDetail gameDetail : gameDetailList) {
        map.put(gameDetail.getId(), gameDetail);
    }
    for (int i = 0; i < length; i++) {
        RecentGameVo recentGameVo = new RecentGameVo();
        recentGameVo.setId(userGameList.get(i).getId());
        recentGameVo.setEmail(userGameList.get(i).getEmail());
        recentGameVo.setGameId(userGameList.get(i).getGameId());
        recentGameVo.setLastPlay(userGameList.get(i).getLastPlay());
        recentGameVo.setPlayTime(userGameList.get(i).getPlayTime());
        recentGameVo.setGameName(map.get(userGameList.get(i).getGameId()).getGameName());
        recentGameVo.setPosterImage(map.get(userGameList.get(i).getGameId()).getPosterImage());
        recentGameVoList.add(recentGameVo);
    }
    return recentGameVoList;
}


}